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
import com.mitrais.trainingadminservice.repository.TrainingPeriodRepository;
import com.mitrais.trainingadminservice.response.PeriodResponse;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    
    @GetMapping(value = "")
    public ResponseEntity getAllPeriod() {
        List<PeriodResponse> response = new ArrayList<PeriodResponse>();
        try {
            List<TrainingPeriod> data = trainingPeriodRepository.findAll();
            data.forEach( d -> {
                PeriodResponse result = new PeriodResponse();
                System.out.println(d.getTrainingName());
                result.setTrainingName(d.getTrainingName());
                result.setActiveStatus(d.isActive());
                result.setStartDate(d.getStartDate());
                response.add(result);
            });
            return ResponseEntity.ok(response);
            
        } catch (EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        
    }
}
