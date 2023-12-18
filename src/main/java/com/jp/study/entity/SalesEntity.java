package com.jp.study.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class SalesEntity {
	
	private BigDecimal salesId;
	/** 伝票番号 */
	private String denpyoNo;
	/** 商品CD */
	private String shohinCd;
	/** 購入日 */
	private LocalDate kounyuDate;
	/** お客様CD */
	private String customerCd;

	private LocalDate createDate;
}
