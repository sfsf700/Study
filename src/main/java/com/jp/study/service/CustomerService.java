package com.jp.study.service;

import org.springframework.stereotype.Service;

import com.jp.study.dto.DenpyoDto;
import com.jp.study.entity.CustomerEntity;
import com.jp.study.repository.CustomerRepositry;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
	
	private final CustomerRepositry customerRepositry;
	
	private final StudyService studyService;
	
	public int findByCustomer(String sei, String mei) {
		
		return customerRepositry.findByCustomer(sei, mei);

	}
	
	
	public void insertNewCustomer(DenpyoDto denpyoDto) {
		
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setCustomerCd(denpyoDto.getCustomerCd());
		customerEntity.setSei(denpyoDto.getCustomerNameSei());
		customerEntity.setMei(denpyoDto.getCustomerNameMei());
		customerEntity.setYubinNo(denpyoDto.getYubinNo());
		customerEntity.setPrefectureCity(denpyoDto.getPrefectureCity());
		customerEntity.setStreetNo(denpyoDto.getStreetNo());
		customerEntity.setRuikeiKonyuKingaku(studyService.sumPrice(denpyoDto.getShohinDto()));
		
		customerRepositry.insertCustomer(customerEntity);
	}

}
