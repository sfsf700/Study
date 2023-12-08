package com.jp.study.util;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CustomeDate {

	private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	
	private LocalDateTime date;
	
	public CustomeDate(Date date) {
		this.date = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}
	
	public CustomeDate(LocalDateTime nowDate) {
		this.date = nowDate;
	}
	
	public CustomeDate nowDate() {
		return new CustomeDate(LocalDateTime.now());
	}
	
	public String getDateyyyyMMdd() {
		if(date == null) {
			return null;
		}
		return dateTimeFormat.format(date);
	}
}
