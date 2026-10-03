package com.quiz.servlet;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest =
                (HttpServletRequest) request;

        HttpServletResponse httpResponse =
                (HttpServletResponse) response;

        HttpSession session =
                httpRequest.getSession(false);

        if (session == null ||
                session.getAttribute("userId") == null) {

            httpResponse.sendRedirect(
                    httpRequest.getContextPath()
                    + "/login.html"
            );

            return;
        }

        String role =
                (String) session.getAttribute("role");

        if (!"ADMIN".equals(role)) {

            httpResponse.sendRedirect(
                    httpRequest.getContextPath()
                    + "/quiz-list"
            );

            return;
        }

        chain.doFilter(
                request,
                response
        );
    }
}