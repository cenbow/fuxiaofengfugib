package com.cqliving.cloud.online.config.manager.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.config.dao.ConfigHotlineDao;
import com.cqliving.cloud.online.config.domain.ConfigHotline;
import com.cqliving.cloud.online.config.dto.ConfigHotlineDto;
import com.cqliving.cloud.online.config.manager.ConfigHotlineManager;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("configHotlineManager")
public class ConfigHotlineManagerImpl extends EntityServiceImpl<ConfigHotline, ConfigHotlineDao> implements ConfigHotlineManager {
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(String updator,Long updatorId,Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(updator,updatorId,new Date(),ConfigHotline.STATUS99, idList);
	}
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int updateStatus(String updator,Long updatorId,Byte status,Long... ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(updator,updatorId,new Date(),status, idList);
	}

    @Override
    public PageInfo<ConfigHotlineDto> queryByPage(PageInfo<ConfigHotlineDto> pageInfo, Map<String, Object> conditions,
            Map<String, Boolean> orders) {
        return this.getEntityDao().queryByPage(pageInfo, conditions, orders);
    }

    @Override
    public ConfigHotlineDto getById(Long id) {
        return this.getEntityDao().getById(id);
    }
    
    /**
     * 修改排序号
     * @author huxiaoping
     * @param sortNo
     * @param updator
     * @param updatorId
     * @param ids
     * @return
     */
    @Override
    @Transactional(value="transactionManager")
    public void updateSortNo(Integer sortNo,String updator,Long updateUserId,Long... ids){
        List<Long> idList = Arrays.asList(ids);
        this.getEntityDao().updateSortNo(sortNo, updator, updateUserId, new Date(), idList);
    }
}
