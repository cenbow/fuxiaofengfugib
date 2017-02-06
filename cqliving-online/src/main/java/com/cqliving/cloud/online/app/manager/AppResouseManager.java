package com.cqliving.cloud.online.app.manager;

import java.util.List;

import com.cqliving.cloud.online.app.domain.AppResouse;
import com.cqliving.cloud.online.info.domain.InfoFile;
import com.cqliving.framework.common.service.EntityService;

/**
 * 资讯资源表【文字,图文,视频,音频】 Manager
 * Date: 2016-04-15 09:43:54
 * @author Code Generator
 */
public interface AppResouseManager extends EntityService<AppResouse> {

	public List<AppResouse> findByInfoContentId(Long informationContentId);
	
	public AppResouse saveAppResouse(AppResouse[] appResouse,InfoFile infoFile);
	
	public List<AppResouse> findByInformationId(Long informationId);
	//同数据库对比,删除数据库多余的
    public void compareToDB(List<AppResouse> sqlResource,List<AppResouse> list);
    
    public List<AppResouse> jsonStrToList(String appResource);
}
