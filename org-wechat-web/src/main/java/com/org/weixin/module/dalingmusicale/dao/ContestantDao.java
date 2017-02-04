package com.org.weixin.module.dalingmusicale.dao;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.wechat.framework.dao.jpa.EntityJpaDao;

import com.org.weixin.module.dalingmusicale.domain.Contestant;

/**
 * 选手 JPA Dao
 * Date: 2016-09-16 09:09:34
 * @author Code Generator
 */
public interface ContestantDao extends EntityJpaDao<Contestant, Long> {
	
	@Query(value="from Contestant where voteStep=?1 order by voteNum desc,nameSpell asc")
	public List<Contestant> findByVoteStep(Byte voteStep);
	
	@Query(value="from Contestant where code = ?1 and voteStep=?2 order by voteNum desc,nameSpell asc")
	public List<Contestant> findByCodeAndStep(String code,Byte voteStep);
	
	@Modifying
	@Query(value="update Contestant set voteNum = ifnull(voteNum,0) + ?2 where code=?1")
	public int modifyVoteNum(String contestantCode,Integer voteNum);
	
	@Modifying
	@Query(value="update Contestant set beginTime=?1,endTime=?2 where voteStep=?3")
	public void updateTimeByVoteStep(Date beginTime,Date endTime,Byte voteStep);
	
	@Modifying
	@Query(value="update Contestant set endTime=?1 where voteStep=?2")
	public void endContest(Date endTime,Byte voteStep);
	
	@Query(value="SELECT c.* FROM `contestant` c where c.vote_step=2 ORDER BY c.vote_num desc,CAST(c.`code` as signed) asc",nativeQuery=true)
	public List<Contestant> findCampion();
}
