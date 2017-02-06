package com.cqliving.cloud.online.actcustom.dao;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.actcustom.dto.ActCustomColumnDto;
import com.cqliving.cloud.online.actcustom.dto.ActCustomQrcodeColumnDto;

/**
 * Title:活动
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年12月21日
 */
public interface ActCustomQrcodeColumnDaoCustom {

	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月21日
	 * @param qrcode
	 * @return
	 */
	public List<ActCustomQrcodeColumnDto> getColumnsByQrcode(String qrcode, boolean isListView);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月21日
	 * @param actCustomColumnId
	 * @param qrcode
	 * @return
	 */
	public ActCustomQrcodeColumnDto getDtoByIdAndCode(Long actCustomColumnId, String qrcode);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月22日
	 * @param qrcode
	 * @param fields
	 * @return
	 */
	public List<Map<String, Object>> getSignList(String qrcode, StringBuilder fields);
	
	/**
	 * Title:获取活动下用户报名信息
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月22日
	 * @param qrcode
	 * @param userId
	 * @return
	 */
	public List<ActCustomColumnDto> getByUserSignInfo(String qrcode, Long userId);
}
