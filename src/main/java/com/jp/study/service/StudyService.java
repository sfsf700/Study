package com.jp.study.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jp.study.entity.CustomerEntity;
import com.jp.study.repository.StudyRepository;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudyService {

	private final StudyRepository studyRepository;
	
	public List<CustomerEntity> getCustomers(String customerCd){
		
		if(StringUtils.isNotEmpty(customerCd)) {
			List<CustomerEntity> list = studyRepository.getCustomer(customerCd);
		}
		
		return null;
	}
	
	public void getNew() {
		
	}
}
