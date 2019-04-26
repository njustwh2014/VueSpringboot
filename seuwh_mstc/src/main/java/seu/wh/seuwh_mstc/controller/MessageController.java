/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package seu.wh.seuwh_mstc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import seu.wh.seuwh_mstc.model.SystemMessage;
import seu.wh.seuwh_mstc.result.ResultInfo;
import seu.wh.seuwh_mstc.service.MessageService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/message") //对于前端请求处理
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
    @Autowired
    MessageService messageService;
    /*
    * 接口：获取用户未读消息数
    *
    * */
    @RequestMapping(value = "/unreadstatus/{userid}",method = RequestMethod.GET)
    public ResultInfo getUserUnreadMessageStatus(@PathVariable Integer userid){
        try{
            Map<String,Object> message=new HashMap<>();
            Integer total=messageService.validateUserHaveUnreadMessage(userid);
            message.put("total",total);
            if(total==0){
                return ResultInfo.ok(message);
            }
            List<SystemMessage> systemMessageList=messageService.getFourUnreadMessage(userid);
            message.put("messagelist",systemMessageList);
            return ResultInfo.ok(message);

        }catch (Exception e){
            logger.error("获取用户未读消息数目时出现异常！"+e.getMessage());
            e.printStackTrace();
            return ResultInfo.build(500,"获取用户未读消息数目时出现异常！");
        }
    }
}
