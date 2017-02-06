package com.cqliving.cloud.online.config.manager;

import java.util.Map;

import com.cqliving.cloud.online.config.domain.ConfigSensitiveWords;
import com.cqliving.cloud.online.config.dto.ConfigSensitiveWordsDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityService;

/**
 * 敏感词表 Manager
 * Date: 2016-05-07 10:02:24
 * @author Code Generator
 */
public interface ConfigSensitiveWordsManager extends EntityService<ConfigSensitiveWords> {
	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	public int deleteLogic(Long[] id);
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids ID
	 * @return
	 */
	public int updateStatus(Byte status,Long... ids);
	
	/**
	 * 通过id查询
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2016年7月6日下午4:12:54
	 */
	public ConfigSensitiveWordsDto getById(Long id);
    
    /**
     * Title:分页查询
     * @author huxiaoping 2016年7月6日下午4:18:44
     * @param map
     * @param sortMap
     * @return
     */
    public PageInfo<ConfigSensitiveWordsDto> queryPage(PageInfo<ConfigSensitiveWordsDto> pageInfo,Map<String, Object> map, Map<String, Boolean> orderMap);
}
