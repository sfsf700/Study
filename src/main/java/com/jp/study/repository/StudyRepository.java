package com.jp.study.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jp.study.dao.DenpyoDao;
import com.jp.study.dao.StudyDao;
import com.jp.study.entity.ShohinEntity;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
@Transactional
public class StudyRepository {
	
	private final StudyDao studyDao;
	private final DenpyoDao denpyoDao;
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

	public int registryDenpyo(int zeinukiGaku, int zeiGaku, int zeikomiGaku, String customerCd, String denpyoNo, LocalDate kounyuDate) {
		try {
			 return denpyoDao.insertDenpyo(zeinukiGaku, zeiGaku, zeikomiGaku, customerCd, denpyoNo, kounyuDate);
		} catch(Exception e) {
			e.printStackTrace();
			return 1;
		}
	}

}
