package com.cqliving.cloud.online.interfacc.dto;

import com.cqliving.tool.common.util.file.Export;

public class ActExportDto {
	@Export(name = "投票标题", order = 1)
	private String voteTitle;
	@Export(name = "投票项", order = 2)
	private String itemTitle;
	@Export(name = "票数", order = 3)
	private Long voteNum;
	public String getVoteTitle() {
		return voteTitle;
	}
	public void setVoteTitle(String voteTitle) {
		this.voteTitle = voteTitle;
	}
	public String getItemTitle() {
		return itemTitle;
	}
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
	public Long getVoteNum() {
		return voteNum;
	}
	public void setVoteNum(Long voteNum) {
		this.voteNum = voteNum;
	}
	
}
