/*
 * @(#) UserController.java, v 1.0 2017/09/26 10:09:40
 * 
 * Copyright (c) 2017, PT. Mitrais, Bali, Indonesia.
 * All rights reserved.
 * 
 * Revision History
 * 
 * 26-Sep-2017 Yuliawan Rizka Syafaat             [1.0]-Initial Coding
 * 
 */

package com.mitrais.trainingadminservice.controller;

import com.mitrais.trainingadminservice.model.Employee;
import com.mitrais.trainingadminservice.repository.EmployeeRepository;
import com.mitrais.trainingadminservice.repository.GradeRepository;
import com.mitrais.trainingadminservice.response.UserResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Class Description
 * 
 */
@RestController
@RequestMapping("api/secure/user")
public class UserController {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private GradeRepository gradeRepository;
    
    private HashMap<Long,String> jobFamily = new HashMap<>();
    private HashMap<Long,String> grade = new HashMap<>();
    
    @GetMapping(value = "eligible")
    public ResponseEntity getAllUsers() {
        List<Employee> employeeList = employeeRepository.findAll();
        
        List<UserResponse> response = new ArrayList<>();
        
        employeeList.forEach( data -> {
            response.add(generateUserResponse(data));
        });
        
        return ResponseEntity.ok(response);
    }
    
    private UserResponse generateUserResponse(Employee data) {
        UserResponse result = new UserResponse();
        
        result.setEmployeeId(data.getEmployeeId());
        result.setFullName(data.getFullName());
        result.setEmail(data.getEmail());
        result.setAccountName(data.getAccountName());
        
        if(jobFamily.isEmpty() || !(jobFamily.containsKey(data.getGradeId())) ) {
            jobFamily.put(data.getGradeId(), gradeRepository.findOne(data.getGradeId()).getJobFamily());
        }
        
        result.setJobFamilyStream(jobFamily.get(data.getGradeId()) + "." + data.getStream());
        
        if(grade.isEmpty() || !(grade.containsKey(data.getGradeId()))) {
            grade.put(data.getGradeId(), gradeRepository.findOne(data.getGradeId()).getGrade());
        }
        result.setGrade(grade.get(data.getGradeId()));
        
        return result;
    }
}
