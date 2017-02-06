package com.cqliving.cloud.online.info.manager;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.info.domain.InfoLable;
import com.cqliving.cloud.online.info.dto.InfoLableDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityService;

/**
 * 资讯标签表 Manager
 * Date: 2016-05-06 10:52:59
 * @author Code Generator
 */
public interface InfoLableManager extends EntityService<InfoLable> {

	public List<InfoLable> findByAppId(Long appId, Byte sourceType);
	
	public void saveLable(InfoLable infoLable);
	
	/**
     * 查询单条记录
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月6日下午5:13:06
     */
    public InfoLableDto getById(Long id);
    
    /**
     * 分页查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月6日下午5:13:06
     */
    public PageInfo<InfoLableDto> queryInfoLabelDtoPage(PageInfo<InfoLableDto> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
}
