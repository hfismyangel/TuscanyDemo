package com.jnshu.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.text.DateFormat;

/**
 * Created by hfismyangel@163.com on 2017/7/5.
 */
public class timeTaglib extends SimpleTagSupport {
    private Long longTimeHandler;
    private PageContext pageContext;
//可以在自定义标准中设置各种属性，要接收属性，值自定义标签类必须实现setter方法


    public void setLongTimeHandler(Long longTimeHandler) {
        this.longTimeHandler = longTimeHandler;
    }

    public void doTag() throws JspException,IOException{
        if (longTimeHandler != null) {
            System.out.println("-----------------------");
            //获取out对象，通过out输出
            JspWriter out = getJspContext().getOut();
            out.print(DateFormat.getInstance().format(longTimeHandler));
        }
    }
}
