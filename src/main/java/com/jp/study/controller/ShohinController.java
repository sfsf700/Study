package com.jp.study.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jp.study.dto.ShohinDto;
import com.jp.study.service.StudyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ShohinController {
	
	private final StudyService studyService;
	
	@RequestMapping(value = "/getShohinInfo", method = {RequestMethod.GET})
	public ResponseEntity<ShohinDto> getShohinInfo(@RequestParam String shohinCd){
		
		if(StringUtils.isEmpty(shohinCd)) {
			return ResponseEntity.ok(null);
		}
		
		ShohinDto shohinDto = studyService.getShohinKingaku(shohinCd);
		
		
		return new ResponseEntity<ShohinDto>(shohinDto, HttpStatus.OK);
		
	}
	
	
	
 
}
