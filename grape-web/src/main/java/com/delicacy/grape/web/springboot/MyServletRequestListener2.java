package com.delicacy.grape.web.springboot;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;


public class MyServletRequestListener2 implements ServletRequestListener {


    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        ServletContext servletContext = request.getServletContext();
        servletContext.log("MyServletRequestListener2 was initialized!");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        ServletContext servletContext = request.getServletContext();
        servletContext.log("MyServletRequestListener2 was destroyed!");
    }
}
