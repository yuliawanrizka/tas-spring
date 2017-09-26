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
import com.mitrais.trainingadminservice.request.PeriodRequest;
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
        try{
        List<PeriodResponse> response = new ArrayList<>();
        
        List<TrainingPeriod> trainingPeriod = trainingPeriodRepository.findAll();
            trainingPeriod.forEach( data -> {
                response.add(generateResultResponse(data));
            });
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("ERROR at \"period/\": " + e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }
    
    @PostMapping(value = "create")
    public ResponseEntity addPeriod(@RequestBody final PeriodRequest request, @RequestAttribute Claims claims) {
        try {
            TrainingPeriod data = new TrainingPeriod();

            data.setTrainingName(request.getTrainingName());
            data.setActive(true);
            data.setStartDate(request.getStartDate());
            data.setEndDate(request.getEndDate());
            data.setCreatorId(new Long(claims.get("userId").toString()));
            data.setCreatedAt(new Timestamp(Calendar.getInstance().getTime().getTime()));
            data.setOpenEnrollment(request.isOpenEnrollment());

            trainingPeriodRepository.save(data);
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        } catch (Exception e) {
            System.out.println("ERROR at \"period/create\": " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
    
    @GetMapping(value = "{id}")
    public ResponseEntity findPeriod(@PathVariable ("id") final Long id) {
        try {
            TrainingPeriod data = trainingPeriodRepository.findOne(id);

            PeriodResponse response = generateResultResponse(data);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("ERROR at \"period/{id}\": " + e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }
    @PostMapping(value = "{id}/edit")
    public ResponseEntity editPeriod(@RequestBody final PeriodRequest request, @PathVariable ("id") final Long id, @RequestAttribute Claims claims) {
        try {
            TrainingPeriod data = trainingPeriodRepository.findOne(id);
            
            data.setTrainingName(request.getTrainingName());
            data.setActive(request.isActive());
            data.setStartDate(request.getStartDate());
            data.setEndDate(request.getEndDate());
            data.setUpdaterId(new Long(claims.get("userId").toString()));
            data.setUpdatedAt(new Timestamp(Calendar.getInstance().getTime().getTime()));
            data.setOpenEnrollment(request.isOpenEnrollment());
            
            trainingPeriodRepository.save(data);
            return ResponseEntity.ok(true);
        } catch (Exception e){
            System.out.println("ERROR at \"period/{id}/edit\": " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
    
    @GetMapping(value = "{id}/delete")
    public ResponseEntity deletePeriod(@PathVariable ("id") final Long id) {
        try {
            trainingPeriodRepository.delete(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            System.out.println("ERROR at \"period/{id}/delete\": " + e);
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
