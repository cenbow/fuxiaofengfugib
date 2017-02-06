package com.cqliving.cloud.online.app.manager;

import java.util.Map;

import com.cqliving.cloud.online.app.domain.AppImageVersion;
import com.cqliving.cloud.online.app.dto.AppImageVersionDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityService;

/**
 * 客户端发布广告表 Manager
 * Date: 2016-05-04 16:01:26
 * @author Code Generator
 */
public interface AppImageVersionManager extends EntityService<AppImageVersion> {
    
    /**
     * 分页查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月4日下午5:13:06
     */
    public PageInfo<AppImageVersionDto> queryPage(PageInfo<AppImageVersionDto> pageInfo, Map<String, Object> map,
            Map<String, Boolean> orderMap);
    
    /**
     * 保存
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月5日上午10:19:32
     */
    public void saveImageVersion(AppImageVersion appImageVersion, String loadingUrl, String linkUrl,Integer showTime);
    
    /**
     * 查询单个记录（包括所有图片）
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月4日下午5:13:06
     */
    public AppImageVersionDto queryById(Long id);

	/**
	 * <p>Description: 获取最新可用版本</p>
	 * @author Tangtao on 2016年7月19日
	 * @param appId
	 * @param type 
	 * @return
	 */
	AppImageVersion getLatest(Long appId, Integer type);
    
	/**
	 * 设置启用
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2016年9月26日下午1:46:47
	 */
	public void startUsing(Long id);
	
	/**
	 * 设置停用
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2016年9月26日下午1:46:47
	 */
	public void nonUse(Long id);
}
