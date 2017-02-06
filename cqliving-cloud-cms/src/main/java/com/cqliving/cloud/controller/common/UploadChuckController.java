/**
 * Copyright (c) 2009 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.controller.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cqliving.basic.facade.PropertiesConfig;
import com.cqliving.cloud.common.constant.PropertyKey;
import com.cqliving.cloud.online.info.domain.InfoFile;
import com.cqliving.cloud.online.info.service.InfoFileService;
import com.cqliving.log.aop.annotation.EduLog;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2013-2016
 * @author fuxiaofeng on 2016年1月25日
 */
@Controller
@RequestMapping(value = "/common")
public class UploadChuckController extends CommonController{
	
	private static Logger log = LoggerFactory.getLogger(UploadChuckController.class);
	private final static String UPLOAD_TEMP = "uploadTemp";
	private final static String UPLOAD_TEMP_EXT = ".part";//分片文件后缀
	
	@Autowired
    private InfoFileService infoFileService;

	/**
	 * Title:检查单个文件的MD5
	 * @author yuwu on 2016年5月7日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/checkFileMD5")
    @EduLog(module="Upload", moduleName="公共文件上传" ,action="post", actionName="公共文件上传", paged = false)
    @ResponseBody
    public Response<Map<String,Object>> checkFileMD5(HttpServletRequest request, HttpServletResponse response) {
		Response<Map<String,Object>> result = new Response<Map<String,Object>>();
		Map<String,Object> map = Maps.newHashMap();
		//String checkType = request.getParameter("checkType");
		// 临时目录用来存放所有分片文件
        String fileMD5Value = request.getParameter("fileMD5Value");
        InfoFile infoFile = infoFileService.getByFileMd5(fileMD5Value).getData();
        if (infoFile != null) {//整个文件已经存在
        	map.put("infoFileId", infoFile.getId());
        	map.put("skip", "true");
        }else{
        	map.put("skip", "false");
        	//下面从临时目录取出文件的分片信息
        	StringBuilder tempFileDir = new StringBuilder(PropertiesConfig.getString(PropertyKey.FILE_LOCAL_PATH));
        	tempFileDir.append(File.separator).append(UPLOAD_TEMP).append(File.separator).append(fileMD5Value);
            File parentFileDir = new File(tempFileDir.toString());
            if (parentFileDir.exists() && parentFileDir.isDirectory()) {
            	File[] files = parentFileDir.listFiles(new UploadFilenameFilter(UploadChuckController.UPLOAD_TEMP_EXT));//获取临时目录下的分片文件
        		Map<String,String> chunkMd5s = Maps.newHashMap();
        		for (int i = 0; i < files.length; i++) {
    				String name = files[i].getName();
					//获取已经存在的分片的文件index
    				String index = name.substring(name.lastIndexOf("_")+1,name.lastIndexOf("."));
    				chunkMd5s.put(index, index);
    			}
        		map.put("chunkMd5s", chunkMd5s);
            }
        }
        result.setData(map);
		return result;
	}
	
	/**
	 * Title:文件合并
	 * @author yuwu on 2016年5月7日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/fileMerge")
    @EduLog(module="Upload", moduleName="公共文件上传" ,action="post", actionName="公共文件上传", paged = false)
    @ResponseBody
    public Response<Map<String,Object>> fileMerge(HttpServletRequest request, HttpServletResponse response) {
		Response<Map<String,Object>> result = new Response<Map<String,Object>>();
		Map<String,Object> map = Maps.newHashMap();
		//String checkType = request.getParameter("checkType");
		// 临时目录用来存放所有分片文件
        String fileMD5Value = request.getParameter("fileMD5Value");
        //String ext = request.getParameter("ext");//文件后缀
        //String type = request.getParameter("type");//文件类型，建议根据文件后缀ext来取类型
        String type = "1";//文件类型，建议根据后缀来取类型
        
        try {
	        String fileName = request.getParameter("fileName");
	        fileName = new String(fileName.getBytes("ISO-8859-1"), "UTF-8");
	        //下面从临时目录取出文件的分片信息
	    	StringBuilder tempFileDir = new StringBuilder(PropertiesConfig.getString(PropertyKey.FILE_LOCAL_PATH));
	    	tempFileDir.append(File.separator).append(UPLOAD_TEMP).append(File.separator).append(fileMD5Value);
	        File parentFileDir = new File(tempFileDir.toString());
	        if (parentFileDir.exists() && parentFileDir.isDirectory()) {
	        	//得到destTempFile就是最终的文件
		        File destTempFile = new File(PropertiesConfig.getString(PropertyKey.FILE_LOCAL_PATH), fileName);
		        File[] files = parentFileDir.listFiles(new UploadFilenameFilter(UploadChuckController.UPLOAD_TEMP_EXT));//获取临时目录下的分片文件
	    		for (int i = 0; i < files.length; i++) {
	    			File partFile = new File(parentFileDir, fileName + "_" + i + UploadChuckController.UPLOAD_TEMP_EXT);
	                FileOutputStream destTempfos = new FileOutputStream(destTempFile, true);
	                FileUtils.copyFile(partFile, destTempfos);
	                destTempfos.close();
				}
	    		//保存记录
	    		InfoFile infoFile = getInfoFile(fileName,fileMD5Value,Byte.valueOf(type));
	    		infoFileService.save(infoFile);
	    		map.put("infoFileId", infoFile.getId());
	    		map.put("fileUrl", PropertiesConfig.getString(PropertyKey.FILE_URL_PATH)+"/" + fileName );
	    		map.put("filePath", destTempFile.getPath());
	    		map.put("fileName", destTempFile.getName());
	            //删除临时目录中的分片文件
	            FileUtils.deleteDirectory(parentFileDir);
	            //可以将合并后的文件添加到文件系统或者存储中,删除临时文件
	            //destTempFile.delete();
	        }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.setCode(-1);
            result.setMessage("合并失败");
        }
        result.setData(map);
		return result;
	}
	
	@RequestMapping(value = "/chunkedUpload")
    @EduLog(module="Upload", moduleName="公共文件上传" ,action="post", actionName="公共文件上传", paged = false)
    @ResponseBody
    public Response<Void> chunkedUpload(HttpServletRequest request, HttpServletResponse response) {
		Response<Void> result = new Response<Void>();
        try {
            String path = request.getParameter("path");
            path = path != null ? java.net.URLDecoder.decode(path, "utf-8"): "";
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);

            if (isMultipart) {
                //String guid = request.getParameter("guid");
                String fileMD5Value = request.getParameter("fileMD5Value");
                String fileName = request.getParameter("name");
                fileName = new String(fileName.getBytes("ISO-8859-1"), "UTF-8");
                // 如果大于1说明是分片处理
                //int chunks = NumberUtils.toInt(request.getParameter("chunks"));
                int chunk = NumberUtils.toInt(request.getParameter("chunk"));
                // 临时目录用来存放所有分片文件
                StringBuilder tempFileDir = new StringBuilder(PropertiesConfig.getString(PropertyKey.FILE_LOCAL_PATH));
            	tempFileDir.append(File.separator).append(UPLOAD_TEMP).append(File.separator).append(fileMD5Value);
                //String tempFileDir = PropertiesConfig.getString(PropertyKey.FILE_LOCAL_PATH) + File.separator + fileMD5Value;
                File parentFileDir = new File(tempFileDir.toString());
                if (!parentFileDir.exists()) {
                    parentFileDir.mkdirs();
                }
                // 分片处理时，前台会多次调用上传接口，每次都会上传文件的一部分到后台(默认每片为5M)
                File tempPartFile = new File(parentFileDir, fileName + "_" + chunk+ UploadChuckController.UPLOAD_TEMP_EXT);
                
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                Map<String, MultipartFile> multipartFiles = multiRequest.getFileMap();
                for (Map.Entry<String, MultipartFile> entry : multipartFiles.entrySet()) {
                    MultipartFile mfile = entry.getValue();
                    mfile.transferTo(tempPartFile);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.setCode(-1);
            result.setMessage("上传失败");
        }
        return result;
    }
	
	public String getModulePath(){
		return "common";
	}
	
	/**
	 * Title:
	 * @author yuwu on 2016年5月7日
	 * @param fileName 文件名称
	 * @param fileMd5 文件MD5值
	 * @param type 文件类型{1:视频,2:音频,3:html}
	 * @return
	 */
	public InfoFile getInfoFile(String fileName,String fileMd5,Byte type){
		InfoFile infoFile = new InfoFile();
		infoFile.setAppId(123456L);
		infoFile.setOriginalName(fileName);
		//infoFile.setFileMd5(fileMd5);
		infoFile.setQiniuKey("");//对应七牛对应的文件名称
		infoFile.setCloudUrl("");//对应七牛下载地址
		infoFile.setType(type);
		infoFile.setStatus(InfoFile.STATUS2);
		infoFile.setFilePath("");//本地系统相对文件路径
		infoFile.setTranscodingTime(null);//转码成功时间
		infoFile.setCreator("");//创建人
		infoFile.setCreatorId(11111111L);//创建人姓名
		infoFile.setCreateTime(new Date());
		return infoFile;
	}
	
	/**
	 * Title:文件过滤器
	 * Copyright (c) CQLIVING 2016
	 * @author yuwu on 2016年5月7日
	 */
	class UploadFilenameFilter implements FilenameFilter{  
        private String type;  
        public UploadFilenameFilter(String type){  
            this.type = type;  
        }  
        public boolean accept(File dir,String name){  
            return name.endsWith(type);  
        }  
    } 
}
