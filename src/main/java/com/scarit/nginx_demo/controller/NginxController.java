package com.scarit.nginx_demo.controller;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class NginxController implements ApplicationListener<WebServerInitializedEvent> {

    public int port;

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {

        this.port = event.getWebServer().getPort();
    }

    @GetMapping("host")
    public String getHost(HttpServletRequest request) {
        InetAddress address;
        try {
            address = InetAddress.getLocalHost();
            return address.getHostAddress() + ":" + port;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "error : Network card information isn't available";
    }
}
