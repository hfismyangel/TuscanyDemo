package com.jnshu.dao;

import com.jnshu.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by hfismyangel@163.com on 2017/7/6.
 */
@Repository
public interface LoginDAO {
    String queryUser(String userName);
    String queryUid(String userName);
    Long matchUidAndTime(String uid);
    int insertUser(User user);
    int insertUpdateTime(Map map);
    int insertPhoto(Map map);
    String selectPic(String name);
}
