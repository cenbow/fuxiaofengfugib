package com.cqliving.cloud.online.config.manager;

import java.util.Map;

import com.cqliving.cloud.online.config.domain.ConfigHotline;
import com.cqliving.cloud.online.config.dto.ConfigHotlineDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityService;

/**
 * config_热线表 Manager
 * Date: 2016-07-07 11:54:13
 * @author Code Generator
 */
public interface ConfigHotlineManager extends EntityService<ConfigHotline> {
	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	public int deleteLogic(String updator,Long updatorId,Long[] ids);
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids ID
	 * @return
	 */
	public int updateStatus(String updator,Long updatorId,Byte status,Long... ids);
	
	/**
     * 分页查询热线
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年7月7日
     */
    public PageInfo<ConfigHotlineDto> queryByPage(PageInfo<ConfigHotlineDto> pageInfo,Map<String, Object> conditions,Map<String, Boolean> orders);
    
    /**
     * 查询详情
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年7月7日
     */
    public ConfigHotlineDto getById(Long id);
    
    /**
     * 修改排序号
     * @author huxiaoping
     * @param sortNo
     * @param updator
     * @param updatorId
     * @param ids
     * @return
     */
    public void updateSortNo(Integer sortNo,String updator,Long updateUserId,Long... ids);
}
