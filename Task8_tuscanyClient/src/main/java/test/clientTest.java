package test;

import com.jnshu.pojo.Developer;
import com.jnshu.pojo.Student;
import com.jnshu.service.CacheUtil;
import com.jnshu.service.DeveloperService;
import com.jnshu.service.LoginService;
import com.jnshu.service.StudentService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by hfismyangel@163.com on 2017/7/17.
 */
public class clientTest {
    private static final Logger logger = Logger.getLogger(clientTest.class);

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "spring-rmiclient.xml");
        StudentService studentService = (StudentService) ctx
                .getBean("studentService");
        List<Student> s = studentService.queryAllStudent();
        for (Student stu : s) {
            System.out.println(stu.toString() + "--------------------------");
        }
        logger.info(studentService.countService() + "---------------------------");
        DeveloperService developerService = (DeveloperService) ctx.getBean("developerService");
        List<Developer> developers = developerService.queryDeveloper();
        for (Developer d : developers) {
            logger.debug(d.toString());
        }
        LoginService loginService = (LoginService) ctx.getBean("loginService");
        logger.debug(loginService.selectPic("hukaibo"));
        CacheUtil cacheUtil = (CacheUtil) ctx.getBean("cacheService");
        logger.debug(cacheUtil == null);
    }


}
