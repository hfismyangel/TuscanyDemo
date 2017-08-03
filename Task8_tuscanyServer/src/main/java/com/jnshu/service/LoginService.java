package com.jnshu.service;

import com.jnshu.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by hfismyangel@163.com on 2017/7/6.
 */
public interface LoginService {
    Boolean loginJudge(String username, String password);
    String queryUserUId(String userName);
    Boolean insertLoginTime(Long loginTime, String uid);
    Boolean matchUidAndCreateTime(String uid, String time);
    Boolean insertUser(User user, String passwordCode, String code) throws UnsupportedEncodingException;
    Boolean insertPhotoService(String uuid, String pic);
    String selectPic(String username);
}
