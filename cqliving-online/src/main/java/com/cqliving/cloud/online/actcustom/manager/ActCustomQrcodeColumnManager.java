package com.cqliving.cloud.online.actcustom.manager;

import com.cqliving.framework.common.service.EntityService;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.actcustom.domain.ActCustomQrcodeColumn;
import com.cqliving.cloud.online.actcustom.dto.ActCustomQrcodeColumnDto;

/**
 * 报名活动自定义收集列 Manager
 * Date: 2016-12-21 09:29:51
 * @author Code Generator
 */
public interface ActCustomQrcodeColumnManager extends EntityService<ActCustomQrcodeColumn> {
	
	/**
	 * Title:根据活动获取配置列
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月21日
	 * @param qrcode
	 * @return
	 */
	public List<ActCustomQrcodeColumnDto> getColumnsByQrcode(String qrcode);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月21日
	 * @param sessionId
	 * @param token
	 * @param qrcode
	 * @param columnIds
	 * @param values
	 */
	public void signSave(String sessionId, String token, String qrcode, String columnIds, String values);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月21日
	 * @param sessionId
	 * @param token
	 * @param qrcode
	 */
	public Map<String, Object> signList(String sessionId, String token, String qrcode);

	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月22日
	 * @param sessionId
	 * @param token
	 * @param qrcode
	 * @return
	 */
	public Map<String, String> isSign(String sessionId, String token, String qrcode);
}
