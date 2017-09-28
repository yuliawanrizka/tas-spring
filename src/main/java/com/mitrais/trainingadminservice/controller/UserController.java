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

import com.mitrais.trainingadminservice.model.EligibleParticipants;
import com.mitrais.trainingadminservice.model.Employee;
import com.mitrais.trainingadminservice.model.UserRole;
import com.mitrais.trainingadminservice.repository.EligibleParticipantsRepository;
import com.mitrais.trainingadminservice.repository.EmployeeRepository;
import com.mitrais.trainingadminservice.repository.GradeRepository;
import com.mitrais.trainingadminservice.repository.UserRoleRepository;
import com.mitrais.trainingadminservice.response.UserResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @Autowired
    private UserRoleRepository userRoleRepository;
    
    @Autowired
    private EligibleParticipantsRepository eligibleParticipantsRepository;
    
    private HashMap<Long,String> jobFamily = new HashMap<>();
    private HashMap<Long,String> grade = new HashMap<>();
    
    @GetMapping(value = "")
    public ResponseEntity getAllUsers() {
        List<Employee> employeeList = employeeRepository.findAll();
        
        List<UserResponse> response = new ArrayList<>();
        
        employeeList.forEach( data -> {
            response.add(generateUserResponse(data));
        });
        
        return ResponseEntity.ok(response);
    }
    @GetMapping(value = "eligible/{id}")
    public ResponseEntity getEligibleUsers(@PathVariable ("id") Long id) {
        List<Employee> employeeList = employeeRepository.findAll();
        List<UserResponse> response = new ArrayList<>();
        List<EligibleParticipants> eligibleData = eligibleParticipantsRepository.findByTrainingPeriodId(id);
        List<Long> eligibleList = new ArrayList<>();
        eligibleData.forEach(x -> {
            eligibleList.add(userRoleRepository.findOne(x.getUserRoleId()).getEmployeeId());
        });
        employeeList.forEach(x -> {
            if (x.isActive()) {
                if(!(eligibleList.contains(x.getEmployeeId()))) {
                    response.add(generateUserResponse(x));
                }
            }
        });
        
        return ResponseEntity.ok(response);
    }
    @GetMapping(value = "trainer")
    public ResponseEntity getTrainer() {
        List<Employee> employeeList = employeeRepository.findAll();
        List<UserResponse> response = new ArrayList<>();
        employeeList.forEach(e -> {
            List<UserRole> roleList = userRoleRepository.findByEmployeeId(e.getEmployeeId());
            roleList.forEach(x -> {
                if(x.getRoleId() == 2) {
                    response.add(generateUserResponse(e));
                }
            });
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
        
        if(data.getStream()== null) {
            result.setJobFamilyStream(jobFamily.get(data.getGradeId()));
        } else {
            result.setJobFamilyStream(jobFamily.get(data.getGradeId()) + "." + data.getStream());
        }
        
        if(grade.isEmpty() || !(grade.containsKey(data.getGradeId()))) {
            grade.put(data.getGradeId(), gradeRepository.findOne(data.getGradeId()).getGrade());
        }
        result.setGrade(grade.get(data.getGradeId()));
        result.setActive(data.isActive());
        result.setRole(getEmployeeRole(data.getEmployeeId()));
        
        return result;
    }
    
    private List<Long> getEmployeeRole(Long id) {
        List<UserRole> userRole = userRoleRepository.findByEmployeeId(id);
        List<Long> x = new ArrayList<>();
        userRole.forEach(role -> {
            x.add(role.getRoleId());
        });
        return x;
    }
}
