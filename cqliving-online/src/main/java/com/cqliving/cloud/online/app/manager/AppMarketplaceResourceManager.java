package com.cqliving.cloud.online.app.manager;

import java.util.List;

import com.cqliving.cloud.online.app.domain.AppMarketplaceResource;
import com.cqliving.cloud.online.interfacc.dto.InitStartLoadingImg;
import com.cqliving.framework.common.service.EntityService;

/**
 * 客户端发布市场资源表 Manager
 * Date: 2016-04-15 09:43:50
 * @author Code Generator
 */
public interface AppMarketplaceResourceManager extends EntityService<AppMarketplaceResource> {

	/**
	 * Title: 获取客户端需要更新的图片
	 * @author Tangtao on 2016年5月1日
	 * @param appId
	 * @param type
	 * @param version 当前客户端版本号
	 * @return
	 * @deprecated 已废弃，请勿使用 By Tangtao 2016-05-25
	 */
	@Deprecated
	List<InitStartLoadingImg> getUpdateImgs(Long appId, Integer type, Integer version);

	/**
	 * <p>Description: 获取最新广告图</p>
	 * @author Tangtao on 2016年5月25日
	 * @param appId
	 * @param type 客户端类型
	 * @return
	 */
	AppMarketplaceResource getLatestByApp(Long appId, Integer type);

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年7月19日
	 * @param appId
	 * @param versionId
	 * @return
	 */
	AppMarketplaceResource getByVersion(Long appId, Long versionId);

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
     * 修改排序号
     * @Description 
     * @Company 
     * @parameter 
     * @return
     */
    public void updateSortNo(Integer sortNo, Long id);
}
