package com.cqliving.cloud.online.info.manager;

import java.util.List;

import com.cqliving.cloud.online.info.domain.InfoFile;
import com.cqliving.framework.common.service.EntityService;

/**
 * 资讯对应的文件表 Manager
 * Date: 2016-05-07 18:00:13
 * @author Code Generator
 */
public interface InfoFileManager extends EntityService<InfoFile> {
	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	public int deleteLogic(Long[] id);
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids ID
	 * @return
	 */
	public int updateStatus(Byte status,Long... ids);
	
	/**
	 * Title:根据文件MD5获取文件记录
	 * @author yuwu on 2016年5月7日
	 * @param fileMd5
	 * @return
	 */
	public InfoFile getByFileMd5(String fileMd5);
	
	/**
	 * Title:获取未上传文件列表
	 * 1、包括上传状态为0（未上传至云平台）或者上传状态为1但上传时间已超过12个小时
	 * @author yuwu on 2016年5月7日
	 * @param fileMd5
	 * @return
	 */
	public List<InfoFile> getNotUploadList();
	//音视频转码
	public void transCoding();
	
	public InfoFile saveInfoFile(InfoFile infoFile);
}
