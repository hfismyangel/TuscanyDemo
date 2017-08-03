package com.jnshu.util;

import com.jnshu.service.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;


/**
 * Created by hfismyangel@163.com on 2017/7/7.
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    LoginService loginService;
    //log4j配置
    private static Logger logger = Logger.getLogger(LoginHandlerInterceptor.class);
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        Cookie[] cookies2 = request.getCookies();
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        if(userName != null && !"".equals(userName.trim()) && password != null && !"".equals(password.trim())){
            return true;
        }
        if(request.getSession().getAttribute("username")!=null){
            return true;
        }
        if (cookies2 != null && cookies2.length > 1) {
            logger.debug("未经登录且无session的访问" + "--------------------------");
            for (Cookie cookie : cookies2) {
                logger.debug(cookie.getName() + "---------------------------");
                if (cookie.getName().equals("dseToken")) {
                    String desEncode = cookie.getValue();
                    String decode = URLDecoder.decode(desEncode, "UTF-8");
                    String decrypt = DesUtils.decrypt(decode, "12345678");
                    logger.debug("解码后的uid加登录时间："+decrypt+"---------------------");
                    String[] strings=decrypt.split("/");
                    String uid = strings[0];
                    String loginTime = strings[1];
                    String username=strings[2];
                    logger.debug(uid+"-----------"+loginTime+"----------"+username);
                    logger.debug(loginTime + "--------------------------");
                    Boolean judge2 = loginService.matchUidAndCreateTime(uid,loginTime);
                    logger.debug("cookie验证："+judge2+"--------------------------------");
                    if (judge2) {
                        request.getSession().setAttribute("username",username);
                        return true;
                    }
                }
            }
        }
        return false;

    }


    //return fales则跳出拦截链，跳出控制器，显示空页面；true则调至下一个拦截器，再交给控制器。


    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


}
