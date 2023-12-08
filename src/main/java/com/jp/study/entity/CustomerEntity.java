package com.jp.study.entity;

import java.util.Date;

import lombok.Data;

@Data
public class CustomerEntity {

	/** 顧客CD */
	private String customerCd;
	/** 姓 */
	private String sei;
	/** 名 */
	private String mei;
	/** 顧客年齢 */
	private int coustomerAge;
	/** 購入回数 */
	private int kounyuKaisu;
	/** 登録日 */
	private Date creaDate;
	/** 更新日 */
	private Date updateDate;
	/** 累計購入金額 */
	private Long ruikeiKonyuKingaku;

}
