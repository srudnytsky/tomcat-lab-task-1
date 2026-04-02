package com.example;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/time")
public class TimeServlet4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ZonedDateTime utcTime = ZonedDateTime.now(ZoneId.of("UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = utcTime.format(formatter) + " UTC";

        response.getWriter().write("<html>");
        response.getWriter().write("<head><title>Current Time</title></head>");
        response.getWriter().write("<body style='font-family: sans-serif; text-align: center; margin-top: 50px;'>");
        response.getWriter().write("<h1>Current Time (UTC)</h1>");
        response.getWriter().write("<p style='font-size: 24px; font-weight: bold;'>" + formattedTime + "</p>");
        response.getWriter().write("</body>");
        response.getWriter().write("</html>");
        response.getWriter().close();
    }
}
