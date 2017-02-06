package com.cqliving.cloud.online.tourism.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.tourism.domain.TourismImage;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 旅游图片表 Service
 * Date: 2016-08-23 13:55:07
 * @author Code Generator
 */
public interface TourismImageService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<TourismImage>> queryForPage(PageInfo<TourismImage> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<TourismImage> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(TourismImage domain);
	/** @author Code Generator *****end*****/
	public Response<List<TourismImage>> findByTourismId(Long tourismId);
}
