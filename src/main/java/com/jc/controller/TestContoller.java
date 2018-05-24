package com.jc.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jc.service.TestService;

@Controller
@RequestMapping("a")
public class TestContoller {
	@Resource
	private TestService testService;
	
	@RequestMapping("a")
	public String a(){
		return this.testService.get();
	}
	public String a1(){
		return "common/404";
	}
}
