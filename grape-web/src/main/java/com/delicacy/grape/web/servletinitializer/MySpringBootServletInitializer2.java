package com.delicacy.grape.web.servletinitializer;

import com.delicacy.grape.web.springboot.MyHttpServlet2;
import com.delicacy.grape.web.springboot.MyOncePerRequestFilter2;
import com.delicacy.grape.web.springboot.MyServletRequestListener2;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;

@Configuration
@ServletComponentScan(basePackages = {"com.delicacy.grape.web.springboot"})
public class MySpringBootServletInitializer2 extends SpringBootServletInitializer {


    private static final String servlet = "myservlet2";
    private static final String servlet_path = "/myservlet2_path";


    @Bean
    public static ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new MyHttpServlet2());
        servletRegistrationBean.setName(servlet);
        servletRegistrationBean.addUrlMappings(servlet_path);
        servletRegistrationBean.addInitParameter("myname", "myvalue");
        return servletRegistrationBean;
    }

    @Bean
    public static FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyOncePerRequestFilter2());
        filterRegistrationBean.addServletNames(servlet);
        filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE);
        return filterRegistrationBean;

    }

    @Bean
    public static ServletListenerRegistrationBean servletListenerRegistrationBean() {
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new MyServletRequestListener2());
        return servletListenerRegistrationBean;
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        builder.sources(MySpringBootServletInitializer2.class);
        return builder;
    }


}
