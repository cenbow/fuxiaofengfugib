package com.cqliving.cloud.online.actcustom.manager;

import com.cqliving.framework.common.service.EntityService;

import java.util.List;

import com.cqliving.cloud.online.actcustom.domain.ActCustomVoteItem;
import com.cqliving.cloud.online.actcustom.dto.ActCustomVoteDto;
import com.cqliving.cloud.online.actcustom.dto.ActCustomVoteItemDto;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;

/**
 * 自定义活动投票选项表 Manager
 * Date: 2017-01-03 10:19:03
 * @author Code Generator
 */
public interface ActCustomVoteItemManager extends EntityService<ActCustomVoteItem> {
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

	public List<ActCustomVoteItem> getColumnsByQrcode(String actCode);

	public void updateActValue(Long actCustomVoteItemId);

	public ActCustomVoteItem queryActeVoteDetail(Long actCustomVoteItemId, String sessionId, String token);

	public ActCustomVoteDto queryActeDetail(String actQrcodeCode);

	public CommonListResult<ActCustomVoteItemDto> getActCustomByAcode(String actQrcodeCode, String itemTitle, Long lastId,Long ranking);
}
