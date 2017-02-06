package com.cqliving.cloud.online.shop.manager.impl;

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
import com.cqliving.cloud.common.constant.BusinessType;
import com.cqliving.cloud.common.constant.PropertyKey;
import com.cqliving.cloud.online.account.dao.UserInfoDao;
import com.cqliving.cloud.online.account.domain.UserAccount;
import com.cqliving.cloud.online.account.domain.UserInfo;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.manager.UserAccountManager;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.config.dao.ConfigRegionDao;
import com.cqliving.cloud.online.config.dao.RecommendInfoDao;
import com.cqliving.cloud.online.config.domain.CommentConfig;
import com.cqliving.cloud.online.config.domain.ConfigRegion;
import com.cqliving.cloud.online.config.manager.CommentAppConfigManager;
import com.cqliving.cloud.online.shop.dao.ShopImageDao;
import com.cqliving.cloud.online.shop.dao.ShopInfoDao;
import com.cqliving.cloud.online.shop.domain.ShopImage;
import com.cqliving.cloud.online.shop.domain.ShopInfo;
import com.cqliving.cloud.online.shop.dto.ShopInfoDto;
import com.cqliving.cloud.online.shop.dto.ShopInfoListDto;
import com.cqliving.cloud.online.shop.manager.ShopInfoManager;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.StringUtil;
import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.common.util.encrypt.Base64Util;
import com.cqliving.tool.utils.ImageUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("shopInfoManager")
public class ShopInfoManagerImpl extends EntityServiceImpl<ShopInfo, ShopInfoDao> implements ShopInfoManager {
	
	@Autowired
	private CommentAppConfigManager commentAppConfigManager;
	@Autowired
	private ConfigRegionDao configRegionDao;
	@Autowired
	private RecommendInfoDao recommendInfoDao;
	@Autowired
	private ShopImageDao shopImageDao;
	@Autowired
	private UserSessionManager userSessionManager;
	@Autowired
	private UserAccountManager userAccountManager;
	@Autowired
	private UserInfoDao userInfoDao;
	
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(ShopInfo.STATUS99, idList);
	}
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public int updateStatus(Byte status,Long... ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(status, idList);
	}

	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public void save(ShopInfo shopInfo, String images, Long userId, String userName) {
		//获取区域信息
		String regionName = "";
		List<ConfigRegion> regions = configRegionDao.getByCode(shopInfo.getAppId(), shopInfo.getRegionCode());
		if (CollectionUtils.isNotEmpty(regions)) {
			regionName = regions.get(0).getName();
		}
		
		Date now = DateUtil.now();
		if (shopInfo.getId() == null) {	//新增
			//保存商铺
			shopInfo.setSourceType(ShopInfo.SOURCETYPE1);
			shopInfo.setCreateTime(now);
			shopInfo.setCreator(userName);
			shopInfo.setCreatorId(userId);
			shopInfo.setPrice(shopInfo.getPrice() * 100);
			shopInfo.setRegionName(regionName);
			shopInfo.setReplyCount(0);
			shopInfo.setCollectCount(0);
			shopInfo.setUpdateTime(now);
			shopInfo.setUpdator(userName);
			shopInfo.setUpdatorId(userId);
			shopInfo.setTopType(ShopInfo.TOPTYPE0);
			save(shopInfo);
		} else {	//修改
			shopInfo.setRegionName(regionName);
			shopInfo.setUpdator(userName);
			shopInfo.setUpdatorId(userId);
			update(shopInfo);
			//删除原有照片
			shopImageDao.deleteByShop(shopInfo.getId());
		}
		
		//保存商铺图片
		ShopImage shopImage;
		int sortNo = 1;
		List<ShopImage> shopImages = Lists.newArrayList();
		for (String url : images.split(",")) {
			shopImage = new ShopImage();
			shopImage.setAppId(shopInfo.getAppId());
			shopImage.setCreateTime(now);
			shopImage.setCreator(userName);
			shopImage.setCreatorId(userId);
			shopImage.setShopId(shopInfo.getId());
			shopImage.setSortNo(sortNo++);
			shopImage.setStatus(ShopImage.STATUS3);
			shopImage.setUpdateTime(now);
			shopImage.setUpdator(userName);
			shopImage.setUpdatorId(userId);
			shopImage.setUrl(url);
			shopImages.add(shopImage);
		}
		shopImageDao.save(shopImages);
	}

	@Override
	public ScrollPage<ShopInfoDto> queryForScrollPage(ScrollPage<ShopInfoDto> scrollPage, Double lat, Double lng, Long appId, Long shopTypeId, String regionCode, Long shopCategoryId, String shopName) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_app_id", appId);
		map.put("EQ_type_id", shopTypeId);
		map.put("EQ_region_code", regionCode);
		map.put("EQ_category_id", shopCategoryId);
		map.put("LIKE_name", shopName);
		map.put("EQ_status", ShopInfo.STATUS3);
		String columnName = scrollPage.getListOrderColumn().get(1).getColumnName();
		if ("distance".equals(columnName)) {	//按距离排序
			return getEntityDao().queryForScrollPageByDistance(scrollPage, map, lat, lng);
		} else {
			return getEntityDao().queryForScrollPage(scrollPage, map, columnName);
		}
	}

	@Override
	@Transactional(rollbackFor = Throwable.class, value = "transactionManager")
	public void top(Long id, String nickname, Long userId) {
		ShopInfo shopInfo = get(id);
		if (ShopInfo.TOPTYPE1.equals(shopInfo.getTopType())) {
			return;
		}
		
		//置顶
		shopInfo.setTopType(ShopInfo.TOPTYPE1);
		shopInfo.setUpdateTime(DateUtil.now());
		shopInfo.setUpdator(nickname);
		shopInfo.setUpdatorId(userId);
		update(shopInfo);
		//取消同分类其他店铺的置顶状态
		getEntityDao().cancelTop(shopInfo.getTypeId(), id);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class, value = "transactionManager")
	public void untop(Long id, String nickname, Long userId) {
		ShopInfo shopInfo = get(id);
		if (ShopInfo.TOPTYPE0.equals(shopInfo.getTopType())) {
			return;
		}
		
		//取消置顶
		shopInfo.setTopType(ShopInfo.TOPTYPE0);
		shopInfo.setUpdateTime(DateUtil.now());
		shopInfo.setUpdator(nickname);
		shopInfo.setUpdatorId(userId);
		update(shopInfo);
	}
	
	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public Byte save(ShopInfo shopInfo, String images, String descriptions, String sessionId, String token) {
		Byte SHOP_ALLOW_USER_ADD = commentAppConfigManager.getConfigValueByName(shopInfo.getAppId(), CommentConfig.SHOP_ALLOW_USER_ADD, BusinessType.SOURCE_TYPE_3),
			SHOP_USER_ADD_NEED_LOGIN = commentAppConfigManager.getConfigValueByName(shopInfo.getAppId(), CommentConfig.SHOP_USER_ADD_NEED_LOGIN, BusinessType.SOURCE_TYPE_3),
			SHOP_USER_ADD_NEET_AUDIT = commentAppConfigManager.getConfigValueByName(shopInfo.getAppId(), CommentConfig.SHOP_USER_ADD_NEET_AUDIT, BusinessType.SOURCE_TYPE_3);
		//验证设置，是否允许用户上传
		if(CommentConfig.VALUE0.equals(SHOP_ALLOW_USER_ADD)){//用户需要登录才可上传
			throw new BusinessException(ErrorCodes.FAILURE, "不允许用户上传");
		}
		UserAccount userAccount = null;
		//验证用户*****由于游客的用户名在userinfo表里，而用户的电话号码又在userAccount表  所以就有下面一大堆代码***
		UserSession userSession = userSessionManager.get(sessionId, token);
		if(userSession == null && StringUtils.isNotBlank(token)){
			throw new BusinessException(ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
		}else if(userSession == null){//创建游客帐号
			userAccount = userAccountManager.createTourist(shopInfo.getAppId(), sessionId);
			userSession = userSessionManager.get(sessionId, token);
		}
		if(userAccount == null){
			userAccount = userAccountManager.get(userSession.getUserId());
			if(userAccount == null){
				throw new BusinessException(ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
			}
		}

		//获取游客的名称
		UserInfo userInfo = userInfoDao.get(userAccount.getId());
		String userName = userInfo.getName();
		//验证设置，商情上传是否需要登录
		if(CommentConfig.VALUE1.equals(SHOP_USER_ADD_NEED_LOGIN) && StringUtils.isBlank(userSession.getToken())){//用户需要登录才可上传
			throw new BusinessException(ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
		}
		Date now = DateUtil.now();
		shopInfo.setLat((StringUtils.isBlank(shopInfo.getLat()) || "0".equals(shopInfo.getLat())) ? null : shopInfo.getLat());
		shopInfo.setLng((StringUtils.isBlank(shopInfo.getLng()) || "0".equals(shopInfo.getLng())) ? null : shopInfo.getLng());
		//保存商铺
		shopInfo.setCreatePhone(userAccount.getTelephone());
		shopInfo.setSourceType(ShopInfo.SOURCETYPE2);
		shopInfo.setCreateTime(now);
		shopInfo.setCreator(userName);
		shopInfo.setCreatorId(userAccount.getId());
		if(shopInfo.getPrice() > (Integer.MAX_VALUE * 0.01)){
			throw new BusinessException(ErrorCodes.FAILURE, "价格超出限制");
		}
		shopInfo.setPrice(shopInfo.getPrice() * 100);
		shopInfo.setReplyCount(0);
		shopInfo.setCollectCount(0);
		shopInfo.setUpdateTime(now);
		shopInfo.setUpdator(userName);
		shopInfo.setUpdatorId(userAccount.getId());
		shopInfo.setTopType(ShopInfo.TOPTYPE0);
		//设置为不需要审核 and 经度和未读都有的情况下是不需要审核的，其他情况都需要审核
		if(CommentConfig.VALUE0.equals(SHOP_USER_ADD_NEET_AUDIT) && StringUtils.isNotBlank(shopInfo.getLat()) && StringUtils.isNotBlank(shopInfo.getLng())){//不需要审核
			shopInfo.setStatus(ShopInfo.STATUS3);
		}else{
			//就算是设置的不需要审核，但经度和纬度只要有一个为空就要后台审核
			shopInfo.setStatus(ShopInfo.STATUS1);
		}
		//保存商铺图片
		String filePath = PropertiesConfig.getString(PropertyKey.FILE_LOCAL_PATH);
		String modulePath = handleModulePath("", shopInfo.getAppId());
		if (!filePath.endsWith(File.separator)) {
			filePath += File.separator;
		}
		File destFile = new File(filePath);
		destFile.mkdirs();
		
		ShopImage shopImage = null;
		List<ShopImage> imgList = Lists.newArrayList();
		String[] imgs = images.split(","),
				des = (descriptions == null ? "" : descriptions).split(",", imgs.length);
		int len = imgs.length;
		int w = 226, h = 226;	//切图基准：{多图: 226x226}
		StringBuilder content = new StringBuilder();
		String imageUrl = "";
		for(int i = 0; i < len; i++){
			shopImage = new ShopImage();
			shopImage.setAppId(shopInfo.getAppId());
			shopImage.setCreateTime(now);
			shopImage.setCreator(userName);
			shopImage.setCreatorId(userAccount.getId());
			shopImage.setUpdateTime(now);
			shopImage.setUpdator(userName);
			shopImage.setUpdatorId(userAccount.getId());
			shopImage.setShopId(shopInfo.getId());
			shopImage.setSortNo(Integer.MAX_VALUE);
			shopImage.setStatus(ShopImage.STATUS3);
			shopImage.setDescription(des[i]);
			imageUrl = getImageUrl(imgs[i], w, h, filePath, modulePath);
			shopImage.setUrl(imageUrl);//轮播图
			imgList.add(shopImage);
			if(i == 0){//封面图
				shopInfo.setCoverImg(imageUrl);
			}
			content.append("<p><img src=\""+imageUrl+"\"></img>").append("</p><p>").append(des[i]).append("</p>");//组装详情图片
		}
		shopInfo.setContent(content.toString());
		save(shopInfo);//开始保存商铺信息
		List<ShopImage> shopImageList = Lists.newArrayList();
		for(ShopImage img : imgList){
			img.setShopId(shopInfo.getId());
			shopImageList.add(img);
		}
		shopImageDao.saves(shopImageList);
		return shopInfo.getStatus();
	}
	
	/**
	 * Title:生成图片
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年10月24日
	 * @param str
	 * @param w
	 * @param h
	 * @param filePath
	 * @param modulePath
	 * @return
	 */
	private String getImageUrl(String str, int w, int h, String filePath, String modulePath){
		String fileName = StringUtil.getUUID() + ".jpg";
        File file = Base64Util.decodeBase64(str.replaceAll("\\s*|\t|\r|\n", ""), modulePath + fileName, filePath);
		//切图 begin
        String cutFilePath = ImageUtil.appendSuffixBySize(w, h, file.getPath());
        File cutFile = new File(cutFilePath);
        ImageUtil.cutForceSize(w, h, file, cutFile);
        //切图 end
        String url = PropertiesConfig.getString(PropertyKey.FILE_URL_PATH);
        if (!url.endsWith(File.separator)) {
        	url += File.separator;
		}
        String fIleUrl = cutFilePath.replace(filePath, url);
		return fIleUrl.replace("\\", "/");
	}
	
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年10月24日
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

	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public void audit(Long[] shopInfoIds, Byte status, String content) {
		Map<String, Object> map = Maps.newHashMap();
		List<Long> ids = Arrays.asList(shopInfoIds);
		map.put("IN_id", ids);
		map.put("EQ_sourceType", ShopInfo.SOURCETYPE2);
		List<Byte> tmp = Lists.newArrayList();
		tmp.add(ShopInfo.STATUS_1);
		tmp.add(ShopInfo.STATUS1);
		map.put("IN_status", tmp);//只有审核未通过和待审核状态的数据才能审核
		List<ShopInfo> shopInfoList = query(map, null);
		ids = Lists.newArrayList();
		for(ShopInfo shopInfo : shopInfoList){
			if(ShopInfo.STATUS3.equals(status) && (StringUtils.isBlank(shopInfo.getLat()) || StringUtils.isBlank(shopInfo.getLat()))){
				throw new BusinessException(ErrorCodes.FAILURE, "店铺坐标不存在，请前往修改");
			}
			ids.add(shopInfo.getId());
		}
		 this.getEntityDao().audit(status, content, ids);
	}

	@Override
	public List<ShopInfo> getRecommendIndex(Long appId) {
		return recommendInfoDao.getRecommendIndex(appId);
	}

	/**
     * 分页查询商商情信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     */
    @Override
    public PageInfo<ShopInfoListDto> queryByPage(PageInfo<ShopInfoListDto> pageInfo, Map<String, Object> map,
            Map<String, Boolean> orderMap) {
        return this.getEntityDao().queryByPage(pageInfo, map, orderMap);
    }
    
    /**
     * 获取推荐到首页的商情
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月13日下午3:34:53
     */
    @Override
    public List<ShopInfoDto> getShopRecommendIndex(Long appId){
        return recommendInfoDao.getShopRecommendIndex(appId);
    }
    
}