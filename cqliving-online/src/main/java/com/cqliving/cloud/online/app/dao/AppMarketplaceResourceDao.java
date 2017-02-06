package com.cqliving.cloud.online.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.app.domain.AppMarketplaceResource;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 客户端发布市场资源表 JPA Dao
 * Date: 2016-04-15 09:43:50
 * @author Code Generator
 */
public interface AppMarketplaceResourceDao extends EntityJpaDao<AppMarketplaceResource, Long>, AppMarketplaceResourceDaoCustom {
    
    /**
     * <p>Description: 修改状态</p>
     * @author huxiaoping on 2016年4月27日
     * @param id
     * @param status
     */
    @Modifying
    @Query(value = "delete AppMarketplaceResource where versionId=?1")
    public void delByVersionId(Long id);
    
    /**
     * <p>Description: 通过versionId获取</p>
     * @author huxiaoping on 2016年4月27日
     * @param id
     * @param status
     */
    @Query(value = "from AppMarketplaceResource where versionId=?1 order by createTime desc")
    public List<AppMarketplaceResource> getByVersionId(Long id);
	
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update AppMarketplaceResource set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	
	/**
     * 修改排序号
     * @author huxiaoping
     * @param sortNo
     * @param ids
     * @return
     */
    @Modifying
    @Query("update AppMarketplaceResource set sortNo=?1 where id = ?2")
    public void updateSortNo(Integer sortNo,Long id);
}
