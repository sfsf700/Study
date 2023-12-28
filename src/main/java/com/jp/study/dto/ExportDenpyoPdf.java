package com.jp.study.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ExportDenpyoPdf {

	private LocalDate kounyuDate;
	
	private String sei;
	
	private String mei;
	
	private String shohinCd;
	
	private String color;	
	
	private Integer zeikomiGaku;
}
