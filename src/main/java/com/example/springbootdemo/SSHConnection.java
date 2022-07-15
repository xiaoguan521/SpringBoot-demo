package com.example.springbootdemo;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Author liuXiaoChen
 * Date 2022-06-14 - 9:06
 * Description TODO
 */
@Component
@Configuration
public class SSHConnection {
    // 自定义的中转接口，需要和数据源接口设置一样
    @Value("${sshconfig.local-port}")
    private Integer localPort;
    // 服务端的数据库端口
    @Value("${sshconfig.remote-port}")
    private Integer remotePort;
    // 服务器端SSH端口 默认是22
    @Value("${sshconfig.ssh.remote-port}")
    private Integer sshRemotePort;
    // SSH用户名
    @Value("${sshconfig.ssh.user}")
    private String sshUser;
    // SSH使用密码
    @Value("${sshconfig.ssh.password}")
    private String sshPassword;
    // 连接到哪个服务端的SSH
    @Value("${sshconfig.ssh.remote-server}")
    private String sshRemoteServer;
    // 服务端的本地mysql服务
    @Value("${sshconfig.mysql.remote-server}")
    private String mysqlRemoteServer;

    //represents each ssh session
    private Session sesion;

    /**
     * 创建SSH连接
     */
    public void createSSH() throws Throwable {

        JSch jsch = new JSch();
        // 需要用到了开启
        sesion = jsch.getSession(sshUser, sshRemoteServer, sshRemotePort);
        sesion.setPassword(sshPassword);

        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        sesion.setConfig(config);
        // 去连接
        sesion.connect(); //ssh connection established!
        //  设置转发
        sesion.setPortForwardingL(localPort, mysqlRemoteServer, remotePort);
    }

    /**
     * 关闭SSH连接
     */
    public void closeSSH() {
        if (sesion != null) {
            sesion.disconnect();
        }
    }


}
