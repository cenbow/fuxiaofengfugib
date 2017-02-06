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
 * <p>Title:AppConnectUsController </p>
 * <p>Description:联系我们 </p>
 * <p>Company: 新华龙掌媒文化传播有限公司  </p>
 * @author huxiaoping 2016年7月25日上午9:57:32
 *
 */
@Controller
@RequestMapping(value = "/module/app_connect_us")
public class AppConnectUsController extends CommonController {

    @Autowired
    private ConfigTextService configTextService;
    @Autowired
    private AppInfoService appInfoService;
    
    /**
     * 增加-查看
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年7月25日上午10:58:23
     */
    @RequestMapping(value ="to_add", method = RequestMethod.GET)
    public String toAdd(HttpServletRequest request, Map<String, Object> map,Long appId){
        //处理APP下拉框
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
            map.put("appId", appId);
        }else{
            map.put("appId", user.getAppId());
        }
        return "tiles.module.app.app_connect_us_detail";
    }
    
    /**
     * 通过appId查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年7月25日上午10:58:30
     */
    @RequestMapping(value ="/common/query_by_appId")
    @ResponseBody
    public Response<ConfigText> updateSortNo(HttpServletRequest request, Map<String, Object> map,Long appId){
        if(null==appId){
            SessionUser user = SessionFace.getSessionUser(request);
            appId = user.getAppId();
        }
        Response<ConfigText> res = configTextService.getByAppId(appId,ConfigText.TYPE2);
        return res;
    }
    
    /**
     * 增加-保存
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年7月25日上午10:58:56
     */
    @RequestMapping(value ="save", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> save(HttpServletRequest request, Map<String, Object> map,ConfigText text){
        if(text==null || StringUtils.isBlank(text.getContent())){
            //没有记录
            return new Response<Void>(-1,"请输入联系我们的信息");
        }
        SessionUser user = SessionFace.getSessionUser(request);
        ConfigText configText = configTextService.getByAppId(null==text.getAppId()?user.getAppId():text.getAppId(),ConfigText.TYPE2).getData();
        Date now = new Date();
        if(null!=configText){
            configText.setContent(text.getContent());
            text = configText;
        }else{
            text.setType(ConfigText.TYPE2); 
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
