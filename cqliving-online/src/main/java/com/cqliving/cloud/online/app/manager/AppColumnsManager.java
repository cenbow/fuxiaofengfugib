package com.cqliving.cloud.online.app.manager;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.app.domain.AppColumns;
import com.cqliving.cloud.online.app.dto.AppColumnsDto;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.GetColumnsData;
import com.cqliving.cloud.online.interfacc.dto.InitStartColumns;
import com.cqliving.framework.common.service.EntityService;

/**
 * 客户端栏目表 Manager
 * Date: 2016-04-28 11:59:18
 * @author Code Generator
 */
public interface AppColumnsManager extends EntityService<AppColumns> {
    
    public void saveColumns(AppColumns appColumns);
    public void updateColumns(AppColumns appColumns);
    public void updateStatus(AppColumns appColumns);
    
    public List<AppColumnsDto> getByConditions(Map<String, Object> conditions);

	/**
	 * Title: 获取需要更新父栏目
	 * @author Tangtao on 2016年5月1日
	 * @param appId
	 * @param version
	 * @return
	 */
	List<InitStartColumns> getUpdateParentCols(Long appId, Integer version);

	/**
	 * Title: 获取子栏目
	 * @author Tangtao on 2016年5月1日
	 * @param appId
	 * @param parentCode
	 * @return
	 */
	CommonListResult<GetColumnsData> getChildren(Long appId, String parentCode);
	
	/**
	 * 修改排序
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2016年5月3日下午1:51:13
	 */
	void sort(Long[] ids, Integer[] sortNums, Long[] parentIds);
	
	/**
	 * 查询栏目列表
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2016年5月16日下午1:51:13
	 */
	public List<AppColumnsDto> getList(Map<String, Object> conditions, Map<String, Boolean> orderMap);
	
	/**
	 * 缓存保存
	 * @Description 
	 * @Company 
	 * @parameter appId
	 * @return
	 * @author huxiaoping 2016年5月25日上午11:37:57
	 */
	public List<GetColumnsData> saveCache(Long appId);
	
	/**
	 * 缓存获取
	 * @Description 
	 * @Company 
	 * @parameter appId
	 * @return
	 * @author huxiaoping 2016年5月25日上午11:37:57
	 */
	public List<GetColumnsData> getCache(Long appId);
	
	/**
     * 发布
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月20日
     */
    public void send(Long AppId);
}
