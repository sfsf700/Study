package com.jp.study.service;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.jp.study.dto.DenpyoDto;
import com.jp.study.dto.ExportDenpyoPdf;
import com.jp.study.dto.PrintPDF;
import com.jp.study.dto.SearchItemDto;
import com.jp.study.dto.ShohinDto;
import com.jp.study.entity.DenpyoEntity;
import com.jp.study.entity.SalesEntity;
import com.jp.study.entity.ShohinEntity;
import com.jp.study.repository.StudyRepository;
import com.jp.study.response.ResponseData;
import com.jp.study.util.Constants;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
@RequiredArgsConstructor
public class StudyService {

	private final StudyRepository studyRepository;

	public List<ShohinEntity> getShohinList() {
		List<ShohinEntity> shohinList = studyRepository.selectAllShohinList();
		
		if(shohinList.size() == 0) {
			return null;
		}
		return shohinList;
	}
	
	public ShohinDto getShohinKingaku(String shohinCd) {
		
		ShohinDto dto = studyRepository.selectByShohinKingaku(shohinCd);
		
		if(ObjectUtils.isNotEmpty(dto)) {
			return dto;
		}
		return null;
	}
	
	public ShohinEntity getShohinInfo(String shohinCd) {
		return studyRepository.findByShohin(shohinCd);
	}
	
	public void saveDenpyo(DenpyoDto denpyoDto) {
		
		/** 購入日の伝票枚数を取得し、伝票番号に変換 */
		int denpyoMaisu = studyRepository.countDenpyoMaisu(denpyoDto.getKounyuDate());
		String denpyoDate = denpyoDto.getKounyuDate().format(DateTimeFormatter.ofPattern("yyMMdd"));
		String denpyoNo = denpyoDate + String.format("%04d", denpyoMaisu);
		
		saveMeisai(denpyoDto, denpyoNo);
		
		int zeinukiGaku = 0;
		int zeiGaku = 0;
		int zeikomiGaku = 0;

		for(ShohinDto shohinDto : denpyoDto.getShohinDto()) {
			zeinukiGaku += shohinDto.getZeinukiGaku();
			zeiGaku += shohinDto.getZeiGaku();
			zeikomiGaku += shohinDto.getZeikomiGaku();
		}
		studyRepository.registryDenpyo(zeinukiGaku, zeiGaku, zeikomiGaku, denpyoDto.getCustomerCd(), denpyoNo, denpyoDto.getKounyuDate(), denpyoDto.getBiko());
		
	}
	
	private void saveMeisai(DenpyoDto denpyoDto, String denpyoNo) {
		
		List<SalesEntity> salesList = new ArrayList<>();
		for(ShohinDto shohin : denpyoDto.getShohinDto()) {
			SalesEntity entity = new SalesEntity();
			entity.setDenpyoNo(denpyoNo);
			entity.setShohinCd(shohin.getShohinCd());
			entity.setKounyuDate(denpyoDto.getKounyuDate());
			entity.setCustomerCd(denpyoDto.getCustomerCd());
			salesList.add(entity);
		}
		
		int result = studyRepository.registryMeisai(salesList);
		System.out.print(result);
	}
	
	public List<DenpyoEntity> selectAllDenpyoList() {
		return studyRepository.selectAllDenpyoList();
	}
	
	public Long sumPrice(List<ShohinDto> shohinDto) {
		
		Long total = 0L;
		
		for(ShohinDto shohin : shohinDto) {
			total += shohin.getZeinukiGaku();
		}
		
		return total;
	}
	
	public String registryNewItem(ShohinDto shohinDto) {
		// 商品コードの番号生成
		int lastShohinCd = studyRepository.findByLastShohinCd(jpColorHenkan(shohinDto.getColor()));
		String shohinNo = String.format("%03d", lastShohinCd + 1);
		
		// 税額、税込額計算
		int zeinukiGaku = shohinDto.getZeinukiGaku();
		int zeiGaku = calcZeigaku(zeinukiGaku);
		
		// カラーコードを漢字に変換
		String jpColor = jpColorHenkan(shohinDto.getColor());
		
		ShohinEntity entity = new ShohinEntity();
		entity.setShohinCd(shohinDto.getColor() + shohinNo);
		entity.setColor(jpColor);
		entity.setZeinukiGaku(shohinDto.getZeinukiGaku());
		entity.setZeiGaku(zeiGaku);
		entity.setZeikomiGaku(zeiGaku + zeinukiGaku);
		entity.setMakeTime(StringUtils.isEmpty(shohinDto.getMakeTime()) ? null : shohinDto.getMakeTime());
		entity.setZairyoMemo(StringUtils.isEmpty(shohinDto.getZairyoMemo()) ? null : shohinDto.getZairyoMemo());
		entity.setShohinMemo(StringUtils.isEmpty(shohinDto.getShohinMemo()) ? null : shohinDto.getShohinMemo());
		studyRepository.insertNewItem(entity);
		
		return  shohinDto.getColor() + shohinNo;
		
	}
	
	private int calcZeigaku(int zeinukiGaku) {
		return zeinukiGaku / 10;
	}
	
	private String jpColorHenkan(String color) {
		String jpColor = null;
		
		switch (color) {
		case Constants.PINK:
			jpColor = "桃";
			break;
		case Constants.BLUE:
			jpColor = "青";
			break;
		case Constants.DEEP_BLUES:
			jpColor = "紺";
			break;
		case Constants.RED:
			jpColor = "赤";
			break;
		case Constants.YELLOW:
			jpColor = "黄";
			break;
		case Constants.GREEN:
			jpColor = "緑";
			break;
		case Constants.VIOLET:
			jpColor = "紫";
			break;
		case Constants.BLACK:
			jpColor = "黒";
			break;
		case Constants.WHITE:
			jpColor = "白";
			break;
		case Constants.ORANGE:
			jpColor = "橙";
			break;
		case Constants.BROWN:
			jpColor = "茶";
			break;
		case Constants.GOLD:
			jpColor = "金";
			break;
		case Constants.SILVER:
			jpColor = "銀";
			break;
		case Constants.VARIOUS:
			jpColor = "その他";
			break;
		}
		return jpColor;
	}
	
	public int deleteItem(String shohinCd) {
		return studyRepository.deleteItem(shohinCd);
	}
	
	public int updateItem(ShohinEntity shohinEntity) {
		
		int zeinukiGaku = shohinEntity.getZeinukiGaku();
		int zeiGaku = calcZeigaku(zeinukiGaku);
		shohinEntity.setZeiGaku(zeiGaku);
		shohinEntity.setZeikomiGaku(zeiGaku + zeinukiGaku);
		return studyRepository.updateItem(shohinEntity);
	}
	
	public ResponseData<SearchItemDto> searchItemDataList(SearchItemDto searchItemDto) {
		ResponseData<SearchItemDto> res = new ResponseData<SearchItemDto>();
		
		if(StringUtils.equals(searchItemDto.getColor(), "0")) {
			searchItemDto.setColor(null);
		}

		if(StringUtils.equals(searchItemDto.getShohinCd(), "0")) {
			searchItemDto.setShohinCd(null);
		}
		
		res.setData(searchItemDto);
		return res;
	}

	public List<ShohinEntity> getSearchItemList(SearchItemDto searchItemDto) {
		return studyRepository.searchItem(searchItemDto);
	}
	
	
	public List<ExportDenpyoPdf> exportDenpyoPdfs(String denpyoNo) throws JRException, IOException{
	
		
		String jasperFilePath =  getClass().getResource("/jasper-template.jasper").getPath();
		
		List<ExportDenpyoPdf> pdfsList = studyRepository.exportDenpyo(denpyoNo);
		String customerName = pdfsList.get(0).getSei() + pdfsList.get(0).getMei();
		
		Integer goukeiKingaku = 0;
		List<PrintPDF> printList = new ArrayList<>();
		for(int i = 0; i < pdfsList.size(); i++) {
			PrintPDF pdf = new PrintPDF();
			goukeiKingaku += pdfsList.get(i).getZeikomiGaku();
			pdf.setNumber(i+1);
			pdf.setColor(pdfsList.get(i).getColor());
			pdf.setShohinCd(pdfsList.get(i).getShohinCd());
			pdf.setZeikomiGaku(pdfsList.get(i).getZeikomiGaku());
			printList.add(pdf);
			
		}
		
		String exportBasePath = "/Users/okuhirokazu/" + denpyoNo + "/";
		String fileName = denpyoNo + ".pdf";
		File directory = new File(exportBasePath);
		if (!directory.exists()) {
		    directory.mkdirs();
		}
		
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(printList);
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("customerName", customerName);
		parameterMap.put("goukeiKingaku", goukeiKingaku.toString());
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFilePath, parameterMap, datasource);
		JasperExportManager.exportReportToPdfFile(jasperPrint, exportBasePath + fileName);
		
	
		return null;
		
	}
	
	
}
