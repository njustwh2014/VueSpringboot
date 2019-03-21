/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package seu.wh.seuwh_mstc.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seu.wh.seuwh_mstc.controller.UserController;
import seu.wh.seuwh_mstc.dao.AdminDao;
import seu.wh.seuwh_mstc.model.Admin;
import seu.wh.seuwh_mstc.result.ResultInfo;
import seu.wh.seuwh_mstc.service.AdminService;
import seu.wh.seuwh_mstc.utils.Md5Utils;

import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {
    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    AdminDao adminDao;


    //login
    @Override
    public ResultInfo login(String username, String password) {

        Admin admin=adminDao.selectByUsername(username);
        if(admin!=null){
            //正常登陆
            if(!Md5Utils.MD5(password+admin.getSalt()).equals(admin.getPassword())){
                return ResultInfo.build(400,"用户名或密码错误！");
            }
            admin.setToken(UUID.randomUUID().toString());//update token
            try{
                adminDao.updateToken(admin);
                return(ResultInfo.ok(admin));
            }catch (Exception e){
                logger.error("更新用户token异常"+e.getMessage());
                e.printStackTrace();
                return(ResultInfo.build(500,"更新Token时服务器异常！"));
            }
        }else{
            //初始阶段统一注册，相当于不设密码
            admin=new Admin();
            admin.setSalt(UUID.randomUUID().toString().substring(0, 5));
            admin.setToken(UUID.randomUUID().toString());
            admin.setPassword(Md5Utils.MD5(password + admin.getSalt()));
            admin.setUsername(username);
            try{
                adminDao.insertAdmin(admin);
                return ResultInfo.ok(admin);
            }catch (Exception e){
                logger.error("添加用户异常"+e.getMessage());
                e.printStackTrace();
                return(ResultInfo.build(500, "添加时服务器发生错误！"));
            }
        }
        return null;
    }
}
