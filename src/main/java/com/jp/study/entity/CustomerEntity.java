package com.jp.study.entity;

import lombok.Data;

@Data
public class CustomerEntity {

	/** 顧客CD */
	private String customerCd;
	/** 顧客名 */
	private String coustomerName;
	/** 顧客年齢 */
	private int coustomerAge;
	/** 購入回数 */
	private Integer kounyuKaisu;
	

}
