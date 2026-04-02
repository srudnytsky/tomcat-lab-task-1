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
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");

        String timezoneParam = request.getParameter("timezone");

        String zoneIdString = (timezoneParam == null || timezoneParam.isEmpty())
                ? "UTC"
                : timezoneParam.replace(" ", "+"); // Обробка знаку +

        ZonedDateTime zonedDateTime;
        try {
            zonedDateTime = ZonedDateTime.now(ZoneId.of(zoneIdString));
        } catch (Exception e) {
            zonedDateTime = ZonedDateTime.now(ZoneId.of("UTC"));
            zoneIdString = "UTC";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = zonedDateTime.format(formatter) + " " + zoneIdString;

        response.getWriter().write("<html>");
        response.getWriter().write("<body style='font-family: sans-serif; text-align: center; margin-top: 50px;'>");
        response.getWriter().write("<h1>Current Time</h1>");
        response.getWriter().write("<p style='font-size: 24px; color: #2c3e50;'>" + formattedTime + "</p>");
        response.getWriter().write("</body>");
        response.getWriter().write("</html>");

        response.getWriter().close();
    }
}
