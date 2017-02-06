package com.cqliving.cloud.online.shopping.service;

import java.util.List;
import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.ReginDto;
import com.cqliving.cloud.online.shopping.domain.ShoppingUserAddress;
import com.cqliving.tool.common.Response;

/**
 * 用户收货地址表 Service
 * Date: 2016-11-17 15:41:44
 * @author Code Generator
 */
public interface ShoppingUserAddressService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ShoppingUserAddress>> queryForPage(PageInfo<ShoppingUserAddress> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ShoppingUserAddress> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(ShoppingUserAddress domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * <p>Description: 添加收货地址</p>
	 * @author Fanghuilin on 2016年11月21日
	 * @param appId
	 * @param recivier
	 * @param cellphone
	 * @param token
	 * @param regionLevelOneId
	 * @param regionLevelOneName
	 * @param regionLevelTwoId
	 * @param regionLevelTwoName
	 * @param regionLevelThreeId
	 * @param regionLevelThreeName
	 * @param regionLevelFourId
	 * @param regionLevelFourName
	 * @param address
	 * @param fullAddress
	 * @param zip
	 * @param isDefault
	 * @param sessionId
	 * @return
	 */
	Response<Void> addShoppingUserAddress(Long appId,String token,String sessionId,ShoppingUserAddress shoppingUserAddress);
	/**
	 * <p>Description: 查询收货地址列表</p>
	 * @author Fanghuilin on 2016年11月21日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @return
	 */
	
	Response<CommonListResult<ShoppingUserAddress>> queryAdressPage(Long appId,String sessionId,String token,Long lastId);
	/**
	 * <p>Description: 修改收货地址</p>
	 * @author Fanghuilin on 2016年11月21日
	 * @param shoppingUserAddressId
	 * @param appId
	 * @param recivier
	 * @param cellphone
	 * @param token
	 * @param regionLevelOneId
	 * @param regionLevelOneName
	 * @param regionLevelTwoId
	 * @param regionLevelTwoName
	 * @param regionLevelThreeId
	 * @param regionLevelThreeName
	 * @param regionLevelFourId
	 * @param regionLevelFourName
	 * @param address
	 * @param fullAddress
	 * @param zip
	 * @param isDefault
	 * @param sessionId
	 * @return
	 */
	Response<Void> updateShoppingUserAddress(Long appId,String token,String sessionId,ShoppingUserAddress shoppingUserAddress);
	/**
	 * <p>Description: 查询收货地址详情</p>
	 * @author Fanghuilin on 2016年11月21日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param shoppingUserAddressId
	 * @return
	 */
	Response<ShoppingUserAddress> queryAdressOne(Long appId, String sessionId, String token);
    /**
     * <p>Description: 删除收货地址</p>
	 * @author Fanghuilin on 2016年11月21日
     * @param appId
     * @param sessionId
     * @param token
     * @param idList
     * @return
     */
	public Response<Void> addressRemove(Long appId, String sessionId, String token, List<Long> idList);
	public Response<Void> addressUf(Long appId, String sessionId, String token, Long shoppingUserAddressId);
	public Response<CommonListResult<ReginDto>> reginList();
	
}
