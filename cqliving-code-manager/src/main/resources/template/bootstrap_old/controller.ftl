package ${names.controllerPackage};

import ${names.domainPackage}.${names.domainClassName};
import ${names.servicePackage}.${names.serviceClassName};
import com.cqliving.cloud.cms.web.common.Constant;
import com.cqliving.cms.web.controller.common.CommonController;
import com.cqliving.cms.web.common.SessionFace;
import com.cqliving.cms.web.common.SessionUser;
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
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Controller
@RequestMapping(value = "${configuration.pageMapping}")
public class ${names.controllerClassName} extends CommonController {

    @Autowired
    private ${names.serviceClassName} ${names.serviceClassName?uncap_first};

    //列表
    @RequestMapping(value ="${configuration.pagePrefix}list")
    public String list(HttpServletRequest request, Map<String, Object> map,PageInfo<${names.domainClassName}> pageInfo,
                                    <#assign counter=0 />
                                    <#list table.columnMetadatas as entity>
                                    <#if configuration.searchColumnsList?size==0 || configuration.searchColumnsList?seq_contains(entity.name?lower_case)>
                                        <#assign counter=counter+1 />
                                        <#if entity.dataType == 2>
                                    @RequestParam(value="begin${entity.propertyName?cap_first}", required=false) ${entity.javaDataType} begin${entity.propertyName?cap_first},
                                    @RequestParam(value="end${entity.propertyName?cap_first}", required=false) ${entity.javaDataType} end${entity.propertyName?cap_first}<#if entity_has_next && (configuration.searchColumnsList?size==0 || configuration.searchColumnsList?size>counter)>,</#if>
                                        <#else>
                                    @RequestParam(value="${entity.propertyName}", required=false) ${entity.javaDataType} ${entity.propertyName}<#if entity_has_next && (configuration.searchColumnsList?size==0 || configuration.searchColumnsList?size>counter)>,</#if>
                                        </#if>
                                    </#if>
                                    </#list>
                                    ) {

<#list table.columnMetadatas as entity>
    <#if configuration.searchColumnsList?size==0 || configuration.searchColumnsList?seq_contains(entity.name?lower_case)>
    <#if entity.javaDataType?lower_case=='date'>
        //${entity.common} 默认时间范围3个月
        if(end${entity.propertyName?cap_first}==null){
            end${entity.propertyName?cap_first}=Calendar.getInstance().getTime();
        }
        if(begin${entity.propertyName?cap_first}==null){
            begin${entity.propertyName?cap_first} = DateUtils.addMonths(end${entity.propertyName?cap_first}, -3);
        }
    </#if>
    </#if>
</#list>

        Map<String, Object> searchMap = new HashMap<String, Object>();
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();

<#--查询条件-->
<#list table.columnMetadatas as entity>
    <#if configuration.searchColumnsList?size==0 || configuration.searchColumnsList?seq_contains(entity.name?lower_case)>
        //${entity.common}
        <#if entity.javaDataType?lower_case=='string'>
        if(StringUtils.isNotBlank(${entity.propertyName})){
            searchMap.put("LIKE_${entity.propertyName}", ${entity.propertyName});
        }
        <#elseif entity.javaDataType?lower_case=='date'>
        if(begin${entity.propertyName?cap_first}!=null){
            searchMap.put("GTE_${entity.propertyName}", DateUtils.truncate(begin${entity.propertyName?cap_first}, Calendar.DATE));
        }
        if(end${entity.propertyName?cap_first}!=null){
            searchMap.put("LTE_${entity.propertyName}", DateUtils.truncate(DateUtils.addDays(end${entity.propertyName?cap_first}, 1), Calendar.DATE));
        }
        <#else>
        if(${entity.propertyName}!=null){
            searchMap.put("EQ_${entity.propertyName}", ${entity.propertyName});
        }
        </#if>
    </#if>
</#list>

<#--request 属性-->
<#list table.columnMetadatas as entity>
    <#if configuration.searchColumnsList?size==0 || configuration.searchColumnsList?seq_contains(entity.name?lower_case)>
    <#if entity.javaDataType?lower_case=='date'>
        map.put("begin${entity.propertyName?cap_first}", begin${entity.propertyName?cap_first});
        map.put("end${entity.propertyName?cap_first}", end${entity.propertyName?cap_first});
    </#if>
    </#if>
</#list>
        map.put("pageInfo", ${names.serviceClassName?uncap_first}.queryForPage(pageInfo, searchMap, sortMap).getData());
        return "${configuration.tilesName}.${configuration.pagePrefix}list";
    }

    //增加-查看
    @RequestMapping(value ="${configuration.pagePrefix}add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
        return "${configuration.tilesName}.${configuration.pagePrefix}detail";
    }


    //增加-保存
    @RequestMapping(value ="${configuration.pagePrefix}add", method = RequestMethod.POST)
    public String postAdd(HttpServletRequest request, Map<String, Object> map,
                                ${names.domainClassName} ${names.domainClassName?uncap_first}){
        try{
        <#list table.columnMetadatas as entity>
            <#if entity.propertyName=='id'>
            //${entity.common}
            ${names.domainClassName?uncap_first}.set${entity.propertyName?cap_first}(null);
            <#elseif entity.name?lower_case != 'id' && configuration.detailColumnsList?size gte 1 && false == configuration.detailColumnsList?seq_contains(entity.name?lower_case)>
            //${entity.common}
            ${names.domainClassName?uncap_first}.set${entity.propertyName?cap_first}(null);
            </#if>
        </#list>

            ${names.serviceClassName?uncap_first}.save(${names.domainClassName?uncap_first});
        }catch (Exception ex){
            logger.error("Save Method (inster) ${names.domainClassName} Error : " + ${names.domainClassName?uncap_first}.toString(), ex);
            //增加失败
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE), map);
        }
        //操作提示
        return super.operSuccess(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS),
                                                    "${configuration.pageMapping}/${configuration.pagePrefix}add.html", map);
    }

    //修改-查看
    @RequestMapping(value ="${configuration.pagePrefix}update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,
                                    @RequestParam(value = "id") Long id){
        ${names.domainClassName} ${names.domainClassName?uncap_first} = ${names.serviceClassName?uncap_first}.get(id).getData();
        if(${names.domainClassName?uncap_first}==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", ${names.domainClassName?uncap_first});
        return "${configuration.tilesName}.${configuration.pagePrefix}detail";
    }

    //修改-保存
    @RequestMapping(value ="${configuration.pagePrefix}update", method = RequestMethod.POST)
    public String postUpdate(HttpServletRequest request, Map<String, Object> map,
                                ${names.domainClassName} ${names.domainClassName?uncap_first}){
        if(${names.domainClassName?uncap_first}==null ||
                ${names.domainClassName?uncap_first}.getId()==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        try{
            ${names.domainClassName} source${names.domainClassName} = ${names.serviceClassName?uncap_first}.get(${names.domainClassName?uncap_first}.getId()).getData();
            if(source${names.domainClassName}==null){
                //没有记录
                return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
            }

    <#list table.columnMetadatas as entity>
        <#if entity.name?lower_case != 'id' && (configuration.detailColumnsList?size==0 || configuration.detailColumnsList?seq_contains(entity.name?lower_case))>
            //${entity.common}
            source${names.domainClassName}.set${entity.propertyName?cap_first}(${names.domainClassName?uncap_first}.get${entity.propertyName?cap_first}());
        </#if>
    </#list>
            ${names.serviceClassName?uncap_first}.save(source${names.domainClassName});
            ${names.domainClassName?uncap_first} = source${names.domainClassName};
        }catch (Exception ex){
            logger.error("Save Method (Update) ${names.domainClassName} Error : " + ${names.domainClassName?uncap_first}.toString(), ex);
            //修改失败
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE), map);
        }
        //操作提示
        return super.operSuccess(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS),
        String.format("${configuration.pageMapping}/${configuration.pagePrefix}update.html?id=%s", ${names.domainClassName?uncap_first}.getId()), map);
    }

    //查看
    @RequestMapping(value ="${configuration.pagePrefix}view")
    public String show(HttpServletRequest request, Map<String, Object> map,
                                    @RequestParam(value = "id") Long id){
        ${names.domainClassName} ${names.domainClassName?uncap_first} = ${names.serviceClassName?uncap_first}.get(id).getData();
        if(${names.domainClassName?uncap_first}==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", ${names.domainClassName?uncap_first});
        return "${configuration.tilesName}.${configuration.pagePrefix}view";
    }


    <#-- 不需要删除方法
    //删除
    @RequestMapping(value ="del")
    public String delete(HttpServletRequest request, Map<String, Object> map,
                                @RequestParam(value = "id") Long id){
        String ref = request.getHeader("referer");
        if(StringUtils.isBlank(ref)){
            ref = "${configuration.pagePath}/${configuration.pagePrefix}list.html";
        }

        try{
            ${names.serviceClassName?uncap_first}.removeById(id);
        }catch (Exception ex){
            logger.error("Del Method (Del) ${names.domainClassName} Error : " + id, ex);
            //删除失败提示
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.DEL_FAILURE), map);
        }
        //操作提示
        return super.operSuccess(new RequestContext(request).getMessage(Constant.I18nMessage.DEL_SUCCESS), ref, map);
    }-->


}
