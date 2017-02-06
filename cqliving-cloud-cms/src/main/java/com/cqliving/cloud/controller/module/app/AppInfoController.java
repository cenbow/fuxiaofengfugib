package com.cqliving.cloud.controller.module.app;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import com.cqliving.cloud.common.Constant;
import com.cqliving.cloud.common.Encryption;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.account.domain.SmsTemplate;
import com.cqliving.cloud.online.account.service.SmsTemplateService;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.app.domain.AppQiniu;
import com.cqliving.cloud.online.app.domain.AppWeather;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.app.service.AppQiniuService;
import com.cqliving.cloud.online.app.service.AppWeatherService;
import com.cqliving.cloud.online.config.domain.AppConfig;
import com.cqliving.cloud.online.config.service.AppConfigService;
import com.cqliving.cloud.online.security.domain.SysUser;
import com.cqliving.cloud.security.service.SysUserService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;


@Controller
@RequestMapping(value = "/module/app")
public class AppInfoController extends CommonController {

    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private AppWeatherService appWeatherService;
    @Autowired
    private AppQiniuService appQiniuService;
    @Autowired
    private SmsTemplateService smsTemplateService;
    @Autowired
    private AppConfigService appConfigService;
    private final String CMSEND = ".cqliving.com";

    //列表
    @RequestMapping(value ="app_info_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage) {
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        PageInfo<AppInfoDto> pageInfo = getPageInfo(request);
        //查询区分数据权限
        SessionUser user = SessionFace.getSessionUser(request);
        if(!SysUser.USERTYPE1.equals(user.getUsertype())){
            searchMap.put("EQ_id", user.getAppId());
        }
        //查询启用账户的APP数据
        //searchMap.put("EQ_status", SysUser.STATUS1);
        sortMap.put("id", false);
        map.put("pageInfo", appInfoService.queryPage(pageInfo, searchMap, sortMap).getData());
        
		//查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/app/info/app_info_list_page";
        }else{
        	return "tiles.module.app.info.app_info_list";
        }
    }

    //增加-查看
    @RequestMapping(value ="app_info_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
        map.put("CMSEND", CMSEND);
        map.put("allIsDefault", AppQiniu.allIsDefault);
        map.put("allIsDefaults", AppWeather.allIsDefaults);
        /** 短信模版类型 */
        map.put("smsTempTypes", SmsTemplate.allTypes);
        map.put("smsTempList", null);
        return "tiles.module.app.info.app_info_detail";
    }


    //增加-保存
    @RequestMapping(value ="app_info_add", method = RequestMethod.POST)
	@ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,AppInfo appInfo,AppQiniu qiniu,
            AppWeather weather,String password,String username,String nickname,String isDefaultWeather,
            Byte[] type,String[] content,String downLoadUrl){
        try{
            //主键
            appInfo.setId(null);
            //创建时间
            //appInfo.setCreateTime(new Date());
            //更新时间
            //appInfo.setUpdateTime(appInfo.getCreateTime());
            SessionUser user = SessionFace.getSessionUser(request);
            if(null!=user){
                //创建人ID
                appInfo.setCreatorId(user.getUserId());
                //创建人名称
                appInfo.setCreator(StringUtils.isBlank(user.getNickname())?user.getUsername():user.getNickname());
                //更新人ID
                appInfo.setUpdatorId(user.getUserId());
                //updator
                appInfo.setUpdator(StringUtils.isBlank(user.getNickname())?user.getUsername():user.getNickname());
            }
            qiniu.setId(null);
            weather.setId(null);
            weather.setIsDefault(StringUtils.isBlank(isDefaultWeather)?0:Byte.valueOf(isDefaultWeather.trim()));
            appInfo.setCmsDomain(appInfo.getCmsDomain()+CMSEND);
            Response<Void> res = appInfoService.saveAppInfo(appInfo, password, username,nickname,qiniu,weather,type,content,downLoadUrl);
            if(res.getCode() < 0){
                return new Response<Void>(-1,StringUtils.isNotBlank(res.getMessage())?res.getMessage():(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE)));
            }
            res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
            return res;
        }catch (Exception ex){
            logger.error("Save Method (inster) AppInfo Error : " + appInfo.toString(), ex);
            //增加失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
    }

    //修改-查看
    @RequestMapping(value ="app_info_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id
            ,@RequestParam(value = "weatherId") Long weatherId, @RequestParam(value = "qiniuId") Long qiniuId){
        AppInfo appInfo = appInfoService.get(id).getData();
        if(appInfo==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", appInfo);
        AppConfig appConfig = appConfigService.findByAppId(id).getData();
        map.put("appConfig", appConfig);
        map.put("CMSEND", CMSEND);
        map.put("allIsDefault", AppQiniu.allIsDefault);
        map.put("allIsDefaults", AppWeather.allIsDefaults);
        if(null!=weatherId){
            AppWeather appWeather = appWeatherService.get(weatherId).getData();
            map.put("weather", appWeather);
        }
        /*if(null!=qiniuId){
            AppQiniu qiniu = appQiniuService.get(qiniuId).getData();
            map.put("qiniu", qiniu);
        }*/
        /** 短信模版类型 */
        map.put("smsTempTypes", SmsTemplate.allTypes);
        List<SmsTemplate> smsTempList = smsTemplateService.getByAppId(id).getData();
        map.put("smsTempList", smsTempList);
        return "tiles.module.app.info.app_info_detail";
    }

    //修改-保存
    @RequestMapping(value ="app_info_update", method = RequestMethod.POST)
	@ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,AppInfo appInfo,
            AppQiniu qiniu,AppWeather weather,String isDefaultWeather,Long weatherId,Long qiniuId,
            Byte[] type,String[] content,String downLoadUrl){
        Response<Void> res = Response.newInstance();
        if(appInfo==null || appInfo.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            AppInfo sourceAppInfo = appInfoService.get(appInfo.getId()).getData();
            if(sourceAppInfo==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }
            
            /** 客户端名称 */
            sourceAppInfo.setName(appInfo.getName()==null?sourceAppInfo.getName():appInfo.getName());
            /** 客户端后台名称 */
            sourceAppInfo.setCmsName(appInfo.getCmsName()==null?sourceAppInfo.getCmsName():appInfo.getCmsName());
            /** 客户端LOGO */
            sourceAppInfo.setLogo(null==appInfo.getLogo()?sourceAppInfo.getLogo():appInfo.getLogo());
            /** 所在区域 */
            sourceAppInfo.setLocation(null==appInfo.getLocation()?sourceAppInfo.getLocation():appInfo.getLocation());
            /** 自定义域名 */
            sourceAppInfo.setAppDomain(null==appInfo.getAppDomain()?sourceAppInfo.getAppDomain():appInfo.getAppDomain());
            /** 后台域名 */
            sourceAppInfo.setCmsDomain(appInfo.getCmsDomain()+CMSEND);
            SessionUser user = SessionFace.getSessionUser(request);
            if(null!=user){
                //更新人ID
                sourceAppInfo.setUpdatorId(user.getUserId());
                //updator
                sourceAppInfo.setUpdator(StringUtils.isBlank(user.getNickname())?user.getUsername():user.getNickname());
            }
            /** 联系电话 */
            sourceAppInfo.setContactPhone(null==appInfo.getContactPhone()?sourceAppInfo.getContactPhone():appInfo.getContactPhone());
            /** 传真 */
            sourceAppInfo.setFax(null==appInfo.getFax()?sourceAppInfo.getFax():appInfo.getFax());
            /** 地址 */
            sourceAppInfo.setAddress(null==appInfo.getAddress()?sourceAppInfo.getAddress():appInfo.getAddress());
            /** 说明 */
            sourceAppInfo.setDescription(null==appInfo.getDescription()?sourceAppInfo.getDescription():appInfo.getDescription());
            /** 最后经纬度 */
            sourceAppInfo.setPosition(null==appInfo.getPosition()?sourceAppInfo.getPosition():appInfo.getPosition());
            /** 短信扩展码号 */
            sourceAppInfo.setSmsCode(null==appInfo.getSmsCode()?sourceAppInfo.getSmsCode():appInfo.getSmsCode());
            /** 前缀 */
            sourceAppInfo.setNamePrefix(null==appInfo.getNamePrefix()?sourceAppInfo.getNamePrefix():appInfo.getNamePrefix());
            /** 短信签名 */
            sourceAppInfo.setSmsSignature(null==appInfo.getSmsSignature()?sourceAppInfo.getSmsSignature():appInfo.getSmsSignature());
            /** 极光推送 AppKey */
            sourceAppInfo.setJpushAppKey(null==appInfo.getJpushAppKey()?sourceAppInfo.getJpushAppKey():appInfo.getJpushAppKey());
            /** 极光推送 AppSecret */
            sourceAppInfo.setJpushAppSecret(null==appInfo.getJpushAppSecret()?sourceAppInfo.getJpushAppSecret():appInfo.getJpushAppSecret());
            /** 百度地图Key */
            sourceAppInfo.setBaiduLbsKey(null==appInfo.getBaiduLbsKey()?sourceAppInfo.getBaiduLbsKey():appInfo.getBaiduLbsKey());
            /** 请求签名字符串，每个APP都不同，目前只对发送验证码、登陆和生成TOKEN接口签名 */
            sourceAppInfo.setRequestSignKey(null==appInfo.getRequestSignKey()?sourceAppInfo.getRequestSignKey():appInfo.getRequestSignKey());
            /** 是否签名，目前只对登陆和生成TOKEN接口签名做判断，不对发送验证码做判断，{1:是,0:否} */
            sourceAppInfo.setIsRequestSign(null==appInfo.getIsRequestSign()?sourceAppInfo.getIsRequestSign():appInfo.getIsRequestSign());
            
            qiniu.setId(qiniuId);
            weather.setId(weatherId);
            weather.setIsDefault(StringUtils.isBlank(isDefaultWeather)?0:Byte.valueOf(isDefaultWeather.trim()));
//            res = appInfoService.save(appInfo);
            res = appInfoService.saveAppInfo(sourceAppInfo, null, null,null,qiniu,weather,type,content,downLoadUrl);
            if(res.getCode() < 0){
                return new Response<Void>(-1,StringUtils.isNotBlank(res.getMessage())?res.getMessage():"修改失败!");
            }
        }catch (Exception ex){
            logger.error("Save Method (Update) AppInfo Error : " + appInfo.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("修改成功!");
        return res;
    }

    //查看
    @RequestMapping(value ="app_info_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id
            ,@RequestParam(value = "weatherId") Long weatherId, @RequestParam(value = "qiniuId") Long qiniuId){
        AppInfo appInfo = appInfoService.get(id).getData();
        if(appInfo==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", appInfo);
        AppConfig appConfig = appConfigService.findByAppId(id).getData();
        map.put("appConfig", appConfig);
        map.put("allIsDefault", AppQiniu.allIsDefault);
        map.put("allIsDefaults", AppWeather.allIsDefaults);
        if(null!=weatherId){
            AppWeather appWeather = appWeatherService.get(weatherId).getData();
            map.put("weather", appWeather);
        }
        if(null!=qiniuId){
            AppQiniu qiniu = appQiniuService.get(qiniuId).getData();
            map.put("qiniu", qiniu);
        }
        /** 短信模版类型 */
        map.put("smsTempTypes", SmsTemplate.allTypes);
        List<SmsTemplate> smsTempList = smsTemplateService.getByAppId(id).getData();
        map.put("smsTempList", smsTempList);
        return "tiles.module.app.info.app_info_view";
    }
    
    /**
     * 跳转到修改密码页面
     * @param request
     * @param map
     * @param userId
     * @return
     */
    @RequestMapping(value ="pwd_update", method = RequestMethod.GET)
    public String toPwdUpdate(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "userId") Long userId){
        SysUser sysUser = sysUserService.get(userId);
        if(sysUser==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", sysUser);
        return "tiles.module.app.info.pwd_update";
    }
    
    /**
     * 
     * <p>Description:修改密码 </p>
     * <p>Company: </p>
     * @param map
     * @param sysUser
     * @author huxiaoping 2016年4月20日上午10:08:01
     * @return
     */
    @RequestMapping(value ="pwd_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> pwdUpdate(HttpServletRequest request, Map<String, Object> map,SysUser sysUser,String confPwd){
        Response<Void> res = Response.newInstance();
        if(null!=sysUser&&null!=sysUser.getId()&&null!=sysUser.getUsername()){
            if(!(StringUtils.isNotBlank(confPwd)&&StringUtils.isNotBlank(sysUser.getPassword()))){
                return new Response<Void>(-1,"请输入密码");
            }
            if(!confPwd.equals(sysUser.getPassword())){
                return new Response<Void>(-1,"密码不一致!");
            }
            SysUser user = sysUserService.get(sysUser.getId());
            if(null==user){
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }
            user.setUsername(sysUser.getUsername());
            if(StringUtils.isNotBlank(sysUser.getPassword())){
                user.setPassword(sysUser.getPassword());
                Encryption.entryptPassword(user);
            }
            user.setCreateDate(new Date());
            sysUserService.save(user);
        }else{
            return new Response<Void>(-1,"请填写正确的用户信息");
        }
        res.setMessage("修改成功！");
        return res;
    }
    
    //删除
    @RequestMapping(value ="app_info_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = appInfoService.delete(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="app_info_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = appInfoService.delete(ids);
        return res;
    }
}