package com.cqliving.cloud.online.wz.manager;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.wz.domain.WzTransfer;
import com.cqliving.cloud.online.wz.domain.WzTransferDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityService;

/**
 * 问题转交流程表 Manager
 * Date: 2016-05-10 09:49:54
 * @author Code Generator
 */
public interface WzTransferManager extends EntityService<WzTransfer> {
	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	public int deleteLogic(Long[] id);
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids ID
	 * @return
	 */
	public int updateStatus(Byte status,Long... ids);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年5月20日
	 * @param userId
	 * @param questionId
	 * @param status
	 * @return
	 */
	List<WzTransfer> getCurrentTransferByUser(Long userId, Long questionId, List<Byte> status);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年5月20日
	 * @param pageInfo
	 * @param map
	 * @param orderMap
	 * @return
	 */
	PageInfo<WzTransferDto> queryDtoForScrollPage(PageInfo<WzTransferDto> pageInfo, Map<String, Object> map, Map<String, Boolean> orderMap);

	public void saveAfterPublisReply(Long id, String auditingDepartment, String content, Long updatorId, String updator);
}
