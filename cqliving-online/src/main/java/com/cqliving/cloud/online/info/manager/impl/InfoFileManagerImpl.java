package com.cqliving.cloud.online.info.manager.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.cqliving.cloud.online.info.dao.InfoFileDao;
import com.cqliving.cloud.online.info.domain.InfoFile;
import com.cqliving.cloud.online.info.dto.QiNiuTransCodeItems;
import com.cqliving.cloud.online.info.dto.QiNiuTransCodeResult;
import com.cqliving.cloud.online.info.manager.InfoFileManager;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.framework.utils.Dates;
import com.cqliving.tool.common.util.StringUtil;
import com.cqliving.tool.common.util.http.HttpClientUtil;
import com.google.common.collect.Lists;

@Service("infoFileManager")
public class InfoFileManagerImpl extends EntityServiceImpl<InfoFile, InfoFileDao> implements InfoFileManager {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(InfoFile.STATUS99, idList);
	}
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int updateStatus(Byte status,Long... ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(InfoFile.STATUS99, idList);
	}
	
	/**
	 * Title:根据文件MD5获取文件记录
	 * @author yuwu on 2016年5月7日
	 * @param fileMd5
	 * @return
	 */
	public InfoFile getByFileMd5(String fileMd5){
		List<InfoFile> list = this.getEntityDao().getByFileMd5(fileMd5);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * Title:获取未上传文件列表
	 * 1、包括上传状态为0（未上传至云平台）或者上传状态为1但上传时间已超过12个小时
	 * @author yuwu on 2016年5月18日
	 * @param fileMd5
	 * @return
	 */
	public List<InfoFile> getNotUploadList(){
		List<Byte> list = Lists.newArrayList();
		list.add(InfoFile.TYPE1);//视频
		list.add(InfoFile.TYPE2);//音频
		return this.getEntityDao().getNotUploadList(list);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InfoFileManager#transCoding()
	 */
	@Override
	@Transactional(value="transactionManager")
	public void transCoding() {
		// 获取转码中的文件
		List<InfoFile> infoFiles = this.getEntityDao().findAllNoTrans();
		logger.info("转码中的文件{}",infoFiles);
		
		if(CollectionUtils.isEmpty(infoFiles))return;
		String url = "http://api.qiniu.com/status/get/prefop?id=qiniuPersistentId";
		
		for(InfoFile infoFile : infoFiles){
			Long refId = infoFile.getRefId();
			if(null != refId){
				logger.info("状态查询：文件在七牛存在直接修改状态");
				InfoFile sqlFile = this.get(refId);
				//引用文件存在，而转码成功，则直接引用该文件的地址
				if(sqlFile != null && sqlFile.getStatus().byteValue() == InfoFile.STATUS3 && StringUtils.isNotBlank(sqlFile.getCloudUrl())){
					this.updateInfoFile(infoFile, sqlFile.getCloudUrl());
					continue;
				}
			}
			
			String domain = infoFile.getQiniuDomain();
			String qiniuPersistentid = infoFile.getQiniuPersistentId();
			String result = HttpClientUtil.sendGetRequest(url.replace("qiniuPersistentId", qiniuPersistentid), null);
			QiNiuTransCodeResult qiNiuTransCodeResult= JSONObject.parseObject(result, QiNiuTransCodeResult.class); 
			logger.info("状态查询请求回调结果转QiNiuTransCodeResult：{}",result);
			
			Integer code = qiNiuTransCodeResult.getCode();
			if(null != code && code.intValue() == QiNiuTransCodeResult.CODE0){
				
				List<QiNiuTransCodeItems> items = qiNiuTransCodeResult.getItems();
				
				if(CollectionUtils.isNotEmpty(items)){
					for(QiNiuTransCodeItems item : items){
						if(item.getKey().toLowerCase().equals(infoFile.getQiniuKey().toLowerCase())){
							String qiniuUrl = domain + item.getKey();
							this.updateInfoFile(infoFile, qiniuUrl);
						}
					}
				}
			}else{
				
				logger.info("七牛转码失败------->>>>>>>>>>>>>>>>>{}",result);
				
			}
		}
		
	}

	private void updateInfoFile(InfoFile infoFile,String qiniuFileUrl){
		
		infoFile.setStatus(InfoFile.STATUS3);
		infoFile.setTranscodingTime(Dates.now());
		infoFile.setCloudUrl(qiniuFileUrl);
		this.getEntityDao().update(infoFile);
		this.getEntityDao().updateInfoAndAppResourse(infoFile.getId(), qiniuFileUrl,infoFile.getOriginalName());
		
	}
	
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InfoFileManager#saveInfoFile(com.cqliving.cloud.online.info.domain.InfoFile)
	 */
	@Override
	@Transactional(value="transactionManager")
	public InfoFile saveInfoFile(InfoFile infoFile) {
		if(null == infoFile || StringUtil.isEmpty(infoFile.getQiniuPersistentId()))
			return null;
		
		List<InfoFile> sqlInfoFile = this.getEntityDao().findByHashCode(infoFile.getQiniuHash());
		if(CollectionUtils.isNotEmpty(sqlInfoFile)){
			InfoFile sqlFile =  sqlInfoFile.get(0);
			infoFile.setRefId(sqlFile.getId());
			infoFile.setId(null);
		}
		
		if(StringUtil.isEmpty(infoFile.getQiniuPersistentId()))return null;
		
		infoFile.setDownloadStatus(InfoFile.DOWNLOADSTATUS1);
		infoFile.setStatus(InfoFile.STATUS2);
		
		return this.getEntityDao().saveAndFlush(infoFile);
	}
}
