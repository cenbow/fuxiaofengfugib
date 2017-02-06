package com.cqliving.cloud.online.config.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.config.domain.ConfigRegion;
import com.cqliving.cloud.online.config.dto.ConfigRegionDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;


/**
 * 区域表 Service
 * Date: 2016-05-16 17:31:38
 * @author Code Generator
 */
public interface ConfigRegionService {
    
    /**
     * Title:
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月16日
     * @param pcode
     * @param query
     * @return
     */
	Response<List<ConfigRegion>> getByPcode(String pcode, String query);

	/**
	 * <p>Description: 获取 App 的区域</p>
	 * @author Tangtao on 2016年5月17日
	 * @param appId
	 * @param type 区域类型{2: 问政, 3: 商情, 10: 旅游}
	 * @return
	 * @see ConfigRegion#TYPE2 问政
	 * @see ConfigRegion#TYPE3 商情
	 * @see ConfigRegion#TYPE10 旅游
	 */
	Response<List<ConfigRegion>> getByAppAndType(Long[] appId,Byte type);
	
	/**
	 * <p>Description: 获取 App 的区域</p>
	 * @author Tangtao on 2016年5月17日
	 * @param appId
	 * @param type 区域类型{2: 问政, 3: 商情, 10: 旅游}
	 * @param query 查询条件
	 * @return
	 * @see ConfigRegion#TYPE2 问政
	 * @see ConfigRegion#TYPE3 商情
	 * @see ConfigRegion#TYPE10 旅游
	 */
	Response<List<ConfigRegion>> getByAppAndType(Long appId, Byte type, String query);
	
	/**
	 * <p>Description: 获取 App 的商情区域</p>
	 * @author Tangtao on 2016年5月28日
	 * @param appId
	 * @param shopTypeId 商情分类 id
	 * @return
	 */
	Response<List<ConfigRegion>> getShopByAppAndType(Long appId, Long shopTypeId);
	
	/** @author Code Generator *****start*****/
	public Response<PageInfo<ConfigRegion>> queryForPage(PageInfo<ConfigRegion> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ConfigRegion> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(ConfigRegion domain);
	
	/**
     * 修改排序号
     * @author huxiaoping
     * @param sortNo
     * @param ids
     * @return
     */
    public Response<Void> updateSortNo(Integer sortNo,Long... ids);
    /**
     * 分页商情区域信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年7月7日
     */
    public Response<PageInfo<ConfigRegionDto>> queryByPage(PageInfo<ConfigRegionDto> pageInfo,Map<String, Object> conditions,Map<String, Boolean> orders);
	//根据类型查找区域
    public Response<List<ConfigRegion>> findByType(Byte type);
}
