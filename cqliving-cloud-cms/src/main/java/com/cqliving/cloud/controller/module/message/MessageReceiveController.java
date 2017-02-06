package com.cqliving.cloud.controller.module.message;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.message.domain.MessageReceive;
import com.cqliving.cloud.online.message.service.MessageReceiveService;
import com.cqliving.tool.common.Response;

@Controller
@RequestMapping(value = "/module/message_receive")
public class MessageReceiveController extends CommonController {

    @Autowired
    private MessageReceiveService messageReceiveService;

    /**
     * 修改读取状态
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月11日上午11:38:04
     */
    @RequestMapping(value ="/common/update_status", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> updateStatus(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = messageReceiveService.updateStatus(MessageReceive.STATUS1, ids);
        return res;
    }
    
    //删除
    @RequestMapping(value ="message_receive_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = messageReceiveService.deleteLogic(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="message_receive_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = messageReceiveService.deleteLogic(ids);
        return res;
    }
}
