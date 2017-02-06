package com.cqliving.cloud.online.actcustom.manager.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.actcustom.manager.ActCustomVoteItemManager;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.shopping.domain.ShoppingUserAddress;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.act.domain.ActQrcode;
import com.cqliving.cloud.online.act.manager.ActQrcodeManager;
import com.cqliving.cloud.online.actcustom.dao.ActCustomVoteItemDao;
import com.cqliving.cloud.online.actcustom.domain.ActCustomVoteItem;
import com.cqliving.cloud.online.actcustom.dto.ActCustomVoteDto;
import com.cqliving.cloud.online.actcustom.dto.ActCustomVoteItemDto;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.dao.support.ScrollPageOrder;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;

import org.apache.commons.lang3.StringUtils;

@Service("actCustomVoteItemManager")
public class ActCustomVoteItemManagerImpl extends EntityServiceImpl<ActCustomVoteItem, ActCustomVoteItemDao> implements ActCustomVoteItemManager {
	@Autowired
	private ActQrcodeManager actQrcodeManager;
	@Autowired
    private UserSessionManager userSessionManager;
	@Autowired
    private ActCustomVoteItemDao actCustomVoteItemDao;
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(ActCustomVoteItem.STATUS99, idList);
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
	/**
	 * 获取投票活动投票项列表
	 * @param actCode
	 * @return
	 */

	@Override
	@Transactional(value="transactionManager")
	public List<ActCustomVoteItem> getColumnsByQrcode(String actCode) {
		return this.getEntityDao().getColumnsListByQrcode(actCode);
	}

	@Override
	@Transactional(value="transactionManager")
	public void updateActValue(Long actCustomVoteItemId) {
		this.getEntityDao().updateActValue(actCustomVoteItemId);
		
	}

	@Override
	@Transactional(value="transactionManager")
	public ActCustomVoteItem queryActeVoteDetail(Long actCustomVoteItemId, String sessionId, String token) {
		
		ActCustomVoteItem rerault=null;
			rerault = this.getEntityDao().findOne(actCustomVoteItemId);
		return rerault;
	}

	@Override
	@Transactional(value="transactionManager")
	public ActCustomVoteDto queryActeDetail(String actQrcodeCode) {
		ActCustomVoteDto actCustomVoteDtoOne=null;
		List<ActCustomVoteDto> actCustomVoteDto=actCustomVoteItemDao.queryActeDetail(actQrcodeCode);
		if(actCustomVoteDto.size()>0){
			actCustomVoteDtoOne=actCustomVoteDto.get(0);
		}
		return actCustomVoteDtoOne;
	}
	@Override
	@Transactional(value="transactionManager")
	public CommonListResult<ActCustomVoteItemDto> getActCustomByAcode(String actQrcodeCode, String itemTitle,Long lastId,Long ranking) {
		
		CommonListResult<ActCustomVoteItemDto> result = new CommonListResult<ActCustomVoteItemDto>();
		ScrollPage<ActCustomVoteItemDto> page = new ScrollPage<ActCustomVoteItemDto>();
		page.addScrollPageOrder(new ScrollPageOrder("ranking", ScrollPage.ASC, ranking));
		page.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.ASC, lastId));
		page.setPageSize(10);
		page = getEntityDao().queryAddressPage(page,itemTitle);
		List<ActCustomVoteItemDto> list = page.getPageResults();
		result.setDataList(list);
		return result;

		
	}
}
