/*
 * @(#) AchievementController.java, v 1.0 2017/09/26 10:10:10
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

import com.mitrais.trainingadminservice.model.Achievement;
import com.mitrais.trainingadminservice.model.Employee;
import com.mitrais.trainingadminservice.model.Grade;
import com.mitrais.trainingadminservice.model.UserRole;
import com.mitrais.trainingadminservice.repository.AchievementRepository;
import com.mitrais.trainingadminservice.repository.EmployeeRepository;
import com.mitrais.trainingadminservice.repository.GradeRepository;
import com.mitrais.trainingadminservice.repository.LocationRepository;
import com.mitrais.trainingadminservice.repository.TrainingPeriodRepository;
import com.mitrais.trainingadminservice.repository.UserRoleRepository;
import com.mitrais.trainingadminservice.response.AchievementResponse;
import io.jsonwebtoken.Claims;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Class Description
 * 
 */
@RestController
@RequestMapping("api/secure/achievement")
public class AchievementController {
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private AchievementRepository achievementRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private TrainingPeriodRepository trainingPeriodRepository;
    
    @GetMapping(value = "{activeRole}")
    public ResponseEntity getAchievement(@PathVariable ("activeRole") final Long activeRole, @RequestAttribute final Claims claims) {
        UserRole validate = userRoleRepository.findByEmployeeIdAndRoleId(new Long(claims.get("userId").toString()), activeRole);
        List<AchievementResponse> response = new ArrayList<>();
        if(validate.isActive()) {
            if ((validate.getRoleId() == 2 || validate.getRoleId() == 4)) {
                UserRole role = userRoleRepository.findByEmployeeIdAndRoleId(new Long(claims.get("userId").toString()), new Long("4"));
                response.add(generateAchievementResponse(role.getUserRoleId()));
            } else {
                List<UserRole> role = userRoleRepository.findByRoleId(new Long("4"));
                role.forEach(e -> {
                    response.add(generateAchievementResponse(e.getUserRoleId()));
                });
            }
        }
        return ResponseEntity.ok(response);
    }
    
    private AchievementResponse generateAchievementResponse (Long role) {
        AchievementResponse dataForResponse = new AchievementResponse();
        
        List<Achievement> data = achievementRepository.findByUserRoleId(role);
        Employee employeeData = getEmployeeData(role);
        Grade gradeData = gradeRepository.findOne(employeeData.getGradeId());
        
        dataForResponse.setEmployeeId(employeeData.getEmployeeId());
        dataForResponse.setEmployeeName(employeeData.getFullName());
        dataForResponse.setJobFamily(gradeData.getJobFamily());
        dataForResponse.setGrade(gradeData.getGrade());
        dataForResponse.setOffice(locationRepository.findOne(employeeData.getLocationId()).getLocation());
        
        data.forEach(e -> {
            switch (e.getCourseId().intValue()) {
                case 1 :
                dataForResponse.setBegining(setAchievementValue(e));
                break;
                case 2 :
                dataForResponse.setLi1(setAchievementValue(e));
                break;
                case 3 :
                dataForResponse.setLi2(setAchievementValue(e));
                break;
                case 4 :
                dataForResponse.setInt1(setAchievementValue(e));
                break;
                case 5 :
                dataForResponse.setInt2(setAchievementValue(e));
                break;
                case 6 :
                dataForResponse.setBw1(setAchievementValue(e));
                break;
                case 7 :
                dataForResponse.setCe1(setAchievementValue(e));
                break;
                case 8 :
                dataForResponse.setBw2(setAchievementValue(e));
                break;
                case 9 :
                dataForResponse.setCe2(setAchievementValue(e));
                break;
                case 10 :
                dataForResponse.setPresentationSkill(setAchievementValue(e));
                break;                
            } 
        });
        return dataForResponse;
    }
    private String setAchievementValue (Achievement e) {
        String result = "";
        if(e.getStatus() != null) {
            if (e.getStatus() == 1) {
                result = "Not Required";
            } else if (e.getStatus() == 2) {
                result = "Term : ";
                result = result + trainingPeriodRepository.findOne(e.getTerm()).getTrainingName();
            }
        } else {
            result = "-";
        }
        return result;
    }
            
    private Employee getEmployeeData (Long id) {
        return employeeRepository.getOne(userRoleRepository.getOne(id).getEmployeeId());
    }
}
