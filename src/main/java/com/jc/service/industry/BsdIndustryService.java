package com.jc.service.industry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jc.dao.industry.BsdIndustryDao;
import com.jc.model.industry.BsdIndustry;

@Service
public class BsdIndustryService {

	@Resource
	private BsdIndustryDao bsdIndustryDao;
	
	public void get(){
		BsdIndustry bsdIndustry = new BsdIndustry();
		bsdIndustry.setIndustryId("a1c1db02-8f92-11e7-b5ca-7cd30ac44fe6");
		bsdIndustry = bsdIndustryDao.selectOne(bsdIndustry);
		System.out.println(bsdIndustry.getIndustryName());
	}
	public void get1(){
		BsdIndustry bsdIndustry = new BsdIndustry();
		bsdIndustry = bsdIndustryDao.getIndustry("a1c1db02-8f92-11e7-b5ca-7cd30ac44fe6");
		System.out.println(bsdIndustry.getIndustryName());
	}
}
