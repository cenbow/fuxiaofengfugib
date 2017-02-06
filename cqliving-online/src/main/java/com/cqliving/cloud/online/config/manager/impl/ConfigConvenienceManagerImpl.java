package com.cqliving.cloud.online.config.manager.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.config.dao.ConfigConvenienceDao;
import com.cqliving.cloud.online.config.domain.ConfigConvenience;
import com.cqliving.cloud.online.config.dto.ConfigConvenienceDto;
import com.cqliving.cloud.online.config.manager.ConfigConvenienceManager;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.ConvenienceData;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Lists;

@Service("configConvenienceManager")
public class ConfigConvenienceManagerImpl extends EntityServiceImpl<ConfigConvenience, ConfigConvenienceDao> implements ConfigConvenienceManager {
	
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(ConfigConvenience.STATUS99, idList);
	}
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int updateStatus(Byte status,Long... ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(ConfigConvenience.STATUS99, idList);
	}

	@Override
	@Transactional(value="transactionManager")
	public int updateSort(Long id, Integer sortNo) {
		return this.getEntityDao().updateSort(id, sortNo);
	}

	@Override
	public PageInfo<ConfigConvenienceDto> queryPage(PageInfo<ConfigConvenienceDto> pageInfo, Map<String, Object> map, Map<String, Boolean> orderMap) {
		return this.getEntityDao().queryPage(pageInfo, map, orderMap);
	}

	@Override
	public CommonListResult<ConvenienceData> getByApp(Long appId) {
		//查询数据
		List<ConfigConvenienceDto> list = getEntityDao().getByApp(appId);
		
		//返回数据
		CommonListResult<ConvenienceData> result = new CommonListResult<ConvenienceData>();
		List<ConvenienceData> dataList = Lists.newArrayList();
		ConvenienceData data;
		if (CollectionUtils.isNotEmpty(list)) {
			for (ConfigConvenienceDto dto : list) {
				data = new ConvenienceData();
				data.setIconMaxUrl(dto.getIconMaxUrl());
				data.setIconMinUrl(dto.getIconMinUrl());
				data.setLinkUrl(dto.getLinkUrl());
				data.setName(dto.getName());
				//分类名称（名称,图标地址）
				String typeName = StringUtils.defaultString(dto.getConvenienceTypeName()) + (StringUtils.isNotBlank(dto.getTypeImgUrl()) ? "," + dto.getTypeImgUrl() : "");
				data.setTypeName(typeName);
				data.setTypeSortNo(dto.getTypeSortNo());
				dataList.add(data);
			}
			result.setDataList(dataList);
		}
		return result;
	}
	
}