package com.jp.study.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jp.study.dao.StudyDao;
import com.jp.study.entity.CustomerEntity;

@Repository
public class StudyRepository {
	
	@Autowired
	private StudyDao studyDao;
	
	public List<CustomerEntity> getCustomer(String customerCd){
		return studyDao.selectCustomer(customerCd);
	}

}
