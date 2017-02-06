package com.cqliving.cloud.online.app.manager.impl;


import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.app.dao.AppResouseDao;
import com.cqliving.cloud.online.app.domain.AppResouse;
import com.cqliving.cloud.online.app.manager.AppResouseManager;
import com.cqliving.cloud.online.info.dao.InfoFileDao;
import com.cqliving.cloud.online.info.domain.InfoFile;
import com.cqliving.cloud.online.info.domain.InformationContent;
import com.cqliving.cloud.online.info.manager.InformationContentManager;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.StringUtil;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("appResouseManager")
public class AppResouseManagerImpl extends EntityServiceImpl<AppResouse, AppResouseDao> implements AppResouseManager {
   
	@Autowired
	InformationContentManager informationContentManager; 
	@Autowired
	InfoFileDao infoFileDao;
	
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.app.manager.AppResouseManager#findByInfoContentId(java.lang.Long)
	 */
	@Override
	public List<AppResouse> findByInfoContentId(Long informationContentId) {
		
		return this.getEntityDao().findByInfoContentId(informationContentId);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.app.manager.AppResouseManager#saveAppResouse(com.cqliving.cloud.online.app.domain.AppResouse)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public AppResouse saveAppResouse(AppResouse[] appResouse,InfoFile infoFile) {
		//这个方法只处理原创的资源
		if(StringUtil.isEmpty(appResouse)){
		    throw new BusinessException(ErrorCodes.FAILURE,"原创新闻内容不能为空");	
		}
		AppResouse ar = appResouse[0];
		Long infocontentId = ar.getInformationContentId();
		if(null != infocontentId){//不是新增，要比较资源的增加修改
			List<AppResouse> sqlAppResource = this.getEntityDao().findByInfoContentId(infocontentId);
			//与数据库比较,多余的删除
			this.compareToDB(sqlAppResource,Arrays.asList(appResouse));
		}
		//处理ar
		return this.saveBySurveyInfo(appResouse);
	}
	
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	private AppResouse saveBySurveyInfo(AppResouse[] appResources){
		
		AppResouse appResouse = appResources[0];
		
		Long infoContentId = appResouse.getInformationContentId();
		String title = appResouse.getName();
		byte type = appResouse.getType().byteValue();
		if(!StringUtil.isEmpty(title) && title.length()>=41){
			title = title.substring(0,40);
		}
		if(InformationContent.TYPE6.byteValue() == type){
			title="多图";
		}else if(InformationContent.TYPE0.byteValue() == type){
			title="普通文本";
		}
		List<InformationContent> ics = informationContentManager.findByInfoId(appResouse.getInformationId());
		int sortNo = 0;
		if(CollectionUtils.isNotEmpty(ics))sortNo = ics.size();
		sortNo += 1 ;
		
		if(null == infoContentId){
			InformationContent informationContent = new InformationContent();
			informationContent.setAppId(appResouse.getAppId());
			informationContent.setInformationId(appResouse.getInformationId());
			informationContent.setSortNo(sortNo);
			informationContent.setStatus(InformationContent.STATUS3);
			informationContent.setTitle(title);
			informationContent.setType(type);
			informationContent = informationContentManager.save(informationContent);
			infoContentId = informationContent.getId();
		}else{
			informationContentManager.updateInfoContent(appResouse.getAppId(), appResouse.getInformationId(),title, appResouse.getType(),sortNo,infoContentId);
		}
		for(AppResouse app : appResources){
			app.setStatus(AppResouse.STATUS3);
			app.setInformationContentId(infoContentId);
			appResouse = this.getEntityDao().saveAndFlush(app);
		}
		return appResouse;
	}

	/*@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	private InfoFile saveInfoFile(InfoFile infoFile,InformationContent informationContent){
		
		if(null == infoFile)return null;
		List<InfoFile> sqlInfoFile = infoFileDao.findByHashCode(infoFile.getQiniuHash());
		if(CollectionUtils.isNotEmpty(sqlInfoFile)){
			InfoFile sqlFile =  sqlInfoFile.get(0);
			infoFile.setRefId(sqlFile.getId());
			infoFile.setId(null);
		}
		
		if(StringUtil.isEmpty(infoFile.getQiniuPersistentId()))return null;
		
		infoFile.setAppId(informationContent.getAppId());
		infoFile.setDownloadStatus(InfoFile.DOWNLOADSTATUS1);
		infoFile.setStatus(InfoFile.STATUS3);
		if(informationContent!=null){
			if(informationContent.getType().byteValue() == InformationContent.TYPE1.byteValue()){
				infoFile.setType(InfoFile.TYPE1);
			}else if(informationContent.getType().byteValue() == InformationContent.TYPE2.byteValue()){
				infoFile.setType(InfoFile.TYPE2);
			}
		}
		return infoFileDao.saveAndFlush(infoFile);
	}*/
	
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.app.manager.AppResouseManager#findByInformationId(java.lang.Long)
	 */
	@Override
	public List<AppResouse> findByInformationId(Long informationId) {
		
		return this.getEntityDao().findByInformationId(informationId);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.app.manager.AppResouseManager#compareToDB(java.util.List, java.util.List)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void compareToDB(List<AppResouse> sqlResource, List<AppResouse> list) {
		if(CollectionUtils.isEmpty(sqlResource)){
			return;
		}
		if(CollectionUtils.isEmpty(list)){//全部删完
			informationContentManager.delById(sqlResource.get(0).getInformationContentId());
			return;
		}
		Long infoContentId = null;
		Iterator<AppResouse> sqlAr = sqlResource.iterator();
		while(sqlAr.hasNext()){
			boolean isDelete = true;
			AppResouse sql = sqlAr.next();
			infoContentId = sql.getInformationId();
			for(AppResouse ar : list){
				if(null != ar.getId() && ar.getId().longValue() == sql.getId().longValue()){
					isDelete = false;
					break;
				}
			}
			if(isDelete){
				sql.setStatus(AppResouse.STATUS99);
				this.getEntityDao().saveAndFlush(sql);
				sqlAr.remove();
			}
		}
		if(CollectionUtils.isEmpty(sqlResource)){//资源全部删完了,infocontent也必须得删掉
			informationContentManager.delById(infoContentId);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.app.manager.AppResouseManager#strToObj(java.lang.String)
	 */
	@Override
	public List<AppResouse> jsonStrToList(String appResource) {
		if(StringUtil.isEmpty(appResource))return null;
		List<AppResouse> appResouses = null;
    	ObjectMapper om = new ObjectMapper();
    	//om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    	try {
    		JavaType javaType = om.getTypeFactory().constructParametricType(List.class, AppResouse.class);
    		appResouses = om.readValue(appResource,javaType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return appResouses;
	}
}
