package com.jnshu.serviceImpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jnshu.dao.StudentDAO;
import com.jnshu.pojo.Student;
import com.jnshu.service.CacheUtil;
import com.jnshu.service.StudentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by hfismyangel@163.com on 2017/7/4.
 */
@Service
public class StudentServiceImpl implements StudentService {
    private static final Logger logger = Logger.getLogger(StudentServiceImpl.class);
    ArrayList<Student> students;
    @Autowired
    StudentDAO studentDAO;
    @Autowired
    @Qualifier("cacheUtilImpl")
    private CacheUtil cacheUtil;

    public int countService() {
        return studentDAO.countStudent();
    }

    public List<Student> queryAllStudent() {
//        ArrayList<Student> memcachedStudent= ( ArrayList<Student>) CacheManager.getMemCachedClient().get("queryAllStudent");
//        if(memcachedStudent!=null){
//            logger.debug("从缓存中读取Student列表---------------------");
//            return memcachedStudent;
//        }else{
//            ArrayList<Student> students = studentDAO.queryAllStudent("%");
//            Boolean boo= CacheManager.getMemCachedClient().add("queryAllStudent",students);
//            logger.debug(boo+"将数据库查询添加到缓存中----------------------");
        String json = cacheUtil.get("StudentList");
        if (json != null) {
            logger.debug("从缓存中读取Student列表---------------------json为：" + json);
            List<Student> studentsCache = new Gson().fromJson(cacheUtil.get("StudentList"), new TypeToken<List<Student
                    >>() {
            }.getType());
            return studentsCache;
        } else {
            students = studentDAO.queryAllStudent("%");
            cacheUtil.put("StudentList", students);
            logger.debug("--------------将Students添加到redis缓存中");
            return students;
        }
    }

}

