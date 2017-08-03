package com.jnshu.serviceImpl;

import com.jnshu.pojo.Student;
import com.jnshu.service.IserviceTest;
import com.jnshu.service.StudentService;
import org.oasisopen.sca.annotation.Reference;

import java.util.List;

/**
 * Created by hfismyangel@163.com on 2017/7/20.
 */
public class IserviceTestImpl implements IserviceTest {
    private StudentService ss;

    public StudentService getSs() {
        return ss;
    }

    @Reference
    public void setSs(StudentService ss) {
        this.ss = ss;
    }

    public List<Student> queryAllStudent() {
        return this.ss.queryAllStudent();
    }
}
