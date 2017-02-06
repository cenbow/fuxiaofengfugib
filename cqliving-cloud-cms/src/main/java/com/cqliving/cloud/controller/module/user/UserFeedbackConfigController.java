package com.cqliving.cloud.controller.module.user;

import java.util.Date;
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
import com.cqliving.cloud.online.config.domain.ConfigText;
import com.cqliving.cloud.online.config.service.ConfigTextService;
import com.cqliving.tool.common.Response;

/**
 * 
 * <p>Title:UserFeedbackConfigController </p>
 * <p>Description: 反馈配置管理</p>
 * <p>Company:新华龙掌媒文化传播有限公司 </p>
 * @author huxiaoping 2016年7月14日下午4:57:20
 *
 */
@Controller
@RequestMapping(value = "/module/user_feedback_config")
public class UserFeedbackConfigController extends CommonController {

    @Autowired
    private ConfigTextService configTextService;
    @Autowired
    private AppInfoService appInfoService;

    //增加-查看
    @RequestMapping(value ="to_save", method = RequestMethod.GET)
    public String toSave(HttpServletRequest request, Map<String, Object> map,Long appId){
        //处理APP下拉框
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
            map.put("appId", appId);
        }else{
            map.put("appId", user.getAppId());
        }
        return "tiles.module.user.user_feedback_config";
    }
    
    //通过appId查询
    @RequestMapping(value ="/common/query_by_appId")
    @ResponseBody
    public Response<ConfigText> updateSortNo(HttpServletRequest request, Map<String, Object> map,Long appId){
        if(null==appId){
            SessionUser user = SessionFace.getSessionUser(request);
            appId = user.getAppId();
        }
        Response<ConfigText> res = configTextService.getByAppId(appId,ConfigText.TYPE3);
        return res;
    }

    //增加-保存
    @RequestMapping(value ="save", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> save(HttpServletRequest request, Map<String, Object> map,ConfigText text){
        if(text==null || StringUtils.isBlank(text.getContent())){
            //没有记录
            return new Response<Void>(-1,"请输入反馈配置信息");
        }
        SessionUser user = SessionFace.getSessionUser(request);
        ConfigText configText = configTextService.getByAppId(null==text.getAppId()?user.getAppId():text.getAppId(),ConfigText.TYPE3).getData();
        Date now = new Date();
        if(null!=configText){
            configText.setContent(text.getContent());
            text = configText;
        }else{
            text.setType(ConfigText.TYPE3); 
            text.setAppId(null==text.getAppId()?user.getAppId():text.getAppId());
            text.setCreateTime(now);
            text.setCreator(user.getNickname());
            text.setCreatorId(user.getUserId());
        }
        text.setUpdateTime(now);
        text.setUpdator(user.getNickname());
        text.setUpdatorId(user.getUserId());
        Response<Void> res = configTextService.save(text);
        if(res.getCode() < 0){
            return new Response<Void>(-1,StringUtils.isNotBlank(res.getMessage())?res.getMessage():(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE)));
        }
        res.setMessage("保存成功!");
        return res;
    }
}