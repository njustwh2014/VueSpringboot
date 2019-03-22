/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package seu.wh.seuwh_mstc.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import seu.wh.seuwh_mstc.dao.AdminDao;
import seu.wh.seuwh_mstc.dao.UserDao;
import seu.wh.seuwh_mstc.model.Admin;
import seu.wh.seuwh_mstc.model.User;
import seu.wh.seuwh_mstc.model.HostHolder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class PassportInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(PassportInterceptor.class);
    @Autowired
    private UserDao userDao;

    @Autowired
    private HostHolder hostHolder;
    @Autowired
    AdminDao adminDao;

    //所有请求执行之前,返回true才能进行下一步，如果返回false后续的Interceptor 和Controller 都不会再执行，直接结束
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.info("request请求地址path[{}] uri[{}]",request.getServletPath(),request.getRequestURI());
        String token=request.getHeader("Token");
        if(token!=null){
            Admin admin=null;
            admin=adminDao.selectByToken(token);
            if(admin!=null){
                hostHolder.setAdmin(admin);
            }
        }
        return true;
    }

    //渲染之前
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }


    //结束后
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostHolder.clear();
    }
}
