package com.cqliving.cloud.online.config.manager.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.config.dao.ConfigRegionDao;
import com.cqliving.cloud.online.config.domain.ConfigRegion;
import com.cqliving.cloud.online.config.dto.ConfigRegionDto;
import com.cqliving.cloud.online.config.manager.ConfigRegionManager;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Maps;

@Service("configRegionManager")
public class ConfigRegionManagerImpl extends EntityServiceImpl<ConfigRegion, ConfigRegionDao> implements ConfigRegionManager {

    @Override
    public List<ConfigRegion> getByPcode(String pcode, String query) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("EQ_pcode", pcode);
        map.put("LIKE_name", query);
        map.put("LIKE_fullName", query);
        map.put("LIKE_phoneticizeAb", query);
        map.put("LIKE_phoneticize", query);
        
        Map<String, Boolean> sortMap = Maps.newHashMap();
        sortMap.put("code", true);
        return getEntityDao().query(map, sortMap);
    }

	@Override
	public List<ConfigRegion> getByAppAndType(Long[] appId, Byte type, String query) {
		Map<String, Object> map = Maps.newHashMap();
		
		if(null != appId){
			map.put("IN_appId",Arrays.asList(appId));
		}
		map.put("EQ_type", type);
		map.put("EQ_status", ConfigRegion.STATUS3);
		if(StringUtils.isNotBlank(query)){
			map.put("LIKE_name", query);
		}
		Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
		sortMap.put("sortNo", true);
		sortMap.put("phoneticize", true);
		sortMap.put("id", false);
		return query(map, sortMap);
	}

	@Override
	public List<ConfigRegion> getShopByAppAndType(Long appId, Long shopTypeId) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_appId", appId);
		map.put("EQ_type", ConfigRegion.TYPE3);
		map.put("EQ_shopTypeId", shopTypeId);
		map.put("EQ_status", ConfigRegion.STATUS3);
		Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
		sortMap.put("sortNo", true);
		sortMap.put("phoneticize", true);
		sortMap.put("id", false);
		return query(map, sortMap);
	}
	
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(ConfigRegion.STATUS99, idList);
	}
	
	/**
	 *修改状态
	 * @param status
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int updateStatus(Byte status,Long... ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(status, idList);
	}

    @Override
    @Transactional(value="transactionManager")
    public void saveInfo(ConfigRegion configRegion) {
        if(null!=configRegion&&null==configRegion.getId()){
            this.save(configRegion);
            configRegion.setCode(configRegion.getId()+"");
            configRegion.setPcode(configRegion.getId()+"");
            this.update(configRegion);
        }else{
            this.update(configRegion);
        }
    }
    
    /**
     * 修改排序号
     * @author huxiaoping
     * @param sortNo
     * @param ids
     * @return
     */
    @Override
    @Transactional(value="transactionManager")
    public void updateSortNo(Integer sortNo,Long... ids){
        List<Long> idList = Arrays.asList(ids);
        this.getEntityDao().updateSortNo(sortNo, idList);
    }
    
    /**
     * 分页商情区域信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年7月7日
     */
    public PageInfo<ConfigRegionDto> queryByPage(PageInfo<ConfigRegionDto> pageInfo,Map<String, Object> conditions,Map<String, Boolean> orders){
        return this.getEntityDao().queryByPage(pageInfo, conditions, orders);
    }

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.config.manager.ConfigRegionManager#findByType(java.lang.Byte)
	 */
	@Override
	public List<ConfigRegion> findByType(Byte type) {
		
		return this.getEntityDao().findByType(type);
	}
	
}
