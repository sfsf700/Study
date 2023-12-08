package com.jp.study.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jp.study.dao.StudyDao;
import com.jp.study.dto.ShohinDto;
import com.jp.study.entity.CustomerEntity;

import lombok.RequiredArgsConstructor;



@Repository
@RequiredArgsConstructor
public class StudyRepository {
	
	private final StudyDao studyDao;

	
	public List<CustomerEntity> getCustomer(String customerCd){
		return studyDao.selectCustomer(customerCd);
	}

	public List<ShohinDto> selectAllShohinList() {
		return studyDao.findAllShohinList();
	}

}
