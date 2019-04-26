/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package seu.wh.seuwh_mstc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seu.wh.seuwh_mstc.dao.SystemMessageDao;
import seu.wh.seuwh_mstc.model.SystemMessage;
import seu.wh.seuwh_mstc.service.MessageService;

import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {


    @Autowired
    SystemMessageDao systemMessageDao;
    /*
    * 验证用户是否有未读消息
    * @输入：用户id
    * @输出：未读消息数
    * */
    @Override
    public Integer validateUserHaveUnreadMessage(Integer userid) {
        Integer ret=systemMessageDao.getUserUnreadStatus(userid);
        return ret;
    }

    /*
     * 获取前4条未读消息
     * @输入：用户id
     * @输出：未读消息
     * */

    @Override
    public List<SystemMessage> getFourUnreadMessage(Integer userid) {
        List<SystemMessage> ret=systemMessageDao.getFourUnreadMessage(userid);
        return ret;
    }
}
