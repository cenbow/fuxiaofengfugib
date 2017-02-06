package com.cqliving.cloud.controller.module.security;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import com.cqliving.cloud.common.Constant;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.app.domain.AppColumns;
import com.cqliving.cloud.online.app.dto.AppColumnsDto;
import com.cqliving.cloud.online.app.dto.TreeNodeState;
import com.cqliving.cloud.online.app.service.AppColumnsService;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.security.domain.SysUser;
import com.cqliving.cloud.online.security.domain.SysUserData;
import com.cqliving.cloud.online.security.dto.SysUserDataDto;
import com.cqliving.cloud.online.security.service.SysUserDataService;
import com.cqliving.cloud.online.shop.service.ShopTypeService;
import com.cqliving.cloud.security.service.SysUserService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.framework.utils.mapper.JsonMapper;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.StringUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/security/sys_user_data")
public class SysUserDataController extends CommonController {

    @Autowired
    private SysUserDataService sysUserDataService;
    @Autowired
    private AppColumnsService appColumnsService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ShopTypeService shopTypeService;
    @Autowired
    private AppInfoService appInfoService;
    
    private static final String APP_COLUMN = "app_column";
    private static final String SHOP_TYPE = "shop_type";
    private static final String APP_COLUMN_NAME = "APP_COLUMN_NAME";
    private static final String APP_COLUMN_ID = "APP_COLUMN_ID";
    //列表
    @RequestMapping(value ={"app_column_list","shop_type_list"})
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
    	
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
		byte type = this.getDataType(request);
		searchMap.put("EQ_type",type);
		
		List<Long> userIds = getAppUserIds(sysUserService.findByAppId(sessionUser.getAppId()));
		if(CollectionUtils.isEmpty(userIds) && null == sessionUser.getAppId() && SysUser.USERTYPE1.byteValue() == sessionUser.getUsertype().byteValue()){
			userIds = getAppUserIds(sysUserService.getAll());
			request.setAttribute("allApp",appInfoService.getAll().getData());
		}
		searchMap.put("IN_userId",userIds);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        PageInfo<SysUserDataDto> pageInfo = getPageInfo(request);
        map.put("pageInfo", sysUserDataService.findPage(pageInfo, searchMap, sortMap).getData());
        request.setAttribute("dataType",type);
        request.setAttribute("addUri",getAddUrl(type));
        request.setAttribute("deleteUri",getDeleteUrl(type));
        request.setAttribute("thisUri",request.getRequestURI());
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/security/sys_user_data_list_page";
        }else{
        	return "tiles.module.security.sys_user_data_list";
        }
    }

    private List<Long> getAppUserIds(List<SysUser> sysUsers){
    	
    	if(CollectionUtils.isEmpty(sysUsers))return null;
    	List<Long> userIds = Lists.newArrayList();
    	Iterator<SysUser> it = sysUsers.iterator();
    	while(it.hasNext()){
    		
    		SysUser sysUser = it.next();
    		userIds.add(sysUser.getId());
    	}
    	return userIds;
    }
    
    private Map<String,String[]> getColumnMap(List<AppColumnsDto> appColumns ){
    	
    	if(CollectionUtils.isEmpty(appColumns))return null;
    	List<String> columnNames = Lists.newArrayList();
    	List<String> columnsId = Lists.newArrayList();
    	Map<String,String[]> map = Maps.newHashMap();
    	this.iteratorColumns(appColumns,columnsId,columnNames);
    	map.put(APP_COLUMN_NAME, columnNames.toArray(new String[]{}));
    	map.put(APP_COLUMN_ID, columnsId.toArray(new String[]{}));
    	return map;
    }
    
    private void iteratorColumns(List<AppColumnsDto> appColumns,List<String> columnsId,List<String> columnNames){
    	//获取已选中的栏目名称
    	if(CollectionUtils.isEmpty(appColumns))return;
    	Iterator<AppColumnsDto> it = appColumns.iterator();
    	while(it.hasNext()){
    		AppColumnsDto dto = it.next();
    		TreeNodeState treeNodeState = dto.getState();
    		if(treeNodeState.isSelected()){
    			columnNames.add(dto.getName());
    			columnsId.add(String.valueOf(dto.getId()));
    		}
    		if(CollectionUtils.isNotEmpty(dto.getNodes())){
    			iteratorColumns(new ArrayList<AppColumnsDto>(dto.getNodes()),columnsId,columnNames);
    		}
    	}
    }
    
    //栏目数据权限增加-查看
    @RequestMapping(value ={"app_column_add"}, method = RequestMethod.GET)
    public String appColumnAdd(HttpServletRequest request, Map<String, Object> map,Long userId){
    	
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	Long appId = sessionUser.getAppId();
    	if(null == appId && SysUser.USERTYPE1.byteValue() == sessionUser.getUsertype().byteValue()){
    		request.setAttribute("allApp",appInfoService.getAll().getData());
    	}
    	//查找栏目数据
    	Map<String,Object> conditions = Maps.newHashMap();
    	conditions.put("EQ_appId", appId);
    	conditions.put("appendAll",false);
    	conditions.put("EQ_columnsType", AppColumns.COLUMNSTYPE0);
    	conditions.put("sysUserDataValue", sysUserDataService.findValueByUserId(userId,this.getDataType(request)).getData());
    	List<AppColumnsDto> appColumns = appColumnsService.getByConditions(conditions).getData();
    	request.setAttribute("appColumnsJson",JsonMapper.nonDefaultMapper().toJson(appColumns));
    	request.setAttribute("appColumnMap",getColumnMap(appColumns));
    	byte type = this.getDataType(request);
    	request.setAttribute("listUri", this.getListUri(type));
    	
    	request.setAttribute("sysUsers",sysUserService.findByAppId(sessionUser.getAppId()));
    	request.setAttribute("userId",userId);
    	return getReturnUrl(request,map,"tiles.module.security.sysuserdata_appcolumn_detail");
    }

    //商情分类增加-查看
    @RequestMapping(value ={"shop_type_add"}, method = RequestMethod.GET)
    public String shopTypeAdd(HttpServletRequest request, Map<String, Object> map,Long userId){
    	
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	Long appId = sessionUser.getAppId();
    	if(null == appId && SysUser.USERTYPE1.byteValue() == sessionUser.getUsertype().byteValue()){
    		request.setAttribute("allApp",appInfoService.getAll().getData());
    	}
    	byte type = this.getDataType(request);
    	request.setAttribute("listUri", this.getListUri(type));
    	request.setAttribute("sysUsers",sysUserService.findByAppId(sessionUser.getAppId()));
    	request.setAttribute("userId",userId);
    	//查找商铺分类
    	request.setAttribute("shopTypes",shopTypeService.getByApp(appId).getData());
    	//查找用户商铺分类权限
    	Long[] userShopType = sysUserDataService.findValueByUserId(userId,this.getDataType(request)).getData();
    	if(!StringUtil.isEmpty(userShopType)){
    		String[] sr = new String[userShopType.length];
    		for(int i=0;i<userShopType.length;i++){
    			sr[i] = String.valueOf(userShopType[i]);
    			request.setAttribute("userShopType",sr);
    		}
    	}
    	return getReturnUrl(request,map,"tiles.module.security.sysuserdata_shoptype_detail");
    }
    
    //增加-保存
    @RequestMapping(value ={"app_column_add","shop_type_add"}, method = RequestMethod.POST)
    @ResponseBody
    public Response<List<SysUserData>> postAdd(HttpServletRequest request, Map<String, Object> map,@RequestParam Long userId,@RequestParam String dataValues,@RequestParam Byte type){
        
    	String[] value = dataValues.split(",");
    	Response<List<SysUserData>> res =  sysUserDataService.saveSysUserDataByType(userId,strTolon(value), type);        
        if(res.getCode() < 0){
        	return new Response<List<SysUserData>>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }
    
    //删除用户权限
    @RequestMapping(value ={"app_column_delete","shop_type_delete"}, method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteSysUserData(HttpServletRequest request,@RequestParam Long userId){
    	
    	byte type = this.getDataType(request);
    	return sysUserDataService.deleteByUserIdAndType(userId, type);
    }
    
    private byte getDataType(HttpServletRequest request){
    	byte type = 2;
    	String uri = request.getRequestURI();
    	if(uri.contains(APP_COLUMN)){
    		type = 2;
    	}else if(uri.contains(SHOP_TYPE)){
    		type = 3;
    	}
    	return type;
    }
    
    private String getAddUrl(byte type){
    	
    	String uri = "/module/security/sys_user_data/app_column_add.html";
    	if(3 == type ){
    		uri = "/module/security/sys_user_data/shop_type_add.html";
    	}
    	return uri;
    }
    private String getDeleteUrl(byte type){
    	String uri = "/module/security/sys_user_data/app_column_delete.html";
    	if(3==type){
    		uri = "/module/security/sys_user_data/shop_type_delete.html";
    	}
    	return uri;
    }
    private String getListUri(byte type){
    	String uri = "/module/security/sys_user_data/app_column_list.html";
    	if(3 == type ){
    		uri = "/module/security/sys_user_data/shop_type_list.html";
    	}
    	return uri;
    }
    
    private Long[] strTolon(String[] value){
    	
    	if(StringUtil.isEmpty(value))return null;
    	Long[] ids = new Long[value.length];
    	for(int i=0;i<value.length;i++){
    		ids[i] = StringUtil.stringToLong(value[i]);
    	}
    	return ids;
    }
    
    //根据appId查找用户
    @RequestMapping(value="common/sys_user_appid")
    @ResponseBody
    public Response<List<SysUser>> findAppId(HttpServletRequest request,@RequestParam Long appId){
    	
    	Response<List<SysUser>> rp = Response.newInstance();
    	try {
			rp.setData(sysUserService.findByAppId(appId));
		} catch (Exception e) {
			rp.setCode(ErrorCodes.FAILURE);
			rp.setMessage(e.getMessage());
			e.printStackTrace();
		}
    	return rp;
    }
}
