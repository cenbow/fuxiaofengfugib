package com.cqliving.cloud.online.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.app.domain.AppTemplet;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 客户端模板表 JPA Dao
 * Date: 2016-05-03 20:01:37
 * @author Code Generator
 */
public interface AppTempletDao extends EntityJpaDao<AppTemplet, Long> {
    /**
     * 查询模板通过Appid和模板code
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月4日下午1:10:51
     */
    @Query("from AppTemplet where appId=?1 and templetCode=?2")
    public List<AppTemplet> queryByAppAndCode(Long appId,String templetCode);
    
    /**
     * 查询模板共有状态
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月21日下午1:10:51
     */
    @Query("from AppTemplet where commonType=?1 order by id")
    public List<AppTemplet> queryByCommonType(Byte commonType);
    
    /**
     * 查询模板通过Appid和模板code,排除某个模板
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月4日下午1:10:51
     */
    @Query("from AppTemplet where appId=?1 and templetCode=?2 and id<> ?3")
    public List<AppTemplet> queryByAppAndCode(Long appId,String templetCode,Long id);
}
