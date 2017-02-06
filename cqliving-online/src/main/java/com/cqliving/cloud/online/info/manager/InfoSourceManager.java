package com.cqliving.cloud.online.info.manager;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.info.domain.InfoSource;
import com.cqliving.cloud.online.info.domain.Information;
import com.cqliving.cloud.online.info.dto.InfoSourceDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityService;

/**
 * 资讯来源表 Manager
 * Date: 2016-04-15 09:44:51
 * @author Code Generator
 */
public interface InfoSourceManager extends EntityService<InfoSource> {

	public List<InfoSource> findByConditions(Map<String,Object> conditions);
	
	public InfoSource saveInfoSource(Information information,String sourceName);
	//修改状态
	public void updateStatus(Byte status, Long... id);
	//修改排序号
	public void updateSortNo(Long id, Integer sortNo);
	
	public PageInfo<InfoSourceDto> queryForPage(PageInfo<InfoSourceDto> pageInfo, Map<String, Object> map);
}
