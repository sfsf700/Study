package com.jp.study.service;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jp.study.dto.DenpyoDto;
import com.jp.study.dto.ShohinDto;
import com.jp.study.entity.ShohinEntity;
import com.jp.study.repository.StudyRepository;

import lombok.RequiredArgsConstructor;

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
	
	
	public void saveDenpyo(DenpyoDto denpyoDto) {
		
		/** 購入日の伝票枚数を取得し、伝票番号に変換 */
		int denpyoMaisu = studyRepository.countDenpyoMaisu(denpyoDto.getKounyuDate());
		String denpyoDate = denpyoDto.getKounyuDate().format(DateTimeFormatter.ofPattern("yyMMdd"));
		String denpyoNo = denpyoDate + String.format("%04d", denpyoMaisu);
		
		int zeinukiGaku = 0;
		int zeiGaku = 0;
		int zeikomiGaku = 0;
		
		for(ShohinDto shohinDto : denpyoDto.getShohinDto()) {
			zeinukiGaku += shohinDto.getZeinukiGaku();
			zeiGaku += shohinDto.getZeiGaku();
			zeikomiGaku += shohinDto.getZeikomiGaku();
		}
		
		studyRepository.registryDenpyo(zeinukiGaku, zeiGaku, zeikomiGaku, denpyoDto.getCustomerCd(), denpyoNo, denpyoDto.getKounyuDate());
		
		
	}
	
}
