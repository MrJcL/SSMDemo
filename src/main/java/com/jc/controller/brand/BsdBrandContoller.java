package com.jc.controller.brand;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jc.service.brand.BsdBrandService;

@Controller
public class BsdBrandContoller {

	@Resource
	private BsdBrandService bsdBrandService;
	
	@RequestMapping("z")
	public String getBrand(){
		System.out.println(bsdBrandService.get("01a82b72c73b4c7daad114d0b9ab844a").getBrandName());
		return "common/404";
	}
}
