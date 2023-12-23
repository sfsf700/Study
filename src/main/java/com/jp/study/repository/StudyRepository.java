package com.jp.study.repository;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jp.study.dao.DenpyoDao;
import com.jp.study.dao.MeisaiDao;
import com.jp.study.dao.StudyDao;
import com.jp.study.dto.SearchItemDto;
import com.jp.study.dto.ShohinDto;
import com.jp.study.entity.DenpyoEntity;
import com.jp.study.entity.SalesEntity;
import com.jp.study.entity.ShohinEntity;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
@Transactional
public class StudyRepository {
	
	private final StudyDao studyDao;
	private final DenpyoDao denpyoDao;
	private final MeisaiDao meisaiDao;
	
	/**
	 * 商品リスト一覧の取得
	 * @return List<ShohinEntity> 
	 */
	public List<ShohinEntity> selectAllShohinList() {
		return studyDao.findAllShohinList();
	}

	/**
	 * 伝票枚数の取得
	 * @param kounyuDate
	 * @return 購入日ごとの伝票枚数
	 */
	public int countDenpyoMaisu(LocalDate kounyuDate) {
		return denpyoDao.countDenpyoMaisu(kounyuDate);
	}

	public int registryDenpyo(int zeinukiGaku, int zeiGaku, int zeikomiGaku, String customerCd, String denpyoNo, LocalDate kounyuDate, String biko) {
		try {
			 return denpyoDao.insertDenpyo(zeinukiGaku, zeiGaku, zeikomiGaku, customerCd, denpyoNo, kounyuDate, biko);
		} catch(Exception e) {
			e.printStackTrace();
			return 1;
		}
	}
	
	public int registryMeisai(List<SalesEntity> salesList) {
		return meisaiDao.bulkInsertMeisai(salesList);
	}

	/**
	 * 商品情報の取得
	 * @param shohinCd
	 * @return ShohinDto
	 */
	public ShohinDto selectByShohinKingaku(String shohinCd) {
		ShohinEntity entity = studyDao.selectByShohinKingaku(shohinCd);
		ShohinDto dto = new ShohinDto();
		dto.setZeinukiGaku(entity.getZeinukiGaku());
		dto.setZeiGaku(entity.getZeiGaku());
		dto.setZeikomiGaku(entity.getZeikomiGaku());
		return dto;
	}

	/**
	 * 全ての伝票情報
	 * @return　List<DenpyoDto>
	 */
	public List<DenpyoEntity> selectAllDenpyoList(){
		return denpyoDao.selectAllDenpyoLust();
	}
	
	public int findByLastShohinCd(String color) {
		String lastShohinCd = studyDao.selectByLastShohinCd(color);
	
		// テーマカラー初回登録時
		if(StringUtils.isEmpty(lastShohinCd)) {
			return 0;
		} else {
			// 該当のテーマカラーで、2個目以降登録時
			String lastCd = lastShohinCd.substring(2);
			int lastNo = Integer.parseInt(lastCd);
			return lastNo;
		}
		
	}
	
	public void insertNewItem(ShohinEntity shohinEntity) {
		studyDao.insertShohin(shohinEntity);
	}
	
	public ShohinEntity findByShohin(String shohinCd) {
		return studyDao.findByShohin(shohinCd);
	}
	
	public int deleteItem(String shohinCd) {
		return studyDao.delete(shohinCd);
	}
	
	public int updateItem(ShohinEntity shohinEntity) {
		return studyDao.updateByPrimary(shohinEntity);
	}
	
	public List<ShohinEntity> searchItem(SearchItemDto searchItemDto) {
		return studyDao.searchItemList(searchItemDto);
		
	}

//	public void bulkInsert(List<DenpyoEntity> denpyoList) {
//		denpyoDao.bulkInsert(denpyoList);
//	}

}
