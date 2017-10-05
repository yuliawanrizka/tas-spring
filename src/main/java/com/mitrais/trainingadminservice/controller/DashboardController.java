/*
 * @(#) DashboardController.java, v 1.0 2017/10/05 11:22:43
 * 
 * Copyright (c) 2017, PT. Mitrais, Bali, Indonesia.
 * All rights reserved.
 * 
 * Revision History
 * 
 * 05-Oct-2017 Yuliawan Rizka Syafaat             [1.0]-Initial Coding
 * 
 */

package com.mitrais.trainingadminservice.controller;

import com.mitrais.trainingadminservice.model.CoursePeriod;
import com.mitrais.trainingadminservice.model.Schedule;
import com.mitrais.trainingadminservice.repository.ClassroomRepository;
import com.mitrais.trainingadminservice.repository.CoursePeriodRepository;
import com.mitrais.trainingadminservice.repository.CourseRepository;
import com.mitrais.trainingadminservice.repository.EmployeeRepository;
import com.mitrais.trainingadminservice.repository.LocationRepository;
import com.mitrais.trainingadminservice.repository.ScheduleRepository;
import com.mitrais.trainingadminservice.repository.UserRoleRepository;
import com.mitrais.trainingadminservice.response.ActiveTrainingResponse;
import com.mitrais.trainingadminservice.response.BccResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
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
@RequestMapping("api/secure/dashboard")
public class DashboardController {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private CoursePeriodRepository coursePeriodRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ClassroomRepository classroomRepository;
    
    @Autowired
    private LocationRepository locationRepository;
    
    private HashMap<Long,String> classroomList = new HashMap<>();
    private HashMap<Long,String> locationList = new HashMap<>();
    
    @GetMapping(value = "active")
    public ResponseEntity getActiveSchedule() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,14); 
        List<Schedule> scheduleList = scheduleRepository.findByStartDateBetween(new Date(System.currentTimeMillis()), new Date(cal.getTimeInMillis()));
        List<ActiveTrainingResponse> response = new ArrayList<>();
        scheduleList.sort((o1, o2) -> o1.getStartDate().compareTo(o2.getStartDate()));
        scheduleList.forEach(e -> {
            response.add(generateActiveTrainingResponse(e));
        });
        return ResponseEntity.ok(response);
    }
    @GetMapping(value = "bcc")
    public ResponseEntity getBCCSchedule() {
        Calendar cal = Calendar.getInstance();
        int offset = cal.get(Calendar.DAY_OF_WEEK) - 1;
        offset = -offset;
        cal.add(Calendar.DATE, offset);
        Date startSearch = new Date(cal.getTimeInMillis());
        cal.add(Calendar.DATE, 7);
        Date endSearch = new Date(cal.getTimeInMillis());
        List<Schedule> scheduleList = scheduleRepository.findByStartDateBetween(startSearch, endSearch);
        HashMap<Long, BccResponse> trainerData = new HashMap<>();
        scheduleList.forEach(e -> {
            CoursePeriod data = coursePeriodRepository.findOne(e.getCoursePeriodId());
            if(courseRepository.findOne(data.getCourseId()).isBccRelated()) {
                if (!(trainerData.containsKey(data.getMainTrainer()))) {
                    trainerData.put(data.getMainTrainer(), new BccResponse(getEmployeeFullName(data.getMainTrainer())));
                }
                BccResponse dataForResponse = trainerData.get(data.getMainTrainer());
                switch (data.getDayOfTraining()) {
                    case 1 :
                    dataForResponse.setSunday(generateSchedule(data, e));
                    break;
                    case 2 :
                    dataForResponse.setMonday(generateSchedule(data, e)); 
                    break;
                    case 3 :
                    dataForResponse.setTuesday(generateSchedule(data, e)); 
                    break;
                    case 4 :
                    dataForResponse.setWednesday(generateSchedule(data, e)); 
                    break;
                    case 5 :
                    dataForResponse.setThursday(generateSchedule(data, e)); 
                    break;
                    case 6 :
                    dataForResponse.setFriday(generateSchedule(data, e)); 
                    break;
                    case 7 :
                    dataForResponse.setSaturday(generateSchedule(data, e)); 
                    break;
                }
            }
        });
        List<BccResponse> response = new ArrayList<>(trainerData.values());
        return ResponseEntity.ok(response);
    }
    
    private String generateSchedule(CoursePeriod data , Schedule e) {
        String result;
        if (classroomList.isEmpty() || !(classroomList.containsKey(data.getClassroomId()))) {
            classroomList.put(data.getClassroomId(),
                    classroomRepository.findOne(data.getClassroomId()).getClassroom() 
                            + ", " +
                            locationRepository.findOne(classroomRepository.findOne(data.getClassroomId()).getLocationId()).getLocation());
        }
        result = classroomList.get(data.getClassroomId()) + " ("+e.getStartTime()+" - "+e.getEndTime()+ ")";
        return result;
    }
    
    private ActiveTrainingResponse generateActiveTrainingResponse(Schedule e) {
        ActiveTrainingResponse result = new ActiveTrainingResponse();
        CoursePeriod cpData = coursePeriodRepository.findOne(e.getCoursePeriodId());
        result.setCourseName(courseRepository.findOne(cpData.getCourseId()).getCourseName());
        result.setMainTrainer(getEmployeeFullName(cpData.getMainTrainer()));
        if(cpData.getBackupTrainer() == null) {
            result.setBackupTrainer("-");
        } else {
            result.setBackupTrainer(getEmployeeFullName(cpData.getBackupTrainer()));
        }
        result.setStartDate(e.getStartDate().toString());
        result.setEndDate(e.getEndDate().toString());
        if (locationList.isEmpty() || !(locationList.containsKey(cpData.getClassroomId()))) {
            locationList.put(cpData.getClassroomId(),
                            locationRepository.findOne(classroomRepository.findOne(cpData.getClassroomId()).getLocationId()).getLocation());
        }
        result.setLocation(locationList.get(cpData.getClassroomId()));
        return result;
    }
    private String getEmployeeFullName (Long id) {
        return employeeRepository.getOne(userRoleRepository.getOne(id).getEmployeeId()).getFullName();
    }
}
