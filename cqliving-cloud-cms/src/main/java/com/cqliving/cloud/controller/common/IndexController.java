package com.cqliving.cloud.controller.common;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.security.service.SysUserService;

/**
 * com.CQLIVING.study.cms.web.controller.
 * User: wangyx
 * Date: 14-11-7
 * Time: 下午4:25
 */
@Controller
public class IndexController extends CommonController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) throws IOException{
        return "login";
    }

    @RequestMapping(value = "/common/error/500")
    public String error500(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) throws IOException{
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	if(sessionUser == null){
            logger.info("isLoginRequest----error500--jsp------>>>>>>>>>");
    		return "/error/500";
    	}else{
            logger.info("isLoginRequest----error--tiles------>>>>>>>>>");
    		return "tiles.error.500";
    	}
    }
    
    @RequestMapping(value = "/common/error/404")
    public String error404(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) throws IOException{
        return "tiles.error.404";
    }
    
    @RequestMapping(value = "/common/error/403")
    public String error403(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) throws IOException{
        return "tiles.error.403";
    }
}
