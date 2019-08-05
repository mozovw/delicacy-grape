package com.delicacy.grape.web.servlet2;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;


public class MyHttpServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Writer writer = response.getWriter();
        ServletContext servletContext = getServletContext();
        servletContext.log("MyHttpServlet doGet ...");
        writer.write("<html><body>Hello,World From MyHttpServlet</body></html>");
    }


}
