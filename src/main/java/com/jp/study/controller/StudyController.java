package com.jp.study.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jp.study.dto.DenpyoDto;
import com.jp.study.dto.ShohinDto;
import com.jp.study.service.StudyService;

import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
public class StudyController {
	
	private final StudyService studyService;
	
	
	@RequestMapping(value = "/home" , method = {RequestMethod.GET})
	public String show() {
		return "study/Study";
	}
	
	
	@RequestMapping(value = "/New", method = {RequestMethod.GET})
	public String newDenpyo(Model model) {
		
		List<ShohinDto> shohinDtoList = studyService.getShohinList();
	
		DenpyoDto denpyoDto = new DenpyoDto();
		denpyoDto.setShohinEntity(new ArrayList<>());
		model.addAttribute("shohinDtoList", shohinDtoList);
		model.addAttribute("denpyoDto", denpyoDto);
		
		return "/study/New";
	}
}
