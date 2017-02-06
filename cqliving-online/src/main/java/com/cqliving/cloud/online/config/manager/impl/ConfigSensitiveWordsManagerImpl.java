package com.cqliving.cloud.online.config.manager.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.config.dao.ConfigSensitiveWordsDao;
import com.cqliving.cloud.online.config.domain.ConfigSensitiveWords;
import com.cqliving.cloud.online.config.dto.ConfigSensitiveWordsDto;
import com.cqliving.cloud.online.config.manager.ConfigSensitiveWordsManager;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("configSensitiveWordsManager")
public class ConfigSensitiveWordsManagerImpl extends EntityServiceImpl<ConfigSensitiveWords, ConfigSensitiveWordsDao> implements ConfigSensitiveWordsManager {
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
	    return this.getEntityDao().updateStatus(ConfigSensitiveWords.STATUS99, idList);
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
		return this.getEntityDao().updateStatus(ConfigSensitiveWords.STATUS99, idList);
	}
	
	/**
	 * 通过id查询信息
	 */
    @Override
    public ConfigSensitiveWordsDto getById(Long id) {
        return this.getEntityDao().getById(id);
    }
    
    /**
     * Title:分页查询
     * @author huxiaoping 2016年7月6日下午4:18:44
     * @param map
     * @param sortMap
     * @return
     */
    @Override
    public PageInfo<ConfigSensitiveWordsDto> queryPage(PageInfo<ConfigSensitiveWordsDto> pageInfo,Map<String, Object> map, Map<String, Boolean> orderMap){
        return this.getEntityDao().queryPage(pageInfo, map, orderMap);
    }
}
