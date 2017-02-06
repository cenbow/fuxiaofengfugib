package com.cqliving.basic.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.druid.util.StringUtils;
import com.cqliving.basic.constant.RedisCachedKey;
import com.cqliving.basic.dao.OptionDao;
import com.cqliving.basic.dao.PropertiesDaoCustom;
import com.cqliving.basic.dao.RegionDao;
import com.cqliving.basic.domain.Option;
import com.cqliving.basic.domain.Region;
import com.cqliving.basic.dto.OptionDto;
import com.cqliving.basic.dto.RegionDto;
import com.cqliving.basic.facade.PropertiesConfig;
import com.cqliving.basic.service.BasicService;
import com.cqliving.redis.base.AbstractRedisClient;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Title:
 * @author yuwu on 2016年6月27日
 */
public class BasicServiceImpl implements BasicService {
	
	private static final Logger logger = LoggerFactory.getLogger(BasicServiceImpl.class);
	
	@Value("${property_config}")
	private String propertyConfig;
	
	@Value("${property_expire:60}")
	private Integer propertyExpire;
	
	@Value("${load.basic.data:true}")
	private String loadBasicData;
	
	@Autowired
	private OptionDao optionDao;
	@Autowired
	private RegionDao regionDao;

	@Autowired
	private PropertiesDaoCustom propertiesDaoCustom;
	@Autowired
	private AbstractRedisClient abstractRedisClient;
	

	// 下拉选项列表Map
	private static Map<String, List<Option>> optionMap = null;
	
	// 一级区域列表
	private static List<Region> oneLevelRegionList = null;
	//其它区域二级以上
	private static List<Region> otherLevelList = null;
	// 区域Map
	private static Map<String, List<Region>> regionMap = null;
	// 根据编码取编码数据
	private static Map<String, Region> regionCodeNameMap = null;
	
	
	public void init() {
		try {
			logger.info("BasicServiceImpl--->启动加载公用字典数据");
			//加载配置文件表
			loadProperties();
			// 加载选项列表数据
			loadOptionData();
			// 加载区域数据
			loadRegionData();
			//加载学校数据
			//loadUnivistiesData();
			//getUniversityData();
		} catch (Exception e) {
			logger.error("BasicServiceImpl--->启动加载数据--->异常");
			e.printStackTrace();
		}
	}
	
	
	public Map<String, String> loadProperties(){
		//String propertyConfig = ApplicationProperties.getProperty("property_config");
		List<Map<String, Object>> configs = propertiesDaoCustom.loadPropertiesWith(propertyConfig);
		Map<String, String> map = Maps.newHashMap();
		for(Map<String, Object> c : configs){
			map.put(c.get("k").toString(), c.get("v")!=null?c.get("v").toString().trim():"");
		}
		abstractRedisClient.set(RedisCachedKey.PROPERTIES_KEY,map,propertyExpire);
		PropertiesConfig.setBasicServiceImpl(this);
		PropertiesConfig.setFutureRedisClient(abstractRedisClient);
		return map;
	}
	
	/**
	 * 加载选项列表
	 * 
	 * <p>Description:</p>
	 */
	private void loadOptionData(){
		optionMap = new HashMap<String, List<Option>>();
		
		List<Option> optionList = optionDao.getAll();
		if (null!=optionList) {
			for (Option Option : optionList) {
				
				String typeCode = Option.getTypeCode();
				
				if (optionMap.containsKey(typeCode)) {
					List<Option> pList = optionMap.get(typeCode);
					pList.add(Option);
					optionMap.put(typeCode, pList);
				} else {
					List<Option> nlist = new ArrayList<Option>();
					nlist.add(Option);
					optionMap.put(typeCode, nlist);
				}
				
			}
		}
	}
	
	
	/**
	 * 加载区域数据
	 * 
	 * <p>Description:</p>
	 */
	private void loadRegionData(){
		
		regionMap = new HashMap<String, List<Region>>();
		regionCodeNameMap = new HashMap<String, Region>();
		// 取国内一级地区
		oneLevelRegionList = regionDao.queryByOneLevelList();
		if (null!=oneLevelRegionList) {
			for(Region re : oneLevelRegionList){
				regionCodeNameMap.put(re.getCode()+"", re);
			}
		}
		
		// 取国内除1级外的区域列表
		List<Region> list = regionDao.queryByOtherLevelList();
		otherLevelList = list;
		if (null!=list) {
			for(Region re : list){
				regionCodeNameMap.put(re.getCode()+"", re);
				
				String pcode = re.getPcode();
				
				if (regionMap.containsKey(pcode)) {
					List<Region> pList = regionMap.get(pcode);
					pList.add(re);
					regionMap.put(pcode, pList);
				} else {
					List<Region> nlist = new ArrayList<Region>();
					nlist.add(re);
					regionMap.put(pcode, nlist);
				}
			}
		}
	}
	/**
	 * 取国内一级区域列表
	 * 
	 * <p>Description:</p>
	 * @return
	 */
	public List<Region> getOneLevelRegionList(){
		return oneLevelRegionList;
	}
	
	/**
	 * 根据区域编码，取该编码下级区域列表
	 * 
	 * <p>Description:</p>
	 * @param code
	 * @return
	 */
	public List<Region> getRegionListByCode(String code){
		return regionMap.get(code);
	}
	
	/**
	 * 根据编码取区域全称
	 * 
	 * <p>Description:</p>
	 * @param code
	 * @return
	 */
	public String getRegionFullNameByCode(String code){
		Region re = regionCodeNameMap.get(code);
		String fullName = "";
		if(null!=re){
			fullName = re.getFullname();
		}
		return fullName;
	}
	
	/**
	 * 根据编码取地区对象
	 * 
	 * <p>Description:</p>
	 * @param code
	 * @return
	 */
	public Region getRegionByCode(String code){
		return regionCodeNameMap.get(code);
	}
	
	/**
	 * 根据类型，取下拉列表
	 * 
	 * <p>
	 * 根据类型，取下拉列表，并按sort_key从小到大排序
	 * </p>
	 * 
	 * @param type
	 * @return
	 */
	public List<Option> getOptionListByType(String type){
		return optionMap.get(type);
	}
	
	
	/**
	 * 根据类型,CODE，取下拉列表名称
	 * 
	 * <p>
	 * 根据类型,CODE，取下拉列表名称
	 * </p>
	 * 
	 * @param type
	 * @return
	 */
	public String getOptionNameByTypeCode(String type, String code) {
		String optionName="";
		if(!StringUtils.isEmpty(type) && !StringUtils.isEmpty(code)){
			List<Option> optionList = getOptionListByType(type);
			if(optionList!=null){
				for(Option opt:optionList){
					if(opt.getCode().equals(code)){
						optionName = opt.getName();
						break;
					}
				}
			}
		}
		return optionName;
	}

	@Override
	public List<Region> queryByOtherLevelList() {
		
		return otherLevelList;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.basic.service.BasicService#findAreaRegion()
	 */
	@Override
	public List<OptionDto> findAreaRegion() {
		
		List<Option> areaOption = this.getOptionListByType(Option.TYPECODE20);
		List<Region> regions = this.getOneLevelRegionList();
		List<RegionDto> regionDtos = this.covert(regions);
		this.iteratorRegion(regionDtos);
		return this.covertDto(regionDtos, areaOption);
	}
	
	
	private void iteratorRegion(List<RegionDto> regionDtos){
		
		if(CollectionUtils.isEmpty(regionDtos))return;
		Iterator<RegionDto> regionIt = regionDtos.iterator();
		while(regionIt.hasNext()){
			RegionDto region = regionIt.next();
			List<Region> subRegions = this.getRegionListByCode(String.valueOf(region.getCode()));
			List<RegionDto> subDtos = this.covert(subRegions);
			region.setSubRegion(subDtos);
			
			if(CollectionUtils.isNotEmpty(subDtos)){
				iteratorRegion(subDtos);
			}
		}
	}
	
	private List<RegionDto> covert(List<Region> regions){
		if(CollectionUtils.isEmpty(regions)){
			return null;
		}
		List<RegionDto> subDtos  = Lists.newArrayList();
		for(Region r : regions){
			RegionDto subDto = new RegionDto();
			BeanUtils.copyProperties(r, subDto);
			subDtos.add(subDto);
		}
		return subDtos;
	}
	
	private List<OptionDto> covertDto(List<RegionDto> regionDtos,List<Option> areaOption){
		
		if(CollectionUtils.isEmpty(areaOption) || CollectionUtils.isEmpty(regionDtos)){
			return null;
		}
		List<OptionDto> dtos = Lists.newArrayList();
		for(Option option:areaOption){
			OptionDto dto = new OptionDto();
			BeanUtils.copyProperties(option, dto);
			List<RegionDto> regionDto = Lists.newArrayList();
			for(RegionDto rdto : regionDtos){
				if(dto.getCode().equals(rdto.getOptionCode())){
					regionDto.add(rdto);
				}
			}
			dto.setRegion(regionDto);
			dtos.add(dto);
		}
		return dtos;
	}
}
