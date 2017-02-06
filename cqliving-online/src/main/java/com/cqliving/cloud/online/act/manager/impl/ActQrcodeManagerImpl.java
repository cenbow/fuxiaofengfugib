package com.cqliving.cloud.online.act.manager.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.act.dao.ActQrcodeDao;
import com.cqliving.cloud.online.act.domain.ActQrcode;
import com.cqliving.cloud.online.act.manager.ActQrcodeManager;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.framework.utils.Dates;
import com.cqliving.tool.common.util.StringUtil;

@Service("actQrcodeManager")
public class ActQrcodeManagerImpl extends EntityServiceImpl<ActQrcode, ActQrcodeDao> implements ActQrcodeManager {

	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(ActQrcode.STATUS99, idList);
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
	 * @see com.cqliving.cloud.online.act.manager.ActQrcodeManager#actUsable(com.cqliving.cloud.online.act.domain.ActQrcode)
	 */
	@Override
	public boolean actUsable(ActQrcode actQrcode) {
        if(null == actQrcode){
        	throw new BusinessException(ErrorCodes.QrcodeActErrorEnum.ACT_NOT_EXISTS.getCode(),ErrorCodes.QrcodeActErrorEnum.ACT_NOT_EXISTS.getDesc());
        }
        Date now = Dates.now();
        if(now.after(actQrcode.getEndTime())){//已结束
        	throw new BusinessException(ErrorCodes.QrcodeActErrorEnum.ACT_EXPIRED.getCode(),ErrorCodes.QrcodeActErrorEnum.ACT_EXPIRED.getDesc());
        }
        if(now.before(actQrcode.getStartTime())){//未开始
        	throw new BusinessException(ErrorCodes.QrcodeActErrorEnum.ACT_NOT_START.getCode(),ErrorCodes.QrcodeActErrorEnum.ACT_NOT_START.getDesc());
        }
		return true;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.act.manager.ActQrcodeManager#findByCode(java.lang.String)
	 */
	@Override
	public ActQrcode findByCode(String code) {

        if(StringUtil.isEmpty(code))return null;
        List<ActQrcode> acts=this.getEntityDao().findByCode(code);
        if(CollectionUtils.isEmpty(acts))return null;
		return acts.get(0);
	}
}
