package com.jnshu.controller;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by hfismyangel@163.com on 2017/7/16.
 */
public class RMITest {
    public static void main(String args[]) throws InterruptedException {


        new ClassPathXmlApplicationContext("spring-*.xml");

        Object lock = new Object();
        synchronized (lock) {
            lock.wait();
        }
    }
}
