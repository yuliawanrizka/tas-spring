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
import com.mitrais.trainingadminservice.request.AchievementRequest;
import com.mitrais.trainingadminservice.response.AchievementResponse;
import io.jsonwebtoken.Claims;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
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
    
    @PostMapping(value = "{id}/edit")
    public ResponseEntity editAchievement(@RequestBody final List<AchievementRequest> request, @PathVariable ("id") final Long id) {
        UserRole userRole = userRoleRepository.findByEmployeeIdAndRoleId(id, new Long("4"));
        request.forEach(e -> {
            Achievement data = achievementRepository.findByUserRoleIdAndCourseId(userRole.getUserRoleId(), e.getBccId());
            data.setStatus(e.getStatus());
            if(e.getStatus() == 2) {
                data.setTerm(e.getTerm());
            }
            achievementRepository.save(data);
        });
        return ResponseEntity.ok(true);
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
                dataForResponse.setBeginingId(setIdforTerm(e));
                break;
                case 2 :
                dataForResponse.setLi1(setAchievementValue(e));
                dataForResponse.setLi1Id(setIdforTerm(e));
                break;
                case 3 :
                dataForResponse.setLi2(setAchievementValue(e));
                dataForResponse.setLi2Id(setIdforTerm(e));
                break;
                case 4 :
                dataForResponse.setInt1(setAchievementValue(e));
                dataForResponse.setInt1Id(setIdforTerm(e));
                break;
                case 5 :
                dataForResponse.setInt2(setAchievementValue(e));
                dataForResponse.setInt2Id(setIdforTerm(e));
                break;
                case 6 :
                dataForResponse.setBw1(setAchievementValue(e));
                dataForResponse.setBw1Id(setIdforTerm(e));
                break;
                case 7 :
                dataForResponse.setCe1(setAchievementValue(e));
                dataForResponse.setCe1Id(setIdforTerm(e));
                break;
                case 8 :
                dataForResponse.setBw2(setAchievementValue(e));
                dataForResponse.setBw2Id(setIdforTerm(e));
                break;
                case 9 :
                dataForResponse.setCe2(setAchievementValue(e));
                dataForResponse.setCe2Id(setIdforTerm(e));
                break;
                case 10 :
                dataForResponse.setPresentationSkill(setAchievementValue(e));
                dataForResponse.setPresentationSkillId(setIdforTerm(e));
                break;                
            } 
        });
        return dataForResponse;
    }
    private Long setIdforTerm (Achievement e) {
        if(e.getStatus() != null) {
            if(e.getStatus() == 1) {
                return new Long("0");
            } else {
                return e.getTerm();
            }
        } else {
            return null;
        }
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
