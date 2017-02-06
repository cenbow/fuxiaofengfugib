package com.cqliving.cloud.online.order.manager.impl;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
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
import com.cqliving.cloud.online.config.domain.CommentConfig;
import com.cqliving.cloud.online.config.manager.CommentAppConfigManager;
import com.cqliving.cloud.online.order.dao.OrderEvaluateDao;
import com.cqliving.cloud.online.order.dao.OrderInfoDao;
import com.cqliving.cloud.online.order.domain.OrderEvaluate;
import com.cqliving.cloud.online.order.domain.OrderInfo;
import com.cqliving.cloud.online.order.dto.OrderEvaluateDto;
import com.cqliving.cloud.online.order.manager.OrderEvaluateManager;
import com.cqliving.cloud.online.shopping.dao.ShoppingGoodsDao;
import com.cqliving.cloud.online.shopping.domain.ShoppingGoods;
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

@Service("orderEvaluateManager")
public class OrderEvaluateManagerImpl extends EntityServiceImpl<OrderEvaluate, OrderEvaluateDao> implements OrderEvaluateManager {
    
	@Autowired
	private AppInfoDao appInfoDao;
	@Autowired
	private CommentAppConfigManager commentAppConfigManager;
	@Autowired
	private OrderInfoDao orderInfoDao;
    @Autowired
    private ShoppingGoodsDao shoppingGoodsDao;
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private UserSessionManager userSessionManager;
    
    /**
     * 逻辑删除
     * @param id
     * @return
     */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(OrderEvaluate.STATUS99, idList);
	}
	
	/**
     * 修改状态
     * @param status 状态
     * @param ids ID
     * @return
     */
	@Override
	@Transactional(value="transactionManager")
	public int updateStatus(Byte status,Long... ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(status, idList);
	}

	/**
     * 分页查询评论信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月25日上午11:37:43
     */
    @Override
    public PageInfo<OrderEvaluateDto> queryEvaluateForPage(PageInfo<OrderEvaluateDto> pageInfo, Map<String, Object> map,
            Map<String, Boolean> orderMap) {
        return this.getEntityDao().queryEvaluateForPage(pageInfo, map, orderMap);
    }

    /**
     * 通过Id查询评论信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月25日上午11:40:42
     */
    @Override
    public OrderEvaluateDto getById(Long id) {
        return this.getEntityDao().getById(id);
    }
    
    /**
     * 审核
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月29日下午2:24:14
     */
    @Override
    @Transactional(value="transactionManager")
    public void auditing(Long[] ids, Long[] goodsIds, Byte status, String auditingContent) {
        if(ids.length>0&&null!=status){
            auditingContent = StringUtils.isBlank(auditingContent)?"":auditingContent;
            //修改状态
            List<Long> idList = Arrays.asList(ids);
            this.getEntityDao().auditing(status, idList,auditingContent);
            //修改评论数
            if(OrderEvaluate.STATUS3.equals(status)){
                for (Long goodId : goodsIds) {
                    shoppingGoodsDao.increasePraiseCount(goodId);
                }
            }
        }
    }

	@Override
	public ScrollPage<OrderEvaluateDto> queryForScrollPage(ScrollPage<OrderEvaluateDto> scrollPage, Map<String, Object> conditionMap) {
		return getEntityDao().queryForScrollPage(scrollPage, conditionMap);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class, value = "transactionManager")
	public Byte add(Long appId, String sessionId, String token, Long orderId, String goodsIds, String goodsScores, String contents, String imgs) {
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
		//判断是否当前用户的订单
		OrderInfo orderInfo = orderInfoDao.get(orderId);
		if (orderInfo == null ) {
			throw new BusinessException(ErrorCodes.FAILURE, "订单不存在");
		}
		if (!orderInfo.getAppId().equals(appId) || !orderInfo.getUserId().equals(userId)) {
			throw new BusinessException(ErrorCodes.FAILURE, "不能评价其他用户的订单");
		}
		if (OrderInfo.PAYFORSTATUS6.equals(orderInfo.getPayforStatus())) {	//已完成
			throw new BusinessException(ErrorCodes.FAILURE, "已评价");
		}
		
		//查询商城相关配置（是否允许发布，发布是否需要审核）
		Byte canComment = commentAppConfigManager.getConfigValueByName(appId, CommentConfig.CAN_COMMENT, BusinessType.SOURCE_TYPE_13);
		if (CommentConfig.VALUE0.equals(canComment.intValue())) {	//不允许评论
			throw new BusinessException(ErrorCodes.FAILURE, "订单不允许评论");
		}
		Byte needAudit = commentAppConfigManager.getConfigValueByName(appId, CommentConfig.COMMENT_NEED_AUDIT, BusinessType.SOURCE_TYPE_13);
		Byte status = needAudit.equals(CommentConfig.VALUE1) ? OrderEvaluate.STATUS1 : OrderEvaluate.STATUS3;
		
		//保存数据
		String[] goodsIdArray = goodsIds.split(",");
		String[] goodsScoreArray = goodsScores.split(",");
		String[] contentArray = contents.split(",");
		if (goodsIdArray.length != goodsScoreArray.length || goodsIdArray.length != contentArray.length) {
			throw new BusinessException(ErrorCodes.FAILURE, "商品、评分或评价的数量不匹配");
		}
		
		Map<Long, String> imgUrlMap = Maps.newLinkedHashMap();
		Map<Long, String> imgListUrlMap = Maps.newLinkedHashMap();
		if (StringUtils.isNotBlank(imgs)) {
			//保存图片，返回图片路径
			String filePath = PropertiesConfig.getString(PropertyKey.FILE_LOCAL_PATH);
			String modulePath = handleModulePath("", appId);
			if (!filePath.endsWith(File.separator)) {
				filePath += File.separator;
			}
			File destFile = new File(filePath);
			destFile.mkdirs();
			int w, h;	
			String[] imgArray = imgs.split(",");
			for (String img : imgArray) {
				//分离商品ID和图片信息
				Long goodsId = NumberUtils.toLong(img.split("_")[0]);
				img = img.split("_")[1];
				System.out.println(img.length());
				String fileName = StringUtil.getUUID() + ".jpg";
				File file = Base64Util.decodeBase64(img.replaceAll("\\s*|\t|\r|\n", ""), modulePath + fileName, filePath);
				
				String url = PropertiesConfig.getString(PropertyKey.FILE_URL_PATH);
				if (!url.endsWith(File.separator)) {
					url += File.separator;
				}
				//保存原图
//				String fileUrl = file.getPath().replace(filePath, url);
//				String urls = imgUrlMap.get(goodsId);
//				if (StringUtils.isBlank(urls)) {
//					imgUrlMap.put(goodsId, fileUrl.replace("\\", "/"));
//				} else {
//					imgUrlMap.put(goodsId, urls + "," + fileUrl.replace("\\", "/"));
//				}
				//切图(列表图) begin
				w = h = 100;	//切图基准：{100x100}
				String cutFilePath = ImageUtil.appendSuffixBySize(w, h, file.getPath());
				File cutFile = new File(cutFilePath);
				ImageUtil.cutForceSize(w, h, file, cutFile);
				String fIleListUrl = cutFilePath.replace(filePath, url);
				String listUrls = imgListUrlMap.get(goodsId);
				if (StringUtils.isBlank(listUrls)) {
					imgListUrlMap.put(goodsId, fIleListUrl.replace("\\", "/"));
				} else {
					imgListUrlMap.put(goodsId, listUrls + "," + fIleListUrl.replace("\\", "/"));
				}
				//切图(列表图) end
				//切图(详情图) begin
				String fileDetailUrl =  ImageUtil.reQuality(file.getPath(), 100, 1.0f);
				fileDetailUrl = fileDetailUrl.replace(filePath, url);
				String urls = imgUrlMap.get(goodsId);
				if (StringUtils.isBlank(urls)) {
					imgUrlMap.put(goodsId, fileDetailUrl.replace("\\", "/"));
				} else {
					imgUrlMap.put(goodsId, urls + "," + fileDetailUrl.replace("\\", "/"));
				}
				//切图(详情图) end
			}
		}
		
		List<OrderEvaluate> entities = Lists.newArrayList();
		OrderEvaluate obj;
		Date now = DateUtil.now();
		for (int i = 0; i < goodsIdArray.length; i++) {
			Long goodsId = NumberUtils.toLong(goodsIdArray[i]);
			ShoppingGoods goods = shoppingGoodsDao.get(goodsId);
			if (goods != null) {	//递增评论数
				shoppingGoodsDao.increaseReplyCount(goodsId);
				shoppingGoodsDao.update(goods);
			}
			obj = new OrderEvaluate();
			obj.setAppId(appId);
			obj.setContent(contentArray[i]);
			obj.setCreateTime(now);
			obj.setCreator(userInfo.getName());
			obj.setCreatorId(userId);
			obj.setGoodsId(goodsId);
			obj.setGoodsScore((Integer.parseInt(goodsScoreArray[i]) > 5 ? 5 : Integer.parseInt(goodsScoreArray[i])) * 20);
			obj.setImageUrls(StringUtils.defaultString(imgUrlMap.get(NumberUtils.toLong(goodsIdArray[i]))));
			obj.setListImageUrls(StringUtils.defaultString(imgListUrlMap.get(NumberUtils.toLong(goodsIdArray[i]))));
			obj.setOrderId(orderId);
			obj.setStatus(status);
			obj.setUpdateTime(now);
			obj.setUpdator(userInfo.getName());
			obj.setUpdatorId(userId);
			obj.setUserId(userId);
			entities.add(obj);
		}
		getEntityDao().save(entities);
		
		//修改订单状态
		orderInfo.setPayforStatus(OrderInfo.PAYFORSTATUS6);
		orderInfoDao.update(orderInfo);
		return status;
	}
	
	/**
	 * <p>Description: 处理图片保存路径</p>
	 * @author Tangtao on 2016年12月8日
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