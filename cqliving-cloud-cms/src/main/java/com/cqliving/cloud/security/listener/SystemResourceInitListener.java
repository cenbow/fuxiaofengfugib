package com.cqliving.cloud.security.listener;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.security.domain.SysResource;
import com.cqliving.cloud.online.security.dto.SysResourceDto;
import com.cqliving.cloud.security.annotation.SystemResource;
import com.cqliving.cloud.security.service.SysResourceService;
import com.cqliving.framework.utils.Dates;
import com.cqliving.tool.common.util.AnnotationUtil;
import com.cqliving.tool.common.util.SpringUtil;

public class SystemResourceInitListener implements ServletContextListener {
	
	public static Logger logger = LoggerFactory.getLogger(SystemResourceInitListener.class);

	@SuppressWarnings("unused")
	private static final long serialVersionUID = -2241085297061924946L;

	@Autowired
	private SysResourceService sysResourceService;

	private String packages;
	
	private Map<String,SysResourceDto> allResource = new HashMap<String, SysResourceDto>();
	
	private Set<SysResourceDto> rootResource = new HashSet<SysResourceDto>();
	private  Map<String,SysResourceDto> freeResource = new HashMap<String, SysResourceDto>();

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		packages = sce.getServletContext().getInitParameter(
				"application.resource.packages");
		sysResourceService = SpringUtil.getBeanByClass(SysResourceService.class);
		logger.info("开始查找资源注解");
		fullSystemResource();
		logger.info("资源查找完成，开始与库中现有资源合并");
		mergeResource(null,rootResource) ;
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}
	/**
	 * 
	 * mergeResource:合并修改系统资源. <p> 
	 * 
	 * @author weiming 
	 * @param parentSysRes	父资源
	 * @param resList		合并资源列表
	 */
	protected void mergeResource(SysResourceDto parentSysRes,Set<SysResourceDto> resList){
		Map<String,Object> searchMap = new HashMap<String, Object>();
		Map<String,Boolean> orderMap = new HashMap<String, Boolean>();
		int sortIdx = 1;
		for(SysResourceDto sysRes:resList){
			searchMap.put("EQ_permissionValue", sysRes.getPermissionValue());
			List<SysResourceDto> dbSysResList = sysResourceService.findByConditions(searchMap, orderMap);
			SysResourceDto dbSysRes = sysRes;
			if(dbSysResList.size()>0){
				dbSysRes = dbSysResList.get(0);
				dbSysRes.setDescn(sysRes.getDescn());
				dbSysRes.setPermissionValue(sysRes.getPermissionValue());
				dbSysRes.setResString(sysRes.getResString());
				dbSysRes.setRestype(sysRes.getRestype());
				dbSysRes.setSortNum(sysRes.getSortNum());
				dbSysRes.setStatus(sysRes.getStatus());
				dbSysRes.setTitle(sysRes.getTitle());
				dbSysRes.setCreateDate(Dates.now());
			}
			if(parentSysRes!=null){
				dbSysRes.setParentId(parentSysRes.getId());
			}else{
				dbSysRes.setParentId(0l);
			}
			if(dbSysRes.getSortNum()==-1){
				dbSysRes.setSortNum(sortIdx);
			}
			SysResource sysResource = new SysResource();
			BeanUtils.copyProperties(dbSysRes, sysResource);
			
			sysResourceService.save(sysResource);
			
			if(sysRes.getSubResource()!=null&&sysRes.getSubResource().size()>0){
				mergeResource(dbSysRes,sysRes.getSubResource());
			}
			sortIdx++;
		}
	}
	/**
	 * 
	 * fullSystemResource:获取系统中所有注解资源. <p> 
	 * 
	 * @author weiming
	 */
	protected void fullSystemResource() {
		Set<Class<?>> classSet = null;
		String[] packageArray = packages.split(",");
		for (String packag : packageArray) {
			logger.info("查找["+packag+"] 包下资源注解");
			if (classSet == null) {
				classSet = AnnotationUtil.findFileClass(packag);
			} else {
				classSet.addAll(AnnotationUtil.findFileClass(packag));
			}
		}
		
		for (Class<?> cls : classSet) {
			Method[] methods = cls.getMethods();
			for (Method method : methods) {
				if (method.isAnnotationPresent(SystemResource.class)) {
					SystemResource res = method
							.getAnnotation(SystemResource.class);
					SysResourceDto sysRes = parse(res);
					allResource.put(sysRes.getPermissionValue(), sysRes);
					if(StringUtils.isBlank(res.parentValue())){
						rootResource.add(sysRes);
					}else if(allResource.get(res.parentValue())!=null){
						Set<SysResourceDto> subResource = allResource.get(res.parentValue()).getSubResource();
						if(subResource==null){
							subResource = new HashSet<SysResourceDto>();
						}
						subResource.add(sysRes);
						allResource.get(res.parentValue()).setSubResource(subResource);
					}else{
						freeResource.put(res.parentValue(), sysRes);
					}
				}
			}
		}
		fullFreeResource();
	}
	/**
	 * 
	 * fullFreeResource:填充初次未找到父资源的游离资源. <p> 
	 * 
	 * @author weiming
	 */
	protected void fullFreeResource() {
		Map<String,SysResourceDto> errResource = new HashMap<String, SysResourceDto>();
		for(String key:freeResource.keySet()){
			if(allResource.containsKey(key)){
				Set<SysResourceDto> subResource = allResource.get(key).getSubResource();
				if(subResource==null){
					subResource = new HashSet<SysResourceDto>();
				}
				subResource.add(freeResource.get(key));
				allResource.get(key).setSubResource(subResource);
				errResource.put(key,freeResource.get(key));
			}
		}
		if(errResource.size()>0){
			logger.warn("已下资源未找到对应的父资源，无法正常处理，请排查！");
			logger.warn("****************************************************");
			for(String key:errResource.keySet()){
				logger.warn("*"+errResource.get(key).getTitle()+"|"+errResource.get(key).getPermissionValue());
			}
			logger.warn("****************************************************");
		}
	}
	/**
	 * 
	 * parse:将注解转换为资源对象
	 * 
	 * @author weiming 
	 * @param annResource
	 * @return
	 */
	protected SysResourceDto parse(SystemResource annResource) {
		SysResourceDto sysRes = new SysResourceDto();
		sysRes.setDescn(annResource.descn());
		sysRes.setPermissionValue(annResource.permissionValue());
		sysRes.setResString(annResource.resString());
		sysRes.setRestype(annResource.restype().toString());
		sysRes.setSortNum(annResource.sortNum());
		sysRes.setTitle(annResource.title());
		sysRes.setStatus(annResource.status().getValue());
		return sysRes;
	}
	public String getPackages() {
		return packages;
	}

	public void setPackages(String packages) {
		this.packages = packages;
	}

}
