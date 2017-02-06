package com.cqliving.cloud.online.shopping.manager;

import com.cqliving.framework.common.service.EntityService;
import com.cqliving.tool.common.Response;

import java.util.List;

import com.cqliving.cloud.online.interfacc.dto.BusinessDetailResult;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.ReginDto;
import com.cqliving.cloud.online.shopping.domain.ShoppingUserAddress;

/**
 * 用户收货地址表 Manager
 * Date: 2016-11-17 15:41:44
 * @author Code Generator
 */
public interface ShoppingUserAddressManager extends EntityService<ShoppingUserAddress> {
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
	 * <p>Description: 添加商品收货地址</p>
	 * @author FangHuiLin on 2016年11月18日
	 * @param appId
	 * @param token
	 * @param sessionId
	 * @param shoppingUserAddress
	 * @return
	 */
	Byte addShoppingUserAddress(Long appId,String token,String sessionId,ShoppingUserAddress shoppingUserAddress);
	/**
	 * <p>Description: 查询收货地址列表</p>
	 * @author FangHuiLin on 2016年11月18日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param lastId
	 * @return
	 */
	CommonListResult<ShoppingUserAddress> queryAdressPage(Long appId,String sessionId,String token,Long lastId);
	/**
	 * <p>Description: 修改收货地址</p>
	 * @author FangHuiLin on 2016年11月18日
	 * @param appId
	 * @param token
	 * @param sessionId
	 * @param shoppingUserAddress
	 */
	void updateShoppingUserAddress(Long appId,String token,String sessionId,ShoppingUserAddress shoppingUserAddress);
	/**
	 * <p>Description: 查询收货地址详情</p>
	 * @author FangHuiLin on 2016年11月18日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param shoppingUserAddressId
	 * @return
	 */
	ShoppingUserAddress queryAdressOne(Long appId, String sessionId, String token);
	/**
	 * <p>Description: 删除收货地址</p>
	 * @author FangHuiLin on 2016年11月18日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param idList
	 * @return
	 */
	public int remove(Long appId, String sessionId, String token, List<Long> idList);
	void addressUf(Long appId, String sessionId, String token, Long shoppingUserAddressId);
	CommonListResult<ReginDto> queryReginList();
	
	
}
