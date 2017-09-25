/*
 * @(#) PeriodController.java, v 1.0 2017/09/18 11:11:31
 * 
 * Copyright (c) 2017, PT. Mitrais, Bali, Indonesia.
 * All rights reserved.
 * 
 * Revision History
 * 
 * 18-Sep-2017 Yuliawan Rizka Syafaat             [1.0]-Initial Coding
 * 
 */

package com.mitrais.trainingadminservice.controller;

import com.mitrais.trainingadminservice.model.TrainingPeriod;
import com.mitrais.trainingadminservice.repository.EmployeeRepository;
import com.mitrais.trainingadminservice.repository.TrainingPeriodRepository;
import com.mitrais.trainingadminservice.repository.UserRoleRepository;
import com.mitrais.trainingadminservice.request.AddPeriodRequest;
import com.mitrais.trainingadminservice.response.PeriodResponse;
import io.jsonwebtoken.Claims;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("api/secure/period")
public class PeriodController {
    
    @Autowired
    private TrainingPeriodRepository trainingPeriodRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    
    private HashMap<Long,String> adminList = new HashMap<>();
    
    @GetMapping(value = "")
    public ResponseEntity getAllPeriod() {
        
        List<PeriodResponse> response = new ArrayList<>();
        
        List<TrainingPeriod> trainingPeriod = trainingPeriodRepository.findAll();
        
        if(!(trainingPeriod.isEmpty())) {
            trainingPeriod.forEach( data -> {
                response.add(generateResultResponse(data));
            });
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }
    
    @PostMapping(value = "create")
    public ResponseEntity addPeriod(@RequestBody final AddPeriodRequest addPeriod, @RequestAttribute Claims claims) {
        try {
            TrainingPeriod addData = new TrainingPeriod();

            addData.setTrainingName(addPeriod.getTrainingName());
            addData.setActive(true);
            addData.setStartDate(addPeriod.getStartDate());
            addData.setEndDate(addPeriod.getEndDate());
            addData.setCreatorId(new Long(claims.get("userId").toString()));
            addData.setCreatedAt(new Timestamp(Calendar.getInstance().getTime().getTime()));

            trainingPeriodRepository.save(addData);
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
        
        
    }
    
    private String getFullName (Long id) {
        return employeeRepository.getOne(userRoleRepository.getOne(id).getEmployeeId()).getFullName();
    }
    
    private PeriodResponse generateResultResponse(TrainingPeriod data){
        PeriodResponse result = new PeriodResponse();
        
        result.setTrainingPeriodId(data.getTrainingPeriodId());
        result.setTrainingName(data.getTrainingName());
        result.setActiveStatus(data.isActive());
        result.setStartDate(data.getStartDate());
        result.setEndDate(data.getEndDate());

        if(adminList.isEmpty() || !(adminList.containsKey(data.getCreatorId()))){
            adminList.put(data.getCreatorId(), getFullName(data.getCreatorId()));
        }
        result.setCreatedBy(adminList.get(data.getCreatorId()));

        if(data.getUpdaterId() != null ) {
            if(!(adminList.containsKey(data.getUpdaterId()))) {
                adminList.put(data.getUpdaterId(), getFullName(data.getUpdaterId()));
            }
        result.setEditedBy(adminList.get(data.getUpdaterId()));
        } else {
            result.setEditedBy("-");
        }
        return result;
    }
}
