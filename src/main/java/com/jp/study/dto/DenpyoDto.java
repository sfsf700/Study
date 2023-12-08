package com.jp.study.dto;

import java.util.Date;
import java.util.List;

import com.jp.study.entity.ShohinEntity;

import lombok.Data;

@Data
public class DenpyoDto {

	/** 伝票番号 */
	private String denpyoNo;
	/** お客様 */
	private String customerCd;
	
	private String customerNameSei;
	
	private String customerNameMei;
	/** 商品情報 */
	private List<ShohinEntity> shohinEntity;
	/** 購入日 */
	private Date kounyuDate;
	/** 顧客CD */
	private String coustomerCd;
	/** 郵便番号 */
	private String yubinNo;
	/** 都道府県・市区町村 */
	private String prefectureCity;
	/** 番地・その他住所 */
	private String streetNo;
	/** 備考 */
	private String biko;

}
