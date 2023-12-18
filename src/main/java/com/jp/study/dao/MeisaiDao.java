package com.jp.study.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jp.study.entity.SalesEntity;

@Mapper
public interface MeisaiDao {
	
	int bulkInsertMeisai(List<SalesEntity> salesList);

}
