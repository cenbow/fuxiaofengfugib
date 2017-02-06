package com.cqliving.log.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqliving.log.aop.annotation.EduLog;

@Controller
public class PtlOlogManagerController {


    @RequestMapping(value = "/olog")
    @EduLog(module="ptlOlogManager", moduleName = "日志", action = "add", actionName = "增加日志", paged = false)
	public String add(HttpServletRequest request, Map<String, Object> model) {
		return "";
	}

    @RequestMapping(value = "/olog/{id}")
    @EduLog(module="olog", moduleName = "日志", action = "get", actionName = "获取日志", paged = false)
    public String get(@PathVariable(value = "id") Long id, HttpServletRequest request, Map<String, Object> model) {
        return "";
    }



}
