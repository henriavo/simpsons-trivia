package com.example.validatingforminput;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model) throws Exception {
        // DID NOT WORK 👎🏽
        System.out.println("insdie postHandle() ******");
        response.setHeader("myheader", "chicago");
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        // DID NOT WORK 👎🏽
        System.out.println("insdie preHandle() ******");
        response.setHeader("myheader", "chicago");

        return true;
    }

}
