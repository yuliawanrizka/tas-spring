/*
 * @(#) EnrollmentController.java, v 1.0 2017/09/26 10:09:55
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

import com.mitrais.trainingadminservice.model.CoursePeriod;
import com.mitrais.trainingadminservice.model.EligibleParticipants;
import com.mitrais.trainingadminservice.model.EnrolledParticipants;
import com.mitrais.trainingadminservice.model.TrainingPeriod;
import com.mitrais.trainingadminservice.model.UserRole;
import com.mitrais.trainingadminservice.repository.CoursePeriodRepository;
import com.mitrais.trainingadminservice.repository.CourseRepository;
import com.mitrais.trainingadminservice.repository.EligibleParticipantsRepository;
import com.mitrais.trainingadminservice.repository.EmployeeRepository;
import com.mitrais.trainingadminservice.repository.EnrolledParticipantsRepository;
import com.mitrais.trainingadminservice.repository.TrainingPeriodRepository;
import com.mitrais.trainingadminservice.repository.UserRoleRepository;
import com.mitrais.trainingadminservice.response.EnrolledResponse;
import io.jsonwebtoken.Claims;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Class Description
 * 
 */
@RestController
@RequestMapping("api/secure/enrollment")
public class EnrollmentController {
    
    @Autowired
    private EnrolledParticipantsRepository enrolledParticipantsRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private EligibleParticipantsRepository eligiblePerticipantsRepository;
    @Autowired
    private TrainingPeriodRepository trainingPeriodRepository;
    @Autowired
    private CoursePeriodRepository coursePeriodRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @GetMapping(value = "")
    public ResponseEntity getMyEnrolled(@RequestAttribute Claims claims) {
        try {
            List<EnrolledResponse> response = new ArrayList<>();
            List<UserRole> roleList = userRoleRepository.findByEmployeeId(new Long(claims.get("userId").toString()));
            roleList.forEach(e -> {
                if(e.getRoleId() == 4) {
                    List<EligibleParticipants> EligibleList =  eligiblePerticipantsRepository.findByUserRoleId(e.getUserRoleId());
                    EligibleList.forEach(x -> {
                        TrainingPeriod periodData = trainingPeriodRepository.findOne(x.getTrainingPeriodId());
                        List<EnrolledParticipants> enrolledList = enrolledParticipantsRepository.findByEligibleParticipantsId(x.getEligibleParticipantsId());
                        enrolledList.forEach(y -> {
                            CoursePeriod cpData = coursePeriodRepository.findOne(y.getCoursePeriodId());
                            EnrolledResponse data = new EnrolledResponse();
                            data.setTrainingName(periodData.getTrainingName());
                            data.setCourseName(courseRepository.findOne(cpData.getCourseId()).getCourseName());
                            if(cpData.getBackupTrainer() == null) {
                                data.setTrainerName(employeeRepository.findOne(userRoleRepository.findOne(cpData.getMainTrainer()).getEmployeeId()).getFullName());
                            } else {
                                data.setTrainerName(employeeRepository.findOne(userRoleRepository.findOne(cpData.getMainTrainer()).getEmployeeId()).getFullName()
                                        + "/" +employeeRepository.findOne(userRoleRepository.findOne(cpData.getBackupTrainer()).getEmployeeId()).getFullName()
                                );
                            }
                            data.setStartAt(periodData.getStartDate().toString());
                            data.setEndAt(periodData.getEndDate().toString());
                            data.setStatus("?");
                            response.add(data);
                        });
                    });
                }
            });
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/enrollment/\": " + e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }
}
