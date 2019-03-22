/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package seu.wh.seuwh_mstc.controller;
//import com.sun.org.apache.xpath.internal.operations.String;
import seu.wh.seuwh_mstc.model.HostHolder;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import seu.wh.seuwh_mstc.dao.UserDao;
import seu.wh.seuwh_mstc.model.User;
import seu.wh.seuwh_mstc.result.ResultInfo;
import seu.wh.seuwh_mstc.utils.EmailUtils;
import seu.wh.seuwh_mstc.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
@RequestMapping(value = "/user") //对于前端请求处理
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private EmailUtils emailUtils;

    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/getall",method = RequestMethod.POST)
    public ResultInfo getAllUser(@RequestBody JSONObject jsonObject){
        Integer pageNumber=jsonObject.getInteger("pageNumber");
        Integer pageSize=jsonObject.getInteger("pageSize");
        return userService.getAllUser(pageNumber,pageSize);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ResultInfo deleteUser(@RequestBody JSONObject jsonObject){
        Integer id=jsonObject.getInteger("id");
        return userService.deleteUser(id);
    }
}
