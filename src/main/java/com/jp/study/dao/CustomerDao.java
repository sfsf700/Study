package com.jp.study.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jp.study.entity.CustomerEntity;

@Mapper
public interface CustomerDao {

	int findByCustomer(@Param("sei") String sei, @Param("mei") String mei);

	int insertCustomer(CustomerEntity customerEntity);

}
