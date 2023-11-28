package com.jp.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class StudyController {
	
	@RequestMapping(value = "/Home" , method = {RequestMethod.GET})
	public String show() {
		return null;
	}
}
