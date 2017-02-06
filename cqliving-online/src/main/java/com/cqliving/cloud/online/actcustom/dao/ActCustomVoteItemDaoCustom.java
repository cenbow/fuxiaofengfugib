package com.cqliving.cloud.online.actcustom.dao;

import java.util.List;

import com.cqliving.cloud.online.actcustom.dto.ActCustomVoteDto;
import com.cqliving.cloud.online.actcustom.dto.ActCustomVoteItemDto;
import com.cqliving.framework.common.dao.support.ScrollPage;

public interface ActCustomVoteItemDaoCustom {
	public List<ActCustomVoteDto> queryActeDetail(String actQrcodeCode);
	public List<ActCustomVoteItemDto> queryActeList(String actQrcodeCode, String itemTitle);
	public ScrollPage<ActCustomVoteItemDto> queryAddressPage(ScrollPage<ActCustomVoteItemDto> page, String itemTitle);
}
