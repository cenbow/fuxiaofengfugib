package com.cqliving.tool.common.util.file;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.file.ExcelTemplate;
import com.cqliving.tool.common.util.file.ExcelUtils;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) xinhualong 2013-2016
 * @author huxiaoping on 2016年6月16日
 */
public class UploadExcel {
	
    private static int excelMaxRows = 4000;

	/**
	 * 上传excel，解析成list
	 * @Description 
	 * @Company 
	 * @parameter request:请求
	 * @parameter template:模板
	 * @parameter t:返回需要的泛型
	 * @return
	 * @author huxiaoping 2016年6月16日下午8:33:07
	 */
    public static <T>Response<List<T>> importExcel(HttpServletRequest request,List<ExcelTemplate> template,Class<T> t) throws Exception{
        Response<List<T>> res = Response.newInstance();
        //文件流
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("excelFile");
        org.apache.poi.ss.usermodel.Workbook workbook = ExcelUtils.createWorkbook(files.get(0));
        Sheet sheet = workbook.getSheetAt(0);
        if(sheet.getPhysicalNumberOfRows() > excelMaxRows) {
            //行数不超过4000行
            throw new BusinessException(excelMaxRows,"行数不能超过" + excelMaxRows + "行");
        }
        // 校验上传文件格式内容是否和标准模板一致
        ExcelUtils.validateExcelStandardByTemplate(sheet, template);
        List<T> retList = new ArrayList<T>();
        Row rows = null;
        for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
            // 获取对应的一行数据
            rows = sheet.getRow(i);
            //获取对象
            T obj = ExcelUtils.transformRowToBean(t, rows, template);
            //检查每一行的数据格式
            ExcelUtils.checkExcelIsBlank(template,obj,i);
            retList.add(obj);
        }
        res.setData(retList);
        return res;
    }
}
