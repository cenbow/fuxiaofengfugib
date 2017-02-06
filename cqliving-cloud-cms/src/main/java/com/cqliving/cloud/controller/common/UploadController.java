/**
 * Copyright (c) 2009 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.controller.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqliving.basic.facade.PropertiesConfig;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.common.UpAndDownloadUtils;
import com.cqliving.cloud.common.constant.PropertyKey;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.log.aop.annotation.EduLog;
import com.cqliving.tool.common.Response;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2013-2016
 * @author fuxiaofeng on 2016年1月25日
 */
public class UploadController extends CommonController{
	
	private static Logger log = LoggerFactory.getLogger(UploadController.class);

	/**
	 * 公共文件上传
	 *
	 * <pre>
	 * 注意事项：如果上传多个文件时，任意一个出现异常，将导致全部失败！！！
	 *
	 * 结果Map说明：a)上传单个文件时
	 *     				返回：	data.put("fileRealName", fileName);
	 *							data.put("fileName", destFile.getName());
	 *                          data.put("fileSize", destFile.length());
	 *                          data.put("fileExtName", getExtensions(fileName));
	 *                          data.put("filePath", outputPath);
	 *                          data.put("uploadDate", DateUtil.formatDate(new Date()));
	 *              b）上传多个文件时
	 *              	返回：	data.put(索引，单个上传文件的返回值);
	 * </pre>
	 *
	 * @param request 请求参数
	 * @param imageName 指定图片名称，可空
	 * @return 结果Map
	 */
	@RequestMapping(value = "/upload")
    @EduLog(module="Upload", moduleName="公共文件上传" ,action="post", actionName="公共文件上传", paged = false)
	@ResponseBody
	public Response<Map<String,Object>> upLoad(HttpServletRequest request, HttpServletResponse response,String imageName) {
		Response<Map<String,Object>> result = new Response<Map<String,Object>>();
		try {
			// 获取登录用户
			SessionUser user = SessionFace.getSessionUser(request);
			if(null==user){
				response.sendRedirect(request.getContextPath()+"/login.html");
				return null;
			}
			try {
				String modulePath = getModulePath();
				Map<String, Object> data = UpAndDownloadUtils.saveUploadFile(request,PropertiesConfig.getString(PropertyKey.FILE_LOCAL_PATH),super.handleModulePath(modulePath, user),user.getUserId(),imageName);

				result.setData(data);
			} catch (BusinessException e) {
				result.setMessage(e.getMessage());
				result.setCode(-1);
			}
		} catch (Exception e) {
			log.debug(e.getMessage(), e);
			result.setMessage("系统异常");
		}
		return result;
	}

	public String getModulePath(){
		return "common";
	}
}
