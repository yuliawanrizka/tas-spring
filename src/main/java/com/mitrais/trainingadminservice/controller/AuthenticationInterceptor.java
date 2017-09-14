///*
// * @(#) AuthenticationInterceptor.java, v 1.0 2017/09/14 08:48:34
// * 
// * Copyright (c) 2017, PT. Mitrais, Bali, Indonesia.
// * All rights reserved.
// * 
// * Revision History
// * 
// * 14-Sep-2017 Yuliawan Rizka Syafaat             [1.0]-Initial Coding
// * 
// */
//
//package com.mitrais.trainingadminservice.controller;
//
//import com.mitrais.trainingadminservice.repository.TokenRepository;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//
///**
// * Class Description
// * 
// */
//@Component
//public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
//    
//    @Autowired
//    private TokenRepository token;
//    
//    @Override
//    public boolean preHandle(HttpServletRequest request,
//            HttpServletResponse response, Object handler) throws Exception {
//        //if the request is for login attempt, allow the request
//        if (true) {
//            return super.preHandle(request, response, handler);
//        } else {
//
//            //if token right, allow access
//            if (true) {
//                
//            
//            return super.preHandle(request, response, handler);
//
//            } else {
//                //else return 403
//                return false;
//            }
//        }
//    }
//}
