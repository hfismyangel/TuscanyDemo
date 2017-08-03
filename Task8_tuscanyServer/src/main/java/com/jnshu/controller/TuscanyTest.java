package com.jnshu.controller;

import org.apache.tuscany.sca.node.Node;
import org.apache.tuscany.sca.node.NodeFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * Created by hfismyangel@163.com on 2017/7/18.
 */
public class TuscanyTest {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-*.xml");
        Node node = NodeFactory.newInstance().createNode( "StudentService.composite");
        node.start();
        while (true ) {
            System.out.println("Service1启动发布成功");
            TimeUnit. SECONDS.sleep(Long.MAX_VALUE);
        }
    }
}
