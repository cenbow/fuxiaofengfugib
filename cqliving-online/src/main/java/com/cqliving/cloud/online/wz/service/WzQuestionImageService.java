package com.cqliving.cloud.online.wz.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.wz.domain.WzQuestionImage;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 问政问题图片表 Service
 * Date: 2016-05-10 09:47:54
 * @author Code Generator
 */
public interface WzQuestionImageService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<WzQuestionImage>> queryForPage(PageInfo<WzQuestionImage> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<WzQuestionImage> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(WzQuestionImage domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年5月16日
	 * @param questionId
	 * @return
	 */
	public Response<List<WzQuestionImage>> getByQuestion(Long questionId);
	
}
