package com.cqliving.cloud.online.manuscript.service;

import com.cqliving.tool.common.Response;

/**
 * 抓稿新闻关系表 Service
 * Date: 2016-11-08 16:06:27
 * @author Code Generator
 */
public interface ManuscriptInfoClassifyService {
	
	/**
	 * Title:重庆app抓稿
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月27日
	 * @return
	 */
	public Response<Void> importData();
	
	/**
	 * Title:城口 抓稿
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月27日
	 * @return
	 */
	public Response<Void> importDataChengKou();
	
	/**
	 * Title:秀山抓稿
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月30日
	 * @return
	 */
	public void impotXiuShan();
	
}
