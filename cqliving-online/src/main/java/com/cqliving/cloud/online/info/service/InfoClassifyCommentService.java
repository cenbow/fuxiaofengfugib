package com.cqliving.cloud.online.info.service;

import java.util.Map;

import com.cqliving.cloud.online.info.domain.InfoClassifyComment;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 资讯栏目推荐表 Service
 * Date: 2016-04-15 09:44:31
 * @author Code Generator
 */
public interface InfoClassifyCommentService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<InfoClassifyComment>> queryForPage(PageInfo<InfoClassifyComment> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<InfoClassifyComment> get(Long id);
	public Response<Void> delete(Long id);
	public Response<Void> save(InfoClassifyComment domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * <p>Description: 忽略推荐</p>
	 * @author Tangtao on 2016年5月11日
	 * @param id
	 * @return
	 */
	Response<Void> ignore(Long id);
	
	/**
	 * <p>Description: 发布到App</p>
	 * @author Tangtao on 2016年5月12日
	 * @param appId
	 * @param infoClassifyId
	 * @param id 
	 * @param appColumnId
	 * @param userId 
	 * @param userName 
	 * @return
	 */
	Response<Void> publishToColumn(Long appId, Long infoClassifyId, Long id, Long appColumnId, String userName, Long userId);
	
}
