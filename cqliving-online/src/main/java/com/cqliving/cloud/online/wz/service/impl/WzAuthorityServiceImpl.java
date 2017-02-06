package com.cqliving.cloud.online.wz.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.wz.domain.WzAppAuthority;
import com.cqliving.cloud.online.wz.domain.WzAuthority;
import com.cqliving.cloud.online.wz.domain.WzCollectInfo;
import com.cqliving.cloud.online.wz.dto.WzAppAuthorityDto;
import com.cqliving.cloud.online.wz.dto.WzAuthorityDto;
import com.cqliving.cloud.online.wz.dto.WzCollectInfoDto;
import com.cqliving.cloud.online.wz.manager.WzAppAuthorityManager;
import com.cqliving.cloud.online.wz.manager.WzAuthorityManager;
import com.cqliving.cloud.online.wz.manager.WzCollectInfoManager;
import com.cqliving.cloud.online.wz.service.WzAuthorityService;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("wzAuthorityService")
@ServiceHandleMapping(managerClass = WzAuthorityManager.class)
public class WzAuthorityServiceImpl implements WzAuthorityService {

	private static final Logger logger = LoggerFactory.getLogger(WzAuthorityServiceImpl.class);
	
	@Autowired
	private WzAuthorityManager wzAuthorityManager;
    @Autowired
    private WzAppAuthorityManager wzAppAuthorityManager;
    @Autowired
    private WzCollectInfoManager wzCollectInfoManager;
	
	public Response<List<WzAuthorityDto>> list(Long appId) {
	    Response<List<WzAuthorityDto>> rs = Response.newInstance();
	    try {
            Map<String, Object> map = Maps.newHashMap();
            Map<String, Boolean> sortMap = Maps.newHashMap();
            sortMap.put("id", true);
	        
	        List<WzAuthority> list = wzAuthorityManager.query(map, sortMap);
	        
	        List<WzAuthorityDto> listDto = Lists.newArrayList();
	        WzAuthorityDto dto = null;
	        WzAppAuthorityDto waaDto = null;
	        WzAppAuthority wzAppAuthority = null;
	        List<WzCollectInfo> collectList = null;
	        List<WzCollectInfoDto> collectDtoList = null;
	        for(WzAuthority wa : list){
	            dto = new WzAuthorityDto();
	            dto.setDescription(wa.getDescription());
	            dto.setId(wa.getId());
	            dto.setName(wa.getName());
	            dto.setType(wa.getType());
	            if(!wa.getType().equals(WzAuthority.TYPE1)){
	                
	                wzAppAuthority = wzAppAuthorityManager.getByAuthorityId(appId, wa.getId());
	                //有配置且设置为是
	                if(wzAppAuthority != null){
	                    waaDto = new WzAppAuthorityDto();
	                    waaDto.setId(wzAppAuthority.getId());
	                    waaDto.setValue(wzAppAuthority.getValue());
	                    //获取收集信息
                        collectList = wzCollectInfoManager.getByAppAuthority(appId, wzAppAuthority.getId());
                        collectDtoList = Lists.newArrayList();
                        for(WzCollectInfo info : collectList){
                            collectDtoList.add(new WzCollectInfoDto(info.getId(), info.getName(), info.getIsRequired()));
                        }
                        waaDto.setCollectInfoList(collectDtoList.size() > 0 ? collectDtoList : null);
	                    dto.setWzAppAuthorityDto(waaDto);
	                }
	            }
	            listDto.add(dto);
	        }
	        
	        
	        rs.setData(listDto);
        } catch (Exception e) {
            logger.error("获取配置列表失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("获取配置列表失败");
        }
		return rs;
	}
	
    @Override
    public Response<WzAppAuthorityDto> getAppAuthByAuthName(Long appId, String configName) {
        
        Response<WzAppAuthorityDto> rs = Response.newInstance();
        try {
            WzAuthority wzAuthority = wzAuthorityManager.getByNameOne(configName);
            if(wzAuthority == null){
                throw new BusinessException(-1, "设置不存在");
            }
            WzAppAuthority wzAppAuthority = wzAppAuthorityManager.getByAuthorityId(appId, wzAuthority.getId());
            //有配置且设置为是
            if(wzAppAuthority != null){
                WzAppAuthorityDto dto = new WzAppAuthorityDto();
                dto.setId(wzAppAuthority.getId());
                dto.setValue(wzAppAuthority.getValue());
                if(wzAppAuthority.getValue().equals("1")){
                    List<WzCollectInfo> list = wzCollectInfoManager.getByAppAuthority(appId, wzAppAuthority.getId());
                    List<WzCollectInfoDto> listDto = Lists.newArrayList();
                    for(WzCollectInfo info : list){
                        listDto.add(new WzCollectInfoDto(info.getId(), info.getName(), info.getIsRequired()));
                    }
                    dto.setCollectInfoList(listDto);
                }
                rs.setData(dto);
            }else{
                throw new BusinessException(-1, "设置不存在");
            }
        } catch (BusinessException e) {
            logger.error("获取配置失败：" + e.getMessage(), e);
            rs.setCode(e.getErrorCode());
            rs.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("获取配置失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("获取配置失败");
        }
        return rs;
    }


    @Override
    public Response<Void> saveAuthority(Long appId, Long userId, String userName, Map<String, Object> map, String[] authorityIds, String[] collectInfoIds, String[] collectInfoNames, Byte[] isRequired) {
        Response<Void> rs = Response.newInstance();
        try {
            if(appId == null){
                throw new BusinessException(-1, "请用app管理员操作。");
            }
            Set<String> key = map.keySet();
            Map<String, Object> paramsMap = Maps.newHashMap();
            for(String str : key){
                try {
                    Integer.parseInt(str);
                    paramsMap.put(str, map.get(str));
                } catch (NumberFormatException e) {}
            }
            
            key = paramsMap.keySet();
            WzAuthority wzAuthority = null;
            WzAppAuthority wzAppAuthority = null;
            WzCollectInfo wzCollectInfo = null;
            Date currentDate = new Date();
            for(String authorityId : key){
                wzAuthority = wzAuthorityManager.get(Long.parseLong(authorityId));
                if(wzAuthority == null){
                    continue ;
                }
                //处理选择型
                wzAppAuthority = wzAppAuthorityManager.getByAuthorityId(appId, wzAuthority.getId());
                if(wzAppAuthority == null){
                    wzAppAuthority = new WzAppAuthority();
                    wzAppAuthority.setValue(paramsMap.get(authorityId).toString());
                    wzAppAuthority.setAppId(appId);
                    wzAppAuthority.setAuthorityId(Long.parseLong(authorityId));
                    wzAppAuthorityManager.save(wzAppAuthority);
                }else{
                    wzAppAuthority.setValue(paramsMap.get(authorityId).toString());
                    wzAppAuthorityManager.update(wzAppAuthority);
                }
                //处理其他类型
                if(wzAppAuthority.getValue().equals(WzAppAuthority.VALUE0)){
                    continue ;
                }
                int len = authorityIds.length;
                boolean isTmp = true;
                //处理界面删除的收集信息字段
                List<WzCollectInfo> collectList = wzCollectInfoManager.getByAppAuthority(appId, wzAppAuthority.getId());
                for(WzCollectInfo info : collectList){
                    isTmp = true;
                    for(int i = 0; i < len; i ++){
                        if(collectInfoIds[i].equals(info.getId().toString())){
                            isTmp = false;
                        }
                    }
                    if(isTmp){
                        wzCollectInfoManager.remove(info);
                    }
                }
                //更新
                isTmp = true;
                for(int i = 0; i < len; i ++){
                    if(authorityIds[i].equals(authorityId) && StringUtils.isNotBlank(collectInfoNames[i])){
                        isTmp = true;
                        if(StringUtils.isNotBlank(collectInfoIds[i])){
                            wzCollectInfo = wzCollectInfoManager.get(Long.parseLong(collectInfoIds[i]));
                            if(wzCollectInfo != null){
                                wzCollectInfo.setName(collectInfoNames[i]);
                                wzCollectInfo.setIsRequired(isRequired[i]);
                                wzCollectInfo.setUpdateTime(currentDate);
                                wzCollectInfo.setUpdator(userName);
                                wzCollectInfo.setUpdatorId(userId);
                                wzCollectInfoManager.update(wzCollectInfo);
                                isTmp = false;
                            }
                        }
                        if(isTmp){
                            wzCollectInfo = new WzCollectInfo();
                            wzCollectInfo.setAppAuthorityId(wzAppAuthority.getId());
                            wzCollectInfo.setAppId(appId);
                            wzCollectInfo.setName(collectInfoNames[i]);
                            wzCollectInfo.setIsRequired(isRequired[i]);
                            wzCollectInfo.setCreateTime(currentDate);
                            wzCollectInfo.setCreator(userName);
                            wzCollectInfo.setCreatorId(userId);
                            wzCollectInfo.setUpdateTime(currentDate);
                            wzCollectInfo.setUpdator(userName);
                            wzCollectInfo.setUpdatorId(userId);
                            wzCollectInfoManager.save(wzCollectInfo);
                        }
                    }
                }
            }
            
        } catch (BusinessException e) {
            logger.error("问政权限保存失败：" + e.getMessage(), e);
            rs.setCode(e.getErrorCode());
            rs.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("问政权限保存失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("问政权限保存失败");
        }
        return rs;
    }
}
