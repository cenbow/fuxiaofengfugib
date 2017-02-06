package com.cqliving.cloud.online.manuscript.manager;

import java.util.Date;

import com.cqliving.cloud.online.manuscript.domain.ManuscriptColumns;
import com.cqliving.cloud.online.manuscript.domain.ManuscriptInfoClassify;
import com.cqliving.cloud.online.manuscript.dto.ChengKouListData;
import com.cqliving.cloud.online.manuscript.dto.ManuscriptInfo;
import com.cqliving.framework.common.service.EntityService;

/**
 * 抓稿新闻关系表 Manager
 * Date: 2016-11-08 16:06:27
 * @author Code Generator
 */
public interface ManuscriptInfoClassifyManager extends EntityService<ManuscriptInfoClassify> {

	/**
	 * Title:一条一条的插入，是为了报错后不影响其他记录的插入
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月24日
	 * @param createId
	 * @param creator
	 * @param randStr
	 * @param mc
	 * @param mi
	 * @param now
	 * @return
	 */
	Integer importData(Long createId, String creator, ManuscriptColumns mc, ManuscriptInfo mi, Date now);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月27日
	 * @param createId
	 * @param creator
	 * @param mc
	 * @param data
	 * @param now
	 */
	Integer importDataChengKou(Long createId, String creator, ManuscriptColumns mc, ChengKouListData data, Date now);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2017年1月3日
	 * @param guid
	 * @param manuscriptColumnsId
	 * @return
	 */
	ManuscriptInfoClassify queryIsImport(String guid, Long manuscriptColumnsId);
	
	/**
	 * Title:秀山 导入
	 * <p>Description:</p>
	 * @author DeweiLi on 2017年1月4日
	 * @param createId
	 * @param creator
	 * @param mc
	 * @param mi
	 * @param now
	 * @param redisKey redis缓存的key
	 * @return
	 */
	Integer importData(Long createId, String creator, ManuscriptColumns mc, ManuscriptInfo mi , Date now, String redisKey);
}
