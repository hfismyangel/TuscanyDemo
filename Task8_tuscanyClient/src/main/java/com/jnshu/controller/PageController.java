package com.jnshu.controller;

import com.jnshu.pojo.Developer;
import com.jnshu.pojo.Student;
import com.jnshu.pojo.User;
import com.jnshu.service.CacheUtil;
import com.jnshu.service.DeveloperService;
import com.jnshu.service.LoginService;
import com.jnshu.service.StudentService;
import com.jnshu.util.DesUtils;
import com.jnshu.util.FileUploadTest;
import com.jnshu.util.SMSTest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Random;
import java.util.UUID;


/**
 * Created by hfismyangel@163.com on 2017/7/4.
 */
@Controller
@RequestMapping(value = "/page")
public class PageController {
    @Autowired
    private StudentService ser;

    @Autowired
    private DeveloperService deve;

    @Autowired
    private LoginService loginService;

    @Autowired
    private StudentService studentService;
    @Autowired
    private SMSTest smsTest;
    @Autowired
    private FileUploadTest fileUploadTest;
    @Autowired
    @Qualifier("cacheService")
    private CacheUtil cacheUtil;

    private static Logger logger = Logger.getLogger(Controller.class);

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> test() {
        List<Student> students = studentService.queryAllStudent();
        return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/registerPage")
    public String register() {
        return "registerPage";
    }


    @RequestMapping("/u/homepage")
    public ModelAndView index1(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        if (userName != null && !"".equals(userName.trim()) && password != null && !"".equals(password.trim())) {
            logger.debug("controller验证username/password-------------------------");
            Boolean judge = loginService.loginJudge(userName, password);
            logger.debug(judge + "-----------------------");
            if (judge == false) {
                request.getRequestDispatcher("/WEB-INF/statics/jsp/loginFail.jsp").forward(request, response);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("username", userName);
                String webpic = loginService.selectPic(userName);
                logger.debug("图片地址查询：" + webpic + "---------------------");
                session.setAttribute("webPic", webpic);
                //session过期时间
                session.setMaxInactiveInterval(600);
            }
        }
        //该方法接收checkbox等多值数据，返回参数数组，若没有勾选返回null，勾选则进行判断
        String[] isUseCookies = request.getParameterValues("isUseCookies");
        //将数据保存在cookies里，非空验证
        if (isUseCookies != null && isUseCookies.length > 0) {
            logger.debug("cookie保存操作-------------------------");
            String uuid = loginService.queryUserUId(userName);
            //取当前时间的长整形值包含毫秒
            long millis = System.currentTimeMillis();
            //把登录时间毫秒加入数据库
            loginService.insertLoginTime(millis, uuid);
            StringBuffer stringBuffer = new StringBuffer(uuid + "/");
            stringBuffer.append(millis + "/");
            stringBuffer.append(userName);
            logger.debug(stringBuffer + "---------------------------");
            //des加密
            String encode = DesUtils.encrypt(stringBuffer.toString(), "12345678");
            //编为utf8，否则存在特殊符号无法存入cookie
            String encode1 = URLEncoder.encode(encode, "UTF-8");
            logger.debug("utf8加密后：" + encode1 + "---------------------------");
            Cookie tokenCookie = new Cookie("dseToken", encode1);
            logger.debug(tokenCookie.getName() + tokenCookie.getValue());
            tokenCookie.setMaxAge(486000);//设置cookie的存在时间
            //   tokenCookie.setDomain(".Task5/login");跨域访问
            //   tokenCookie.setPath("/");
            response.addCookie(tokenCookie);
        } else {
            //如果未勾选保存密码，清除该cookie同时保留其他用户的cookie，但login页面对cookie进行匹配验证，其他cookie不会被读出
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length > 0) {
                for (Cookie c : cookies) {
                    if (c.getName().equals("userName")) {
                        c.setMaxAge(0);
                        response.addCookie(c);
                        //上面两段合在一起销毁cookies，不能落下第二句
                    }
                }
            }
        }
        ModelAndView modelAndView = new ModelAndView("body1.page");
        int num = ser.countService();
        modelAndView.addObject("count", num);
        List<Student> students = studentService.queryAllStudent();
        modelAndView.addObject("studentList", students);
        logger.debug((students == null) + "-------------------------------");
        return modelAndView;
    }

    @RequestMapping("/u/profession")
    public ModelAndView professionPage() {
        List<Developer> list = deve.queryDeveloper();
        ModelAndView modelAndView = new ModelAndView("body2.page", "develperList", list);
        return modelAndView;
    }


    @RequestMapping("/register")
    public String userRegister(@ModelAttribute User user, HttpServletRequest request) throws UnsupportedEncodingException {
        logger.debug(user.toString() + "------------------------");
        String passwordCode = request.getParameter("telephoneRegister");
        Integer code = (Integer) request.getSession().getAttribute("code");
        logger.debug(passwordCode + "-------------------------------");
        if (loginService.insertUser(user, passwordCode, code.toString())) {
            request.getSession().setAttribute("username", user.getUserName());
            return "registerSuccess";
        }
        return "registerFail";
    }

    @RequestMapping("/sendmessage")
    public void sendMessage(@ModelAttribute("telephone") String telephoneRegister, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug(request.getParameter("data"));
        String userIp = getIp(request);
        logger.debug(userIp + "--------------------------");
        String num = cacheUtil.get(userIp);
        logger.debug(num + "----------------------------");
        if (num == null) {
            cacheUtil.put(userIp, 1);
        }
        if (num != null & Integer.parseInt(num) < 5) {
            cacheUtil.put(userIp, Integer.parseInt(cacheUtil.get(userIp)) + 1);
            //短信验证
            Random random = new Random();
            Integer code = random.nextInt(9000) + 1000;
            try {
                smsTest.messageSend(telephoneRegister, code.toString());
            } catch (Exception e) {
                logger.debug("smsSystemException:" + e.toString());
            }
            request.getSession().setAttribute("code", code);
        }
        if (Integer.parseInt(num) > 1) {
            logger.debug("今日验证码发送已达到限制");
            request.getRequestDispatcher("/WEB-INF/statics/jsp/getcodefail.jsp").forward(request, response);
        }

    }

    @RequestMapping("/u/doupload")
    public String uploadPhoto(@ModelAttribute("photoPath") String photoPath, HttpServletRequest request) {
        UUID uuid = UUID.randomUUID();
        try {
            fileUploadTest.upload(photoPath, uuid.toString());
            logger.debug("-----------------上传成功！");
            String username = (String) request.getSession().getAttribute("username");
            String userId = loginService.queryUserUId(username);
            loginService.insertPhotoService(userId, "http://ot0c2ozoa.bkt.clouddn.com/" + uuid.toString());
        } catch (IOException e) {
            logger.debug("upLoadSystemException:" + e.toString());
        }
        return "uploadSuccess";
    }

    @RequestMapping("/u/upload")
    public String uploadPic() {
        return "userInf.page";
    }

    private String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.indexOf(",") != -1) {
            ip = ip.substring(0, ip.indexOf(","));
        }
        return ip.trim();
    }

}

