package com.cqliving.cloud.online.wz.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.wz.domain.WzTransfer;
import com.cqliving.cloud.online.wz.domain.WzTransferData;
import com.cqliving.cloud.online.wz.domain.WzTransferDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 问题转交流程表 Service
 * Date: 2016-05-10 09:49:54
 * @author Code Generator
 */
public interface WzTransferService {

	/** @author Code Generator *****start*****/
    public Response<PageInfo<WzTransferDto>> queryForPage(PageInfo<WzTransferDto> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
    public Response<WzTransfer> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(WzTransfer domain);
	/** @author Code Generator *****end*****/
    
	/**
	 * Title:获得用户待处理的流程
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年5月19日
	 * @param userId
	 * @return
	 */
	Response<List<WzTransfer>> getCurrentTransferByUser(Long userId, Long questionId, List<Byte> status);
	
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年5月21日
	 * @param domain
	 * @return
	 */
    public Response<Void> update(WzTransfer domain);
    
    /**
     * Title:转交
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月21日
     * @param id
     * @param currentUserId
     * @param auditingDepartment
     * @param description
     * @param userId
     * @param nickname
     * @return
     */
    public Response<Void> transferSave(Long id, Long currentUserId, String auditingDepartment, String description, Long userId, String nickname);
    
    /**
     * Title:
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月21日
     * @param id
     * @return
     */
    public Response<List<WzTransferData>> getByQuestion(Long id);
	
    /**
     * Title:回复
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月21日
     * @param id
     * @param auditingDepartment
     * @param content
     * @param userId
     * @return
     */
    Response<Void> replySave(Long id, String auditingDepartment, String content, Long userId);
    
    /**
     * Title:批量提交
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月21日
     * @param id
     * @param auditingDepartment
     * @param content
     * @param userId
     * @return
     */
    Response<Void> submitBatchSave(Long[] ids);
    /**
     * Title:批量发布
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月21日
     * @param ids
     * @return
     */
    Response<Void> publishBatchSave(Long[] ids);
    
    Response<Void> del(Long[] ids, boolean hasPublishAllowDel);
    
	public Response<Void> saveAfterPublisReply(Long id, String auditingDepartment, String content, Long updatorId, String updator);
}
