package com.example;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.TimeZone;

@WebFilter("/time")
public class TimezoneValidateFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String timezoneParam = request.getParameter("timezone");

        if (timezoneParam == null || timezoneParam.isEmpty()) {
            chain.doFilter(request, response);
            return;
        }

        String formattedTz = timezoneParam.replace(" ", "+");

        if (isValidTimezone(formattedTz)) {
            chain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("text/html; charset=utf-8");
            response.getWriter().write("<html><body><h1>Invalid timezone</h1></body></html>");
            response.getWriter().close();
        }
    }

    private boolean isValidTimezone(String tz) {
        if (tz.equalsIgnoreCase("UTC")) return true;

        String checkId = tz.startsWith("UTC") ? tz.replace("UTC", "GMT") : tz;
        TimeZone timeZone = TimeZone.getTimeZone(checkId);

        return !timeZone.getID().equals("GMT") || tz.equalsIgnoreCase("GMT") || tz.equalsIgnoreCase("UTC");
    }

}
