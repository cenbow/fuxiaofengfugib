package com.cqliving.cloud.controller.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baidu.ueditor.ActionEnter;
import com.baidu.ueditor.define.BaseState;
import com.cqliving.basic.facade.PropertiesConfig;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.common.UpAndDownloadUtils;
import com.cqliving.cloud.common.UploadEnums;
import com.cqliving.cloud.common.constant.PropertyKey;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.log.aop.annotation.EduLog;
import com.cqliving.tool.common.util.StringUtil;
import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.common.util.file.FileUtils;

/**
 * Title:
 * <p>
 * Description:百度编辑器上传公共方法
 * </p>
 * Copyright (c) xhl
 * <pre>
 * 类说明：类上面不能配置RequestMapping，否则百度编辑器将不能正常创建
 *      
 * </pre>
 * 
 * @author huxiaoping
 */
@Controller
public class UEUploadController extends CommonController {

    // 日志记录
    private static Logger log = LoggerFactory.getLogger(UEUploadController.class);

    /**
     * 百度编辑器文件上传
     *
     * <pre>
     * 返回值说明：
     *     	返回：第一次请求，action为config时，返回包括请求获取配置信息是否成功，配置信息等。
     *           其他上传请求，返回BaseState对象对应的json字符串。
     * </pre>
     *
     * @param request
     *            请求参数
     * @param response
     *            响应参数
     * @param action
     *            请求名称,百度编辑器在所有上传请求都自定义了一个上传名称
     * @return 结果json串
     */
    @RequestMapping(value = "ueUpload")
    @EduLog(module = "common", moduleName = "公共模块", action = "ueUpload", actionName = "百度编辑器文件上传", paged = false)
    public void ueUpload(HttpServletRequest request, HttpServletResponse response, String action) {
        try {
            // 获取登录用户
            SessionUser user = SessionFace.getSessionUser(request);
            try {
                request.setCharacterEncoding("utf-8");
                if ("config".equalsIgnoreCase(action)) {// 创建百度编辑器需要获取对应配置
                    setJsonFormat(response);// 设置编码格式
                    String realPath = request.getSession().getServletContext().getRealPath("/");
                    log.info("realPath=" + realPath);
                    // 读取并返回配置信息
                    response.getWriter().write(new ActionEnter(request, realPath).exec());

                }else if("catchimage".equalsIgnoreCase(action)){
                	String[] httpUrl = request.getParameterValues("source[]");
                	
                	if(!StringUtil.isEmpty(httpUrl)){
                		for(String url : httpUrl){
                			this.downloadNet(url, PropertiesConfig.getString(PropertyKey.FILE_LOCAL_PATH),FileUtils.getExtensions(url));
                		}
                	}
                	
                } else if ("uploadimage".equalsIgnoreCase(action) || "uploadvideo".equalsIgnoreCase(action)
                        || "uploadscrawl".equalsIgnoreCase(action) || "uploadfile".equalsIgnoreCase(action)) {// 各种上传
                    //上传
                    String modulePath = UploadEnums.Module.COMMON.getModulePath();//公共模块
                    
                    Map<String, Object> dataMap = UpAndDownloadUtils.saveUploadFile(request,
                            PropertiesConfig.getString(PropertyKey.FILE_LOCAL_PATH), super.handleModulePath(modulePath, user),
                            user == null ? Long.MAX_VALUE : user.getUserId(),null);
                    //构造返回对象
                    BaseState state = new BaseState(true);
                    if (null!=dataMap) {
                        state.putInfo("size",null == dataMap.get("fileSize") ? 0 : Long.valueOf(dataMap.get("fileSize").toString()));
                        state.putInfo("title", "");
                        // TODO 返回的地址还需要加上前面的http前缀。目前不知道怎么获取。
                        state.putInfo("url", dataMap.get("filePath") == null ? "" : PropertiesConfig.getString(PropertyKey.FILE_URL_PATH)+dataMap.get("filePath").toString());
                        state.putInfo("type",dataMap.get("fileExtName") == null ? "" : dataMap.get("fileExtName").toString());
                        state.putInfo("original",dataMap.get("fileRealName") == null ? "" : dataMap.get("fileRealName").toString());
                    }
                    log.info("state-->" + state.toJSONString());
                    response.getWriter().print(state.toJSONString());
                }
            } catch (BusinessException e) {
                log.error("自定义异常", e);
            }
        } catch (Exception e) {
            log.error("异常", e);
        }
    }
    
    
    public void downloadNet(String httpurl,String fileLocalPath,String extName) throws MalformedURLException {
        // 下载网络文件
        int byteread = 0;

        URL url = new URL(httpurl);
        FileOutputStream fs = null;
        try {
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            
            SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.YYYYMMDDHHMMSS);
            StringBuilder paths = new StringBuilder(fileLocalPath);
            if (fileLocalPath.endsWith(File.separator)) {
                paths.append("httpimg");
            } else {
                paths.append(File.separator).append("httpimg");
            }
            paths.append(File.separator).append(sdf.format(new Date()));
            
            paths.append(File.separator).append(FileUtils.getUUID()+extName);
            String outputPath = paths.toString();
            FileUtils.createNewFile(new File(outputPath));
            fs = new FileOutputStream(outputPath);

            byte[] buffer = new byte[1204];
            while ((byteread = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteread);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
				try {
					if(fs != null)
					fs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        }
    }
}
