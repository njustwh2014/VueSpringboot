/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
/*
* 用户管理
*
*
*
* */
package seu.wh.seuwh_mstc.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import seu.wh.seuwh_mstc.dao.UserDao;
import seu.wh.seuwh_mstc.model.User;
import seu.wh.seuwh_mstc.result.ResultInfo;
import seu.wh.seuwh_mstc.service.UserService;
import seu.wh.seuwh_mstc.utils.EmailUtils;
import seu.wh.seuwh_mstc.utils.Md5Utils;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    //session 的过期时间
    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;

    @Autowired
    UserDao userDao;


    //获取所有用户
    @Override
    public ResultInfo getAllUser(Integer pageNumber, Integer pageSize) {
        try{
            List<Map<String,Object>> allUser=userDao.getAllUser((pageNumber-1)*pageSize,pageSize);
            Map<String,Object> ret=new HashMap<>();
            ret.put("data",allUser);
            Integer total=userDao.countUser();
            ret.put("total",total);
            return ResultInfo.ok(ret);
        }catch (Exception e){
            logger.error("获取所有用户时出现异常",e.getMessage());
            e.printStackTrace();
            return ResultInfo.build(500,"获取所有用户时服务器异常");
        }
    }


    //删除用户

    @Override
    public ResultInfo deleteUser(Integer id) {
        try{
            userDao.deleteUser(id);
            return ResultInfo.ok();
        }catch (Exception e){
            logger.error("删除用户时出现异常",e.getMessage());
            e.printStackTrace();
            return ResultInfo.build(500,"删除用户时服务器出现异常！");
        }
    }
}