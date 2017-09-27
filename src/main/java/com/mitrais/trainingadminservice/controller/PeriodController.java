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

import com.mitrais.trainingadminservice.model.EligibleParticipants;
import com.mitrais.trainingadminservice.model.TrainingPeriod;
import com.mitrais.trainingadminservice.model.UserRole;
import com.mitrais.trainingadminservice.repository.EligibleParticipantsRepository;
import com.mitrais.trainingadminservice.repository.EmployeeRepository;
import com.mitrais.trainingadminservice.repository.TrainingPeriodRepository;
import com.mitrais.trainingadminservice.repository.UserRoleRepository;
import com.mitrais.trainingadminservice.request.EligibleRequest;
import com.mitrais.trainingadminservice.request.PeriodRequest;
import com.mitrais.trainingadminservice.response.EligibleResponse;
import com.mitrais.trainingadminservice.response.PeriodResponse;
import io.jsonwebtoken.Claims;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    
    @Autowired
    private EligibleParticipantsRepository eligibleParticipantsRepository;
    
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
            System.out.println("ERROR at \"api/secure/period/\": " + e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }
    
    @PostMapping(value = "create")
    public ResponseEntity addPeriod(@RequestBody final PeriodRequest request, @RequestAttribute Claims claims) {
        try {
            TrainingPeriod data = new TrainingPeriod();
            
            TrainingPeriod dataInserted = dataInsertion(data, request);
            dataInserted.setCreatorId(new Long(claims.get("userId").toString()));
            dataInserted.setCreatedAt(new Timestamp(Calendar.getInstance().getTime().getTime()));

            trainingPeriodRepository.save(dataInserted);
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/period/create\": " + e);
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
            System.out.println("ERROR at \"api/secure/period/" + id + "\": " + e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }
    @PostMapping(value = "{id}/edit")
    public ResponseEntity editPeriod(@RequestBody final PeriodRequest request, @PathVariable ("id") final Long id, @RequestAttribute Claims claims) {
        try {
            TrainingPeriod data = trainingPeriodRepository.findOne(id);
            
            TrainingPeriod dataInserted = dataInsertion(data, request);
            dataInserted.setUpdaterId(new Long(claims.get("userId").toString()));
            dataInserted.setUpdatedAt(new Timestamp(Calendar.getInstance().getTime().getTime()));
            
            trainingPeriodRepository.save(dataInserted);
            return ResponseEntity.ok(true);
        } catch (Exception e){
            System.out.println("ERROR at \"api/secure/period/" + id + "/edit\": " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
    
    @DeleteMapping(value = "{id}/delete")
    public ResponseEntity deletePeriod(@PathVariable ("id") final Long id) {
        try {
            trainingPeriodRepository.delete(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/period/" + id + "/delete\": " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
    
    @GetMapping(value = "{id}/eligible")
    public ResponseEntity getEligibleParticipants(@PathVariable("id") final Long id) {
        try{
            List<EligibleResponse> response = new ArrayList<>();
            List<EligibleParticipants> data = eligibleParticipantsRepository.findByTrainingPeriodId(id);
            data.forEach(x -> {
                response.add(new EligibleResponse(getEmployeeId(id), getEmployeeFullName(x.getUserRoleId())));
            });
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/period/" + id + "/eligible\": " + e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        
    }
    
    @PostMapping(value = "{id}/eligible/add")
    public ResponseEntity addEligible(@RequestBody final EligibleRequest request, @PathVariable("id") final Long id ) {
        try {
            addEligibleUser(request, id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/period/" + id + "/eligible/add\": " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
    @DeleteMapping(value = "{id}/eligible/delete")
    public ResponseEntity functionName(@RequestBody final EligibleRequest request, @PathVariable("id") final Long id) {
        try {
            deleteEligibleUser(request, id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/period/" + id + "/eligible/add\": " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
    
    private Long getEmployeeId (Long id) {
        return userRoleRepository.getOne(id).getEmployeeId();
    }
    
    private String getEmployeeFullName (Long id) {
        return employeeRepository.getOne(userRoleRepository.getOne(id).getEmployeeId()).getFullName();
    }
    
    private PeriodResponse generateResultResponse(TrainingPeriod data){
        PeriodResponse result = new PeriodResponse();
        
        result.setTrainingPeriodId(data.getTrainingPeriodId());
        result.setTrainingName(data.getTrainingName());
        result.setActiveStatus(data.isActive());
        result.setCoursesCount(0);
        result.setStartDate(data.getStartDate());
        result.setEndDate(data.getEndDate());

        if(adminList.isEmpty() || !(adminList.containsKey(data.getCreatorId()))){
            adminList.put(data.getCreatorId(), getEmployeeFullName(data.getCreatorId()));
        }
        result.setCreatedBy(adminList.get(data.getCreatorId()));

        if(data.getUpdaterId() != null ) {
            if(!(adminList.containsKey(data.getUpdaterId()))) {
                adminList.put(data.getUpdaterId(), getEmployeeFullName(data.getUpdaterId()));
            }
        result.setEditedBy(adminList.get(data.getUpdaterId()));
        } else {
            result.setEditedBy("-");
        }
        result.setOpenEnrollment(data.isOpenEnrollment());
        return result;
    }
    private TrainingPeriod dataInsertion(TrainingPeriod data, PeriodRequest source) {
        data.setTrainingName(source.getTrainingName());
        data.setActive(source.isActive());
        data.setStartDate(source.getStartDate());
        data.setEndDate(source.getEndDate());
        data.setOpenEnrollment(source.isOpenEnrollment());
        return data;
    }
    
    private void addEligibleUser(EligibleRequest data, Long id) {
        List<UserRole> role = userRoleRepository.findByEmployeeId(data.getEmployeeId());
        EligibleParticipants request = new EligibleParticipants();
        role.forEach(x -> {
            if(x.getRoleId() == 4) {
                List <EligibleParticipants> eligible = eligibleParticipantsRepository.findByUserRoleId(x.getUserRoleId());
                eligible.forEach(y -> {
                    if(Objects.equals(y.getTrainingPeriodId(), id)) {
                        throw new IllegalArgumentException("The Employee is already enrolled.");
                    }
                });
                request.setUserRoleId(x.getUserRoleId());
                request.setTrainingPeriodId(id);
                eligibleParticipantsRepository.save(request);
            }
        });
    }
    
    private void deleteEligibleUser (EligibleRequest data, Long id) {
        List<UserRole> role = userRoleRepository.findByEmployeeId(data.getEmployeeId());
        role.forEach(x -> {
            if(x.getRoleId() == 4) {
                List <EligibleParticipants> eligible = eligibleParticipantsRepository.findByUserRoleId(x.getUserRoleId());
                eligible.forEach(y -> {
                    if(Objects.equals(y.getTrainingPeriodId(), id)) {
                        eligibleParticipantsRepository.delete(y.getEligibleParticipantsId());
                    }
                });
                
            }
        });
    }
}
