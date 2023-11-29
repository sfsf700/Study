package com.jp.study.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jp.study.entity.CustomerEntity;

@Mapper
public interface StudyDao {

	
	List<CustomerEntity> selectCustomer(@Param("customerCd") String customerCd);
	
	
	int selectByPrimary(@Param("customerCd") String customerCd);
}
