package com.cqliving.cloud.online.info.service;

import java.util.Map;

import com.cqliving.cloud.online.info.domain.InfoFile;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 资讯对应的文件表 Service
 * Date: 2016-05-07 18:00:13
 * @author Code Generator
 */
public interface InfoFileService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<InfoFile>> queryForPage(PageInfo<InfoFile> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<InfoFile> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(InfoFile domain);
	
	public Response<InfoFile> getByFileMd5(String fileMd5);
	/** @author Code Generator *****end*****/
	//视频音频回调转码
	public Response<Void> transCoding();
}
