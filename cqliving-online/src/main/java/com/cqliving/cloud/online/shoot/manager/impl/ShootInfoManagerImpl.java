package com.cqliving.cloud.online.shoot.manager.impl;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.basic.facade.PropertiesConfig;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.ShootInfoUtil;
import com.cqliving.cloud.common.constant.BusinessType;
import com.cqliving.cloud.common.constant.ClientControlType;
import com.cqliving.cloud.common.constant.PropertyKey;
import com.cqliving.cloud.online.account.dao.UserAccountDao;
import com.cqliving.cloud.online.account.dao.UserInfoDao;
import com.cqliving.cloud.online.account.dao.UserPraiseDao;
import com.cqliving.cloud.online.account.domain.UserAccount;
import com.cqliving.cloud.online.account.domain.UserInfo;
import com.cqliving.cloud.online.account.domain.UserPraise;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.app.dao.AppInfoDao;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.config.dao.ConfigSensitiveWordsDao;
import com.cqliving.cloud.online.config.domain.CommentConfig;
import com.cqliving.cloud.online.config.domain.ConfigSensitiveWords;
import com.cqliving.cloud.online.config.manager.CommentAppConfigManager;
import com.cqliving.cloud.online.info.domain.InfoClassify;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.ShootInfoData;
import com.cqliving.cloud.online.shoot.dao.ShootImagesDao;
import com.cqliving.cloud.online.shoot.dao.ShootInfoDao;
import com.cqliving.cloud.online.shoot.domain.ShootImages;
import com.cqliving.cloud.online.shoot.domain.ShootInfo;
import com.cqliving.cloud.online.shoot.dto.ShootInfoDto;
import com.cqliving.cloud.online.shoot.manager.ShootInfoManager;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.dao.support.ScrollPageOrder;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.StringUtil;
import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.common.util.encrypt.Base64Util;
import com.cqliving.tool.utils.ImageUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("shootInfoManager")
public class ShootInfoManagerImpl extends EntityServiceImpl<ShootInfo, ShootInfoDao> implements ShootInfoManager {
	
	@Autowired
	private AppInfoDao appInfoDao;
	@Autowired
	private ConfigSensitiveWordsDao configSensitiveWordsDao;
	@Autowired
	private CommentAppConfigManager commentAppConfigManager;
	@Autowired
	private ShootImagesDao shootImagesDao;
	@Autowired
	private UserAccountDao userAccountDao;
	@Autowired
	private UserInfoDao userInfoDao;
	@Autowired
	private UserPraiseDao userPraiseDao;
	@Autowired
	private UserSessionManager userSessionManager;
	
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(ShootInfo.STATUS99, idList);
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
		return this.getEntityDao().updateStatus(status, idList);
	}

	@Override
	public CommonListResult<ShootInfoData> queryForScrollPage(Long appId, String sessionId, String token, Byte type, Long shootInfoId, Long lastId) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		//查询用户
		UserSession userSession = userSessionManager.get(sessionId, token);
		Long praiseUserId  = userSession == null ? -1L : userSession.getUserId();
		
		//查询数据
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("EQ_id", shootInfoId);
		conditions.put("EQ_appId", appId);
		if (type.intValue() == 2) {	//我的随手拍
			//查询登录用户
			if (userSession == null) { 
				//需要登录
				throw new BusinessException(
						ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), 
						ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
			}
			conditions.put("EQ_userId", userSession == null ? -1L : userSession.getUserId());
			//查询待审核、上线和下线的随手拍
			conditions.put("IN_status", new Byte[]{ShootInfo.STATUS2, ShootInfo.STATUS3, ShootInfo.STATUS88});
		} else {
			conditions.put("EQ_status", ShootInfo.STATUS3);
		}
		ScrollPage<ShootInfoDto> scrollPage = new ScrollPage<ShootInfoDto>();
		scrollPage.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastId));
		scrollPage.setPageSize(10);
		scrollPage = getEntityDao().queryForScrollPage(scrollPage, conditions, praiseUserId);
		List<ShootInfoDto> dtos = scrollPage.getPageResults();
		//查询评论点赞记录
		List<UserPraise> userPraises = Lists.newArrayList();
		if (praiseUserId != -1) {
			conditions.clear();
			conditions.put("EQ_appId", appId);
			conditions.put("EQ_type", UserPraise.TYPE0);
			conditions.put("EQ_operateType", UserPraise.OPERATETYPE0);
			conditions.put("EQ_sourceType", BusinessType.SOURCE_TYPE_4);
			conditions.put("EQ_sourceUserId", praiseUserId);
			conditions.put("EQ_status", InfoClassify.STATUS3);
			userPraises = userPraiseDao.query(conditions, null);
		}
		
		//获取敏感词
		List<ConfigSensitiveWords> sensitiveWordsList = configSensitiveWordsDao.getByAppId(appId);
		List<String> sensitiveList = Lists.newArrayList();
		for (ConfigSensitiveWords obj : sensitiveWordsList) {
			if (ConfigSensitiveWords.STATUS3.equals(obj.getStatus())) {
				sensitiveList.add(obj.getName());
			}
		}
		//返回结果
		CommonListResult<ShootInfoData> result = new CommonListResult<ShootInfoData>();
		List<ShootInfoData> dataList = Lists.newArrayList();
		ShootInfoData data;
		if (CollectionUtils.isNotEmpty(dtos)) {
			for (ShootInfoDto dto : dtos) {
				data = new ShootInfoData();
				String content = StringUtil.replaceSensitiveWord(dto.getContent(), sensitiveList, "**");
				data.setContent(content);
				data.setCreateTime(DateUtil.convertTimeToFormatHore1(dto.getCreateTime().getTime()));
				data.setDescs(dto.getDescs());
				data.setDetailViewType(ClientControlType.DETAIL_VIEW_TYPE_4);
				data.setId(dto.getId());
				data.setIsPraised((byte) 0);
				for (UserPraise obj : userPraises) {	//判断是否点赞此随手拍
					if (obj.getSourceId().equals(dto.getId())) {
						data.setIsPraised((byte) 1);
						break;
					}
				}
				data.setImgs(dto.getImgs());
				data.setNickname(dto.getNickname());
				data.setPlace(dto.getPlace());
				data.setPriseCount(dto.getPriseCount());
				data.setReplyCount(dto.getReplyCount());
				data.setShareUrl(ShootInfoUtil.getShareUrl(dto.getId()));
				data.setShootType(dto.getShootType());
				data.setSourceType(BusinessType.SOURCE_TYPE_4);
				data.setStatus(dto.getStatus());
				data.setSynopsis(StringUtils.isNotEmpty(content) ? content : "捕捉灵感一刻，分享生活故事。");
				data.setTitle(appInfo.getName() + "-随手拍");
				data.setUrl("");
				data.setUserId(dto.getUserId());
				data.setUserImg(dto.getUserImg());
				dataList.add(data);
			}
			result.setDataList(dataList);
		}
		return result;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class, value = "transactionManager")
	public Byte add(Long appId, String sessionId, String token, String place, String lat, String lng, String content, Byte shootType, String imgs, String imgDescs) {
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
		UserAccount userAccount = userAccountDao.get(userId);
		
		//判断用户账户状态，禁用账户不能发布随手拍 By Tangtao 2017-01-11
		if (userAccount == null || !UserAccount.STATUS0.equals(userAccount.getStatus())) {
			throw new BusinessException(ErrorCodes.FAILURE, "您不能发布随手拍");
		}
		
		//查询随手拍相关配置（发布是否需要审核）
		Byte needAudit = commentAppConfigManager.getConfigValueByName(appId, CommentConfig.SHOOT_INFO_NEED_AUDIT, BusinessType.SOURCE_TYPE_4);
		Byte status = needAudit.equals(CommentConfig.VALUE1) ? ShootInfo.STATUS2 : ShootInfo.STATUS3;
		//保存数据
		//保存随手拍表
		Date now = DateUtil.now();
		ShootInfo shootInfo = new ShootInfo();
		shootInfo.setAppId(appId);
		shootInfo.setContent(content);
		shootInfo.setCreateTime(now);
		shootInfo.setCreator(userInfo.getName());
		shootInfo.setCreatorId(userInfo.getId());
		shootInfo.setLat(lat);
		shootInfo.setLng(lng);
		shootInfo.setPlace(place);
		shootInfo.setPriseCount(0);
		shootInfo.setReplyCount(0);
		shootInfo.setShootType(shootType);
		shootInfo.setStatus(status);
		shootInfo.setUpdateTime(now);
		shootInfo.setUpdator(userInfo.getName());
		shootInfo.setUpdatorId(userInfo.getId());
		shootInfo.setUserId(userId);
		save(shootInfo);
		
		//保存随手拍图片数据
		String[] imgArray = imgs.split(",");
		String[] imgDescArray = imgDescs.split(",");
		if (imgArray.length != imgDescArray.length) {
			throw new BusinessException(ErrorCodes.FAILURE, "图片地址和描述的数量不匹配");
		}
		List<String> imgUrlList = Lists.newArrayList();
		//保存图片，返回图片路径
		String filePath = PropertiesConfig.getString(PropertyKey.FILE_LOCAL_PATH);
		String modulePath = handleModulePath("", appId);
		if (!filePath.endsWith(File.separator)) {
			filePath += File.separator;
		}
		File destFile = new File(filePath);
		destFile.mkdirs();
		int w, h;	//切图基准：{多图: 226x226, 横向单图: 460x340, 纵向单图: 340x460}
		for (String img : imgArray) {
			System.out.println(img.length());
			String fileName = StringUtil.getUUID() + ".jpg";
	        File file = Base64Util.decodeBase64(img.replaceAll("\\s*|\t|\r|\n", ""), modulePath + fileName, filePath);
	        //切图 begin
	        if (imgArray.length > 1) {	//多图
				w = h = 226;
			} else {
				//判断图片是纵向还是横向
				if (ImageUtil.getImgStyle(file) == 1) {
					w = 460;
					h = 340;
				} else {
					w = 340;
					h = 460;
				}
			}
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
		//保存随后拍图片表
		List<ShootImages> shootImages = Lists.newArrayList();
		ShootImages images;
		for (int i = 0; i < imgArray.length; i++) {
			images = new ShootImages();
			images.setCreateTime(now);
			images.setDescription(imgDescArray[i]);
			images.setImageUrl(imgUrlList.get(i));
			images.setShootId(shootInfo.getId());
			shootImages.add(images);
		}
		shootImagesDao.save(shootImages);
		return status;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class, value = "transactionManager")
	public boolean remove(Long appId, String sessionId, String token, Long id) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		
		//查询用户
		UserSession userSession = userSessionManager.get(sessionId, token);
		if (userSession == null) {
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
		}
		UserInfo userinfo = userInfoDao.get(userSession.getUserId());
		
		//查询随手拍数据
		ShootInfo shootInfo = get(id);
		if (shootInfo == null) {
			throw new BusinessException(ErrorCodes.FAILURE, "记录不存在");
		}
		if (!shootInfo.getUserId().equals(userSession.getUserId())) {
			throw new BusinessException(ErrorCodes.FAILURE, "不能删除其他用户的数据");
		}
		
		//删除
		shootInfo.setStatus(ShootInfo.STATUS99);
		shootInfo.setUpdateTime(DateUtil.now());
		shootInfo.setUpdator(userinfo.getName());
		shootInfo.setUpdatorId(userinfo.getId());
		update(shootInfo);
		return true;
	}

	@Override
	public PageInfo<ShootInfoDto> queryDtoForPage(PageInfo<ShootInfoDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap) {
		return getEntityDao().queryDtoForPage(pageInfo, searchMap, sortMap);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class, value = "transactionManager")
	public void updateStatus(Byte status, Long id, Long userId, String nickname) {
		ShootInfo obj = get(id);
		if (obj != null) {
			obj.setStatus(status);
			obj.setUpdateTime(DateUtil.now());
			obj.setUpdator(nickname);
			obj.setUpdatorId(userId);
			update(obj);
		}
	}
	
	/**
	 * <p>Description: 处理图片保存路径</p>
	 * @author Tangtao on 2016年7月4日
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