package com.cqliving.cloud.controller.module.security;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.message.domain.MessageReceive;
import com.cqliving.cloud.online.message.dto.MessageInfoDto;
import com.cqliving.cloud.online.message.service.MessageInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.manager.security.shiro.realm.SampleRealm;
import com.google.common.collect.Maps;

/**
 * @author junsansi
 * Date: 2015-3-3
 */
@Controller
@RequestMapping("/module/security")
public class SecurityIndexController extends CommonController {

//	@Autowired
//	private  SysUserService sysUserService;
	@Autowired
    private MessageInfoService messageInfoService;

    @Autowired
    private SampleRealm sampleRealm;


	@SuppressWarnings("rawtypes")
	@RequestMapping("index")
	public String index(HttpServletRequest request, Map<String, Object> map){
//		SysUser user = sysUserService.findByUsername(SessionFace.getSessionUser(request).getUsername());
		//默认显示登陆用户的帐户信息
//		map.put("item", user);
	    SessionUser user = SessionFace.getSessionUser(request);
		Map<String, Object> searchMap = new TreeMap<String, Object>();
        PageInfo<MessageInfoDto> pageInfo = new PageInfo<MessageInfoDto>(5, 1);
        searchMap.put("EQ_readStatus", MessageReceive.STATUS0);//排除逻辑删除状态
        // 接收人
        searchMap.put("EQ_receiverId", user.getUserId());
        
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        //按照未读状态排序
        sortMap.put("readStatus", true);
        sortMap.put("createTime", false);
        
        map.put("pageInfo", messageInfoService.queryLetterForPage(pageInfo, searchMap, sortMap).getData());

        sampleRealm.clearCachedAuthorizationInfo("");
        for(Iterator it = SecurityUtils.getSubject().getPrincipals().iterator(); it.hasNext();){
            System.out.println(it.next());
        }

		//return "index_security";
        return "tiles.module.security.index";
	}
}
