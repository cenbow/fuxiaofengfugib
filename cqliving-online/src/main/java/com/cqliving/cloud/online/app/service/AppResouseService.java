package com.cqliving.cloud.online.app.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.app.domain.AppResouse;
import com.cqliving.cloud.online.info.domain.InfoFile;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 资讯资源表【文字,图文,视频,音频】 Service
 * Date: 2016-04-15 09:43:54
 * @author Code Generator
 */
public interface AppResouseService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<AppResouse>> queryForPage(PageInfo<AppResouse> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<AppResouse> get(Long id);
	public Response<Void> delete(Long id);
	public Response<Void> save(AppResouse domain);
	/** @author Code Generator *****end*****/
	public Response<List<AppResouse>> findByInfoContentId(Long informationContentId);
	
	//保存APPResourse
	public Response<AppResouse> saveAppResouse(AppResouse[] appResouse,InfoFile infoFile);
	
	public Response<List<AppResouse>> findByInformationId(Long informationId);
}
