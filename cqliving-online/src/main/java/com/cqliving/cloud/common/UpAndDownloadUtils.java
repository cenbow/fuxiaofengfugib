package com.cqliving.cloud.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.util.StringUtil;
import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.common.util.file.FileUtils;
import com.cqliving.tool.utils.ImageUtil;
import com.google.common.collect.Maps;

/**
 * 
 * Title:文件操作工具类
 * <p>
 * Description:
 * </p>
 * Copyright (c) feinno 2013-2016
 * 
 * @author tangqiang on 2014年12月10日
 */
public class UpAndDownloadUtils {
    private static final Logger logger = LoggerFactory.getLogger(UpAndDownloadUtils.class);

    private static final String LEFT_SLASH = "/";

    private static final float IMG_QUALITY = 0.5f;
    private static final String DATE_FORMAT_STR = "yyyyMM";
    /**
     * <p>
     * Description:文件下载
     * </p>
     * 
     * @param path
     *            下载路径
     * @param name
     *            下载后的名称
     * @param response
     * @throws BusinessException
     * @throws FileNotFoundException
     */
    public static void downloadFile(String path, String name, HttpServletResponse response)
            throws BusinessException, FileNotFoundException {
        downloadFile(new FileInputStream(path), name, response);
    }

    /**
     * <p>
     * Description:文件下载
     * </p>
     * 
     * @param stream
     *            InputStream
     * @param name
     *            下载后的名称
     * @param response
     * @throws BusinessException
     */
    public static void downloadFile(InputStream stream, String name, HttpServletResponse response)
            throws BusinessException {
        byte[] buffer = new byte[1024 * 2];
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            if (response.getContentType() == null || "".equals(response.getContentType())) {
                response.reset();
                // 下载
                response.setContentType("application/x-download;charset=UTF-8");
            }
            String fileName = new String(name.getBytes("gb2312"), "iso8859-1");
            response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
            // 输出流
            int len;
            while ((len = stream.read(buffer)) > 0) {
                os.write(buffer, 0, len);
            }
            stream.close();
            os.flush();
        } catch (Exception e) {
            throw new BusinessException("exportExcel method error:" + e.getMessage());
        } finally {
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    throw new BusinessException("exportExcel method error:" + e.getMessage());
                }
            }

        }
    }

    /**
     * 文件上传
     * 
     * @param request
     * @param fileLocalPath
     * @param modulePath
     * @param userId
     * @param imageName 指定图片名称
     * @return
     */
    public static Map<String, Object> saveUploadFile(HttpServletRequest request, String fileLocalPath,
            String modulePath, Long userId,String imageName) {
        
        if (StringUtils.isBlank(fileLocalPath)) {
            throw new BusinessException("系统上传路径为空，请检查配置参数");
        }
        if (StringUtils.isBlank(modulePath)) {
            modulePath = UploadEnums.Module.COMMON.getModulePath();
        }        
        
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> multipartFiles = multiRequest.getFileMap();

        Map<String, Object> resultMap = null;
        for (Map.Entry<String, MultipartFile> entry : multipartFiles.entrySet()) {
            resultMap = Maps.newHashMap();
            MultipartFile mfile = entry.getValue();
            String fileName = mfile.getOriginalFilename();// 文件名
            try {
                String extName = FileUtils.getExtensions(fileName);// 文件后缀
                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_STR);
                StringBuilder paths = new StringBuilder(fileLocalPath);
                if (fileLocalPath.endsWith(File.separator)) {
                    paths.append(modulePath);
                } else {
                    paths.append(File.separator).append(modulePath);
                }
               // paths.append(File.separator).append(userId);
                paths.append(File.separator).append(sdf.format(new Date()));
                paths.append(File.separator).append(StringUtils.isBlank(imageName)?FileUtils.getUuidFileName(fileName):imageName.toLowerCase());
                String outputPath = paths.toString();
                File destFile = new File(outputPath);
                destFile.mkdirs();
                mfile.transferTo(destFile);
                //按照指定规格压缩图片
                ImageUtil.makeThumb(request.getParameter("thumb"), destFile);
                //因为是手机访问，所以规定图片最大宽为720
                String maxSize = request.getParameter("nosize");
                if(StringUtil.isEmpty(maxSize)){
                	ImageUtil.makeThumb(destFile,720);
                	outputPath = size(request,outputPath);
                	destFile =new File(outputPath);
                }
                resultMap.put("fileRealName", fileName);// 上传的文件名称
                resultMap.put("fileName", destFile.getName());// 上传后的文件名称
                resultMap.put("fileSize", destFile.length());// 文件大小
                resultMap.put("fileExtName", extName);// 上传的文件后缀
                String filePath = outputPath.replace(fileLocalPath, "");
                resultMap.put("filePath", filePath.replace("\\", LEFT_SLASH));
                resultMap.put("uploadDate", DateUtil.formatDateNowDefault());
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return resultMap;
    }
    
    private static String size(HttpServletRequest request,String file){
    	if(StringUtil.isEmpty(file))return "";
    	String size = request.getParameter("imgsize");
    	if(StringUtil.isEmpty(size))return file;
    	String thumb = request.getParameter("thumb");
    	if(StringUtil.isEmpty(thumb)){
    		return ImageUtil.reQuality(file,StringUtil.stringToLong(size),IMG_QUALITY);
    	}
    	String[] arrSize = thumb.split(",");
		if (StringUtil.isEmpty(arrSize)){
		    return ImageUtil.reQuality(file,StringUtil.stringToLong(size),IMG_QUALITY);
		}
		//改变图片大小还要改变按照图片宽高变化后的图片大小
		for (String str : arrSize) {
			String[] wh = str.split("x");
			int w = StringUtil.stringToInteger(wh[0]);
			String thumbFile = ImageUtil.appendSuffixBySize(w,wh.length <= 1 ? 0 : StringUtil.stringToInteger(wh[1]), file);
			ImageUtil.reQuality(thumbFile,StringUtil.stringToLong(size),IMG_QUALITY);
		}
		return ImageUtil.reQuality(file,StringUtil.stringToLong(size),IMG_QUALITY);
    }
   
}
