package ${names.controllerPackage};

import ${names.domainPackage}.${names.domainClassName};
import ${names.servicePackage}.${names.serviceClassName};
import com.cqliving.cloud.common.Constant;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.framework.common.dao.support.PageInfo;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping(value = "${configuration.pageMapping}")
public class ${names.controllerClassName} extends CommonController {

    @Autowired
    private ${names.serviceClassName} ${names.serviceClassName?uncap_first};

    //列表
    @RequestMapping(value ="${configuration.pagePrefix}list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	<#assign counter=0 />
        <#list table.columnMetadatas as entity>
	        <#if configuration.searchColumnsList?size==0 || configuration.searchColumnsList?seq_contains(entity.name?lower_case)>
	            <#assign counter=counter+1 />
	            <#if entity.dataType == 2>
        ,@RequestParam(value="search_GTE_${entity.propertyName}", required=false) ${entity.javaDataType} search_GTE_${entity.propertyName}
        ,@RequestParam(value="search_LT_${entity.propertyName}", required=false) ${entity.javaDataType} search_LT_${entity.propertyName}
	            <#else>
	            </#if>
	        </#if>
        </#list>
    	) {
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        
        <#--查询条件-->
		<#list table.columnMetadatas as entity>
		    <#if configuration.searchColumnsList?size==0 || configuration.searchColumnsList?seq_contains(entity.name?lower_case)>
		        <#if entity.javaDataType?lower_case=='date'>
        //默认时间范围3个月
    	search_LT_${entity.propertyName} = search_LT_${entity.propertyName} != null ?search_LT_${entity.propertyName}: Calendar.getInstance().getTime();
        map.put("search_LT_${entity.propertyName}", search_LT_${entity.propertyName});
        searchMap.put("LT_${entity.propertyName}", DateUtils.truncate(DateUtils.addDays(search_LT_${entity.propertyName}, 1), Calendar.DATE));
        search_GTE_${entity.propertyName} = search_GTE_${entity.propertyName} != null ? search_GTE_${entity.propertyName}:DateUtils.addMonths(search_LT_${entity.propertyName}, -3);
        map.put("search_GTE_${entity.propertyName}", search_GTE_${entity.propertyName});
        searchMap.put("GTE_${entity.propertyName}", search_GTE_${entity.propertyName});
		        </#if>
		    </#if>
		</#list>
		
        PageInfo<${names.domainClassName}> pageInfo = getPageInfo(request);
        <#-- 如果实体有status字段，则列表查询时需要排除已经逻辑删除的记录 -->
        <#if table.isContainsStatusField >
        searchMap.put("NOTEQ_status", ${names.domainClassName}.STATUS99);//排除逻辑删除状态
		</#if>
        map.put("pageInfo", ${names.serviceClassName?uncap_first}.queryForPage(pageInfo, searchMap, sortMap).getData());
        
        <#list table.columnMetadatas as entity>
            <#if configuration.listColumnsList?size==0 || configuration.listColumnsList?seq_contains(entity.name?lower_case)>
                <#if entity.dataType == 1 || entity.dataType == 4 || entity.dataType == 5>
                	<#--数字-->
                    <#if entity.options??>
        map.put("all${entity.propertyName?cap_first}s", ${names.domainClassName}.all${entity.propertyName?cap_first}s);
                    </#if>
                </#if>
            </#if>
        </#list>
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "${configuration.pageMapping}/${configuration.pagePrefix}list_page";
        }else{
        	return "tiles.${configuration.tilesName}.${configuration.pagePrefix}list";
        }
    }

    //增加-查看
    @RequestMapping(value ="${configuration.pagePrefix}add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
    	return getReturnUrl(request,map,"tiles.${configuration.tilesName}.${configuration.pagePrefix}detail");
    }


    //增加-保存
    @RequestMapping(value ="${configuration.pagePrefix}add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,${names.domainClassName} ${names.domainClassName?uncap_first}){
        <#list table.columnMetadatas as entity>
            <#if entity.propertyName=='id'>
        //${entity.common}
        ${names.domainClassName?uncap_first}.set${entity.propertyName?cap_first}(null);
            <#elseif entity.name?lower_case != 'id' && configuration.detailColumnsList?size gte 1 && false == configuration.detailColumnsList?seq_contains(entity.name?lower_case)>
            //${entity.common}
        ${names.domainClassName?uncap_first}.set${entity.propertyName?cap_first}(null);
            </#if>
        </#list>
        Response<Void> res = ${names.serviceClassName?uncap_first}.save(${names.domainClassName?uncap_first});
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="${configuration.pagePrefix}update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ${names.domainClassName} ${names.domainClassName?uncap_first} = ${names.serviceClassName?uncap_first}.get(id).getData();
        if(${names.domainClassName?uncap_first}==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", ${names.domainClassName?uncap_first});
        return getReturnUrl(request,map,"tiles.${configuration.tilesName}.${configuration.pagePrefix}detail");
    }

    //修改-保存
    @RequestMapping(value ="${configuration.pagePrefix}update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,${names.domainClassName} ${names.domainClassName?uncap_first}){
        Response<Void> res = Response.newInstance();
        if(${names.domainClassName?uncap_first}==null || ${names.domainClassName?uncap_first}.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            ${names.domainClassName} source${names.domainClassName} = ${names.serviceClassName?uncap_first}.get(${names.domainClassName?uncap_first}.getId()).getData();
            if(source${names.domainClassName}==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

    <#list table.columnMetadatas as entity>
        <#if entity.name?lower_case != 'id' && (configuration.detailColumnsList?size==0 || configuration.detailColumnsList?seq_contains(entity.name?lower_case))>
            //${entity.common}
            source${names.domainClassName}.set${entity.propertyName?cap_first}(${names.domainClassName?uncap_first}.get${entity.propertyName?cap_first}());
        </#if>
    </#list>
            res= ${names.serviceClassName?uncap_first}.save(source${names.domainClassName});
            ${names.domainClassName?uncap_first} = source${names.domainClassName};
        }catch (Exception ex){
            logger.error("Save Method (Update) ${names.domainClassName} Error : " + ${names.domainClassName?uncap_first}.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="${configuration.pagePrefix}view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ${names.domainClassName} ${names.domainClassName?uncap_first} = ${names.serviceClassName?uncap_first}.get(id).getData();
        if(${names.domainClassName?uncap_first}==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", ${names.domainClassName?uncap_first});
        return getReturnUrl(request,map,"tiles.${configuration.tilesName}.${configuration.pagePrefix}view");
    }

    <#-- 不需要删除方法 -->
    //删除
    @RequestMapping(value ="${configuration.pagePrefix}delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        <#-- 如果实体有status字段，则列表查询时需要排除已经逻辑删除的记录 -->
        <#if table.isContainsStatusField >
        Response<Void> res = ${names.serviceClassName?uncap_first}.deleteLogic(id);
        <#else>
        Response<Void> res = ${names.serviceClassName?uncap_first}.delete(id);
		</#if>
        return res;
    }

	//批量删除
    @RequestMapping(value ="${configuration.pagePrefix}delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
   		<#-- 如果实体有status字段，则列表查询时需要排除已经逻辑删除的记录 -->
        <#if table.isContainsStatusField >
        Response<Void> res = ${names.serviceClassName?uncap_first}.deleteLogic(ids);
        <#else>
        Response<Void> res = ${names.serviceClassName?uncap_first}.delete(ids);
		</#if>
        return res;
    }
}
