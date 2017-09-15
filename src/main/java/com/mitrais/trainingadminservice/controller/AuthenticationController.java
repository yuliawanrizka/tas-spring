/*
 * @(#) AuthenticationController.java, v 1.0 2017/09/15 10:33:49
 * 
 * Copyright (c) 2017, PT. Mitrais, Bali, Indonesia.
 * All rights reserved.
 * 
 * Revision History
 * 
 * 15-Sep-2017 Yuliawan Rizka Syafaat             [1.0]-Initial Coding
 * 
 */

package com.mitrais.trainingadminservice.controller;

import com.mitrais.trainingadminservice.model.Employee;
import com.mitrais.trainingadminservice.repository.EmployeeRepository;
import com.mitrais.trainingadminservice.request.LoginRequest;
import com.mitrais.trainingadminservice.response.LoginResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Class Description
 * 
 */
@RestController
@RequestMapping("api/auth")
public class AuthenticationController {
    @Autowired
    private EmployeeRepository employeeRepository;
    
    //for implementaton, please change the key, also the key on AuthenticationFilter.java
    private final String signatureKey = "3f0f203f52b2900e069f7865d9f256d3";
    
    @PostMapping(value = "")
    public ResponseEntity functionName(@RequestBody final LoginRequest loginRequestBody) {
        
        String jwtToken = "";
        
        BCryptPasswordEncoder checker = new BCryptPasswordEncoder();
        
        String username = loginRequestBody.getUsername();
        String password = loginRequestBody.getPassword();
        
        Employee employee = employeeRepository.findByAccountName(username);
        
        if (checker.matches(password, employee.getPassword())) {
            LoginResponse loginResponseBody = new LoginResponse();
            
            if (loginRequestBody.isRememberMe()) {
                jwtToken = Jwts.builder().setSubject(username)
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis() + (2 * 24 * 60 * 60 * 1000)))
                        .signWith(SignatureAlgorithm.HS256, signatureKey)
                        .compact();
            } else {
                jwtToken = Jwts.builder().setSubject(username)
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis() + (2 * 60 * 60 * 1000)))
                        .signWith(SignatureAlgorithm.HS256, signatureKey)
                        .compact();
            }
           
            int[] role = new int[]{1, 2, 3, 4};
            loginResponseBody.setFullName(employee.getFullName());
            loginResponseBody.setRole(role);
            loginResponseBody.setToken(jwtToken);
            
            return ResponseEntity.ok(loginResponseBody);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username or Password is incorrect");
        }
    }
    
    @GetMapping(value = "test")
    public String  functionName() {
        String jwtToken = Jwts.builder().setSubject("me").setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + (3600 * 1000)))
                        .signWith(SignatureAlgorithm.HS256, signatureKey).compact();
        return jwtToken;
    }
}
