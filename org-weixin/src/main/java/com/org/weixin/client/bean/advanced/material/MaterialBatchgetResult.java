package com.org.weixin.client.bean.advanced.material;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.org.weixin.client.bean.base.BaseResult;

public class MaterialBatchgetResult extends BaseResult{

	private String total_count;

	private String item_count;

	private List<MaterialBatchgetResultItem> item;

	public String getTotal_count() {
		return total_count;
	}

	public void setTotal_count(String total_count) {
		this.total_count = total_count;
	}

	public String getItem_count() {
		return item_count;
	}

	public void setItem_count(String item_count) {
		this.item_count = item_count;
	}

	public List<MaterialBatchgetResultItem> getItem() {
		return item;
	}

	public void setItem(List<MaterialBatchgetResultItem> item) {
		this.item = item;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
