package com.zoctan.api.service.impl;

import com.zoctan.api.entity.Slaver;
import com.zoctan.api.mapper.SlaverMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by fanseasn on 2020/11/26.
 */
/*
 @author Season
 @DESCRIPTION 
 @create 2020/11/26
*/
@Component
public class InitSlaver {

    @Value("${server.port}")
    private String port;

    @Autowired
    private SlaverMapper slaverMapper;

    @PostConstruct
    public void Init()
    {
        System.out.println("启动注册slaver");
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            System.out.println("启动注册slaver异常"+e.getMessage());
            e.printStackTrace();
        }
        String ip=address.getHostAddress();
        Slaver sa=new Slaver();
        sa.setIp(ip);
        sa.setSlavername("执行机"+ip);
        sa.setPort(port);
        sa.setStatus("空闲");
        sa.setStype("");
        sa.setMemo("执行机"+ip);
        if(slaverMapper.findslaverbyip(ip).size()==0)
        {
            slaverMapper.addslaver(sa);
            System.out.println("启动注册slaver完成.......................................................");
        }
        else
        {
            System.out.println("启动注册slaver已注册");
        }
    }
}
