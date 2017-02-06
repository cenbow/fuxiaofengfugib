/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.act.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.act.dao.ActVoteClassifyDaoCustom;
import com.cqliving.cloud.online.act.domain.ActVoteItem;
import com.cqliving.cloud.online.act.dto.ActVoteClassifyDto;
import com.cqliving.cloud.online.act.dto.ActVoteItemDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.google.common.collect.Lists;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年6月27日
 */
public class ActVoteClassifyDaoImpl implements ActVoteClassifyDaoCustom {

	@Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplate;
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.act.dao.ActVoteClassifyDaoCustom#findByClassifyId(java.lang.Long)
	 */
	@Override
	public ActVoteClassifyDto findByClassifyId(Long classifyId) {

       StringBuilder sql = new StringBuilder();
       
       sql.append("select avc.*,avi.act_value,avi.act_vote_classify_id,avi.content,");
       sql.append("avi.create_time vote_item_create_time,avi.id vote_item_id,avi.image_url,avi.info_file_id,avi.init_value,");
       sql.append("avi.item_title,avi.item_desc,avi.number,avi.sort_no vote_item_sort_no,");
	   sql.append("avi.`status`,avi.video_url ");
	   sql.append("from act_vote_classify avc ,act_vote_item avi ");
	   sql.append("where avc.ID = avi.act_vote_classify_id and avc.status=3 and avi.status=3 ");
       sql.append("and avc.id = ? ");
	   sql.append("order by avc.sort_no ,avi.sort_no ");
       
	   List<ActVoteClassifyDto> actVoteClassifyDtos = mysqlPagedJdbcTemplate.queryForList(ActVoteClassifyDto.class, sql.toString(),classifyId);
	   
	   if(CollectionUtils.isEmpty(actVoteClassifyDtos))return null;
	   List<ActVoteItemDto> items = Lists.newArrayList();
	   
	   for(ActVoteClassifyDto actVoteClassifyDto : actVoteClassifyDtos){
		   ActVoteItem item = this.cover(actVoteClassifyDto);
		   ActVoteItemDto dto = new ActVoteItemDto();
		   BeanUtils.copyProperties(item, dto);
		   items.add(dto);
	   }
	   
	   ActVoteClassifyDto dto = actVoteClassifyDtos.get(0);
	   dto.setActVoteItems(items);
	   return dto;
	}

    private ActVoteItem cover(ActVoteClassifyDto actVoteClassifyDto){
		
		if(null == actVoteClassifyDto) return null;
		
		ActVoteItem actVoteItem = new ActVoteItem();
		
		BeanUtils.copyProperties(actVoteClassifyDto, actVoteItem);
		actVoteItem.setId(actVoteClassifyDto.getVoteItemId());
		actVoteItem.setSortNo(actVoteClassifyDto.getVoteItemSortNo());
		actVoteItem.setCreateTime(actVoteClassifyDto.getVoteItemCreateTime());
		return actVoteItem;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.act.dao.ActVoteClassifyDaoCustom#deleteVoteClassifyByVoteId(java.lang.Long)
	 */
	@Override
	public void deleteVoteClassifyByVoteId(Long voteId) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from act_vote_classify where act_vote_id = ? ");
		
		mysqlPagedJdbcTemplate.update(sql.toString(), voteId);
		
		sql = new StringBuilder();
		/*sql.append("delete from act_vote_item where act_vote_classify_id in ");
		sql.append("(select id from act_vote_classify where act_vote_id = ?)");*/
		sql.append("DELETE avi from act_vote_item  avi ,act_vote_classify avc ");
		sql.append("where avi.act_vote_classify_id = avc.ID and avc.act_vote_id = ?");
		mysqlPagedJdbcTemplate.update(sql.toString(), voteId);
	}
}
