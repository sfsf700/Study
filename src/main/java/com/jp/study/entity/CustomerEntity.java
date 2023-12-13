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
	/** 郵便番号 */
	private String yubinNo;
	/** 都道府県・市区町村 */
	private String prefectureCity;
	/** 番地・その他住所 */
	private String streetNo;
	/** 購入回数 */
	private int kounyuKaisu;
	/** 登録日 */
	private Date creaDate;
	/** 更新日 */
	private Date updateDate;
	/** 累計購入金額 */
	private Long ruikeiKonyuKingaku;

}
