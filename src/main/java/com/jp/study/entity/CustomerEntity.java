package com.jp.study.entity;

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

}
