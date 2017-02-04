package com.org.weixin.weixin.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.tool.common.util.date.DateUtil;
import com.feinno.module.memcached.SpyMemcachedClient;
import com.org.common.Constants;
import com.org.weixin.client.api.SnsAPI;
import com.org.weixin.client.api.UserAPI;
import com.org.weixin.client.bean.base.EventMessage;
import com.org.weixin.client.bean.base.message.req.ReqTextMessage;
import com.org.weixin.client.bean.base.token.SnsToken;
import com.org.weixin.client.bean.base.token.Token;
import com.org.weixin.client.bean.base.user.User;
import com.org.weixin.system.domain.SysWechatLoginLog;
import com.org.weixin.system.domain.SysWechatUser;
import com.org.weixin.system.dto.AccInfoDto;
import com.org.weixin.system.service.SysWechatLoginLogService;
import com.org.weixin.system.service.SysWechatUserService;
import com.org.weixin.util.Constant;

/**
 * 微信核心服务
 * Title:微信核心业务处理服务
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author fengshi on 2015年7月10日
 */
@Service("weixinCoreService")
public class WeixinCoreService {

	@Resource
	SpyMemcachedClient spyMemcachedClient;//memcache service
	@Resource
	SysWechatUserService wechatUserService;//用户 service
	@Resource
	SysWechatLoginLogService wechatLoginLogService;//用户登录日志 service
	
	private Logger logger = LoggerFactory.getLogger(WeixinCoreService.class);
	
	/**
	 * 处理微信请求类型
	 * <p>Description:处理微信请求类型，分别派发给相应的方法处理</p>
	 * @param eventMessage
	 * @return
	 * @author fengshi on 2015年7月10日
	 */
	public String TypeHandle(EventMessage eventMessage , Long accId){
		//获取事件类型
		String type = eventMessage.getMsgType();
		//事件处理
		if(type.equals(Constant.ReqMsgType.EVENT)){
			String msg = EventHandle(eventMessage , accId);
			return msg;
		}else{
			//消息处理
			switch (type) {
				case Constant.ReqMsgType.TEXT:
					logger.debug("处理文本消息："+eventMessage.getContent());
					break;
				case Constant.ReqMsgType.LINK:
					logger.debug("处理链接消息："+eventMessage.getContent());
					break;
				case Constant.ReqMsgType.IMAGE:
					logger.debug("处理图片消息："+eventMessage.getContent());
					break;
				case Constant.ReqMsgType.LOCATION:
					logger.debug("处理位置消息："+eventMessage.getContent());
					break;
				case Constant.ReqMsgType.SHORTVIDEO:
					logger.debug("处理短视频消息："+eventMessage.getContent());
					break;
				case Constant.ReqMsgType.VIDEO:
					logger.debug("处理视频消息："+eventMessage.getContent());
					break;
				case Constant.ReqMsgType.VOICE:
					logger.debug("处理音频消息："+eventMessage.getContent());
					break;
				default :
					logger.error("不能处理的消息："+eventMessage.getMsgType());
					break;
			}
		}
		return null;
	}
	
	/**
	 * 处理微信事件handle
	 * <p>Description:负责处理微信所有事件分发</p>
	 * @param eventMessage
	 * @return
	 * @author fengshi on 2015年7月10日
	 */
	@Transactional
	public String EventHandle(EventMessage eventMessage , Long accId){
		switch (eventMessage.getEvent()) {
			case Constant.ReqMsgEvent.SUBSCRIBE :
				logger.debug("处理关注事件："+eventMessage.getEventKey());
				//用户关注后，获取用户的基本信息，保存到本地数据库
				Map<Long,AccInfoDto> accInfoMap = spyMemcachedClient.get(Constants.Weixin.WEIXIN_ACC_MAP);
				if(null == accInfoMap){
					logger.error("没有获取到tokenMap");
					return null;
				}
				String accessToken = accInfoMap.get(accId).getAccessToken();
				if(StringUtils.isBlank(accessToken)){
					logger.error("没有获取到accessToken");
					return null;
				}
				User user = UserAPI.userInfo(accessToken,eventMessage.getFromUserName());
				if(User.SUCCESS.equals(user.getErrcode())){
					List<SysWechatUser> list = wechatUserService.getUserByOpenId(user.getOpenid());
					if(null != list){
						for (SysWechatUser u : list) {
							u.setSubscribe((byte)1);
							u.setSubscribeTime(new Date());
							u.setUpdateTime(new Date());
							wechatUserService.update(u);
						}
					}else{
						SysWechatUser wuser = new SysWechatUser();
						BeanUtils.copyProperties(user, wuser);
						wuser.setCreateTime(new Date());
						wuser.setModuleId(0L);
						wechatUserService.save(wuser);
					}
					
					String replyMsg = "欢迎你的关注！";
					ReqTextMessage xmlTextMessage = new ReqTextMessage(
		                     eventMessage.getFromUserName(),
		                     eventMessage.getToUserName(),
		                     replyMsg);
					logger.debug("处理关注事件完毕！"+user);
					return replyMsg;
				}
				break;
			case Constant.ReqMsgEvent.UNSUBSCRIBE :
				logger.debug("处理取消关注事件："+eventMessage.getEventKey());
				List<SysWechatUser> list = wechatUserService.getUserByOpenId(eventMessage.getFromUserName());
				if(null != list){
					for (SysWechatUser u : list) {
						u.setSubscribe((byte)0);
						u.setUnsubscribe((byte) 1);
						u.setUpdateTime(new Date());
						wechatUserService.update(u);
					}
				}
				break;
			case Constant.ReqMsgEvent.CLICK :
				logger.debug("处理点击事件："+eventMessage.getEventKey());
				break;
			case Constant.ReqMsgEvent.VIEW :
				logger.debug("处理页面跳转事件："+eventMessage.getEventKey());
				break;
			case Constant.ReqMsgEvent.LOCATION :
				logger.debug("处理上报地理位置事件："+eventMessage.getEventKey());
				break;
			case Constant.ReqMsgEvent.SCAN :
				logger.debug("处理带参二维码扫描事件："+eventMessage.getEventKey());
				break;
			default :
				logger.error("不能处理的事件："+eventMessage.getEvent());
				break;
		}
		return null;
	}
	
	/**
	 * auth2.0页面授权
	 * <p>Description:通过code获取access_token和openId，如果用户已存在，直接返回openId，否则通过access_token获取用户信息，返回openId</p>
	 * @param code
	 * @param accId
	 * @param mId
	 * @param needUserAuthorise 是否需要用户授权
	 * @return
	 * @author fengshi on 2015年7月10日
	 */
	@Transactional
	public String Auth2OpenId(Long accId, Long mId, String code, boolean needUserAuthorise) {
		Map<Long,AccInfoDto> accInfoMap = spyMemcachedClient.get(Constants.Weixin.WEIXIN_ACC_MAP);
		if(null == accInfoMap || null == accInfoMap.get(accId)){
			logger.error("不能获取accInfoMap：{},addId:{}",accInfoMap,accId);
			return null;
		}
		//通过code获取token
		SnsToken snsToken = SnsAPI.oauth2AccessToken(accInfoMap.get(accId).getAppid(),accInfoMap.get(accId).getSecret(),code);
		if(SnsToken.SUCCESS.equals(snsToken.getErrcode())){
			//判断用户是否已经存在
			SysWechatUser wuser = wechatUserService.getUserByOpenIdAndMId(snsToken.getOpenid(), mId);
			if (null != wuser) {
				spyMemcachedClient.set(Constants.Weixin.WEIXIN_CACHE_USER + wuser.getOpenid(), Constants.Weixin.WEIXIN_CACHE_USER_TIME, wuser);
				//记录登录日志
				SysWechatLoginLog log = new SysWechatLoginLog();
				log.setAccId(accId);
				log.setLoginTime(new Date());
				log.setNickName(wuser.getNickname());
				log.setUserId(wuser.getId());
				wechatLoginLogService.save(log);
				return snsToken.getOpenid();
			} else {
				if (needUserAuthorise) {	//用户授权
					User user = SnsAPI.userinfo(snsToken.getAccess_token(), snsToken.getOpenid(), "zh_CN");
					if(Token.SUCCESS.equals(user.getErrcode())){
						logger.debug("获取用户数据成功！");
						//插入数据库
						wuser = new SysWechatUser();
						//过滤emoji表情
						Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
						Matcher emojiMatcher = emoji.matcher(user.getNickname().trim());
						//如果匹配到emoji
						StringBuffer sb = new StringBuffer();  
						while(emojiMatcher.find()){
							emojiMatcher.appendReplacement(sb, "*");  
						}  
						emojiMatcher.appendTail(sb); 
						user.setNickname(sb.toString());
						BeanUtils.copyProperties(user, wuser);
						wuser.setModuleId(mId);
						wuser.setAccountId(accId);
						wuser.setCreateTime(new Date());
						wuser.setUpdateTime(new Date());
						wechatUserService.save(wuser);
						//记录登录日志
						SysWechatLoginLog log = new SysWechatLoginLog();
						log.setAccId(accId);
						log.setLoginTime(new Date());
						log.setNickName(wuser.getNickname());
						log.setUserId(wuser.getId());
						wechatLoginLogService.save(log);
						spyMemcachedClient.set(Constants.Weixin.WEIXIN_CACHE_USER + wuser.getOpenid(), Constants.Weixin.WEIXIN_CACHE_USER_TIME, wuser);
						return user.getOpenid();
					}
					logger.error("获取user失败!user:"+user);
				} else {	//静默授权，直接保存用户
					Date now = DateUtil.now();
					wuser = new SysWechatUser();
					wuser.setAccountId(accId);
					wuser.setCreateTime(now);
					wuser.setModuleId(mId);
					wuser.setOpenid(snsToken.getOpenid());
					wuser.setUpdateTime(now);
					wechatUserService.save(wuser);
					//记录登录日志
					SysWechatLoginLog log = new SysWechatLoginLog();
					log.setAccId(accId);
					log.setLoginTime(new Date());
					log.setNickName(wuser.getNickname());
					log.setUserId(wuser.getId());
					wechatLoginLogService.save(log);
					spyMemcachedClient.set(Constants.Weixin.WEIXIN_CACHE_USER + wuser.getOpenid(), Constants.Weixin.WEIXIN_CACHE_USER_TIME, wuser);
					return snsToken.getOpenid();
				}
			}
		}
		logger.error("获取snsToken失败!CODE:"+code+",snsToken:"+snsToken);
		return null;
	}
	
}
