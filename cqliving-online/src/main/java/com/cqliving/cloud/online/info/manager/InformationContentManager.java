package com.cqliving.cloud.online.info.manager;

import java.util.List;

import com.cqliving.cloud.online.info.domain.InformationContent;
import com.cqliving.framework.common.service.EntityService;

/**
 * 资讯内容表 Manager
 * Date: 2016-04-15 09:44:24
 * @author Code Generator
 */
public interface InformationContentManager extends EntityService<InformationContent> {

	public List<InformationContent> findByInfoId(Long infoId);
	
	public void infoContentSort(Integer[] sorts, Long[] contentIds);
	
	public void compareToDB(List<InformationContent> sqlData,List<InformationContent> data);
	
	public int updateInfoContent(Long appId,Long infomationId,String title,Byte type,Integer sortNo,Long infoContentId);
	
	public void delByInfoId(Long infoId);
	
	public void delById(Long id);
}
