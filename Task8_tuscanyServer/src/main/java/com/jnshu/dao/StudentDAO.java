package com.jnshu.dao;

import com.jnshu.pojo.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by hfismyangel@163.com on 2017/7/4.
 */
@Repository
public interface StudentDAO {
    int countStudent();
    ArrayList<Student> queryAllStudent(String s);
}
