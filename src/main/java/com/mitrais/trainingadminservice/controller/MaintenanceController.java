/*
 * @(#) MaintenanceController.java, v 1.0 2017/09/26 10:10:27
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
import com.mitrais.trainingadminservice.model.TrainingPeriod;
import com.mitrais.trainingadminservice.model.UserRole;
import com.mitrais.trainingadminservice.repository.ClassroomRepository;
import com.mitrais.trainingadminservice.repository.CoursePeriodRepository;
import com.mitrais.trainingadminservice.repository.CourseRepository;
import com.mitrais.trainingadminservice.repository.EmployeeRepository;
import com.mitrais.trainingadminservice.repository.EnrolledParticipantsRepository;
import com.mitrais.trainingadminservice.repository.LocationRepository;
import com.mitrais.trainingadminservice.repository.TrainingPeriodRepository;
import com.mitrais.trainingadminservice.repository.UserRoleRepository;
import com.mitrais.trainingadminservice.request.ActiveRoleRequest;
import com.mitrais.trainingadminservice.response.MaintenanceResponse;
import io.jsonwebtoken.Claims;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("api/secure/maintenance")
public class MaintenanceController {
    
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private CoursePeriodRepository coursePeriodRepository;
    @Autowired
    private TrainingPeriodRepository trainingPeriodRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private EnrolledParticipantsRepository enrolledParticipantsRepository;
    
    private HashMap<Long,String> classroomList = new HashMap<>();
    
    @PostMapping(value = "")
    public ResponseEntity getTrainingMaintenance(@RequestBody final ActiveRoleRequest request, @RequestAttribute final Claims claims) {
        try {
            UserRole validate = userRoleRepository.findByEmployeeIdAndRoleId(new Long(claims.get("userId").toString()), request.getActiveRole());
            List<MaintenanceResponse> response = new ArrayList<>();
            if(validate.isActive() && (validate.getRoleId() == 1 || validate.getRoleId() == 2) ) {
                List<CoursePeriod> data;
                if (validate.getRoleId()== 2) {
                    data = coursePeriodRepository.findByMainTrainerOrBackupTrainer(validate.getUserRoleId(), validate.getUserRoleId());
                    
                } else {
                    data = coursePeriodRepository.findAll();
                }
                data.forEach(e -> {
                    response.add(generateMaintenanceResponse(e));
                });
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/maintenance/\": " + e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }
    private MaintenanceResponse generateMaintenanceResponse(CoursePeriod e){
        MaintenanceResponse dataForResponse = new MaintenanceResponse();
        TrainingPeriod trainingPeriodData = trainingPeriodRepository.findOne(e.getTrainingPeriodId());
        dataForResponse.setScheduleId(e.getCoursePeriodId());
        dataForResponse.setPeriodName(trainingPeriodData.getTrainingName());
        dataForResponse.setCourseName(courseRepository.findOne(e.getCourseId()).getCourseName());
        if(e.getBackupTrainer() == null) {
            dataForResponse.setTrainer(employeeRepository.findOne(userRoleRepository.findOne(e.getMainTrainer()).getEmployeeId()).getFullName());
        } else {
            dataForResponse.setTrainer(employeeRepository.findOne(userRoleRepository.findOne(e.getMainTrainer()).getEmployeeId()).getFullName()
            +"/"+ employeeRepository.findOne(userRoleRepository.findOne(e.getBackupTrainer()).getEmployeeId()).getFullName());
        }
        if (classroomList.isEmpty() || !(classroomList.containsKey(e.getClassroomId()))) {
            classroomList.put(e.getClassroomId(),
                    classroomRepository.findOne(e.getClassroomId()).getClassroom() 
                            + ", " +
                            locationRepository.findOne(classroomRepository.findOne(e.getClassroomId()).getLocationId()).getLocation());
        }
        dataForResponse.setClassroom(classroomList.get(e.getClassroomId()));
        if(e.isPeriodical()) {
            dataForResponse.setScheduleType("Periodical");
        } else {
            dataForResponse.setScheduleType("Fixed");
        }
        dataForResponse.setStartDate(trainingPeriodData.getStartDate().toString());
        dataForResponse.setEndDate(trainingPeriodData.getEndDate().toString());
        dataForResponse.setNumberOfParticipants(enrolledParticipantsRepository.findByCoursePeriodId(e.getCoursePeriodId()).size());
        return dataForResponse;
    }
    
}

