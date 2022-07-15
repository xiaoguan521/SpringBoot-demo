package com.example.springbootdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Author liuXiaoChen
 * Date 2022-06-14 - 9:10
 * Description TODO
 */
@Slf4j
@WebListener
@Component
public class MyContextListener implements ServletContextListener {
    @Autowired
    private SSHConnection conexionssh;
    /**
     * 监听Servlet初始化事件
     */
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        // 建立连接
        try {
            conexionssh.createSSH();
            log.info("成功建立SSH连接！");
        } catch (Throwable e) {
            log.info("SSH连接失败！");
            e.printStackTrace(); // error connecting SSH server
        }
    }

    /**
     * 监听Servlet终止事件
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // 断开连接
        try {
            conexionssh.closeSSH(); // disconnect
            log.info("成功断开SSH连接!");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("断开SSH连接出错！");
        }
    }

}
