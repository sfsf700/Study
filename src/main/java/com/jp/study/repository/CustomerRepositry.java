package com.jp.study.repository;

import org.springframework.stereotype.Repository;

import com.jp.study.dao.CustomerDao;
import com.jp.study.entity.CustomerEntity;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CustomerRepositry {
	
	private final CustomerDao customerDao;

	public int findByCustomer(String sei, String mei) {
		return customerDao.findByCustomer(sei, mei);
	}

	public void insertCustomer(CustomerEntity customerEntity) {
		customerDao.insertCustomer(customerEntity);
	}
}
