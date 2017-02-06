package com.cqliving.cloud.online.act.manager.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.basic.facade.PropertiesConfig;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.constant.PropertyKey;
import com.cqliving.cloud.online.account.dao.UserAccountDao;
import com.cqliving.cloud.online.account.domain.UserAccount;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.act.dao.UserActQrcodeDao;
import com.cqliving.cloud.online.act.domain.ActQrcode;
import com.cqliving.cloud.online.act.domain.UserActQrcode;
import com.cqliving.cloud.online.act.manager.ActQrcodeManager;
import com.cqliving.cloud.online.act.manager.UserActQrcodeManager;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.framework.utils.Dates;
import com.cqliving.framework.utils.qrcode.Qrcode;
import com.cqliving.framework.utils.qrcode.Qrcode.Builder;
import com.cqliving.tool.common.util.StringUtil;
import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.common.util.file.FileUtils;

@Service("userActQrcodeManager")
public class UserActQrcodeManagerImpl extends EntityServiceImpl<UserActQrcode, UserActQrcodeDao> implements UserActQrcodeManager {
	
	@Autowired
	UserSessionManager userSessionManager;
	@Autowired
	ActQrcodeManager actQrcodeManager;
	@Autowired
	UserAccountDao userAccountDao;
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(UserActQrcode.STATUS99, idList);
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

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.act.manager.UserActQrcodeManager#verify(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(value="transactionManager")
	public UserActQrcode verify(String code, String token) {
		//参数检查
		this.checkActParam(code, token);
		List<UserActQrcode> list = this.getEntityDao().findByToken(token);
		if(CollectionUtils.isEmpty(list)){//该用户未获取优惠券
			throw new BusinessException(ErrorCodes.QrcodeActErrorEnum.USER_NOT_COUPON.getCode(),ErrorCodes.QrcodeActErrorEnum.USER_NOT_COUPON.getDesc());
		}
		UserActQrcode userAct = list.get(0);
		if(UserActQrcode.ISUSE1.byteValue() == userAct.getIsUse().byteValue()){
			throw new BusinessException(ErrorCodes.QrcodeActErrorEnum.HAD_VERIFIED.getCode(),ErrorCodes.QrcodeActErrorEnum.HAD_VERIFIED.getDesc());
		}
		//检查活动是否有效,用二维码的code，防止抓包传值
		ActQrcode actQrcode = actQrcodeManager.findByCode(code);
		actQrcodeManager.actUsable(actQrcode);
		if(!code.equals(userAct.getActQrcodeCode())){
			throw new BusinessException(ErrorCodes.QrcodeActErrorEnum.USER_NOT_RIGHT.getCode(),ErrorCodes.QrcodeActErrorEnum.USER_NOT_RIGHT.getDesc());
		}
		userAct.setUseTime(Dates.now());
		userAct.setIsUse(UserActQrcode.ISUSE1);
		return this.getEntityDao().saveAndFlush(userAct);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.act.manager.UserActQrcodeManager#findByCode(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(value="transactionManager")
	public UserActQrcode findByCode(String actCode, String token) {
		//参数检查
		this.checkActParam(actCode, token);
		UserSession userSession = userSessionManager.getByToken(token);
		if(null == userSession){
			throw new BusinessException(ErrorCodes.QrcodeActErrorEnum.USER_NOT_LOGIN.getCode(),ErrorCodes.QrcodeActErrorEnum.USER_NOT_LOGIN.getDesc());
		}
		UserAccount userAccount = userAccountDao.get(userSession.getUserId());
		//是注册用户，且用户名和手机号不能为空
		if(UserAccount.TYPE0.byteValue() != userAccount.getType().byteValue() ||
				StringUtil.isEmpty(userAccount.getUserName()) || StringUtil.isEmpty(userAccount.getTelephone())){
			throw new BusinessException(ErrorCodes.QrcodeActErrorEnum.NOT_PHONE_USER.getCode(),ErrorCodes.QrcodeActErrorEnum.NOT_PHONE_USER.getDesc());
		}
		//查找是否已经获取了优惠券
		List<UserActQrcode> userActs = this.getEntityDao().findByUserIdActCode(userSession.getUserId(), actCode);
		if(CollectionUtils.isNotEmpty(userActs)){
			return userActs.get(0);
			//throw new BusinessException(ErrorCodes.QrcodeActErrorEnum.USER_HAD_JOINED.getCode(),ErrorCodes.QrcodeActErrorEnum.USER_HAD_JOINED.getDesc());
		}
		//根据code查找活动数据
		ActQrcode actQrcode = actQrcodeManager.findByCode(actCode);
		//活动是否可用
		actQrcodeManager.actUsable(actQrcode);
		UserActQrcode userActQrcode = new UserActQrcode();
		Date now = Dates.now();
		userActQrcode.setActQrcodeCode(actQrcode.getCode());
		userActQrcode.setCreateTime(now);
		userActQrcode.setCreator(userAccount.getUserName());
		userActQrcode.setIsUse(UserActQrcode.ISUSE0);
		userActQrcode.setPhone(userAccount.getTelephone());
		userActQrcode.setQrcodeImageUrl(getQrcodeImgUrl(actQrcode.getUrl(),actCode,token,userAccount.getId(),now));
		userActQrcode.setStatus(UserActQrcode.STATUS3);
		userActQrcode.setToken(token);
		userActQrcode.setUserId(userAccount.getId());
		return this.getEntityDao().saveAndFlush(userActQrcode);
	}
	
	private boolean checkActParam(String actCode,String token){
		if(StringUtil.isEmpty(token) || StringUtil.isEmpty(actCode)){//参数无效
			throw new BusinessException(ErrorCodes.QrcodeActErrorEnum.ACT_ERR_PARM.getCode(),ErrorCodes.QrcodeActErrorEnum.ACT_ERR_PARM.getDesc());
		}
		return true;
	}
	
	private String getQrcodeImgUrl(String actUrl,String actCode,String token,Long userId,Date date){
		
		if(StringUtil.isEmpty(actUrl) || StringUtil.isEmpty(actCode) || StringUtil.isEmpty(token)){
			return null;
		}
		String commonPath = "/activity/qrcode/"+DateUtil.formatDate(date,"yyyyMM");
		String fileUrl = PropertiesConfig.getString(PropertyKey.FILE_URL_PATH)+commonPath;
		String localPath = PropertiesConfig.getString(PropertyKey.FILE_LOCAL_PATH)+commonPath;
		String fileName = StringUtil.getUUID()+"_"+userId+"_"+DateUtil.formatDate(date, DateUtil.FORMAT_YYYYMMDD)+".jpg";
		
		String[] urlArr = actUrl.split("\\?");
		String content = null;
		if(urlArr.length <=1){
			content = urlArr[0]+"?code="+actCode+"&token="+token;
		}else{
			String param = urlArr[1]+"&code="+actCode+"&token="+token;
			content = urlArr[0] + param;
		}
		Builder builder = Qrcode.encode(content);
		FileOutputStream out = null;
		try {
			String file = localPath+"/"+fileName;
			File pfile = new File(file);
			FileUtils.validDirectory(pfile.getParent(),true);
			out = new FileOutputStream(file);
			builder.write(out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{
			closeOut(out);
		}
		return fileUrl+"/"+fileName;
	}
	
	private void closeOut(FileOutputStream out){
		if(null != out){
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
