package com.cqliving.cloud.online.config.manager.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.config.dao.CommentAppConfigDao;
import com.cqliving.cloud.online.config.dao.CommentConfigDao;
import com.cqliving.cloud.online.config.domain.CommentAppConfig;
import com.cqliving.cloud.online.config.domain.CommentConfig;
import com.cqliving.cloud.online.config.manager.CommentAppConfigManager;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Maps;

@Service("commentAppConfigManager")
public class CommentAppConfigManagerImpl extends EntityServiceImpl<CommentAppConfig, CommentAppConfigDao> implements CommentAppConfigManager {
	
	@Autowired
	private CommentConfigDao commentConfigDao;

	@Override
	public Byte getConfigValueByName(Long appId, String name, Byte type) {
		//查询系统默认配置
		List<CommentConfig> commentConfigs = commentConfigDao.getByName(name, type);
		if (CollectionUtils.isEmpty(commentConfigs)) {
			throw new BusinessException(ErrorCodes.FAILURE, "未找到系统配置");
		}
		CommentConfig commentConfig = commentConfigs.get(0);
		//查询App配置
		List<CommentAppConfig> commentAppConfigs = getEntityDao().getByAppAndCofigId(appId, commentConfig.getId());
		
		//无App配置时，返回默认配置
		return CollectionUtils.isNotEmpty(commentAppConfigs) ? commentAppConfigs.get(0).getValue() : commentConfig.getValue();
	}

	@Override
	public List<CommentAppConfig> getByAppAndType(Long appId, Byte type) {
		if (appId == null) {
			return Collections.emptyList();
		}
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_appId", appId);
		map.put("EQ_type", type);
		return query(map, null);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class, value = "transactionManager")
	public void save(Long appId, Long userId, String nickname, Map<Long, Byte> parma, Byte type) {
		//查询是否存在原有配置
		Date now = DateUtil.now();
		List<CommentAppConfig> commentAppConfigs = getByAppAndType(appId, type);
		if (CollectionUtils.isEmpty(commentAppConfigs)) {
			//不再新增配置记录，改为手动初始化数据 By Tangtao 2016-10-27
			//不存在原有记录，新增
			/*CommentAppConfig appConfig;
			List<CommentAppConfig> appConfigs = Lists.newArrayList();
			for (Long configId : parma.keySet()) {
				appConfig = new CommentAppConfig();
				appConfig.setAppId(appId);
				appConfig.setCreateTime(now);
				appConfig.setCreator(nickname);
				appConfig.setCreatorId(userId);
				appConfig.setCommentConfigId(configId);
				appConfig.setType(type);
				appConfig.setUpdateTime(now);
				appConfig.setUpdator(nickname);
				appConfig.setUpdatorId(userId);
				appConfig.setValue(parma.get(configId));
				appConfigs.add(appConfig);
			}
			getEntityDao().save(appConfigs);*/
		} else {
			//存在原有记录
//			boolean flag;
//			CommentAppConfig appConfig;
			for (Long configId : parma.keySet()) {
//				flag = false;	//重置标识
				for (CommentAppConfig oldConfig : commentAppConfigs) {
					if (configId.equals(oldConfig.getCommentConfigId())) {
//						flag = true;	//有原有配置
						if (!parma.get(configId).equals(oldConfig.getValue())) {
							//原配置值被修改
							oldConfig.setUpdateTime(now);
							oldConfig.setUpdator(nickname);
							oldConfig.setUpdatorId(userId);
							oldConfig.setValue(parma.get(configId));
							getEntityDao().update(oldConfig);
						}
						break;
					}
				}
				//不再新增配置记录，改为手动初始化数据 By Tangtao 2016-10-27
				/*if (!flag) {	//无原有配置，新增
					appConfig = new CommentAppConfig();
					appConfig.setAppId(appId);
					appConfig.setCreateTime(now);
					appConfig.setCreator(nickname);
					appConfig.setCreatorId(userId);
					appConfig.setCommentConfigId(configId);
					appConfig.setType(type);
					appConfig.setUpdateTime(now);
					appConfig.setUpdator(nickname);
					appConfig.setUpdatorId(userId);
					appConfig.setValue(parma.get(configId));
					getEntityDao().save(appConfig);
				}*/
			}
		}
	}
	
}