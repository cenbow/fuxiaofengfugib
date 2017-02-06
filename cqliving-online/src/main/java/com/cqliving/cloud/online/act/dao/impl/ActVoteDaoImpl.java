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

import com.cqliving.cloud.online.act.dao.ActVoteDaoCustom;
import com.cqliving.cloud.online.act.domain.ActVote;
import com.cqliving.cloud.online.act.domain.ActVoteItem;
import com.cqliving.cloud.online.act.dto.ActVoteClassifyDto;
import com.cqliving.cloud.online.act.dto.ActVoteDto;
import com.cqliving.cloud.online.act.dto.ActVoteItemDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.google.common.collect.Lists;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年6月25日
 */
public class ActVoteDaoImpl implements ActVoteDaoCustom {

	@Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplate;

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.act.dao.ActVoteDaoCustom#findDetailById(java.lang.Long)
	 */
	@Override
	public ActVoteDto findDetailById(Long id) {

        StringBuilder sql = new StringBuilder();
        
        sql.append("select * from act_vote where id = ? and status=3 ");
        
        List<ActVoteDto> actVoteDtos = mysqlPagedJdbcTemplate.queryForList(ActVoteDto.class, sql.toString(), id);
        
        if(CollectionUtils.isEmpty(actVoteDtos))return null;
        
        ActVoteDto actVoteDto = actVoteDtos.get(0);
        
        sql = new StringBuilder();
        sql.append("select avc.*,avi.act_value,avi.act_vote_classify_id,avi.content,");
        sql.append("avi.create_time vote_item_create_time,avi.id vote_item_id,avi.image_url,avi.info_file_id,avi.init_value,");
        sql.append("avi.item_title,avi.item_desc,avi.number,avi.sort_no vote_item_sort_no,");
		sql.append("avi.`status`,avi.video_url ");
		sql.append("from act_vote_classify avc ,act_vote_item avi ");
		sql.append("where avc.ID = avi.act_vote_classify_id and avi.status=3 and avc.status=3 ");
		sql.append("and avc.act_vote_id = ? ");
		sql.append("order by avc.sort_no ,avi.sort_no ");
		
		List<ActVoteClassifyDto> actVoteClassifyDtos = mysqlPagedJdbcTemplate.queryForList(ActVoteClassifyDto.class, sql.toString(), id);
		
		if(CollectionUtils.isEmpty(actVoteClassifyDtos)) return actVoteDto;
		
		List<ActVoteClassifyDto> voteClassify = Lists.newArrayList();
		for(ActVoteClassifyDto actVoteClassifyDto : actVoteClassifyDtos){
			
			if(!voteClassify.contains(actVoteClassifyDto)){
				voteClassify.add(actVoteClassifyDto);
				
				List<ActVoteItemDto> voteItems = actVoteClassifyDto.getActVoteItems();
				if(null == voteItems){
					voteItems = Lists.newArrayList();
					actVoteClassifyDto.setActVoteItems(voteItems);
				}
				ActVoteItem item = this.cover(actVoteClassifyDto);
				if(!voteItems.contains(item) && item.getActVoteClassifyId().longValue() == actVoteClassifyDto.getId().longValue()){
					ActVoteItemDto dto = new ActVoteItemDto();
					BeanUtils.copyProperties(item, dto);
					voteItems.add(dto);
				}
			}else {
				int index = voteClassify.lastIndexOf(actVoteClassifyDto);
				ActVoteClassifyDto dto = voteClassify.get(index);
				
				List<ActVoteItemDto> voteItems = dto.getActVoteItems();
				if(null == voteItems){
					voteItems = Lists.newArrayList();
					dto.setActVoteItems(voteItems);
				}
				ActVoteItem item = this.cover(actVoteClassifyDto);
				if(!voteItems.contains(item) && item.getActVoteClassifyId().longValue() == dto.getId().longValue()){
					ActVoteItemDto itemdto = new ActVoteItemDto();
					BeanUtils.copyProperties(item, itemdto);
					voteItems.add(itemdto);
				}
			}
		}
		
		actVoteDto.setActVoteClassifyDtos(voteClassify);
		
		return actVoteDto;
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
	 * @see com.cqliving.cloud.online.act.dao.ActVoteDaoCustom#findByVoteClassifyId(java.lang.Long)
	 */
	@Override
	public ActVote findByVoteClassifyId(Long voteClassifyId) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select av.* from act_vote av inner join act_vote_classify avc on av.id = avc.act_vote_id where avc.id = ? ");
		sql.append(" and av.status=3 and avc.status=3 ");
		List<ActVote> data = mysqlPagedJdbcTemplate.queryForList(ActVote.class, sql.toString(), voteClassifyId);
		if(CollectionUtils.isNotEmpty(data)){
			return data.get(0);
		}
		return null;
	}
	
}
