package com.cqliving.cloud.online.config.service;

import java.util.Map;

import com.cqliving.cloud.online.config.domain.ConfigSensitiveWords;
import com.cqliving.cloud.online.config.dto.ConfigSensitiveWordsDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 敏感词表 Service
 * Date: 2016-05-07 10:02:24
 * @author Code Generator
 */
public interface ConfigSensitiveWordsService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ConfigSensitiveWords>> queryForPage(PageInfo<ConfigSensitiveWords> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ConfigSensitiveWords> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(ConfigSensitiveWords domain);
	/** @author Code Generator *****end*****/
	
	/**
     * 通过id查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年7月6日下午4:12:54
     */
    public Response<ConfigSensitiveWordsDto> getById(Long id);
    
    /**
     * Title:分页查询
     * @author huxiaoping 2016年7月6日下午4:18:44
     * @param map
     * @param sortMap
     * @return
     */
    public Response<PageInfo<ConfigSensitiveWordsDto>> queryPage(PageInfo<ConfigSensitiveWordsDto> pageInfo,Map<String, Object> map, Map<String, Boolean> orderMap);
}