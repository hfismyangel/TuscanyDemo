package com.jnshu.service;

import com.jnshu.pojo.Student;
import org.oasisopen.sca.annotation.Remotable;

import java.util.List;

/**
 * Created by hfismyangel@163.com on 2017/7/4.
 */
@Remotable
public interface StudentService {
    int countService();
    List<Student> queryAllStudent();
}
