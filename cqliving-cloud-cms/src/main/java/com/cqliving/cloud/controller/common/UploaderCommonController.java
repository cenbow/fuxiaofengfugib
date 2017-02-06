/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.controller.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqliving.cloud.common.UpAndDownloadUtils;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年6月1日
 */
@Controller
@RequestMapping(value="common")
public class UploaderCommonController extends UploadController{
    
    /**
     * <p>Description: 下载模板</p>
     * @param response
     * @author huxiaoping on 2016年6月21日
     */
    @RequestMapping("downTemp")
    public void downloadTemplate(HttpServletRequest request,HttpServletResponse response,String tempName) {
        try {
            String rootPath = request.getSession().getServletContext().getRealPath("");
            String filePath = File.separator + "resource" + File.separator + "template" + File.separator + tempName;
            InputStream stream = new FileInputStream(new File(rootPath + filePath));
            UpAndDownloadUtils.downloadFile(stream, tempName, response);
        } catch (Exception e) {
            logger.error("下载模板失败", e);
        }
    }
}
