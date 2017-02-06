package com.cqliving.cloud.online.building.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.building.domain.BuildingImage;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 楼房图片表 JPA Dao
 * Date: 2016-10-10 17:07:13
 * @author Code Generator
 */
public interface BuildingImageDao extends EntityJpaDao<BuildingImage, Long> {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update BuildingImage set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	
	/**
	 * Title:根据buildingId修改状态
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年10月11日
	 * @param status
	 * @param buildingId
	 * @return
	 */
	@Modifying
    @Query("update BuildingImage set status = ?1 where buildingId=?2")
	public int updateStatusByBuildingId(Byte status, Long buildingId);
	
	/**
	 * Title:根据buildingId删除
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年10月11日
	 * @param buildingId
	 * @return
	 */
	@Modifying
	@Query("delete from BuildingImage where buildingInfoId=?1")
	public int deleteByBuildingId(Long buildingId);
	
	@Query(value="from BuildingImage where buildingInfoId=?1 and status=3")
	public List<BuildingImage> findByBuildingId(Long buildingId);
}
