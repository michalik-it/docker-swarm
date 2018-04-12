package com.stanusch.docker.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

@RestController
public class HelloWord {

    @Autowired
    private NameStorage nameStorage;

    @RequestMapping("/hello")
    public String hello() {
        String name = nameStorage.getName() == null ? "You" : nameStorage.getName();
        return "Hello " + name + ". Machine details: " +  this.getIp();
    }

    @RequestMapping("/name/{name}")
    public String name(@PathVariable String name) {
        nameStorage.setName(name);
        return nameStorage.getName() + " stored";
    }

    private String getIp() {
        String result = "";
        Enumeration e = null;
        try {
            e = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e1) {
            e1.printStackTrace();
        }
        int no = 0;
        while(e.hasMoreElements())
        {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration ee = n.getInetAddresses();

            while (ee.hasMoreElements())
            {
                no++;
                InetAddress i = (InetAddress) ee.nextElement();
                result += " " + no + ")IP: " + i.getHostAddress() + " ("+ n.getDisplayName() + ") //// ";
            }
        }
        return result;
    }

}
