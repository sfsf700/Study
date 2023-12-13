package com.jp.study.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jp.study.dto.DenpyoDto;
import com.jp.study.entity.ShohinEntity;
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
		
		List<ShohinEntity> shohinList = studyService.getShohinList();
		
		DenpyoDto denpyoDto = new DenpyoDto();
		
		
		model.addAttribute("shohinList", shohinList);
		model.addAttribute("denpyoDto", denpyoDto);
		
		return "/study/New";
	}
	
	@RequestMapping(value = "/Save", method = {RequestMethod.POST})
	public String saveDenpyo(@ModelAttribute DenpyoDto denpyoDto) {
		
		
		studyService.saveDenpyo(denpyoDto);
		
		
		 
		return "study/Study";
	}
}
