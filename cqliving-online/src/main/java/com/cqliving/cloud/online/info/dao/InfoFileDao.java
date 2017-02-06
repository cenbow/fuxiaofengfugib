package com.cqliving.cloud.online.info.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.info.domain.InfoFile;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 资讯对应的文件表 JPA Dao
 * Date: 2016-05-07 18:00:13
 * @author Code Generator
 */
public interface InfoFileDao extends EntityJpaDao<InfoFile, Long>,InfoFileDaoCustom {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update InfoFile set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	
	/**
	 * Title:根据文件MD5获取文件记录
	 * @author yuwu on 2016年5月7日
	 * @param fileMd5
	 * @return
	 */
    @Query("select t1 from InfoFile t1 where t1.qiniuHash = ?1")
    public List<InfoFile> getByFileMd5(String fileMd5);

    /**
	 * Title:获取未上传文件列表
	 * 1、包括上传状态为0（未上传至云平台）或者上传状态为1但上传时间已超过12个小时
	 * @author yuwu on 2016年5月18日
	 * @param fileMd5
	 * @return
	 */
    @Query("select t1 from InfoFile t1 where (t1.downloadStatus = "+InfoFile.DOWNLOADSTATUS1+" or (t1.downloadStatus = "+InfoFile.DOWNLOADSTATUS2+" and TIMESTAMPDIFF(MINUTE,t1.addQueueTime,now()) > 12*60)) and t1.type in ?1")
	public List<InfoFile> getNotUploadList(List<Byte> types);
    
    @Query(value="from InfoFile where qiniuHash = ?1")
    public List<InfoFile> findByHashCode(String hashCode);
    
    @Query(value="from InfoFile where status = 2 ")
    public List<InfoFile> findAllNoTrans();
}
