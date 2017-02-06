package com.cqliving.cloud.controller.module.act;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import com.cqliving.cloud.common.Constant;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.act.domain.ActInfo;
import com.cqliving.cloud.online.act.domain.ActTemplate;
import com.cqliving.cloud.online.act.domain.ActVote;
import com.cqliving.cloud.online.act.dto.ActVoteDto;
import com.cqliving.cloud.online.act.service.ActInfoService;
import com.cqliving.cloud.online.act.service.ActTemplateService;
import com.cqliving.cloud.online.act.service.ActVoteService;
import com.cqliving.cloud.online.info.domain.InfoFile;
import com.cqliving.framework.utils.Dates;
import com.cqliving.tool.common.Response;

@Controller
@RequestMapping(value = "/module/act")
public class ActVoteController extends CommonController {

    @Autowired
    private ActVoteService actVoteService;
    @Autowired
    private ActTemplateService actTemplateService;
    @Autowired
    private ActInfoService actInfoService;
    
    //增加-修改-查看
    @RequestMapping(value ="act_voteadd", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,Long actInfoListId,@RequestParam Long actInfoId){
    	
    	ActInfo actInfo = actInfoService.get(actInfoId).getData();
    	if(null == actInfo){
    		 //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
    	}
    	map.put("actInfo",actInfo);
    	
    	if(null != actInfoListId && 0 != actInfoListId){
    		
    		ActVote actVote = actVoteService.findByActInfoListId(actInfoListId).getData();
    		
    		ActVoteDto actVoteDto = actVoteService.findDetailById(actVote.getId()).getData();
            if(actVoteDto==null){
                //没有记录
                return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
            }
            map.put("item", actVoteDto);
    	}
    	
    	map.put("actTemplate", actTemplateService.getByApp(actInfo.getAppId(), ActTemplate.TYPE3).getData());
        return "tiles.module.act.act_votedetail";
    }

    //修改-保存
    @RequestMapping(value ="act_vote_save", method = RequestMethod.POST)
    @ResponseBody
    public Response<ActVoteDto> postUpdate(HttpServletRequest request, Map<String, Object> map,ActVoteDto actVoteDto,InfoFile infoFile){
    	
    	//开始时间不得小于结束时间
    	Date startTime = actVoteDto.getStartTime();
    	Date endTime = actVoteDto.getEndTime();
    	
    	ActInfo actInfo = actInfoService.get(actVoteDto.getActInfoId()).getData();
    	
    	if(null == actInfo || (!actInfo.getEndTime().equals(endTime) && actInfo.getEndTime().before(endTime))){
    		return new Response<ActVoteDto>(ErrorCodes.FAILURE,"结束时间不得大于活动结束时间");
    	}
    	
    	if(!actInfo.getStartTime().equals(startTime) && actInfo.getStartTime().after(startTime)){
    		return new Response<ActVoteDto>(ErrorCodes.FAILURE,"开始时间不得小于活动开始时间");
    	}
    	
    	if(!startTime.equals(endTime) && endTime.before(startTime)){
    		return new Response<ActVoteDto>(ErrorCodes.FAILURE,"开始时间不得大于结束时间");
    	}
    	
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	Long userId = sessionUser.getUserId();
    	String nickName = sessionUser.getNickname();
    	Date now = Dates.now();
    	if(null == actVoteDto.getId() || 0 == actVoteDto.getId()){
    		actVoteDto.setCreateTime(now);
        	actVoteDto.setCreator(nickName);
        	actVoteDto.setCreatorId(userId);
        	actVoteDto.setStatus(ActVote.STATUS3);
    	}
    	actVoteDto.setUpdateTime(now);
    	actVoteDto.setUpdator(nickName);
    	actVoteDto.setUpdatorId(userId);
    	
    	
        return actVoteService.saveActVoteDto(actVoteDto,infoFile);
        
    }

}
