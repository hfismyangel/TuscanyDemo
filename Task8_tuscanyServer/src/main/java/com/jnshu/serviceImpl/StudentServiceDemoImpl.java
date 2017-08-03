package com.jnshu.serviceImpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jnshu.dao.StudentDAO;
import com.jnshu.pojo.Student;
import com.jnshu.service.StudentServiceDemo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * Title:    Task8_tuscanyServer
 * Description:
 * Company:  www.jnshu.com
 *
 * @author hfismyangel@163.com
 * @version 1.0
 * @Ddate 2017/7/20
 */
public class StudentServiceDemoImpl implements StudentServiceDemo{
    @Autowired
    private StudentDAO studentDAO;
    public static final Logger logger = Logger.getLogger(StudentServiceDemoImpl.class);
    public List<Student> queryAllStudent() {
        /**
        *@Author hfismyangel@163.com
        *@Description:查询所有学生
        *@Date: 19:38 2017/7/20
           * @param
        */
        List<Student> students = studentDAO.queryAllStudent("%");
            return students;
        }
    }


