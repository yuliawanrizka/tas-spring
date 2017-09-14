/*
 * @(#) AuthenticationController.java, v 1.0 2017/09/13 16:00:27
 * 
 * Copyright (c) 2017, PT. Mitrais, Bali, Indonesia.
 * All rights reserved.
 * 
 * Revision History
 * 
 * 13-Sep-2017 Yuliawan Rizka Syafaat             [1.0]-Initial Coding
 * 
 */

package com.mitrais.trainingadminservice.controller;

import com.mitrais.trainingadminservice.model.EmployeeData;
import com.mitrais.trainingadminservice.model.Token;
import com.mitrais.trainingadminservice.repository.EmployeeDataRepository;
import com.mitrais.trainingadminservice.request.AuthenticationRequest;
import com.mitrais.trainingadminservice.response.AuthenticationResponse;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Class Description
 * 
 */
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    
    @Autowired
    private EmployeeDataRepository employeeData;
    
    @PostMapping(value = "")
    public ResponseEntity login(@RequestBody final AuthenticationRequest request) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        EmployeeData employee = employeeData.findByAccountName(request.getUsername());
        String passwordCompare = Encode_SHA512(employee.getSalt() + request.getPassword());
        if (employee.getPassword() == null ? passwordCompare == null : employee.getPassword().equals(passwordCompare) ) {
            AuthenticationResponse response = new AuthenticationResponse();
            Token tokenEmployee = new Token();
            Long time = System.currentTimeMillis();
            response.setId(employee.getEmployeeId());
            response.setUsername(employee.getAccountName());
            String token = generateToken(512);
            tokenEmployee.setToken(token);
            tokenEmployee.setRememberMe(request.isRememberMe());
            if (request.isRememberMe()) {
                time = time + (3*24*60*60*1000); // 3 days
            } else {
                time = time + (15*60*1000); // 15 minutes
            }
            tokenEmployee.setExpiry(new Timestamp(time));
            employee.setToken(tokenEmployee);
            response.setToken(token);
            response.setRole(new int[]{ 1 , 2 , 3, 4 });
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username or Password is incorrect");
        }
    }
    
    private String generateToken(int randomStrLength) {
        char[] possibleCharacters = (new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~!@#$%^&*-_=")).toCharArray();
        String randomStr = RandomStringUtils.random( randomStrLength, 0, possibleCharacters.length-1, false, false, possibleCharacters, new SecureRandom() );
        return randomStr;
    }
    private String Encode_SHA512(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte [] inputBytes = input.getBytes("UTF-16LE");
        byte[] digest = md.digest(inputBytes);
        return Base64.getEncoder().encodeToString(digest);
    }
}
