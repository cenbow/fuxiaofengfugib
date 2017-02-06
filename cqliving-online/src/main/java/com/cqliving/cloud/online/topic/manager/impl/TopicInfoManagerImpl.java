package com.cqliving.cloud.online.topic.manager.impl;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.basic.facade.PropertiesConfig;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.constant.BusinessType;
import com.cqliving.cloud.common.constant.PropertyKey;
import com.cqliving.cloud.online.account.dao.UserInfoDao;
import com.cqliving.cloud.online.account.domain.UserInfo;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.app.dao.AppInfoDao;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.config.dao.RecommendInfoDao;
import com.cqliving.cloud.online.config.domain.CommentConfig;
import com.cqliving.cloud.online.config.domain.RecommendInfo;
import com.cqliving.cloud.online.config.manager.CommentAppConfigManager;
import com.cqliving.cloud.online.shop.domain.ShopInfo;
import com.cqliving.cloud.online.topic.dao.TopicImageDao;
import com.cqliving.cloud.online.topic.dao.TopicInfoDao;
import com.cqliving.cloud.online.topic.domain.TopicDefaultImage;
import com.cqliving.cloud.online.topic.domain.TopicImage;
import com.cqliving.cloud.online.topic.domain.TopicInfo;
import com.cqliving.cloud.online.topic.manager.TopicDefaultImageManager;
import com.cqliving.cloud.online.topic.manager.TopicInfoManager;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.StringUtil;
import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.common.util.encrypt.Base64Util;
import com.cqliving.tool.utils.ImageUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("topicInfoManager")
public class TopicInfoManagerImpl extends EntityServiceImpl<TopicInfo, TopicInfoDao> implements TopicInfoManager {
	
	@Autowired
	private AppInfoDao appInfoDao;
	@Autowired
	private CommentAppConfigManager commentAppConfigManager;
	@Autowired
	private TopicImageDao topicImageDao;
	@Autowired
	private TopicDefaultImageManager topicDefaultImageManager;
	@Autowired
	private UserInfoDao userInfoDao;
	@Autowired
	private UserSessionManager userSessionManager;
	@Autowired
	private RecommendInfoDao recommendInfoDao;
	
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		//修改推荐表的状态
		recommendInfoDao.updateStatusBySourceId(RecommendInfo.STATUS99, idList);
		return this.getEntityDao().updateStatus(TopicInfo.STATUS99, idList);
	}
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int updateStatus(Byte status,Long... ids){
		List<Long> idList = Arrays.asList(ids);
		if(!TopicInfo.allStatuss.containsKey(status)){
			throw new BusinessException(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
		}
		return this.getEntityDao().updateStatus(status, idList);
	}

	/**
	 * 修改置顶状态【可以 置顶多个话题】
	 * @param status 状态
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public void updateTopStatus(Byte status, Long id, String imageUrl, Long userId, String userName){
		//参数有效性验证
		if(!TopicInfo.allIsTops.containsKey(status)){
			throw new BusinessException(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
		}
		TopicInfo topicInfo = this.get(id);
		Date now = new Date();
		if(TopicInfo.ISTOP1.equals(status)){
			/*新的需求变动，可以置顶多个话题
			this.getEntityDao().resetIsTopStatus(TopicInfo.ISTOP0, topicInfo.getAppId(), TopicInfo.ISTOP1);//取消已经置顶的数据*/
			topicInfo.setTopImageUrl(imageUrl);//置顶是需要传图片的
			
			RecommendInfo recommendInfo = new RecommendInfo();
			recommendInfo.setAppId(topicInfo.getAppId());
			recommendInfo.setSourceId(topicInfo.getId());
			recommendInfo.setSourceType(RecommendInfo.SOURCETYPE7);
			recommendInfo.setName(topicInfo.getName());
			recommendInfo.setImageUrl(imageUrl);
			recommendInfo.setSortNo(0);
			recommendInfo.setStatus(RecommendInfo.STATUS1);
			recommendInfo.setCreator(userName);
			recommendInfo.setCreatorId(userId);
			recommendInfo.setCreateTime(now);
			recommendInfo.setUpdateTime(now);
			recommendInfo.setUpdator(userName);
			recommendInfo.setUpdatorId(userId);
			recommendInfoDao.save(recommendInfo);
		}
		topicInfo.setIsTop(status);
		topicInfo.setUpdateTime(now);
		this.update(topicInfo);
	}

	@Override
	@Transactional(value="transactionManager")
	public void saveByAdmin(TopicInfo topicInfo, String[] imageUrls) {
		if(imageUrls != null && imageUrls.length > 0){//取图片中的第一张
			topicInfo.setListImageUrl(imageUrls[0]);
		}else{//没有图片取系统默认的图片
			TopicDefaultImage topicDefaultImage = topicDefaultImageManager.getByAppId(topicInfo.getAppId());
			if(topicDefaultImage != null){
				topicInfo.setListImageUrl(topicDefaultImage.getImageUrl());
				imageUrls = new String[1];
				imageUrls[0] = topicDefaultImage.getImageUrl();
			}else{
				throw new BusinessException(ErrorCodes.FAILURE, "请上传话题图片或到话题配置去设置默认图片。");
			}
		}
		//保存或修改话题信息
		topicInfo = this.getEntityDao().save(topicInfo);
		//处理话题图片
		List<TopicImage> topicImageList = Lists.newArrayList();
		if(imageUrls != null && imageUrls.length > 0){
			TopicImage topicImage = null;
			for(String str : imageUrls){
				topicImage = new TopicImage();
				topicImage.setAppId(topicInfo.getAppId());
				topicImage.setTopicInfoId(topicInfo.getId());
				topicImage.setUrl(str);
				topicImage.setSortNo(0);
				topicImage.setStatus(TopicImage.STATUS3);
				topicImage.setCreateTime(topicInfo.getUpdateTime());
				topicImage.setCreator(topicInfo.getUpdator());
				topicImage.setCreatorId(topicInfo.getUpdatorId());
				topicImage.setUpdateTime(topicInfo.getUpdateTime());
				topicImage.setUpdator(topicInfo.getUpdator());
				topicImage.setUpdatorId(topicInfo.getUpdatorId());
				topicImageList.add(topicImage);
			}
		}
		//先删除旧数据
		topicImageDao.deleteByTopicInfoId(topicInfo.getId());
		//再插入新数据
		if(topicImageList.size() > 0){
			topicImageDao.saves(topicImageList);
		}
	}

	/**
	 * 推荐到首页、取消推荐到首页
	 * @param status
	 * @param id
	 * @param imageUrl
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public void updateRecommendStatus(Byte status, Long id, String imageUrl) {
		//参数有效性验证
		if(!TopicInfo.allIsRecommends.containsKey(status)){
			throw new BusinessException(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
		}
		TopicInfo topicInfo = this.get(id);
		if(TopicInfo.ISRECOMMEND1.equals(status)){//取消已经置顶的数据
			this.getEntityDao().resetIsRecommendStatus(TopicInfo.ISRECOMMEND0, topicInfo.getAppId(), TopicInfo.ISRECOMMEND1);
			topicInfo.setRecommendImageUrl(imageUrl);//置顶是需要传图片的
		}
		topicInfo.setIsRecommend(status);
		topicInfo.setUpdateTime(new Date());
		this.update(topicInfo);
	}

	@Override
	public List<TopicInfo> getTopList(Long appId) {
		return recommendInfoDao.getTopTopicList(appId, RecommendInfo.STATUS3, RecommendInfo.SOURCETYPE7);
	}

	@Override
	public ScrollPage<TopicInfo> queryForScrollPage(ScrollPage<TopicInfo> scrollPage, Long appId, Long type, String name, Byte isRecommend) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_app_id", appId);
		map.put("LIKE_types", type != null ? "," + type + "," : null);
		map.put("EQ_isRecommend", isRecommend);
		map.put("EQ_status", ShopInfo.STATUS3);
		return getEntityDao().queryForScrollPage(scrollPage, map, name);
	}

	@Override
	public ScrollPage<TopicInfo> queryMyScrollPage(ScrollPage<TopicInfo> scrollPage, Long appId, String sessionId, String token) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		//查询用户
		UserSession userSession = userSessionManager.getByToken(token);
		if (userSession == null) { 
			//需要登录
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), 
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
		}
		Long userId = userSession.getUserId();
		//查询数据
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_app_id", appId);
		map.put("EQ_creator_id", userId);
		map.put("IN_status", new Byte[]{TopicInfo.STATUS1, TopicInfo.STATUS3, TopicInfo.STATUS88});
		return getEntityDao().queryForScrollPage(scrollPage, map, "");
	}

	@Override
	@Transactional(rollbackFor = Throwable.class, value = "transactionManager")
	public Byte add(Long appId, String sessionId, String token, String name, String content, String imgs, String typeIds) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		//查询用户
		UserSession userSession = userSessionManager.getByToken(token);
		if (userSession == null) { 
			//需要登录
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), 
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
		}
		Long userId = userSession.getUserId();
		UserInfo userInfo = userInfoDao.get(userId);
		
		//查询话题相关配置（发布是否需要审核）
		Byte needAudit = commentAppConfigManager.getConfigValueByName(appId, CommentConfig.TOPIC_INFO_NEED_AUDIT, BusinessType.SOURCE_TYPE_7);
		Byte status = needAudit.equals(CommentConfig.VALUE1) ? TopicInfo.STATUS1 : TopicInfo.STATUS3;
		
		//保存数据
		//保存图片，返回图片路径
		String[] imgArray = imgs.split(",");
		List<String> imgUrlList = Lists.newArrayList();
		String filePath = PropertiesConfig.getString(PropertyKey.FILE_LOCAL_PATH);
		String modulePath = handleModulePath("", appId);
		if (!filePath.endsWith(File.separator)) {
			filePath += File.separator;
		}
		File destFile = new File(filePath);
		destFile.mkdirs();
		int w, h;	//切图基准：{多图: 226x226}
		for (String img : imgArray) {
			String fileName = StringUtil.getUUID() + ".jpg";
	        File file = Base64Util.decodeBase64(img.replaceAll("\\s*|\t|\r|\n", ""), modulePath + fileName, filePath);
	        //切图 begin
			w = h = 226;
	        String cutFilePath = ImageUtil.appendSuffixBySize(w, h, file.getPath());
	        File cutFile = new File(cutFilePath);
	        ImageUtil.cutForceSize(w, h, file, cutFile);
	        //切图 end
	        String url = PropertiesConfig.getString(PropertyKey.FILE_URL_PATH);
	        if (!url.endsWith(File.separator)) {
	        	url += File.separator;
			}
	        String fIleUrl = cutFilePath.replace(filePath, url);
	        imgUrlList.add(fIleUrl.replace("\\", "/"));
		}
		//查询话题默认图片
		TopicDefaultImage topicDefaultImage = topicDefaultImageManager.getByAppId(appId);
		//保存话题表
		Date now = DateUtil.now();
		TopicInfo topicInfo = new TopicInfo();
		topicInfo.setAppId(appId);
		topicInfo.setContent(content);
		topicInfo.setCreateTime(now);
		topicInfo.setCreator(userInfo.getName());
		topicInfo.setCreatorId(userInfo.getId());
		topicInfo.setIsAudit(TopicInfo.ISAUDIT0);
		topicInfo.setIsRecommend(TopicInfo.ISRECOMMEND0);
		topicInfo.setIsTop(TopicInfo.ISTOP0);
		topicInfo.setListImageUrl(CollectionUtils.isNotEmpty(imgUrlList) ? imgUrlList.get(0) : topicDefaultImage.getImageUrl());
		topicInfo.setName(name);
		topicInfo.setReplyCount(0);
		topicInfo.setSourceType(TopicInfo.SOURCETYPE1);
		topicInfo.setStatus(status);
		topicInfo.setTypes("," + typeIds + ",");
		topicInfo.setUpdateTime(now);
		topicInfo.setUpdator(userInfo.getName());
		topicInfo.setUpdatorId(userInfo.getId());
		save(topicInfo);
		//保存话题图片表
		List<TopicImage> topicImages = Lists.newArrayList();
		TopicImage images;
		int sortNo = 1;
		for (String url : imgUrlList) {
			images = new TopicImage();
			images.setAppId(appId);
			images.setCreateTime(now);
			images.setCreator(userInfo.getName());
			images.setCreatorId(userInfo.getId());
			images.setSortNo(sortNo++);
			images.setStatus(TopicImage.STATUS3);
			images.setTopicInfoId(topicInfo.getId());
			images.setUpdateTime(now);
			images.setUpdator(userInfo.getName());
			images.setUpdatorId(userInfo.getId());
			images.setUrl(url);
			topicImages.add(images);
		}
		topicImageDao.save(topicImages);
		return status;
	}
	
	@Override
	@Transactional(rollbackFor = Throwable.class, value = "transactionManager")
	public Boolean remove(Long appId, String sessionId, String token, Long id) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		//查询用户
		UserSession userSession = userSessionManager.getByToken(token);
		if (userSession == null) { 
			//需要登录
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), 
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
		}
		UserInfo userInfo = userInfoDao.get(userSession.getUserId());
		
		//查询话题数据
		TopicInfo topicInfo = get(id);
		if (topicInfo == null) {
			throw new BusinessException(ErrorCodes.FAILURE, "记录不存在");
		}
		if (!topicInfo.getCreatorId().equals(userSession.getUserId())) {
			throw new BusinessException(ErrorCodes.FAILURE, "不能删除其他用户的数据");
		}
		
		//删除
		topicInfo.setStatus(TopicInfo.STATUS99);
		topicInfo.setUpdateTime(DateUtil.now());
		topicInfo.setUpdator(userInfo.getName());
		topicInfo.setUpdatorId(userInfo.getId());
		return true;
	}

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年7月27日
	 * @param modulePath
	 * @param appId
	 * @return
	 */
	private String handleModulePath(String modulePath, Long appId) {
		if (StringUtil.isEmpty(modulePath)) {
			modulePath = "common";
		}
		StringBuilder modulePathBuilder = new StringBuilder();
		modulePathBuilder.append(File.separator).append("app_").append(null == appId ? 0 : appId);
		modulePathBuilder.append(File.separator).append("server");
//		modulePathBuilder.append(File.separator).append(DateUtil.formatDate(DateUtil.now(), DateUtil.FORMAT_YYYY_MM_DD));
//		modulePathBuilder.append(File.separator).append(modulePath);
		return modulePathBuilder.toString();
    }
	
}
