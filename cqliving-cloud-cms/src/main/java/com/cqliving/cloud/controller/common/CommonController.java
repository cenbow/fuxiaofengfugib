package com.cqliving.cloud.controller.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.cqliving.cloud.common.Constant;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.security.domain.SysUser;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.util.StringUtil;

/**
 * com.CQLIVING.eord.web.controller. User: wangyx Date: 14-11-22 Time: 上午10:33
 */
public class CommonController {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public static final String SEARCHE_PREFIX = "search_";
	/**
	 * 初始化绑定数据，加入字符串转日期
	 * modify by yuwu 20160714，该方法只能注册一种格式的时间字符串，替代方法见StringToDateConverter。
	 * @param request
	 * @param binder
	 */
//	@InitBinder
//	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
//		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		CustomDateEditor dateEditor = new CustomDateEditor(format, true);
//		binder.registerCustomEditor(Date.class, dateEditor);
//		
//		DateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		CustomDateEditor dateTimeEditor = new CustomDateEditor(formatTime, true);
//		binder.registerCustomEditor(Date.class, dateTimeEditor);
//		
//		DateFormat formatHourMinite = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		CustomDateEditor dateHourMiniteEditor = new CustomDateEditor(formatHourMinite, true);
//		binder.registerCustomEditor(Date.class, dateHourMiniteEditor);
//	}

	/**
	 * 分页
	 * 
	 * @param request
	 * @param pageSize
	 * @return
	 */
	protected <T> PageInfo<T> getPageInfo(HttpServletRequest request, int pageSize) {
		int currentPage = 1;
		if (StringUtils.isNotBlank(request.getParameter("pageNo"))) {
			try {
				currentPage = Integer.parseInt(request.getParameter("pageNo"));
			} catch (Exception ex) {
			}
		}
		if (StringUtils.isNotBlank(request.getParameter("countOfCurrentPage"))) {
			pageSize = Integer.parseInt(request.getParameter("countOfCurrentPage"));
			if(pageSize > 100){
				pageSize = 100;
			}
		}
		PageInfo<T> pageInfo = new PageInfo<T>(pageSize, currentPage);
		return pageInfo;
	}

	/**
	 * 分页
	 * 
	 * @param request
	 * @return
	 */
	protected <T>PageInfo<T> getPageInfo(HttpServletRequest request) {
		return getPageInfo(request, 10);
	}

	/**
	 * <p>
	 * Description: 分页对象
	 * </p>
	 * 
	 * @param request
	 * @param pageSize
	 * @return
	 */
	protected Pageable getPageable(HttpServletRequest request, int pageSize) {
		int currentPage = 1;
		if (StringUtils.isNotBlank(request.getParameter("pageNo"))) {
			try {
				currentPage = Integer.parseInt(request.getParameter("pageNo"));
			} catch (Exception ex) {
			}
		}
		return new PageRequest((currentPage - 1), pageSize);
	}

	/**
	 * <p>
	 * Description:分页对象
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	protected Pageable getPageable(HttpServletRequest request) {
		return getPageable(request, 10);
	}

	protected <T> PageInfo<T> asPageInfo(Page<T> page) {
		PageInfo<T> pageInfo = new PageInfo<T>();
		pageInfo.setCountOfCurrentPage(page.getSize());
		pageInfo.setCurrentPage(page.getNumber() + 1);
		pageInfo.setTotalCount(page.getTotalElements());
		pageInfo.setPageResults(page.getContent());
		return pageInfo;
	}

	/**
	 * 操作成功提示
	 * 
	 * @param msg
	 *            - 提示信息
	 * @param location
	 *            - 跳转页面
	 * @param map
	 * @return
	 */
	protected String operSuccess(String msg, String location, Map<String, Object> map) {
		map.put("alertinfo", Constant.Application.COMMON_SUCCESS_ALERTINFO);
		map.put("msg", msg);
		map.put("location", location);
		return "tiles.error.500";
	}

	/**
	 * 操作失败提示
	 * 
	 * @param msg
	 *            - 提示信息
	 * @param map
	 * @return
	 */
	protected String operFailure(String msg, Map<String, Object> map) {
		map.put("alertinfo", Constant.Application.COMMON_DANGER_ALERTINFO);
		map.put("msg", msg);
		return "tiles.error.500";
	}
	
	/**
     * 设置json编码格式.
     * 
     * @version
     */
	protected void setJsonFormat(HttpServletResponse response) {
        response.setContentType("text/json; charset=utf-8");
        response.setCharacterEncoding("utf-8");
    }
	
	/**
     * 区分数据权限
     * 
     * @version
     */
	protected void userDate(SessionUser user,List<AppInfoDto> appList,Map<String, Object> searchMap){
	    //非超管用户才需要in查询其所有app下的数据
        if(!SysUser.USERTYPE1.equals(user.getUsertype())){
            if(null!=appList&&appList.size()>1){
                List<Long> appIds = new ArrayList<Long>();
                for (AppInfoDto app : appList) {
                    appIds.add(app.getId());
                }
                searchMap.put("IN_appId", appIds);
            }else{
                searchMap.put("EQ_appId", user.getAppId());
            }
        }
	}
	
	//特殊处理
	protected String handleModulePath(String modulePath,SessionUser sessionUser){
    	
		if(StringUtil.isEmpty(modulePath))modulePath = "common";
		
		StringBuilder modulePathBuilder = new StringBuilder();
		modulePathBuilder.append("app_").append(null == sessionUser.getAppId() ? 0 : sessionUser.getAppId());
		modulePathBuilder.append(File.separator).append("cms");
		//modulePathBuilder.append(File.separator).append(DateUtil.formatDate(Dates.now(), DateUtil.FORMAT_YYYY_MM_DD));
		//modulePathBuilder.append(File.separator).append(modulePath);
		return modulePathBuilder.toString();
    }
	
	/**
	 * Title:根据参数_model_判断返回jsp页面或title模板
	 * @author yuwu on 2016年7月7日
	 * @param request
	 * @param map 设置返回页面的参数
	 * @param url 返回的页面
	 * @return
	 */
	protected String getReturnUrl(HttpServletRequest request,Map<String, Object> map,String url){
    	String _model_ = request.getParameter("_model_");
    	if(StringUtils.isNotBlank(_model_)){
        	map.put("_model_", _model_);
        	//return "/module/shoot/shoot_detail";
        	return url.substring(url.indexOf(".")).replace(".", "/");
        }else{
        	//tiles.module.shoot.shoot_detail
        	return url;
        }
    }
}