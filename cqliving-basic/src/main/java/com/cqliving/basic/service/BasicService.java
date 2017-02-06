package com.cqliving.basic.service;

import java.util.List;

import com.cqliving.basic.domain.Option;
import com.cqliving.basic.domain.Region;
import com.cqliving.basic.dto.OptionDto;

/**
 * 
 * 公用基础接口
 * 
 * <p>
 * Description:
 * 
 * 区域现暂：只支持中国 前三级区域
 * 
 * 
 * </p>
 * Copyright (c) CQLIVING 2013
 * 
 * @author lihanpei on 2014-3-26
 */
public interface BasicService {

	/**
	 * 取国内一级区域列表
	 * 
	 * @return
	 */
	public List<Region> getOneLevelRegionList();

	/**
	 * 根据区域编码，取该编码下级区域列表
	 * 
	 * 
	 * @param code
	 * @return
	 */
	public List<Region> getRegionListByCode(String code);

	/**
	 * 根据编码取区域全称
	 * 
	 * 
	 * @param code
	 * @return
	 */
	public String getRegionFullNameByCode(String code);

	/**
	 * 根据编码取地区对象
	 * 
	 * 
	 * @param code
	 * @return
	 */
	public Region getRegionByCode(String code);

	/**
	 * 根据类型，取下拉列表
	 * 
	 * @param type
	 * @return
	 */
	public List<Option> getOptionListByType(String type);

	/**
	 * 根据类型,CODE，取下拉列表名称
	 * 
	 * 
	 * @param type
	 * @return
	 */
	public String getOptionNameByTypeCode(String type, String code);

	//fuxiaofeng
	public List<Region> queryByOtherLevelList();
	//fuxiaofeng 查找大区区域
	public List<OptionDto> findAreaRegion();
}
