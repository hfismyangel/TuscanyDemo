package com.jnshu.service;

import com.jnshu.pojo.Student;
import org.oasisopen.sca.annotation.Remotable;

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
@Remotable
public interface StudentServiceDemo {
    List<Student> queryAllStudent();
}
