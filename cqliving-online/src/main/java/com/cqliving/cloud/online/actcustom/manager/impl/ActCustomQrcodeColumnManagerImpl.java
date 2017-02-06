package com.cqliving.cloud.online.actcustom.manager.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.UserAccount;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.manager.UserAccountManager;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.act.domain.ActQrcode;
import com.cqliving.cloud.online.act.manager.ActQrcodeManager;
import com.cqliving.cloud.online.actcustom.dao.ActCustomQrcodeColumnDao;
import com.cqliving.cloud.online.actcustom.dao.UserActCustomColumnDao;
import com.cqliving.cloud.online.actcustom.dao.UserActCustomSignupDao;
import com.cqliving.cloud.online.actcustom.domain.ActCustomColumn;
import com.cqliving.cloud.online.actcustom.domain.ActCustomQrcodeColumn;
import com.cqliving.cloud.online.actcustom.domain.UserActCustomColumn;
import com.cqliving.cloud.online.actcustom.domain.UserActCustomSignup;
import com.cqliving.cloud.online.actcustom.dto.ActCustomColumnDto;
import com.cqliving.cloud.online.actcustom.dto.ActCustomQrcodeColumnDto;
import com.cqliving.cloud.online.actcustom.manager.ActCustomQrcodeColumnManager;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.PhoneUtil;
import com.cqliving.tool.utils.Regexs;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年12月21日
 */
@Service("actCustomQrcodeColumnManager")
public class ActCustomQrcodeColumnManagerImpl extends EntityServiceImpl<ActCustomQrcodeColumn, ActCustomQrcodeColumnDao> implements ActCustomQrcodeColumnManager {
	
	@Autowired
	private UserSessionManager userSessionManager;
	@Autowired
	private UserAccountManager userAccountManager;
	@Autowired
	private UserActCustomSignupDao userActCustomSignupDao;
	@Autowired
	private UserActCustomColumnDao userActCustomColumnDao;
	@Autowired
	private ActQrcodeManager actQrcodeManager;

	@Override
	public List<ActCustomQrcodeColumnDto> getColumnsByQrcode(String qrcode) {
		return this.getEntityDao().getColumnsByQrcode(qrcode, false);
	}

	@Override
	@Transactional(value="transactionManager")
	public void signSave(String sessionId, String token, String qrcode, String columnIds, String values) {
		UserSession userSession = userSessionManager.getByToken(token);
		if(userSession == null){
			throw new BusinessException(ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
		}
		//判断用户是否已经报名
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_actQrcodeCode", qrcode);
		map.put("EQ_userId", userSession.getUserId());
		map.put("NOTEQ_status", UserActCustomSignup.STATUS99);
		List<UserActCustomSignup> tmpList = userActCustomSignupDao.query(map, null);
		if(tmpList != null && tmpList.size() > 0){
			throw new BusinessException(ErrorCodes.FAILURE, "已经报名");
		}
		UserAccount userAccount = userAccountManager.get(userSession.getUserId());
		Date now = new Date();
		String[] columnId = columnIds.split(","),
				value = values.split(",", columnId.length);
		//验证活动是否有效
		ActQrcode actQrcode  =actQrcodeManager.findByCode(qrcode);
		if(actQrcode == null){
			throw new BusinessException(ErrorCodes.FAILURE, "活动已失效");
		}
		if(actQrcode.getStartTime().after(now)){
			throw new BusinessException(ErrorCodes.FAILURE, "活动还未开始");
		}
		if(actQrcode.getEndTime().before(now)){
			throw new BusinessException(ErrorCodes.FAILURE, "活动已结束");
		}
		//用户参与报名表
		UserActCustomSignup userActCustomSignup = new UserActCustomSignup();
		userActCustomSignup.setActQrcodeCode(qrcode);
		userActCustomSignup.setCreateTime(now);
		userActCustomSignup.setStatus(UserActCustomSignup.STATUS3);
		userActCustomSignup.setToken(token);
		userActCustomSignup.setUserId(userSession.getUserId());
		userActCustomSignup.setUserName(userAccount.getUserName());
		userActCustomSignup = userActCustomSignupDao.save(userActCustomSignup);
		//用户报名活动自定义收集列
		String str;
		Long actCustomColumnId;
		ActCustomQrcodeColumnDto actCustomQrcodeColumnDto;
		UserActCustomColumn userActCustomColumn;
		List<UserActCustomColumn> list = Lists.newArrayList();
		for(int i = 0; i < columnId.length; i ++){
			//验证参数
			try {
				actCustomColumnId = Long.parseLong(columnId[i]);
				actCustomQrcodeColumnDto = this.getEntityDao().getDtoByIdAndCode(actCustomColumnId, qrcode);
				if(actCustomQrcodeColumnDto == null) {
					throw new BusinessException(ErrorCodes.FAILURE, "字段不存在");
				}
			} catch (NumberFormatException e) {
				throw new BusinessException(ErrorCodes.FAILURE, "参数格式不正确");
			}
			str = StringUtils.isBlank(value[i]) ? "" : value[i];
			//必填
			if(ActCustomQrcodeColumn.ISREQUIRED1.equals(actCustomQrcodeColumnDto.getIsRequired()) && StringUtils.isBlank(str)){
				throw new BusinessException(ErrorCodes.FAILURE, "请输入" + actCustomQrcodeColumnDto.getName());
			}
			//长度
			if(actCustomQrcodeColumnDto.getLength() > 0 && actCustomQrcodeColumnDto.getLength() < str.length()){
				throw new BusinessException(ErrorCodes.FAILURE, actCustomQrcodeColumnDto.getName() + "内容太长");
			}
			if(StringUtils.isNotBlank(str)){
				if(ActCustomColumn.TYPE1.equals(actCustomQrcodeColumnDto.getType())){//身份证
					String reg = "^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$";
					if(!Regexs.matcher(reg, str)){
						throw new BusinessException(ErrorCodes.FAILURE, "请输入正确的身份证");
					}
				}else if(ActCustomColumn.TYPE2.equals(actCustomQrcodeColumnDto.getType())){//手机
					if(!PhoneUtil.validatePhone(str)){
						throw new BusinessException(ErrorCodes.FAILURE, "请输入正确的手机号");
					}
				}else if(ActCustomColumn.TYPE3.equals(actCustomQrcodeColumnDto.getType())){//用户名
				}
			}
			userActCustomColumn = new UserActCustomColumn();
			userActCustomColumn.setActCustomColumnId(actCustomColumnId);
			userActCustomColumn.setUserActCustomSignupId(userActCustomSignup.getId());
			userActCustomColumn.setValue(str);
			list.add(userActCustomColumn);
		}
		userActCustomColumnDao.saves(list);
	}

	@Override
	public Map<String, Object> signList(String sessionId, String token, String qrcode) {
		List<ActCustomQrcodeColumnDto> dtoList = this.getEntityDao().getColumnsByQrcode(qrcode, true);
		StringBuilder fields = new StringBuilder();
		StringBuilder tmp = new StringBuilder();
		for(ActCustomQrcodeColumnDto dto : dtoList){
			if(fields.length() > 0){
				fields.append(",");
				tmp.append(",");
			}
			fields.append("GROUP_CONCAT(CASE WHEN c.type="+dto.getType()+" AND d.is_list_view=1 THEN b.`value` ELSE NULL END) AS c" + dto.getType());
			tmp.append("c" + dto.getType());
		}
		
		List<Map<String, Object>> list = this.getEntityDao().getSignList(qrcode, fields);
		Map<String, Object> map = Maps.newHashMap();
		map.put("columns", tmp);
		map.put("columnValues", list);
		map.put("dataSize", list.size());
		return map;
	}

	@Override
	public Map<String, String> isSign(String sessionId, String token, String qrcode) {
		UserSession userSession = userSessionManager.getByToken(token);
		if(userSession == null){
			throw new BusinessException(ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
		}
		//判断用户是否已经报名
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_actQrcodeCode", qrcode);
		map.put("EQ_userId", userSession.getUserId());
		map.put("NOTEQ_status", UserActCustomSignup.STATUS99);
		List<UserActCustomSignup> tmpList = userActCustomSignupDao.query(map, null);
		if(tmpList != null && tmpList.size() > 0){
			//返回报名信息
			Map<String, String> rsMap = Maps.newHashMap();
			List<ActCustomColumnDto> list = this.getEntityDao().getByUserSignInfo(qrcode, userSession.getUserId());
			if(list != null && list.size() > 0){
				for(ActCustomColumnDto dto : list){
					if(ActCustomColumn.TYPE2.equals(dto.getType())){
						rsMap.put("phone", dto.getValue());
					}else if(ActCustomColumn.TYPE3.equals(dto.getType())){
						rsMap.put("userName", dto.getValue());
					}
				}
			}
			return rsMap;
//			throw new BusinessException(ErrorCodes.FAILURE, "已经报名");
		}
		
		Date now = new Date();
		//验证活动是否有效
		ActQrcode actQrcode  =actQrcodeManager.findByCode(qrcode);
		if(actQrcode == null){
			throw new BusinessException(-2, "活动已失效");
		}
		if(actQrcode.getStartTime().after(now)){
			throw new BusinessException(-3, "活动还未开始");
		}
		if(actQrcode.getEndTime().before(now)){
			throw new BusinessException(-4, "活动已结束");
		}
		return null;
	}

}
