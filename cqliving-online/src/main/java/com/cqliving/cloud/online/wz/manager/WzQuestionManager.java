package com.cqliving.cloud.online.wz.manager;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.WzQuestionDetailResult;
import com.cqliving.cloud.online.interfacc.dto.WzQuestionResult;
import com.cqliving.cloud.online.wz.domain.WzCollectInfo;
import com.cqliving.cloud.online.wz.domain.WzQuestion;
import com.cqliving.cloud.online.wz.dto.WzQuestionData;
import com.cqliving.cloud.online.wz.dto.WzQuestionDto;
import com.cqliving.cloud.online.wz.dto.WzQuestionExcelDownload;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.service.EntityService;

/**
 * 问政问题表 Manager Date: 2016-05-09 16:59:11
 * 
 * @author Code Generator
 */
public interface WzQuestionManager extends EntityService<WzQuestion> {

    /**
     * Title:问政列表查询
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月25日
     * @param conditions
     * @param page
     * @return
     */
    ScrollPage<WzQuestionDto> queryScrollPage(Map<String, Object> conditions, ScrollPage<WzQuestionDto> page);
    
    /**
     * Title:我的问政列表
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月11日
     * @param appId
     * @param token
     * @param lastId
     * @param query
     * @return
     */
    List<WzQuestionDto> getMyQuestionByPage(Long appId, String sessionId, String token, String lastId, String query);
    
    /**
     * Title:
     * <p>
     * Description:
     * </p>
     * 
     * @author DeweiLi on 2016年5月10日
     * @param questionId
     * @param isGetTransfer 是否需要获取问政事件进度
     * @return
     */
    WzQuestionDto getQuestionDetails(Long questionId, boolean isGetTransfer);
    
    /**
     * @deprecated 不建议使用此方法了，请使用saveI方法
     * Title:问政新增和修改
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月11日
     * @param appId
     * @param sessionId
     * @param token
     * @param id
     * @param type
     * @param regionCode
     * @param regionName
     * @param title
     * @param content
     * @param photos
     * @param place
     * @param postion
     * @return 1:需要审核；0：不需要审核。返回回去为提示用
     */
    Integer saveOrModify(Long appId, String sessionId, String token, WzQuestion params, String[] photos, Long[] collectId, String[] collectValue);
    
    /**
     * Title:保存和修改接口
     * <p>Description:</p>
     * @author DeweiLi on 2016年7月25日
     * @param appId
     * @param sessionId
     * @param token
     * @param params
     * @param photos
     * @param collectIds
     * @param collectValues
     * @return 1:需要审核；0：不需要审核。返回回去为提示用
     */
    Integer saveI(Long appId, String sessionId, String token, WzQuestion params, String photos, String collectIds, String collectValues);
    
    /**
     * Title:删除我的问政
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月11日
     * @param appId
     * @param token
     * @param questionid
     */
    void deleteMyQuestion(Long appId, String sessionId, String token, Long questionId);
    
    /**
     * 修改状态
     * @param status 状态
     * @param ids ID
     * @return
     */
    public int updateStatus(Byte status,Long... ids);
    
    /**
     * 删除评论
     * @param replyId
     * @return
     */
    void delReply(Long replyId);
    
    /**
     * Title:
     * <p>Description:</p>
     * @author DeweiLi on 2016年6月6日
     * @param appId
     * @param sessionId
     * @param token
     * @return
     */
    List<WzCollectInfo> addData(Long appId, String sessionId, String token);
    
    /**
     * Title:
     * <p>Description:</p>
     * @author DeweiLi on 2016年7月19日
     * @param conditions
     * @param page
     * @return
     */
    CommonListResult<WzQuestionData> getPageByWzQuestion(Map<String, Object> conditions, ScrollPage<WzQuestionDto> page);
    
    /**
     * Title:
     * <p>Description:</p>
     * @author DeweiLi on 2016年8月24日
     * @param conditions
     * @param page
     * @return
     */
    CommonListResult<WzQuestionData> getPageByWzQuestionNew(Map<String, Object> conditions, ScrollPage<WzQuestionDto> page);

    /**
     * Title:获得问政详情
     * <p>Description:</p>
     * @author DeweiLi on 2016年8月23日
     * @param questionId
     * @return
     */
    WzQuestionResult getDetails(Long questionId);
    
    /**
     * Title:修改问政获得详情接口用
     * <p>Description:</p>
     * @author DeweiLi on 2016年9月9日
     * @param questionId
     * @return
     */
    WzQuestionDetailResult getDetailsUseModify(Long questionId);

	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年10月18日
	 * @param searchMap
	 * @param sortMap
	 * @return
	 */
	List<WzQuestionExcelDownload> excelDownload(Map<String, Object> searchMap, Map<String, Boolean> sortMap);
}
