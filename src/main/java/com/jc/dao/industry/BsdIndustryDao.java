package com.jc.dao.industry;

import com.jc.model.industry.BsdIndustry;

import tk.mybatis.mapper.common.Mapper;

public interface BsdIndustryDao extends Mapper<BsdIndustry>{

	public BsdIndustry getIndustry(String Id);
}
