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
        int validTime;
        
        BCryptPasswordEncoder checker = new BCryptPasswordEncoder();
        
        String username = loginRequestBody.getUsername();
        String password = loginRequestBody.getPassword();
        
        Employee employee = employeeRepository.findByAccountName("mitrais\\" + username);
        
            if (employee != null && checker.matches(password, employee.getPassword())) {
                LoginResponse loginResponseBody = new LoginResponse();

                if (loginRequestBody.isRememberMe()) {
                    validTime = 3 * 24 * 60 * 60 * 1000; // 3 days
                } else {
                    validTime = 3 * 60 * 60 * 1000; // 3 hours
                }
                jwtToken = Jwts.builder().setSubject(username)
                            .claim("userId", employee.getEmployeeId())
                            .setExpiration(new Date(System.currentTimeMillis() + validTime))
                            .signWith(SignatureAlgorithm.HS256, signatureKey)
                            .compact();

                int role = 1;
                loginResponseBody.setFullName(employee.getFullName());
                loginResponseBody.setRole(role);
                loginResponseBody.setToken(jwtToken);
                
                return ResponseEntity.ok(loginResponseBody);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username or Password is incorrect");
            }
    }
}
