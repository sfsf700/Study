package com.jp.study.entity;

import java.util.Date;

import lombok.Data;

@Data
public class ShohinEntity {
	
	/** 商品コード */
	private String shohinCd;
	/** 商品名称 */
	private String shohinMeisho;
	/** 税抜額 */
	private Long zeinukiGaku;
	/** 税額 */
	private Long zeiGaku;
	/** 税込額 */
	private Long zeikomiGaku;
	/** 削除フラグ */
	private String deleteFlag;
	/** 登録日 */
	private Date createDate;
	/** 更新日 */
	private Date updateDate;
	

}
