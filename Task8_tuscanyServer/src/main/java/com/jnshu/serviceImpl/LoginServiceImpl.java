package com.jnshu.serviceImpl;

import com.jnshu.dao.LoginDAO;
import com.jnshu.pojo.User;
import com.jnshu.service.LoginService;
import com.jnshu.util.MD5ENcode;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * Created by hfismyangel@163.com on 2017/7/6.
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDAO loginDAO;
    private static Logger logger = Logger.getLogger(LoginServiceImpl.class);


    /*
    注册时password在MD5加盐后存入DB，登陆时重新验证。
     */
    public Boolean loginJudge(String username, String password) {
        String passwordDB = loginDAO.queryUser(username);
        if (passwordDB != null) {
            String salt = "helloworld";
            MD5ENcode encoderMd5 = new MD5ENcode(salt, "MD5");
            String encode = encoderMd5.encode(password);
            logger.debug(encode + "-------------------");
            return encoderMd5.isPasswordValid(passwordDB, password);
        }
        return false;
    }

    public String queryUserUId(String userName) {
        String uid = loginDAO.queryUid(userName);
        return uid;
    }

    public Boolean insertLoginTime(Long loginTime, String uid) {
        Map timeMap = new HashMap();
        timeMap.put("loginTime", loginTime);
        timeMap.put("uid", uid);
        int i = loginDAO.insertUpdateTime(timeMap);
        if (i == 1) {
            return true;
        }
        return false;
    }

    public Boolean matchUidAndCreateTime(String uid, String time) {
        long loginTime = loginDAO.matchUidAndTime(uid);
        if (loginTime == Long.parseLong(time)) {
            return true;
        }
        return false;
    }


    public Boolean insertUser(User user, String passwordCode, String code) throws UnsupportedEncodingException {
        logger.debug("service----------------user:" + user.toString());
        if (code.equals(passwordCode)) {
            //取当前时间的长整形值包含毫秒
            long millis = System.currentTimeMillis();
            //把登录时间毫秒加入实体
            user.setLogin_at(millis);
            UUID uuid = UUID.randomUUID();
            user.setUid(uuid.toString());
            String salt = "helloworld";
            MD5ENcode encoderMd5 = new MD5ENcode(salt, "MD5");
            String encode = encoderMd5.encode(user.getPassword());
            user.setPassword(encode);
            loginDAO.insertUser(user);
            return true;
        } else {
            return false;
        }

    }

    public Boolean insertPhotoService(String uuid, String pic) {
        Map map = new HashMap();
        map.put("uuid", uuid);
        map.put("pic", pic);
        if (loginDAO.insertPhoto(map) == 1) {
            return true;
        }
        return false;
    }

    public String selectPic(String username) {
        String pic = loginDAO.selectPic(username);
        return pic;
    }



}
