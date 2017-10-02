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

import com.mitrais.trainingadminservice.model.EligibleParticipants;
import com.mitrais.trainingadminservice.model.EnrolledParticipants;
import com.mitrais.trainingadminservice.model.UserRole;
import com.mitrais.trainingadminservice.repository.EligibleParticipantsRepository;
import com.mitrais.trainingadminservice.repository.EnrolledParticipantsRepository;
import com.mitrais.trainingadminservice.repository.UserRoleRepository;
import io.jsonwebtoken.Claims;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    
//    @GetMapping(value = "")
//    public ResponseEntity getMyEnrolled(@RequestAttribute Claims claims) {
//        try {
//            List<UserRole> roleList = userRoleRepository.findByEmployeeId(new Long(claims.get("userId").toString()));
//            roleList.forEach(e -> {
//                if(e.getRoleId() == 4) {
//                    List<EligibleParticipants> EligibleList =  eligiblePerticipantsRepository.findByUserRoleId(e.getUserRoleId());
//                    EligibleList.forEach(x -> {
//                        List<EnrolledParticipants> enrolledList = enrolledParticipantsRepository.findByEligibleParticipantsId(x.getEligibleParticipantsId());
//                        
//                    });
//                }
//            });
//        } catch (Exception e) {
//        }
//        return ResponseEntity.ok(null);
//    }
}
