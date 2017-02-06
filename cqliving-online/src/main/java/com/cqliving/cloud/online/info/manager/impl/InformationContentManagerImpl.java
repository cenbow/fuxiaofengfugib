package com.cqliving.cloud.online.info.manager.impl;


import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.info.dao.InformationContentDao;
import com.cqliving.cloud.online.info.domain.InformationContent;
import com.cqliving.cloud.online.info.manager.InformationContentManager;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.StringUtil;

@Service("informationContentManager")
public class InformationContentManagerImpl extends EntityServiceImpl<InformationContent, InformationContentDao> implements InformationContentManager {

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InformationContentManager#findByInfoId(java.lang.Long)
	 */
	@Override
	public List<InformationContent> findByInfoId(Long infoId) {
		
		return this.getEntityDao().findByInformationId(infoId);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InformationContentManager#infoContentSort(java.lang.Integer[], java.lang.Long[])
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void infoContentSort(Integer[] sorts, Long[] contentIds) {
		if(StringUtil.isEmpty(contentIds) || StringUtil.isEmpty(sorts))return;
		for(int i=0;i<sorts.length;i++){
			this.getEntityDao().sort(contentIds[i], sorts[i]);
		}
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InformationContentManager#compareToDB(java.util.List, java.util.List)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void compareToDB(List<InformationContent> sqlData, List<InformationContent> data) {
		
		if(CollectionUtils.isEmpty(sqlData) || CollectionUtils.isEmpty(data)){
			return;
		}
		//如果页面data的ID在数据库中不存在，则将数据库中的删掉
		Iterator<InformationContent> sqlIter = sqlData.iterator();
		while(sqlIter.hasNext()){
			InformationContent sqlcontent = sqlIter.next();
			boolean isDelete = true;
			for(InformationContent content : data){
				if(null != content && null != content.getId() && sqlcontent.getId().longValue() == content.getId().longValue()){//
					isDelete = false;
					break;
				}
			}
			if(isDelete){
				this.delById(sqlcontent.getId());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InformationContentManager#updateInfoContent(java.lang.Long, java.lang.Long, java.lang.String, java.lang.Byte, java.lang.Long)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public int updateInfoContent(Long appId,Long infomationId,String title,Byte type,Integer sortNo,Long infoContentId) {
		
		return this.getEntityDao().updateInfoContent(appId, infomationId, title, type,sortNo, infoContentId);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InformationContentManager#delByInfoId(java.lang.Long)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void delByInfoId(Long infoId) {
		
		List<InformationContent> list = this.findByInfoId(infoId);
		if(CollectionUtils.isEmpty(list))return;
		for(InformationContent ic : list){
			this.delById(ic.getId());
		}
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InformationContentManager#delById(java.lang.Long)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void delById(Long id) {
		this.getEntityDao().deleteById(id);
	}

}
