package com.jp.study.dao;

import java.time.LocalDate;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DenpyoDao {
	
	/**
	 * 伝票枚数の取得
	 * @param kounyuDate
	 * @return 購入日ごとの伝票枚数
	 */
	int countDenpyoMaisu(@Param("kounyuDate") LocalDate kounyuDate);

	/**
	 * 伝票の登録
	 * @param zeinukiGaku
	 * @param zeiGaku
	 * @param zeikomiGaku
	 * @param customerCd
	 * @param denpyoNo
	 * @return
	 */
	int insertDenpyo(@Param("zeinukiGaku") int zeinukiGaku, @Param("zeiGaku") int zeiGaku, @Param("zeikomiGaku") int zeikomiGaku, 
			@Param("customerCd") String customerCd, @Param("denpyoNo") String denpyoNo, @Param("kounyuDate") LocalDate kounyuDate);

}
