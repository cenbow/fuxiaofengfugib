package com.cqliving.cloud.online.act.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.act.domain.ActCollectInfo;
import com.cqliving.cloud.online.act.dto.ActCollectInfoDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 活动信息收集表 Service
 * Date: 2016-06-07 09:14:12
 * @author Code Generator
 */
public interface ActCollectInfoService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ActCollectInfo>> queryForPage(PageInfo<ActCollectInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ActCollectInfo> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(ActCollectInfo domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * Title:根据appId获取活动收集信息集合
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月8日
	 * @param appId
	 * @return
	 */
	Response<List<ActCollectInfo>> getByApp(Long appId);
	
	/**
     * 分页查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月12日上午11:30:22
     */
    public Response<PageInfo<ActCollectInfoDto>> queryPage(PageInfo<ActCollectInfoDto> pageInfo,Map<String, Object> map, Map<String, Boolean> orderMap);
    
    /**
     * 通过id查询单个信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月12日上午11:30:22
     */
    public Response<ActCollectInfoDto> findById(Long id);
    
    /**
     * 保存
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月12日上午11:30:22
     */
    public Response<Void> save(ActCollectInfo domain,String[] value);
    
    /**
     * 通过id删除
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月13日上午10:19:18
     */
    public Response<Void> delById(Long... id);
}
