package com.jc.service.brand;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jc.dao.brand.BsdBrandDao;
import com.jc.model.brand.BsdBrand;

@Service
public class BsdBrandService {
	@Resource
	private BsdBrandDao bsdBrandDao;
	public BsdBrand get(String Id){
		return bsdBrandDao.getBrand(Id);
	}
}