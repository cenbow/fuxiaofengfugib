package com.cqliving.cloud.controller.module.info;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import com.cqliving.cloud.common.Constant;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.info.dto.InfoSliderConfigDto;
import com.cqliving.cloud.online.info.service.InfoSliderConfigService;
import com.cqliving.tool.common.Response;

@Controller
@RequestMapping(value = "/module/info_slider_config")
public class InfoSliderConfigController extends CommonController {

    @Autowired
    private InfoSliderConfigService infoSliderConfigService;
    
    @Autowired
    private AppInfoService appInfoService;

    //增加-查看
    @RequestMapping(value ="info_slider_config_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map,Long appId){
        //查询出管理员多有的app信息
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }
        map.put("appId", appId);
    	return getReturnUrl(request,map,"tiles.module.info.info_slider_config_detail");
    }
    
    /**
     * 加载配置信息
     * @Description
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年8月30日
     */
    @RequestMapping(value ="/common/load_config_info")
    public String loadConfigInfo(HttpServletRequest request, Map<String, Object> map,Long appId){
        appId = appId==null?SessionFace.getSessionUser(request).getAppId():appId;
        List<InfoSliderConfigDto> sliderList  = infoSliderConfigService.getListByAppId(appId).getData();
        map.put("sliderList", sliderList);
        return "/module/info/load_config_info";
    }


    //增加-保存
    @RequestMapping(value ="info_slider_config_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,Long appId,Long[] id,Long columnsId[],Integer[] value ){
        SessionUser user = SessionFace.getSessionUser(request);
        appId = appId==null?user.getAppId():appId;
        //保存
        Response<Void> res = infoSliderConfigService.save(appId, id, columnsId, value, user.getUserId(), user.getNickname());
        if(res.getCode() < 0){
            return new Response<Void>(-1,StringUtils.isNotBlank(res.getMessage())?res.getMessage():(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE)));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }
}