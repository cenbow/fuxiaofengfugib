package com.cqliving.cloud.online.wz.manager.impl;

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
import com.cqliving.cloud.common.constant.ClientControlType;
import com.cqliving.cloud.common.constant.PropertyKey;
import com.cqliving.cloud.online.account.dao.UserInfoDao;
import com.cqliving.cloud.online.account.dao.UserInfoReplyDao;
import com.cqliving.cloud.online.account.domain.UserAccount;
import com.cqliving.cloud.online.account.domain.UserInfo;
import com.cqliving.cloud.online.account.domain.UserInfoReply;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.manager.UserAccountManager;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.app.dao.AppInfoDao;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.WzCollectInfoData;
import com.cqliving.cloud.online.interfacc.dto.WzQuestionDetailResult;
import com.cqliving.cloud.online.interfacc.dto.WzQuestionResult;
import com.cqliving.cloud.online.wz.dao.WzQuestionDao;
import com.cqliving.cloud.online.wz.domain.WzAppAuthority;
import com.cqliving.cloud.online.wz.domain.WzAuthority;
import com.cqliving.cloud.online.wz.domain.WzCollectInfo;
import com.cqliving.cloud.online.wz.domain.WzQuestion;
import com.cqliving.cloud.online.wz.domain.WzQuestionCollectInfo;
import com.cqliving.cloud.online.wz.domain.WzQuestionImage;
import com.cqliving.cloud.online.wz.dto.WzEventProgress;
import com.cqliving.cloud.online.wz.dto.WzQuestionData;
import com.cqliving.cloud.online.wz.dto.WzQuestionDto;
import com.cqliving.cloud.online.wz.dto.WzQuestionExcelDownload;
import com.cqliving.cloud.online.wz.manager.WzAppAuthorityManager;
import com.cqliving.cloud.online.wz.manager.WzAuthorityManager;
import com.cqliving.cloud.online.wz.manager.WzCollectInfoManager;
import com.cqliving.cloud.online.wz.manager.WzQuestionCollectInfoManager;
import com.cqliving.cloud.online.wz.manager.WzQuestionImageManager;
import com.cqliving.cloud.online.wz.manager.WzQuestionManager;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.dao.support.ScrollPageOrder;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.StringUtil;
import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.common.util.encrypt.Base64Util;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("wzQuestionManager")
public class WzQuestionManagerImpl extends EntityServiceImpl<WzQuestion, WzQuestionDao> implements WzQuestionManager {

    @Autowired
    private UserSessionManager userSessionManager;
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private UserInfoReplyDao userInfoReplyDao;
    @Autowired
    private WzQuestionImageManager wzQuestionImageManager;
    @Autowired
    private WzAuthorityManager wzAuthorityManager;
    @Autowired
    private WzAppAuthorityManager wzAppAuthorityManager;
    @Autowired
    private WzQuestionCollectInfoManager wzQuestionCollectInfoManager;
    @Autowired
    private WzCollectInfoManager wzCollectInfoManager;
    @Autowired
    private AppInfoDao appInfoDao;
    @Autowired
    private UserAccountManager userAccountManager;

    @Override
    public ScrollPage<WzQuestionDto> queryScrollPage(Map<String, Object> conditions, ScrollPage<WzQuestionDto> page) {
        return getEntityDao().queryDtoForScrollPage(page, conditions);
    }

    @Override
    public List<WzQuestionDto> getMyQuestionByPage(Long appId, String sessionId, String token, String lastId, String query) {
        UserSession userSession = userSessionManager.get(sessionId, token);

        ScrollPage<WzQuestionDto> page = new ScrollPage<>();
        page.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastId));
        page.setPageSize(10);

        Map<String, Object> conditions = Maps.newHashMap();
        conditions.put("EQ_appId", appId);
        conditions.put("LIKE_title", query);
        conditions.put("EQ_creatorId", userSession.getUserId());
        conditions.put("NOTEQ_status", WzQuestion.STATUS99);// 删除的不查询

        page = getEntityDao().queryDtoForScrollPage(page, conditions);
        return page.getPageResults();
    }

    @Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    public WzQuestionDto getQuestionDetails(Long questionId, boolean isGetTransfer) {
        WzQuestion wzQuestion = getEntityDao().findOne(questionId);
        if(wzQuestion == null || WzQuestion.STATUS88.equals(wzQuestion.getStatus()) || WzQuestion.STATUS99.equals(wzQuestion.getStatus())){
        	throw new BusinessException(-2, "数据已经删除或不存在。");
        }
        //浏览量增加1
        wzQuestion.setViewCount(wzQuestion.getViewCount() + 1);
        this.getEntityDao().update(wzQuestion);
        
        WzQuestionDto dto = new WzQuestionDto();
        dto.setId(wzQuestion.getId());
        dto.setAppId(wzQuestion.getAppId());
        if(wzQuestion.getStatus() > 0){
        	dto.setAuditingDepartment(wzQuestion.getAuditingDepartment());
            dto.setAuditingTime(wzQuestion.getAuditingTime());
        }
        if(WzQuestion.STATUS7.equals(wzQuestion.getStatus())){//已经发布才显示回复内容
        	dto.setReplyTime(wzQuestion.getReplyTime());
            dto.setReplyContent(wzQuestion.getReplyContent());
        }
        dto.setCreator(wzQuestion.getCreator());
        dto.setContent(wzQuestion.getContent());
        dto.setCreateTime(wzQuestion.getCreateTime());
        dto.setViewCount(wzQuestion.getViewCount());
        dto.setReplyCount(wzQuestion.getReplyCount());
        dto.setCreator(wzQuestion.getCreator());
        dto.setTitle(wzQuestion.getTitle());
        dto.setType(wzQuestion.getType());
        dto.setRegionCode(wzQuestion.getRegionCode());
        dto.setRegionName(wzQuestion.getRegionName());

        // 查询图片
        dto.setImages(wzQuestionImageManager.getByQuestion(questionId));
        
        // 获得事件进度
        if (isGetTransfer) {
            List<WzEventProgress> list = Lists.newArrayList();
            WzEventProgress progress = null;
            // 已受理
            if (wzQuestion.getStatus() > WzQuestion.STATUS2 && !wzQuestion.getStatus().equals(99)) {
            	progress = new WzEventProgress();
                progress.setQuestionId(wzQuestion.getId());
                progress.setStatus(WzEventProgress.STATUS1);
                progress.setDateTime(wzQuestion.getAuditingTime());
                list.add(progress);
            }
            // 已转交相关部门
            if (wzQuestion.getStatus() > WzQuestion.STATUS3 && !wzQuestion.getStatus().equals(99)) {
            	progress = new WzEventProgress();
                progress.setQuestionId(wzQuestion.getId());
                progress.setStatus(WzEventProgress.STATUS2);
                progress.setDateTime(wzQuestion.getTransferTime());
                list.add(progress);
            }
            // 已回复
            if (wzQuestion.getStatus().equals(WzQuestion.STATUS7)) {
            	progress = new WzEventProgress();
                progress.setQuestionId(wzQuestion.getId());
                progress.setStatus(WzEventProgress.STATUS3);
                progress.setDateTime(wzQuestion.getReplyTime());
                list.add(progress);
            }
            dto.setEventProgress(list);
        }
        //获得收集内容
		List<WzQuestionCollectInfo> collentList = wzQuestionCollectInfoManager.getInfoByCollect(wzQuestion.getId(), null);
		dto.setCollectInfoList(collentList);
        return dto;
    }

    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    public WzQuestionResult getDetails(Long questionId) {
    	WzQuestion wzQuestion = getEntityDao().findOne(questionId);
    	if(wzQuestion == null || WzQuestion.STATUS88.equals(wzQuestion.getStatus()) || WzQuestion.STATUS99.equals(wzQuestion.getStatus())){
    		throw new BusinessException(-2, "数据已经删除或不存在。");
    	}
    	//浏览量增加1
    	wzQuestion.setViewCount(wzQuestion.getViewCount() + 1);
    	this.getEntityDao().update(wzQuestion);
    	
    	WzQuestionResult result = new WzQuestionResult();
    	result.setQuestionId(wzQuestion.getId());
    	result.setTitle(wzQuestion.getTitle());
    	result.setContent(wzQuestion.getContent());
    	result.setCreator(wzQuestion.getCreator());
    	if(wzQuestion.getStatus() > 0){
    		result.setAuditingTime(DateUtil.format(wzQuestion.getAuditingTime(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM));
    		result.setAuditingDepartment(wzQuestion.getAuditingDepartment());
    	}
    	if(WzQuestion.STATUS7.equals(wzQuestion.getStatus())){//已经发布才显示回复内容
    		result.setReplyTime(DateUtil.format(wzQuestion.getReplyTime(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM));
    		result.setReplyContent(wzQuestion.getReplyContent());
    	}
    	// 查询图片
    	List<WzQuestionImage> imageList = wzQuestionImageManager.getByQuestion(questionId);
    	String imageStr = "";
    	if(imageList != null && imageList.size() > 0){
    		for(WzQuestionImage img : imageList){
    			if(!"".equals(imageStr)){
    				imageStr += ",";
    			}
    			imageStr += img.getImageUrl();
    		}
    	}
    	result.setImages(imageStr);
    	// 获得事件进度
    	String eventStr = "";
    	// 已受理
    	if (WzQuestion.STATUS2 < wzQuestion.getStatus()  && !WzQuestion.STATUS99.equals(wzQuestion.getStatus())) {
    		if(!"".equals(eventStr)){
    			eventStr += ",";
    		}
    		eventStr += WzEventProgress.STATUS1 + "_" + DateUtil.format(wzQuestion.getAuditingTime(), DateUtil.FORMAT_YYYY_MM_DD);
    	}
    	// 已转交相关部门
		if (wzQuestion.getStatus() > WzQuestion.STATUS3 && !WzQuestion.STATUS99.equals(wzQuestion.getStatus())) {
			if(!"".equals(eventStr)){
    			eventStr += ",";
    		}
    		eventStr += WzEventProgress.STATUS2 + "_" + DateUtil.format(wzQuestion.getTransferTime(), DateUtil.FORMAT_YYYY_MM_DD);
		}
		// 已回复
		if (WzQuestion.STATUS7.equals(wzQuestion.getStatus())) {
			if(!"".equals(eventStr)){
    			eventStr += ",";
    		}
    		eventStr += WzEventProgress.STATUS3 + "_" + DateUtil.format(wzQuestion.getReplyTime(), DateUtil.FORMAT_YYYY_MM_DD);
		}
		result.setEventProgress(eventStr);
		return result;
    }
    
    @Override
    public WzQuestionDetailResult getDetailsUseModify(Long questionId) {
    	WzQuestion wzQuestion = getEntityDao().findOne(questionId);
    	if(wzQuestion == null || WzQuestion.STATUS88.equals(wzQuestion.getStatus()) || WzQuestion.STATUS99.equals(wzQuestion.getStatus())){
    		throw new BusinessException(-2, "数据已经删除或不存在。");
    	}
    	
    	WzQuestionDetailResult result = new WzQuestionDetailResult();
    	result.setQuestionId(wzQuestion.getId());
    	result.setTitle(wzQuestion.getTitle());
    	result.setContent(wzQuestion.getContent());
    	// 查询图片
    	List<WzQuestionImage> imageList = wzQuestionImageManager.getByQuestion(questionId);
    	String imageStr = "";
    	if(imageList != null && imageList.size() > 0){
    		for(WzQuestionImage img : imageList){
    			if(!"".equals(imageStr)){
    				imageStr += ",";
    			}
    			imageStr += img.getImageUrl();
    		}
    	}
    	result.setImages(imageStr);
    	
    	result.setType(wzQuestion.getType());
    	result.setTypeStr(WzQuestion.allTypes.get(wzQuestion.getType()));
    	result.setRegionCode(wzQuestion.getRegionCode());
    	result.setRegionName(wzQuestion.getRegionName());
    	//获得收集内容
    	List<WzQuestionCollectInfo> collList = wzQuestionCollectInfoManager.getInfoByCollect(wzQuestion.getId(), null);
    	//获取收集信息
    	WzAuthority wzAuthority = wzAuthorityManager.getByNameOne(WzAuthority.NAME_IS_COLLECT_USER_INFO);
    	WzAppAuthority wzAppAuthority = null;
    	if(wzAuthority != null){
    		wzAppAuthority = wzAppAuthorityManager.getByAuthorityId(wzQuestion.getAppId(), wzAuthority.getId());
    	}
    	List<WzCollectInfo> collectList = null;
    	if(wzAppAuthority != null && WzAppAuthority.VALUE1.equals(wzAppAuthority.getValue())){
    		collectList = wzCollectInfoManager.getByAppAuthority(wzQuestion.getAppId(), wzAppAuthority.getId());
    	}
    	List<WzCollectInfoData> cList = Lists.newArrayList();
    	if(collectList != null && collectList.size() > 0){
    		WzCollectInfoData wzCollectInfoData = null;
    		for(WzCollectInfo wzCollectInfo : collectList){
    			wzCollectInfoData = new WzCollectInfoData();
    			wzCollectInfoData.setId(wzCollectInfo.getId());
    			wzCollectInfoData.setName(wzCollectInfo.getName());
    			for(WzQuestionCollectInfo wzQuestionCollectInfo : collList){
    				if(wzCollectInfo.getId().equals(wzQuestionCollectInfo.getCollectInfoId())){
    					wzCollectInfoData.setValue(wzQuestionCollectInfo.getValue());
    					break;
    				}
    			}
    			cList.add(wzCollectInfoData);
    		}
    	}
    	result.setCollectInfo(cList);
    	return result;
    }

    @Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    public Integer saveOrModify(Long appId, String sessionId, String token, WzQuestion params, String[] photos, Long[] collectId, String[] collectValue) {
        // 查询配置 问政是否有审核，isCheck=true有审核
        boolean isCheck = false;
        WzAuthority authority = wzAuthorityManager.getByNameOne(WzAuthority.NAME_QUESTION_IS_CHECK);
        if (authority != null) {
            WzAppAuthority appAuthority = wzAppAuthorityManager.getByAuthorityId(appId, authority.getId());
            isCheck = appAuthority != null && appAuthority.getValue().equals("1");
        }
        UserSession userSession = userSessionManager.get(sessionId, token);
        if (userSession == null) {
            throw new BusinessException(ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
        }
        //验证游客是否允许问政
        if(StringUtils.isBlank(userSession.getToken())){
        	authority = wzAuthorityManager.getByNameOne(WzAuthority.NAME_ALLOW_TOURIST_PUBLISH);
        	if(authority != null){
        		WzAppAuthority appAuthority = wzAppAuthorityManager.getByAuthorityId(appId, authority.getId());
        		if(appAuthority == null || WzAppAuthority.VALUE0.equals(appAuthority.getValue())){
        			throw new BusinessException(ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
        		}
        	}
        }
        
        UserInfo userInfo = userInfoDao.get(userSession.getUserId());
        // 判断是新增或修改
        boolean isSave = !(params.getId() != null && params.getId() > 0);
        WzQuestion wzQuestion = null;
        if (isSave) {
            wzQuestion = new WzQuestion();
        } else {
            wzQuestion = getEntityDao().findOne(params.getId());
        }
        // 开始设置值
        Date currentDate = new Date();
        wzQuestion.setAppId(appId);
        wzQuestion.setType(params.getType());
        if (isCheck) {
            wzQuestion.setStatus(WzQuestion.STATUS2);
        } else {
            wzQuestion.setStatus(WzQuestion.STATUS3);
            wzQuestion.setAuditingTime(currentDate);
            //如果是审核通过且设置了自动回复。
            String replyContent = "";
            WzAuthority wzAuthority = wzAuthorityManager.getByNameOne(WzAuthority.NAME_AUTO_REPLY);
            if(wzAuthority != null){
                WzAppAuthority wzAppAuthority = wzAppAuthorityManager.getByAuthorityId(appId, wzAuthority.getId());
                if(wzAppAuthority != null && wzAppAuthority.getValue().equals(WzAppAuthority.VALUE1)){
                    List<WzCollectInfo> list = wzCollectInfoManager.getByAppAuthority(appId, wzAppAuthority.getId());
                    if(list.size() > 0){
                        replyContent = list.get(0).getName();
                    }
                }
            }
            AppInfo appInfo = appInfoDao.get(appId);
            if(StringUtils.isNotBlank(replyContent)){
            	wzQuestion.setReplyContent(replyContent); 
                wzQuestion.setStatus(WzQuestion.STATUS7);
                wzQuestion.setReplyTime(currentDate);
                if(appInfo != null){
                	wzQuestion.setAuditingtor(appInfo.getName() + "问政系统审核");
                	wzQuestion.setAuditingDepartment(appInfo.getName() + "问政系统回复");
                }
            }
            if(appInfo != null){
            	wzQuestion.setAuditingtor(appInfo.getName() + "问政系统审核");
            }
        }
        //保存用户手机号 2016年10月18日 10:01:31新增功能
    	UserAccount userAccount = userAccountManager.get(userSession.getUserId());
    	if(userAccount != null)
    		wzQuestion.setCreatorPhone(userAccount.getTelephone());
    	
        wzQuestion.setRegionCode(params.getRegionCode());
        wzQuestion.setRegionName(params.getRegionName());
        wzQuestion.setTitle(params.getTitle());
        wzQuestion.setContent(params.getContent());
        wzQuestion.setViewCount(0);
        wzQuestion.setReplyCount(0);
        wzQuestion.setPlace(params.getPlace());
        wzQuestion.setLat(params.getLat());
        wzQuestion.setLng(params.getLng());
        wzQuestion.setUpdateTime(currentDate);
        wzQuestion.setUpdatorId(userInfo.getId());
        wzQuestion.setUpdator(userInfo.getName());

        List<WzQuestionImage> imageList = null;
        WzQuestionImage wzQuestionImage = null;
        WzQuestionCollectInfo collectInfo = null;
        List<WzQuestionCollectInfo> collentList = Lists.newArrayList();
        
        //处理收集信息
        int len = collectId != null ? collectId.length : 0;
        if(collectValue != null && collectId != null && collectId.length == collectValue.length){
        	for(int i = 0; i < len; i++){
        		collectInfo = new WzQuestionCollectInfo();
        		collectInfo.setQuestionId(wzQuestion.getId());
        		collectInfo.setCollectInfoId(collectId[i]);
        		collectInfo.setValue(collectValue[i]);
        		collentList.add(collectInfo);
        	}
        }
        if (isSave) {
            wzQuestion.setCreateTime(currentDate);
            wzQuestion.setCreatorId(userInfo.getId());
            wzQuestion.setCreator(userInfo.getName());
            wzQuestion = this.save(wzQuestion);
        } else {
            wzQuestion.setUpdateTime(currentDate);
            wzQuestion.setUpdatorId(userInfo.getId());
            wzQuestion.setUpdator(userInfo.getName());
            this.update(wzQuestion);
            // 查询出之前的图片并删除
            Map<String, Object> map = Maps.newHashMap();
            map.put("EQ_questionId", params.getId());
            Map<String, Boolean> sortMap = Maps.newHashMap();
            sortMap.put("sortNo", true);
            imageList = wzQuestionImageManager.query(map, sortMap);
            for (WzQuestionImage image : imageList) {
                wzQuestionImageManager.remove(image);
            }
            //删除收集对象，后面在插入
            List<WzQuestionCollectInfo> tmp = wzQuestionCollectInfoManager.query(map, null);
            for(WzQuestionCollectInfo info : tmp){
            	wzQuestionCollectInfoManager.remove(info);
            }
        }
        // 图片处理wzQuestionImageManager
        if (photos != null && photos.length > 0 && wzQuestion.getId() > 0) {
            imageList = Lists.newArrayList();
            for (String str : photos) {
                wzQuestionImage = new WzQuestionImage();
                wzQuestionImage.setImageUrl(str);
                wzQuestionImage.setName("");
                wzQuestionImage.setSortNo(0);
                wzQuestionImage.setQuestionId(wzQuestion.getId());
                imageList.add(wzQuestionImage);
            }
            wzQuestionImageManager.saves(imageList);
        }
        //插入收集信息
        if(collentList.size() > 0){
            for(WzQuestionCollectInfo wci : collentList){
                wci.setQuestionId(wzQuestion.getId());
                wzQuestionCollectInfoManager.save(wci);
            }
        }
        return isCheck ? 1 : 0;
    }

    @Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    public void deleteMyQuestion(Long appId, String sessionId, String token, Long questionId) {
        UserSession userSession = userSessionManager.get(sessionId, token);
        if (userSession == null) {
            throw new BusinessException(ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(),
                    ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
        }
        WzQuestion question = getEntityDao().get(questionId);
        if (question != null && !question.getAppId().equals(appId)) {
            throw new BusinessException(ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(),
                    ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc());
        }

        // 已经发布的是否允许删除
        WzAuthority authority = wzAuthorityManager.getByNameOne(WzAuthority.NAME_ALREADY_PUBLISH_ALLOW_DEL);
        if (authority != null) {
            WzAppAuthority appAuthority = wzAppAuthorityManager.getByAuthorityId(appId, authority.getId());
            if (appAuthority != null && appAuthority.getValue().equals("0")
                    && question.getStatus().equals(WzQuestion.STATUS7)) {
                throw new BusinessException(-2, "问政已经发布，无法删除");
            }
        }
        question.setStatus(WzQuestion.STATUS99);
        getEntityDao().update(question);
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
    @Transactional(value="transactionManager")
	public void delReply(Long replyId) {
		UserInfoReply userInfoReply = userInfoReplyDao.get(replyId);
		//减少问政评论数
		this.getEntityDao().decreaseReplyCount(userInfoReply.getSourceId());
		//删除评论
		List<Long> listId = Lists.newArrayList();
		listId.add(replyId);
		userInfoReplyDao.updateStatus(UserInfoReply.STATUS99, listId);
	}

	@Override
	public List<WzCollectInfo> addData(Long appId, String sessionId, String token) {
		UserSession userSession = userSessionManager.get(sessionId, token);
		WzAuthority wzAuthority = null;
		WzAppAuthority wzAppAuthority = null;
		if(userSession == null){
			userAccountManager.createTourist(appId, sessionId);
			userSession = userSessionManager.getTourist(sessionId);
		}
		if(StringUtils.isBlank(userSession.getToken())){
			//是否允许游客问政
			wzAuthority = wzAuthorityManager.getByNameOne(WzAuthority.NAME_ALLOW_TOURIST_PUBLISH);
			if(wzAuthority != null){
				wzAppAuthority = wzAppAuthorityManager.getByAuthorityId(appId, wzAuthority.getId());
			}
			if(wzAppAuthority != null && WzAppAuthority.VALUE0.equals(wzAppAuthority.getValue())){//游客不允许问政
				throw new BusinessException(ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
			}
		}
		//获取收集信息
		wzAuthority = wzAuthorityManager.getByNameOne(WzAuthority.NAME_IS_COLLECT_USER_INFO);
		if(wzAuthority != null){
			wzAppAuthority = wzAppAuthorityManager.getByAuthorityId(appId, wzAuthority.getId());
		}
		List<WzCollectInfo> collectList = null;
		if(wzAppAuthority != null && WzAppAuthority.VALUE1.equals(wzAppAuthority.getValue())){
			collectList = wzCollectInfoManager.getByAppAuthority(appId, wzAppAuthority.getId());
		}
		return collectList;
	}

	@Override
	public CommonListResult<WzQuestionData> getPageByWzQuestion(Map<String, Object> conditions, ScrollPage<WzQuestionDto> page) {
	    ScrollPage<WzQuestionDto> scrollPage = getEntityDao().queryDtoForScrollPage(page, conditions);
	    List<WzQuestionDto> list = scrollPage.getPageResults();
	    List<WzQuestionData> resultList = Lists.newArrayList();
	    WzQuestionData wzQuestionData = null;
        String webPath = PropertiesConfig.getString(PropertyKey.WEB_URL_PATH);
        if(CollectionUtils.isNotEmpty(list)){
        	for(WzQuestionDto dto : list){
    	        wzQuestionData = new WzQuestionData();
    	        wzQuestionData.setId(dto.getId());
    	        wzQuestionData.setReplyCount(dto.getReplyCount() == null || dto.getReplyCount() < 1 ? 0 : dto.getReplyCount());
    	        wzQuestionData.setViewCount(dto.getViewCount() == null || dto.getViewCount() < 1 ? 0 : dto.getViewCount());
    	        wzQuestionData.setCreateTime(DateUtil.format(dto.getCreateTime(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM));
    	        wzQuestionData.setTitle(dto.getTitle());
    	        
    	        if(WzQuestion.allStatussFront.containsKey(dto.getStatus())){
    	        	wzQuestionData.setStatus(WzQuestion.allStatussFront.get(dto.getStatus()));
    	        }else{
    	        	wzQuestionData.setStatus("未知");
    	        }
    	        wzQuestionData.setSourceType(BusinessType.SOURCE_TYPE_2);
    	        wzQuestionData.setDetailViewType(ClientControlType.DETAIL_VIEW_TYPE_2);
				wzQuestionData.setUrl(webPath + "/wenzheng/view/" + dto.getId() + ".html");
				wzQuestionData.setShareUrl(webPath + "/wenzheng/share/" + dto.getId() + ".html");

    	        resultList.add(wzQuestionData);
    	    }
        }
	    CommonListResult<WzQuestionData> res = new CommonListResult<WzQuestionData>();
	    res.setDataList(resultList);
	    return res;
	}
	
	@Override
	public CommonListResult<WzQuestionData> getPageByWzQuestionNew(Map<String, Object> conditions, ScrollPage<WzQuestionDto> page) {
		ScrollPage<WzQuestionDto> scrollPage = getEntityDao().queryDtoForScrollPage(page, conditions);
		List<WzQuestionDto> list = scrollPage.getPageResults();
		List<WzQuestionData> resultList = Lists.newArrayList();
		WzQuestionData wzQuestionData = null;
		String webPath = PropertiesConfig.getString(PropertyKey.WEB_URL_PATH);
		if(CollectionUtils.isNotEmpty(list)){
			for(WzQuestionDto dto : list){
				wzQuestionData = new WzQuestionData();
				wzQuestionData.setId(dto.getId());
				wzQuestionData.setReplyCount(dto.getReplyCount() == null || dto.getReplyCount() < 1 ? 0 : dto.getReplyCount());
				wzQuestionData.setViewCount(dto.getViewCount() == null || dto.getViewCount() < 1 ? 0 : dto.getViewCount());
				wzQuestionData.setCreateTime(DateUtil.format(dto.getCreateTime(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM));
				wzQuestionData.setTitle(dto.getTitle());
				
				if(WzQuestion.allStatussFront.containsKey(dto.getStatus())){
					wzQuestionData.setStatus(WzQuestion.allStatussFront.get(dto.getStatus()));
				}else{
					wzQuestionData.setStatus("未知");
				}
				wzQuestionData.setSourceType(BusinessType.SOURCE_TYPE_2);
				wzQuestionData.setDetailViewType(ClientControlType.DETAIL_VIEW_TYPE_2);
				wzQuestionData.setUrl(webPath + "/wenzheng/view/" + dto.getId() + ".html");
				wzQuestionData.setShareUrl(webPath + "/wenzheng/share/" + dto.getId() + ".html");
				
				resultList.add(wzQuestionData);
			}
		}
		CommonListResult<WzQuestionData> res = new CommonListResult<WzQuestionData>();
		res.setDataList(resultList);
		return res;
	}

	@Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public Integer saveI(Long appId, String sessionId, String token, WzQuestion params, String photos, String collectIds, String collectValues) {
        // 查询配置 问政是否有审核，isCheck=true有审核
        boolean isCheck = false;
        WzAuthority authority = wzAuthorityManager.getByNameOne(WzAuthority.NAME_QUESTION_IS_CHECK);
        if (authority != null) {
            WzAppAuthority appAuthority = wzAppAuthorityManager.getByAuthorityId(appId, authority.getId());
            isCheck = appAuthority != null && appAuthority.getValue().equals("1");
        }
        UserSession userSession = userSessionManager.get(sessionId, token);
        if (userSession == null) {
            throw new BusinessException(ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
        }
        //验证游客是否允许问政
        if(StringUtils.isBlank(userSession.getToken())){
        	authority = wzAuthorityManager.getByNameOne(WzAuthority.NAME_ALLOW_TOURIST_PUBLISH);
        	if(authority != null){
        		WzAppAuthority appAuthority = wzAppAuthorityManager.getByAuthorityId(appId, authority.getId());
        		if(appAuthority == null || WzAppAuthority.VALUE0.equals(appAuthority.getValue())){
        			throw new BusinessException(ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
        		}
        	}
        }
        
        UserInfo userInfo = userInfoDao.get(userSession.getUserId());
        // 判断是新增或修改
        boolean isSave = !(params.getId() != null && params.getId() > 0);
        WzQuestion wzQuestion = null;
        if (isSave) {
            wzQuestion = new WzQuestion();
        } else {
            wzQuestion = getEntityDao().findOne(params.getId());
        }
        // 开始设置值
        Date currentDate = new Date();
        wzQuestion.setAppId(appId);
        wzQuestion.setType(params.getType());
        if (isCheck) {
            wzQuestion.setStatus(WzQuestion.STATUS2);
        } else {
            wzQuestion.setStatus(WzQuestion.STATUS3);
            wzQuestion.setAuditingTime(currentDate);
            //如果是审核通过且设置了自动回复。
            String replyContent = "";
            WzAuthority wzAuthority = wzAuthorityManager.getByNameOne(WzAuthority.NAME_AUTO_REPLY);
            if(wzAuthority != null){
                WzAppAuthority wzAppAuthority = wzAppAuthorityManager.getByAuthorityId(appId, wzAuthority.getId());
                if(wzAppAuthority != null && wzAppAuthority.getValue().equals(WzAppAuthority.VALUE1)){
                    List<WzCollectInfo> list = wzCollectInfoManager.getByAppAuthority(appId, wzAppAuthority.getId());
                    if(list.size() > 0){
                        replyContent = list.get(0).getName();
                    }
                }
            }
            AppInfo appInfo = appInfoDao.get(appId);
            if(StringUtils.isNotBlank(replyContent)){
            	wzQuestion.setReplyContent(replyContent); 
                wzQuestion.setStatus(WzQuestion.STATUS7);
                wzQuestion.setReplyTime(currentDate);
                if(appInfo != null){
                	wzQuestion.setAuditingtor(appInfo.getName() + "问政系统审核");
                	wzQuestion.setAuditingDepartment(appInfo.getName() + "问政系统回复");
                }
            }
            if(appInfo != null){
            	wzQuestion.setAuditingtor(appInfo.getName() + "问政系统审核");
            }
        }
        //保存用户手机号 2016年10月18日 10:01:31新增功能
    	UserAccount userAccount = userAccountManager.get(userSession.getUserId());
    	wzQuestion.setCreatorPhone(userAccount.getTelephone());
        wzQuestion.setRegionCode(params.getRegionCode());
        wzQuestion.setRegionName(params.getRegionName());
        wzQuestion.setTitle(params.getTitle());
        wzQuestion.setContent(params.getContent());
        wzQuestion.setViewCount(0);
        wzQuestion.setReplyCount(0);
        wzQuestion.setPlace(params.getPlace());
        wzQuestion.setLat(params.getLat());
        wzQuestion.setLng(params.getLng());
        wzQuestion.setUpdateTime(currentDate);
        wzQuestion.setUpdatorId(userInfo.getId());
        wzQuestion.setUpdator(userInfo.getName());

        List<WzQuestionImage> imageList = null;
        WzQuestionImage wzQuestionImage = null;
        WzQuestionCollectInfo collectInfo = null;
        List<WzQuestionCollectInfo> collentList = Lists.newArrayList();
        
        //处理收集信息
        if(StringUtils.isNotBlank(collectIds) && StringUtils.isNotBlank(collectValues)){
        	String[] cIds = collectIds.split(","),
        			cValues = collectValues.split(",", collectValues.length());
        	int len = cIds != null ? cIds.length : 0;
            if(cValues != null && cIds != null && len <= cValues.length){
            	for(int i = 0; i < len; i++){
            		try {
            			collectInfo = new WzQuestionCollectInfo();
                		collectInfo.setQuestionId(wzQuestion.getId());
                		collectInfo.setCollectInfoId(Long.parseLong(cIds[i]));
                		collectInfo.setValue(cValues[i]);
                		collentList.add(collectInfo);
					} catch (NumberFormatException e) {
						continue;
					}
            	}
            }
        }
        if (isSave) {
            wzQuestion.setCreateTime(currentDate);
            wzQuestion.setCreatorId(userInfo.getId());
            wzQuestion.setCreator(userInfo.getName());
            wzQuestion = this.save(wzQuestion);
        } else {
            wzQuestion.setUpdateTime(currentDate);
            wzQuestion.setUpdatorId(userInfo.getId());
            wzQuestion.setUpdator(userInfo.getName());
            this.update(wzQuestion);
            // 查询出之前的图片并删除
            Map<String, Object> map = Maps.newHashMap();
            map.put("EQ_questionId", params.getId());
            Map<String, Boolean> sortMap = Maps.newHashMap();
            sortMap.put("sortNo", true);
            imageList = wzQuestionImageManager.query(map, sortMap);
            for (WzQuestionImage image : imageList) {
                wzQuestionImageManager.remove(image);
            }
            //删除收集对象，后面在插入
            List<WzQuestionCollectInfo> tmp = wzQuestionCollectInfoManager.query(map, null);
            for(WzQuestionCollectInfo info : tmp){
            	wzQuestionCollectInfoManager.remove(info);
            }
        }
        // 图片处理wzQuestionImageManager
        if(StringUtils.isNotBlank(photos)){
        	String[] imgs = photos.split(",");
        	
        	String filePath = PropertiesConfig.getString(PropertyKey.FILE_LOCAL_PATH);
    		String modulePath = handleModulePath("", appId);
    		if (!filePath.endsWith(File.separator)) {
    			filePath += File.separator;
    		}
    		File destFile = new File(filePath);
    		destFile.mkdirs();
        	
        	imageList = Lists.newArrayList();
            for (String img : imgs) {
                wzQuestionImage = new WzQuestionImage();
                wzQuestionImage.setName("");
                wzQuestionImage.setSortNo(0);
                wzQuestionImage.setQuestionId(wzQuestion.getId());
                
                String fileName = StringUtil.getUUID() + ".jpg";
    	        File file = Base64Util.decodeBase64(img.replaceAll("\\s*|\t|\r|\n", ""), modulePath + fileName, filePath);
    	        //不切图
    	        String url = PropertiesConfig.getString(PropertyKey.FILE_URL_PATH);
    	        if (!url.endsWith(File.separator)) {
    	        	url += File.separator;
    			}
    	        String cutFilePath = file.getPath();
    	        String fIleUrl = cutFilePath.replace(filePath, url);
    	        wzQuestionImage.setImageUrl(fIleUrl.replace("\\", "/"));
                imageList.add(wzQuestionImage);
            }
            wzQuestionImageManager.saves(imageList);
        }
        //插入收集信息
        if(collentList.size() > 0){
            for(WzQuestionCollectInfo wci : collentList){
                wci.setQuestionId(wzQuestion.getId());
                wzQuestionCollectInfoManager.save(wci);
            }
        }
        return isCheck ? 1 : 0;
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

	@Override
	public List<WzQuestionExcelDownload> excelDownload(Map<String, Object> searchMap, Map<String, Boolean> sortMap) {
		return this.getEntityDao().excelDownload(searchMap, sortMap);
	}
}
