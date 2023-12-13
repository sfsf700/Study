package com.jp.study.dto;

import lombok.Data;


@Data
public class ShohinDto {
	
	/** 商品CD */
	private String shohinCd;
	/** 商品名称 */
	private String shohinMeisho;
	/** 税抜額 */
	private int zeinukiGaku;
	/** 税額 */
	private int zeiGaku;
	/** 税込額 */
	private int zeikomiGaku;

}
