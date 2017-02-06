package com.cqliving.cloud.online.config.manager;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.config.domain.ConfigRegion;
import com.cqliving.cloud.online.config.dto.ConfigRegionDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityService;

/**
 * 区域表 Manager
 * Date: 2016-05-16 17:31:38
 * @author Code Generator
 */
public interface ConfigRegionManager extends EntityService<ConfigRegion> {
    
    /**
     * Title:
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月16日
     * @param pcode
     * @return
     */
    List<ConfigRegion> getByPcode(String pcode, String query);

	/**
	 * <p>Description: 获取 App 的区域</p>
	 * @author Tangtao on 2016年5月17日
	 * @param appId
	 * @param type
	 * @param query
	 * @return
	 */
	List<ConfigRegion> getByAppAndType(Long[] appId, Byte type, String query);

	/**
	 * <p>Description: 获取 App 的商情区域</p>
	 * @author Tangtao on 2016年5月28日
	 * @param appId
	 * @param shopTypeId 商情分类 id
	 * @return
	 */
	List<ConfigRegion> getShopByAppAndType(Long appId, Long shopTypeId);
	
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
	 * 保存
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2016年7月28日下午4:53:06
	 */
	public void saveInfo(ConfigRegion configRegion) ;
	
	/**
     * 修改排序号
     * @author huxiaoping
     * @param sortNo
     * @param ids
     * @return
     */
    public void updateSortNo(Integer sortNo,Long... ids);
    
    /**
     * 分页商情区域信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年7月7日
     */
    public PageInfo<ConfigRegionDto> queryByPage(PageInfo<ConfigRegionDto> pageInfo,Map<String, Object> conditions,Map<String, Boolean> orders);
    
    public List<ConfigRegion> findByType(Byte type);
}
