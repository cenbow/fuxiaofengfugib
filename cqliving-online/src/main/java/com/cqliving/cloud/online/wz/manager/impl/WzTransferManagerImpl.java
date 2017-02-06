package com.cqliving.cloud.online.wz.manager.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.wz.dao.WzQuestionDao;
import com.cqliving.cloud.online.wz.dao.WzTransferDao;
import com.cqliving.cloud.online.wz.domain.WzQuestion;
import com.cqliving.cloud.online.wz.domain.WzTransfer;
import com.cqliving.cloud.online.wz.domain.WzTransferDto;
import com.cqliving.cloud.online.wz.manager.WzTransferManager;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Maps;

@Service("wzTransferManager")
public class WzTransferManagerImpl extends EntityServiceImpl<WzTransfer, WzTransferDao> implements WzTransferManager {
	
	@Autowired
	private WzQuestionDao wzQuestionDao;
    
    public PageInfo<WzTransferDto> queryByPage(PageInfo<WzTransferDto> pageInfo,Map<String, Object> map,Map<String, Boolean> orderMap){
        return this.getEntityDao().queryDtoForScrollPage(pageInfo, map, orderMap);
    }
    
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(WzTransfer.STATUS99, idList);
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
		return this.getEntityDao().updateStatus(WzTransfer.STATUS99, idList);
	}

    @Override
    public List<WzTransfer> getCurrentTransferByUser(Long userId, Long questionId, List<Byte> status) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("EQ_currentUserId", userId);
        map.put("EQ_questionId", questionId);
        map.put("IN_status", status);
        
        return getEntityDao().query(map, null);
    }

    @Override
    public PageInfo<WzTransferDto> queryDtoForScrollPage(PageInfo<WzTransferDto> pageInfo, Map<String, Object> map,
            Map<String, Boolean> orderMap) {
        return getEntityDao().queryDtoForScrollPage(pageInfo, map, orderMap);
    }

	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void saveAfterPublisReply(Long id, String auditingDepartment, String content, Long updatorId, String updator) {
		WzTransfer wzTransfer = get(id);
		if(wzTransfer == null){
			throw new BusinessException(-1, "数据不存在");
		}
		WzQuestion wzQuestion = wzQuestionDao.get(wzTransfer.getQuestionId());
		if(wzQuestion == null){
			throw new BusinessException(-1, "数据不存在");
		}
		wzTransfer.setReplayContent(content);
		this.getEntityDao().update(wzTransfer);
		wzQuestion.setReplyContent(content);
		wzQuestion.setAuditingDepartment(auditingDepartment);
		wzQuestion.setUpdateTime(new Date());
		wzQuestion.setUpdator(updator);
		wzQuestion.setUpdatorId(updatorId);
		wzQuestionDao.update(wzQuestion);
	}
}
