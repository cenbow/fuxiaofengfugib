package com.cqliving.cloud.controller.module.wz;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.wz.domain.WzQuestionCollectInfo;
import com.cqliving.cloud.online.wz.service.WzAuthorityService;
import com.cqliving.cloud.online.wz.service.WzQuestionCollectInfoService;
import com.cqliving.tool.common.Response;

@Controller
@RequestMapping(value = "/module/wz")
public class WzAuthorityController extends CommonController {

    @Autowired
    private WzAuthorityService wzAuthorityService;
    @Autowired
    private WzQuestionCollectInfoService wzQuestionCollectInfoService;
    @Autowired
    private AppInfoService appInfoService;

    /**
     * Title:
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月16日
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(value ="wz_authority_list")
    public String list(HttpServletRequest request, Map<String, Object> map, Long appId) {
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(sessionUser.getUsertype(), sessionUser.getUserId()).getData();
        if(appId == null){
        	if(sessionUser.getAppId() == null){
        		appId = appList.get(0).getId();
        	}else{
        		appId = sessionUser.getAppId();
        	}
        }
        map.put("appList",appList);
        map.put("appId", appId);
        map.put("list", wzAuthorityService.list(appId).getData());
        return "tiles.module.wz.wz_authority_list";
    }
    
    /**
     * Title:验证信息收集是否已经关联数据，已经关联返回true
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月13日
     * @param request
     * @param collentId
     * @return
     */
    @ResponseBody
    @RequestMapping(value="collectValidate", method=RequestMethod.POST)
    public Response<Boolean> collectValidate(HttpServletRequest request, Long collentId){
        Response<Boolean> rs = Response.newInstance();
        List<WzQuestionCollectInfo> list = wzQuestionCollectInfoService.getInfoByCollect(null, collentId).getData();
        rs.setData(list != null &&list.size() > 0);
        return rs;
    }
    
    /**
     * Title:
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月16日
     * @param request
     * @param map
     * @param authorityId
     * @param collectInfoId
     * @param collectInfoName
     * @return
     */
    @ResponseBody
    @RequestMapping(value="wz_authority_save", method = RequestMethod.POST)
    public Response<Void> save(HttpServletRequest request, @RequestParam Map<String, Object> map, @RequestParam Long appId, @RequestParam String[] authorityId, @RequestParam String[] collectInfoId, @RequestParam String[] collectInfoName, @RequestParam Byte[] isRequired){
        Response<Void> rs = Response.newInstance();
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        rs = wzAuthorityService.saveAuthority(appId, sessionUser.getUserId(), sessionUser.getNickname(), map, authorityId, collectInfoId, collectInfoName, isRequired);
        return rs;
    }
}
