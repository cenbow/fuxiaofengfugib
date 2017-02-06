package com.cqliving.cloud.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.basic.facade.PropertiesConfig;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.common.constant.BusinessType;
import com.cqliving.cloud.common.constant.PropertyKey;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.security.dto.MenuView;
import com.cqliving.cloud.security.cache.SecurityCache;
import com.cqliving.cloud.security.service.SysMenuService;
import com.cqliving.tool.common.util.StringUtil;

/**
 * 菜单高亮设置
 */
@Service
public class NavFilter implements Filter {

	private Logger log = LoggerFactory.getLogger(NavFilter.class);

    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SecurityCache securityCache;
    @Autowired
    private AppInfoService appInfoService;
    
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

        //加载菜单
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        if(sessionUser!=null){
        	List<MenuView> list = sysMenuService.queryAuthorisedMenus(sessionUser.getUsername());
        	request.setAttribute("authorisedMenus",list);
        }else{
        	securityCache.clearAllAuthorization();
        	securityCache.clearAllMenu();
        }
        //导航高亮控制
        String navLm=request.getParameter("navLm");
        String navPid=request.getParameter("navPid");
        
        //左面菜单
        if(StringUtils.isNotBlank(navLm)){
            SessionFace.setAttribute(request, "navLm", navLm);
        }
        if(StringUtils.isNotBlank(navPid)){
        	SessionFace.setAttribute(request, "navPid", navPid);
        }
        //属性
        setStudyProperty(request);
        //根据域名取APP信息
        setAppInfo(request);
        
		chain.doFilter(request, response);
	}

    @Override
    public void destroy() {

    }

    
    /**
	 * Title:
	 * <p>Description:将图片地址存放在session中</p>
	 * @param httpRequest
	 */
	public void setStudyProperty(HttpServletRequest httpRequest){
		// 图片服务器地址，供页面展示用,例：http://localhost:8080
		String imageUrl = (String) httpRequest.getSession().getAttribute("imageUrl");
		String fileUrlPath = PropertiesConfig.getString(PropertyKey.FILE_URL_PATH);
		if (StringUtil.isEmpty(imageUrl) || (!StringUtil.isEmpty(fileUrlPath) && !fileUrlPath.equals(imageUrl))) {
			try {
				httpRequest.getSession().setAttribute("imageUrl",fileUrlPath);
			} catch (Exception ex) {
				log.error("NavFilter过滤器中，取图片地址--->资源配置---->失败：", ex);
			}
		}
		
		String webUrlPath = (String) httpRequest.getSession().getAttribute("webUrlPath");
		String webPath = PropertiesConfig.getString(PropertyKey.WEB_URL_PATH);
		
		if (StringUtil.isEmpty(webUrlPath) || (!StringUtil.isEmpty(webPath) && !webPath.equals(webUrlPath))) {
			try {
				httpRequest.getSession().setAttribute("webUrlPath",webPath);
			} catch (Exception ex) {
				log.error("NavFilter过滤器中，取前台网站地址--->资源配置---->失败：", ex);
			}
		}
		
		//获取图片规格尺寸,返回具体规格：200x300
		//取法示例：${_imgConfigSize_[1].thumb[0]}
		Map<Long,Map<String,String[]>> imgConfigSize = (Map<Long,Map<String,String[]>>) httpRequest.getSession().getAttribute("_imgConfigSize_");
		if(null == imgConfigSize || imgConfigSize.isEmpty()){
			try {
				httpRequest.getSession().setAttribute("_imgConfigSize_",BusinessType.getImgConfig());
			} catch (Exception ex) {
				log.error("NavFilter过滤器中，取图片规格尺寸--->资源配置---->失败：", ex);
			}
		}
		
		//获取图片规格尺寸参数,返回：imgsize=30&thumb=300x300,200
		//取法示例：${_imgConfig_[1]}
		Map<Long, String> imgConfig = (Map<Long, String>) httpRequest.getSession().getAttribute("_imgConfig_");
		if(null == imgConfig || imgConfig.isEmpty()){
			try {
				httpRequest.getSession().setAttribute("_imgConfig_",BusinessType.IMG_THUMB_SIZE);
			} catch (Exception ex) {
				log.error("NavFilter过滤器中，取图片规格尺寸--->资源配置---->失败：", ex);
			}
		}
	}
	
	public boolean setAppInfo(HttpServletRequest request){
		
		AppInfo sessionAppInfo = (AppInfo) SessionFace.getAttribute(request, SessionFace.APPINFO_SESSION_KEY);
		
		String servletName = request.getServerName().toLowerCase();
		
		//测试用
		String temp_appid = request.getParameter("temp_appid");
		if(!StringUtil.isEmpty(temp_appid)){
			Long appId = StringUtil.stringToLong(temp_appid);
		    AppInfo appInfo = appInfoService.get(appId).getData();
		    if(null != appInfo){
		    	servletName = appInfo.getCmsDomain();
		    }
		}
		
		AppInfo appInfo = appInfoService.findByDomain(servletName).getData();
		if(null == appInfo){
			appInfo = new AppInfo();
			appInfo.setName("重庆新华龙掌媒平台");
		}
		
		if(null != sessionAppInfo){
			
			String sessionDomain = sessionAppInfo.getCmsDomain();
			if(!StringUtil.isEmpty(servletName) && !StringUtil.isEmpty(sessionDomain) && servletName.equals(sessionDomain)){
				return false;
			}
		}
		
		SessionFace.setAttribute(request, SessionFace.APPINFO_SESSION_KEY, appInfo);
		return true;
	}
}



