package com.cqliving.cloud.server.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.interfacc.dto.CommonKeyValueData;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.ReginDto;
import com.cqliving.cloud.online.shopping.domain.ShoppingUserAddress;
import com.cqliving.cloud.online.shopping.service.ShoppingUserAddressService;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Lists;
/**
 * 
 * 收货地址类
 * 
 */
@RequestMapping(value = {"shoppingUser"})
@Controller
public class ShoppingUserAddressController {
	private static final Logger logger = LoggerFactory.getLogger(TopicController.class);
	@Autowired
	private ShoppingUserAddressService shoppingUserAddressService;
	
	/**
	 * <p>Description: 添加收货地址</p>
	 * @author FangHuiLin on 2016年11月18日
	 * @param request
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
	@ResponseBody
	@RequestMapping(value = {"addressAdd"}, method = {RequestMethod.POST})
	public Response<Void> addressSave(HttpServletRequest request, 
			@RequestParam Long appId, 
			@RequestParam String recivier, 
			@RequestParam String cellphone,
			Long shoppingUserAddressId,
			@RequestParam String token,
			@RequestParam Long regionLevelOneId,
			@RequestParam Long regionLevelTwoId,
			@RequestParam Long regionLevelThreeId,
			@RequestParam String address,
			String zip,
			@RequestParam Byte isDefault,
			@RequestParam String sessionId) {
		
		logger.debug("===================== 调用保存收货地址接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, token=%s,recivier=%s, cellphone=%s, regionLevelOneId=%d,regionLevelTwoId=%d,regionLevelThreeId=%d,address=%s,zip=%s,isDefault=%d,sessionId=%s ", 
						appId, token, recivier, cellphone, regionLevelOneId,regionLevelTwoId,regionLevelThreeId, address, zip,isDefault,sessionId));
		Response<Void> response = Response.newInstance();
		ShoppingUserAddress shoppingUserAddress=new ShoppingUserAddress();
		//检查参数的必要性
		if (appId == null ||regionLevelOneId == null ||regionLevelTwoId == null ||regionLevelThreeId == null || StringUtils.isBlank(token)|| StringUtils.isBlank(recivier)|| StringUtils.isBlank(cellphone)||isDefault==null||StringUtils.isBlank(sessionId)||StringUtils.isBlank(address)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用保存收货地址接口异常：" + response);
			return response;
		}
		shoppingUserAddress.setId(shoppingUserAddressId);
		shoppingUserAddress.setAppId(appId);
		shoppingUserAddress.setRecivier(recivier);
		shoppingUserAddress.setCellphone(cellphone);
		shoppingUserAddress.setRegionLevelOneId(regionLevelOneId);
		shoppingUserAddress.setRegionLevelTwoId(regionLevelTwoId);
		shoppingUserAddress.setRegionLevelThreeId(regionLevelThreeId);
		shoppingUserAddress.setAddress(address);
		shoppingUserAddress.setZip(zip);
		shoppingUserAddress.setIsDefault(isDefault);
		if(shoppingUserAddressId!=null){
			response = shoppingUserAddressService.updateShoppingUserAddress(appId,token,sessionId,shoppingUserAddress);
		}else{
			response = shoppingUserAddressService.addShoppingUserAddress(appId,token,sessionId,shoppingUserAddress);
		}
		logger.debug("调用保存或者修改收货地址接口结果：" + response);
		return response;
	}
	/**
	 * <p>Description: 修改收货地址</p>
	 * @author FangHuiLin on 2016年11月18日
	 * @param request
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
	@ResponseBody
	@RequestMapping(value = {"addressUpdate"}, method = {RequestMethod.POST})
	public Response<Void> addressUpdate(HttpServletRequest request, 
			@RequestParam Long shoppingUserAddressId,
			@RequestParam Long appId, 
			String recivier, 
			String cellphone,
			String token,
			Long regionLevelOneId,
			String regionLevelOneName,
			Long regionLevelTwoId,
			String regionLevelTwoName,
			Long regionLevelThreeId,
			String regionLevelThreeName,
			Long regionLevelFourId,
			String regionLevelFourName,
			String address,
			String fullAddress,
			String zip,
			@RequestParam Byte isDefault,
			@RequestParam String sessionId) {
		logger.debug("===================== 调用修改收货地址接口 =====================>" + 
				String.format("\n<请求参数：shoppingUserAddressId=%d,appId=%d, token=%s,recivier=%s, cellphone=%s, regionLevelOneId=%d,regionLevelOneName=%s, regionLevelTwoId=%d, regionLevelTwoName=%s, regionLevelThreeId=%d, regionLevelThreeName=%s, regionLevelFourId=%d, regionLevelFourName=%s, address=%s,fullAddress=%s,zip=%s,isDefault=%d,sessionId=%s ", 
						shoppingUserAddressId,appId, token, recivier, cellphone, regionLevelOneId, regionLevelOneName, regionLevelTwoId, regionLevelTwoName, regionLevelThreeId, regionLevelThreeName, regionLevelFourId, regionLevelFourName, address, fullAddress, zip,isDefault,sessionId));
		Response<Void> response = Response.newInstance();
		ShoppingUserAddress shoppingUserAddress=new ShoppingUserAddress();
		shoppingUserAddress.setId(shoppingUserAddressId);
		shoppingUserAddress.setAppId(appId);
		shoppingUserAddress.setRecivier(recivier);
		shoppingUserAddress.setCellphone(cellphone);
		shoppingUserAddress.setRegionLevelOneId(regionLevelOneId);
		shoppingUserAddress.setRegionLevelOneName(regionLevelOneName);
		shoppingUserAddress.setRegionLevelTwoId(regionLevelTwoId);
		shoppingUserAddress.setRegionLevelTwoName(regionLevelTwoName);
		shoppingUserAddress.setRegionLevelThreeId(regionLevelThreeId);
		shoppingUserAddress.setRegionLevelThreeName(regionLevelThreeName);
		shoppingUserAddress.setRegionLevelFourId(regionLevelFourId);
		shoppingUserAddress.setRegionLevelFourName(regionLevelFourName);
		shoppingUserAddress.setAddress(fullAddress);
		shoppingUserAddress.setFullAddress(fullAddress);
		shoppingUserAddress.setZip(zip);
		shoppingUserAddress.setIsDefault(isDefault);
		response = shoppingUserAddressService.updateShoppingUserAddress(appId,token,sessionId,shoppingUserAddress);
		return response;
	}
	/**
	 * <p>Description: 查询收货地址列表</p>
	 * @author FangHuiLin on 2016年11月18日
	 * @param request
	 * @param appId
	 * @param token
	 * @param sessionId
	 * @param lastId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"addressList"}, method = {RequestMethod.POST})
	public Response<CommonListResult<ShoppingUserAddress>> addressSelect(HttpServletRequest request, 
			@RequestParam Long appId, 
			String token,
			@RequestParam String sessionId,
			Long lastId) {
		logger.debug("===================== 调用查询收货地址列表接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, token=%s,sessionId=%s,lastId=%d ", 
						appId, token, sessionId,lastId));
		Response<CommonListResult<ShoppingUserAddress>> response = Response.newInstance();
		
		if (appId == null || StringUtils.isBlank(sessionId)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用查询结果接口异常：" + response);
			return response;
		}
		response=shoppingUserAddressService.queryAdressPage(appId, sessionId, token,lastId);
		return response;
	}
	/**
	 * <p>Description: 查询收货地址详情</p>
	 * @author FangHuiLin on 2016年11月18日
	 * @param request
	 * @param shoppingUserAddressId
	 * @param appId
	 * @param token
	 * @param sessionId
	 * @return
	 */
	 
	@ResponseBody
	@RequestMapping(value = {"addressDetails"}, method = {RequestMethod.POST})
	public Response<ShoppingUserAddress> addressSelectOne(HttpServletRequest request,
			@RequestParam Long appId, 
			@RequestParam String token,
			@RequestParam String sessionId) {
		logger.debug("===================== 调用查询收货地址列表接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, token=%s,sessionId=%s ", 
						appId, token, sessionId));
		Response<ShoppingUserAddress> response = Response.newInstance();
		
		if (appId == null || StringUtils.isBlank(sessionId)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用查询结果接口异常：" + response);
			return response;
		}
		response=shoppingUserAddressService.queryAdressOne(appId, sessionId, token);
		return response;
	}
	/**
	 * <p>Description: 删除收货地址</p>
	 * @author FangHuiLin on 2016年11月18日
	 * @param request
	 * @param shoppingUserAddressId
	 * @param appId
	 * @param token
	 * @param sessionId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"addressRemove"}, method = {RequestMethod.POST})
	public Response<Void> addressRemove(HttpServletRequest request, 
			@RequestParam String shoppingUserAddressIds,
			@RequestParam Long appId, 
			@RequestParam String token,
			@RequestParam String sessionId) {
		logger.debug("===================== 调用查询收货地址列表接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, token=%s,sessionId=%s,shoppingUserAddressId=%s ", 
						appId, token, sessionId,shoppingUserAddressIds));
		Response<Void> response = Response.newInstance();
		
		if (appId == null || StringUtils.isBlank(token)|| StringUtils.isBlank(sessionId)|| StringUtils.isBlank(shoppingUserAddressIds)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用查询结果接口异常：" + response);
			return response;
		}
		List<Long> idList = Lists.newArrayList();
		for (String id : shoppingUserAddressIds.split(",")) {
			idList.add(NumberUtils.toLong(id.trim()));
		}
		response=shoppingUserAddressService.addressRemove(appId, sessionId, token,idList);
		return response;
	}
	/**
	 * <p>Description: 修改商品收货地址为默认</p>
	 * @author FangHuiLin on 2016年12月2日
	 * @param request
	 * @param shoppingUserAddressId
	 * @param appId
	 * @param token
	 * @param sessionId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"addressUf"}, method = {RequestMethod.POST})
	public Response<Void> addressUf(HttpServletRequest request, 
			@RequestParam Long shoppingUserAddressId,
			@RequestParam Long appId, 
			@RequestParam String token,
			@RequestParam String sessionId) {
		logger.debug("===================== 调用修改收货地址为默认接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, token=%s,sessionId=%s,shoppingUserAddressId=%d ", 
						appId, token, sessionId,shoppingUserAddressId));
		Response<Void> response = Response.newInstance();
		
		if (appId == null ||shoppingUserAddressId == null || StringUtils.isBlank(token)|| StringUtils.isBlank(sessionId)) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用查询结果接口异常：" + response);
			return response;
		}
		response=shoppingUserAddressService.addressUf(appId, sessionId, token,shoppingUserAddressId);
		return response;
	}
	/**
	 * <p>Description: 区域获取</p>
	 * @author FangHuiLin on 2016年11月28日
	 * @param request
	 * @param appId
	 * @param token
	 * @param sessionId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"regin"}, method = {RequestMethod.POST})
	public Response<CommonListResult<ReginDto>> reginlist(HttpServletRequest request, 
			@RequestParam Long appId, 
			String token,
			String sessionId) {
		
		logger.debug("===================== 调用区域地址表接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, token=%s,sessionId=%s ", 
						appId, token, sessionId));
		Response<CommonListResult<ReginDto>> response = Response.newInstance();
		
		if (appId == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用查询结果接口异常：" + response);
			return response;
		}
		Long dat1=System.currentTimeMillis();
		response=shoppingUserAddressService.reginList();
		Long dat2=System.currentTimeMillis();
		logger.debug("时间差："+(dat2-dat1));
		return response;
	}
}
