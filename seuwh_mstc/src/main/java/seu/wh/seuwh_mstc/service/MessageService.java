/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package seu.wh.seuwh_mstc.service;

import seu.wh.seuwh_mstc.model.SystemMessage;
import seu.wh.seuwh_mstc.result.ResultInfo;

import java.util.List;

public interface MessageService {

    /*
     * 验证用户是否有未读消息
     * @输入：用户id
     * @输出：未读消息数
     * */
    public Integer validateUserHaveUnreadMessage(Integer userid);

    /*
    * 获取前4条未读消息
    * @输入：用户id
    * @输出：未读消息
    * */
    public List<SystemMessage> getFourUnreadMessage(Integer userid);
}
