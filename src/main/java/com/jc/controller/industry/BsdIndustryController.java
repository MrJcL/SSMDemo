package com.jc.controller.industry;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jc.service.industry.BsdIndustryService;

@Controller
public class BsdIndustryController {

	@Resource
	private BsdIndustryService bsdIndustryService;
	
	@RequestMapping("q")
	public String get(){
		bsdIndustryService.get();
		return "common/404";
	}
	@RequestMapping("q1")
	public String get1(){
		bsdIndustryService.get1();
		return "common/404";
	}
}
