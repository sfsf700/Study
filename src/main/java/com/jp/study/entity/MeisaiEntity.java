package com.jp.study.entity;

import java.util.Date;

import lombok.Data;

@Data
public class MeisaiEntity {
	/** 伝票番号 */
	private String denpyoNo;
	/** 商品CD*/
	private String shouhinCd;
	/** 商品名 */
	private String shouhinName;
	/** 税抜額 */
	private Integer zeinukiGaku;
	/** 税込額 */
	private Integer zeikomiGaku;
	/** 税額 */
	private Integer zeiGaku;
	/** 購入日 */
	private Date kounyuDate;
	/** 顧客CD */
	private String coustomerCd;
	/** 登録日 */
	private Date createDate;
	/** 登録者 */
	private String createUser;
	

}
