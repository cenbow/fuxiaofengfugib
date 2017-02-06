package com.cqliving.cloud.online.shopping.manager.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.basic.dao.RegionDao;
import com.cqliving.basic.domain.Region;
import com.cqliving.basic.service.BasicService;
import com.cqliving.cloud.common.CacheConstant;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.UserInfoReply;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.account.manager.impl.UserInfoReplyManagerImpl;
import com.cqliving.cloud.online.app.dao.AppInfoDao;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.info.domain.InfoFile;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.ReginDto;
import com.cqliving.cloud.online.shopping.dao.ShoppingUserAddressDao;
import com.cqliving.cloud.online.shopping.domain.ShoppingUserAddress;
import com.cqliving.cloud.online.shopping.manager.ShoppingUserAddressManager;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.dao.support.ScrollPageOrder;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.redis.base.AbstractRedisClient;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Maps;




@Service("shoppingUserAddressManager")
public class ShoppingUserAddressManagerImpl extends EntityServiceImpl<ShoppingUserAddress, ShoppingUserAddressDao> implements ShoppingUserAddressManager {
	private static final Logger logger = LoggerFactory.getLogger(UserInfoReplyManagerImpl.class);
	
	@Autowired
	private AppInfoDao appInfoDao;
	@Autowired
    private UserSessionManager userSessionManager;
	@Autowired
	private RegionDao regionDao;
    @Autowired
    private AbstractRedisClient abstractRedisClient;
    @Autowired
    private BasicService basicService;
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(ShoppingUserAddress.STATUS99, idList);
	}
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int updateStatus(Byte status,Long... ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(status, idList);
	}
	/**
	 * 添加收货地址
	 */
	@Override
	@Transactional(value="transactionManager")
	public Byte addShoppingUserAddress(Long appId,String token,String sessionId,ShoppingUserAddress shoppingUserAddress){
		    //查询 app 是否存在
			AppInfo appInfo = appInfoDao.get(appId);
			Byte status=null; 
			if (appInfo == null) {	//客户端不存在
				throw new BusinessException(
						ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
						ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
			}
			
			Date now = DateUtil.now();
			//查询用户
			Long userId;
			String fulladdress="";
			UserSession userSession = userSessionManager.getByToken(token);
			if (userSession == null) {	//用户未登录
				throw new BusinessException(
						ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), 
						ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
			}
			userId =userSession.getUserId();
			List<ShoppingUserAddress> shoppingAdd= this.getEntityDao().selectByAu(appId,userId);
			//修改默认地址状态
			if(shoppingUserAddress.getIsDefault()==1){
				getEntityDao().updateStatusBef(appId,userId);
			}
			
			if(shoppingUserAddress.getRegionLevelOneId()!=null){
				Region region1=basicService.getRegionByCode(Long.toString(shoppingUserAddress.getRegionLevelOneId()));
				shoppingUserAddress.setRegionLevelOneName(region1.getName());
				fulladdress+=region1.getName();
			}
			if(shoppingUserAddress.getRegionLevelTwoId()!=null){
				Region region2=basicService.getRegionByCode(Long.toString(shoppingUserAddress.getRegionLevelTwoId()));
				shoppingUserAddress.setRegionLevelTwoName(region2.getName());
				fulladdress+=region2.getName();
			}
			if(shoppingUserAddress.getRegionLevelThreeId()!=null){
				Region region3=basicService.getRegionByCode(Long.toString(shoppingUserAddress.getRegionLevelThreeId()));
				shoppingUserAddress.setRegionLevelThreeName(region3.getName());
				fulladdress+=region3.getName();
			}
			fulladdress+=shoppingUserAddress.getAddress();
			if(shoppingAdd.size()==0){
				shoppingUserAddress.setIsDefault((byte) 1);
			}
			status = UserInfoReply.STATUS3;
			shoppingUserAddress.setFullAddress(fulladdress);
			shoppingUserAddress.setUserId(userId);
			shoppingUserAddress.setCreateTime(now);
			shoppingUserAddress.setUpdateTime(now);
			shoppingUserAddress.setStatus(status);
			save(shoppingUserAddress);
		    return status;
	}
	/**
	 * 查询收货地址列表
	 */
	public CommonListResult<ShoppingUserAddress> queryAdressPage(Long appId,String sessionId,String token,Long lastId){
		CommonListResult<ShoppingUserAddress> result = new CommonListResult<ShoppingUserAddress>();
		//查询用户
		UserSession userSession = userSessionManager.getByToken(token);
		if (userSession == null) {	//用户未登录
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), 
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
		}
		//查询数据
		ScrollPage<ShoppingUserAddress> page = new ScrollPage<ShoppingUserAddress>();
		page.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastId));
		page.setPageSize(10);
		page = getEntityDao().queryAddressPage(page, appId, userSession.getUserId());
		List<ShoppingUserAddress> list = page.getPageResults();
		result.setDataList(list);
		return result;
	}
	/**
	 * 修改收货地址
	 */
	@Override
	@Transactional(value="transactionManager")
	public void updateShoppingUserAddress(Long appId,String token,String sessionId,ShoppingUserAddress shoppingUserAddress) {
		    //查询 app 是否存在
			AppInfo appInfo = appInfoDao.get(appId);
			if (appInfo == null) {	//客户端不存在
				throw new BusinessException(
						ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
						ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
			}
			ShoppingUserAddress OldshoppingUserAddress=get(shoppingUserAddress.getId());
			Date now = DateUtil.now();
			//查询用户
			Long userId;
			UserSession userSession = userSessionManager.getByToken(token);
			if (userSession == null) {	//用户未登录
				throw new BusinessException(
						ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), 
						ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
			}
			userId = userSession.getUserId();
			if(!(OldshoppingUserAddress.getAppId().equals(appId))||!(OldshoppingUserAddress.getUserId().equals(userId))){
				throw new BusinessException(ErrorCodes.FAILURE, "修改失败");
			}
			int updateId = 0;
			String fulladdress="";
			if(shoppingUserAddress.getIsDefault()==1){
				updateId=getEntityDao().updateStatusBef(appId,userId);
			}
			if(shoppingUserAddress.getRegionLevelOneId()!=null){
				Region region1=basicService.getRegionByCode(Long.toString(shoppingUserAddress.getRegionLevelOneId()));
				OldshoppingUserAddress.setRegionLevelOneName(region1.getName());
				fulladdress+=region1.getName();
			}
			if(shoppingUserAddress.getRegionLevelTwoId()!=null){
				Region region2=basicService.getRegionByCode(Long.toString(shoppingUserAddress.getRegionLevelTwoId()));
				OldshoppingUserAddress.setRegionLevelTwoName(region2.getName());
				fulladdress+=region2.getName();
			}
			if(shoppingUserAddress.getRegionLevelThreeId()!=null){
				Region region3=basicService.getRegionByCode(Long.toString(shoppingUserAddress.getRegionLevelThreeId()));
				OldshoppingUserAddress.setRegionLevelThreeName(region3.getName());
				fulladdress+=region3.getName();
			}
			fulladdress+=shoppingUserAddress.getAddress();
			
			OldshoppingUserAddress.setAppId(appId);
			OldshoppingUserAddress.setRecivier(shoppingUserAddress.getRecivier());
			OldshoppingUserAddress.setCellphone(shoppingUserAddress.getCellphone());
			OldshoppingUserAddress.setRegionLevelOneId(shoppingUserAddress.getRegionLevelOneId());
			OldshoppingUserAddress.setRegionLevelTwoId(shoppingUserAddress.getRegionLevelTwoId());
			OldshoppingUserAddress.setRegionLevelThreeId(shoppingUserAddress.getRegionLevelThreeId());
			OldshoppingUserAddress.setRegionLevelFourId(shoppingUserAddress.getRegionLevelFourId());
			OldshoppingUserAddress.setAddress(shoppingUserAddress.getAddress());
			OldshoppingUserAddress.setFullAddress(fulladdress);
			OldshoppingUserAddress.setZip(shoppingUserAddress.getZip());
			OldshoppingUserAddress.setIsDefault(shoppingUserAddress.getIsDefault());
			OldshoppingUserAddress.setUserId(userId);
			OldshoppingUserAddress.setUpdateTime(now);
			if(updateId==1){
				getEntityDao().update(OldshoppingUserAddress);
			}
			
	}
	/**
	 * 查询收货地址详情
	 */
	@Override
	public ShoppingUserAddress queryAdressOne(Long appId, String sessionId, String token) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		//查询用户
		Long userId;
		
		//判断用户是否登录
		UserSession userSession = userSessionManager.getByToken(token);
		if (userSession == null) {	//用户未登录
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), 
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
		}
		userId = userSession.getUserId();
		ShoppingUserAddress rerault=null;
		if( this.getEntityDao().selectByAu(appId,userId).size()!=0){
			rerault = this.getEntityDao().selectByAu(appId,userId).get(0);
		}
		return rerault;
	}
	/**
	 * 删除收货地址
	 */
	@Override
	@Transactional(value="transactionManager")
	public int remove(Long appId, String sessionId, String token, List<Long> idList) {
		UserSession userSession = userSessionManager.getByToken(token);
		if (userSession == null) {	//用户未登录
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), 
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
		}
		Long userId;
		userId = userSession.getUserId();
		List<ShoppingUserAddress> ShoppingUserAddresslist= this.getEntityDao().selectId(appId, userId,idList);
		if(ShoppingUserAddresslist.size()!=0){
			List<ShoppingUserAddress> adresslist=this.getEntityDao().selectIds(appId, userId,idList);
			if(adresslist.size()!=0){
				this.getEntityDao().updateStatusUf(appId, userId,adresslist.get(0).getId());
			}
			
		}
		return this.getEntityDao().updateStatusAndUserId(ShoppingUserAddress.STATUS99, userSession.getUserId(),idList);
		
	}
	/**
	 * 获取区域列表
	 */
	@Override
	@Transactional(value="transactionManager")
	public CommonListResult<ReginDto> queryReginList() {
		CommonListResult<ReginDto> result = new CommonListResult<ReginDto>();
		List<ReginDto> list1 = abstractRedisClient.getList(CacheConstant.BASIC_REGION_STR, ReginDto.class);
		if(list1 != null){
			result.setDataList(list1);
			return result;
		}
		
		Map<String, Object> map = Maps.newHashMap();
		map.put("GT_code", 1);
		map.put("NOTEQ_pcode", 1);
		Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
		sortMap.put("hieraechy", true);
		List<Region> regionList=regionDao.query(map, sortMap);    //区域总列表
		List<ReginDto> reginList1=new ArrayList<ReginDto>();      //一级区域列表
		List<ReginDto> reginList2=new ArrayList<ReginDto>();      //二级区域列表
		
		for(int i=0;i<regionList.size();i++){
			//判断是不是一级
			if(regionList.get(i).getHieraechy().equals(1L)){
				ReginDto regin=new ReginDto();
				regin.setCode(regionList.get(i).getCode());
				regin.setName(regionList.get(i).getName());
				reginList1.add(regin);
			}
			//判断是不是二级
			if(regionList.get(i).getHieraechy().equals(2L)){
				ReginDto regin1=new ReginDto();
				regin1.setCode(regionList.get(i).getCode());
				regin1.setName(regionList.get(i).getName());
				for(int j=0;j<reginList1.size();j++){
					Long code1=reginList1.get(j).getCode();
					Long code2=Long.parseLong(regionList.get(i).getPcode());
					if(code1.equals(code2)){
						List<ReginDto> list = reginList1.get(j).getRegionList();
						if(CollectionUtils.isEmpty(list)){
							list= new ArrayList<ReginDto>();
							reginList1.get(j).setRegionList(list);
						}
						list.add(regin1);
						
					}
				}
				reginList2.add(regin1);
			}
			//判断是不是三级
			if(regionList.get(i).getHieraechy().equals(3L)){
				ReginDto regin2=new ReginDto();
				regin2.setCode(regionList.get(i).getCode());
				regin2.setName(regionList.get(i).getName());
				for(int n=0;n<reginList2.size();n++){
					if(reginList2.get(n).getCode().equals(Long.parseLong(regionList.get(i).getPcode()))){
						List<ReginDto> list2 = reginList2.get(n).getRegionList();
						if(CollectionUtils.isEmpty(list2)){
							list2= new ArrayList<ReginDto>();
							reginList2.get(n).setRegionList(list2);
						}
						list2.add(regin2);
					}
				}
			}
		}
		abstractRedisClient.set(CacheConstant.BASIC_REGION_STR, reginList1);
		result.setDataList(reginList1);
		return result;
	}

	@Override
	@Transactional(value="transactionManager")
	public void addressUf(Long appId, String sessionId, String token, Long shoppingUserAddressId) {
		UserSession userSession = userSessionManager.getByToken(token);
		if (userSession == null) {	//用户未登录
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), 
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
		}
		Long userId;
		userId = userSession.getUserId();
		ShoppingUserAddress OldshoppingUserAddress=get(shoppingUserAddressId);
		if(!(OldshoppingUserAddress.getAppId().equals(appId))||!(OldshoppingUserAddress.getUserId().equals(userId))){
			throw new BusinessException(ErrorCodes.FAILURE, "修改失败");
		}
		//int updateId = 0;
	   getEntityDao().updateStatusBef(appId,userId);
	   this.getEntityDao().updateStatusUf(appId, userId,shoppingUserAddressId);
		
		
		
	}
}
