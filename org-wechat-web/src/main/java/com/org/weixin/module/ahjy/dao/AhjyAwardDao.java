package com.org.weixin.module.ahjy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.wechat.framework.dao.jpa.EntityJpaDao;

import com.org.weixin.module.ahjy.domain.AhjyAward;

/**
 * 奖品表 JPA Dao
 *
 * Date: 2016-03-29 11:44:40
 *
 * @author Acooly Code Generator
 *
 */
public interface AhjyAwardDao extends EntityJpaDao<AhjyAward,Long> {
    /**
     * Title:随机获取一个奖品
     * @author yuwu on 2016年3月27日
     * @param activityId
     * @return
     */
    @Query(nativeQuery = true,value = "select * from Ahjy_Award where num > 0 order by rand() limit 1")
    List<AhjyAward> geRandomList();
    @Query(nativeQuery = true,value = "select * from Ahjy_Award where num > 0 order by num asc")
    List<AhjyAward> getList();
}
