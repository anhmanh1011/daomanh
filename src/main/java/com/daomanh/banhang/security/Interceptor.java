package com.daomanh.banhang.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

public class Interceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            request.setAttribute("username", loginedUser.getUsername());

        }

        return true;
    }
}
