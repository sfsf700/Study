package com.jp.study.dto;


import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DenpyoDto  {

	/** 伝票番号 */
	private String denpyoNo;
	/** お客様CD */
	@NotNull
	private String customerCd;
	/** お客様　姓 */
	private String customerNameSei;
	/** お客様 名 */
	private String customerNameMei;
	/** 商品情報 */
	private List<ShohinDto> shohinDto;
	/** 購入日 */
	private LocalDate kounyuDate;
	/** 郵便番号 */
	private String yubinNo;
	/** 都道府県・市区町村 */
	private String prefectureCity;
	/** 番地・その他住所 */
	private String streetNo;
	/** 備考 */
	private String biko;

}
