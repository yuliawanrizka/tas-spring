/*
 * @(#) UserController.java, v 1.0 2017/09/26 10:09:40
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

import com.mitrais.trainingadminservice.configuration.AppConstant;
import com.mitrais.trainingadminservice.model.EligibleParticipants;
import com.mitrais.trainingadminservice.model.Employee;
import com.mitrais.trainingadminservice.model.Grade;
import com.mitrais.trainingadminservice.model.Location;
import com.mitrais.trainingadminservice.model.UserRole;
import com.mitrais.trainingadminservice.repository.EligibleParticipantsRepository;
import com.mitrais.trainingadminservice.repository.EmployeeRepository;
import com.mitrais.trainingadminservice.repository.GradeRepository;
import com.mitrais.trainingadminservice.repository.LocationRepository;
import com.mitrais.trainingadminservice.repository.UserRoleRepository;
import com.mitrais.trainingadminservice.request.UserEditRequest;
import com.mitrais.trainingadminservice.request.UserEditRequest.RoleList;
import com.mitrais.trainingadminservice.request.UserRequest;
import com.mitrais.trainingadminservice.response.GradeResponse;
import com.mitrais.trainingadminservice.response.UserResponse;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Class Description
 * 
 */
@RestController
@RequestMapping("api/secure/user")
public class UserController {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private GradeRepository gradeRepository;
    
    @Autowired
    private UserRoleRepository userRoleRepository;
    
    @Autowired
    private EligibleParticipantsRepository eligibleParticipantsRepository;
    
    @Autowired
    private LocationRepository locationRepository;
    
    private HashMap<Long,String> jobFamily = new HashMap<>();
    private HashMap<Long,String> grade = new HashMap<>();
    private HashMap<Long,String> location = new HashMap<>();
    
    @GetMapping(value = "")
    public ResponseEntity getAllUsers() {
        try {
            List<Employee> employeeList = employeeRepository.findAll();

            List<UserResponse> response = new ArrayList<>();

            employeeList.forEach( data -> {
                response.add(generateUserResponse(data));
            });

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/user\": " + e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }
    
    @PostMapping(value = "add")
    public ResponseEntity addUser(@RequestBody final UserRequest request) {
        try {
            Employee addData = new Employee();
            BCryptPasswordEncoder creator = new BCryptPasswordEncoder(AppConstant.BCRYPT_STRENGTH, new SecureRandom());

            addData.setAccountName(request.getAccountName());
            addData.setPassword(creator.encode(request.getPassword()));
            addData.setFullName(request.getFullName());
            addData.setGradeId(request.getGradeId());
            addData.setStream(addData.getStream());
            addData.setEmail(request.getEmail());
            addData.setActive(true);
            addData.setLocationId(request.getLocationId());

            employeeRepository.save(addData);
            
            Employee dataSaved = employeeRepository.findByAccountName(request.getAccountName());
            UserRole settingRole = new UserRole();
            
            settingRole.setEmployeeId(dataSaved.getEmployeeId());
            settingRole.setRoleId(new Long("4"));
            
            userRoleRepository.save(settingRole);

            return ResponseEntity.ok(true);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/user/add\": " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
        
    }
    
    @GetMapping(value = "{id}")
    public ResponseEntity getUser(@PathVariable ("id") final Long id) {
        try {
            Employee data = employeeRepository.findOne(id);
            UserResponse response = generateUserResponse(data);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/user/"+id+"\": " + e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        
    }
    
    @PostMapping(value = "{id}/edit")
    public ResponseEntity editUser(@RequestBody final UserEditRequest request, @PathVariable ("id") final Long id) {
        try {
            Employee editData = employeeRepository.findOne(id);
        
            editData.setActive(request.isActive());

            employeeRepository.save(editData);

            List<UserRole> userRoleList = userRoleRepository.findByEmployeeId(id);
            List<RoleList> roles = request.getRole();
            List<Long> roleActivate = new ArrayList<>();
            List<Long> roleExist = new ArrayList<>();
            roles.forEach(x -> {
                roleActivate.add(x.getRoleId());
            });
            if(request.isActive()) {
                userRoleList.forEach(e -> {
                    roleExist.add(e.getRoleId());
                    if(e.getRoleId() == 4){
                        e.setActive(true);

                    } else if (roleActivate.contains(e.getRoleId())) {
                        e.setActive(true);
                    } else {
                        e.setActive(false);
                    }
                    userRoleRepository.save(e);
                });
                roleActivate.removeAll(roleExist);
                if(!(roleActivate.isEmpty())) {
                        roleActivate.forEach(x -> {
                        UserRole createRole = new UserRole();
                        createRole.setEmployeeId(id);
                        createRole.setRoleId(x);
                        createRole.setActive(true);
                        userRoleRepository.save(createRole);
                    });
                }
            } else {
                userRoleList.forEach(e -> {
                    e.setActive(false);
                });
            }

            return ResponseEntity.ok(true);
            
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/user/"+ id +"/edit\": " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
    
    @GetMapping(value = "eligible/{id}")
    public ResponseEntity getEligibleUsers(@PathVariable ("id") Long id) {
        try {
            List<Employee> employeeList = employeeRepository.findAll();
            List<UserResponse> response = new ArrayList<>();
            List<EligibleParticipants> eligibleData = eligibleParticipantsRepository.findByTrainingPeriodId(id);
            List<Long> eligibleList = new ArrayList<>();
            eligibleData.forEach(x -> {
                eligibleList.add(userRoleRepository.findOne(x.getUserRoleId()).getEmployeeId());
            });
            employeeList.forEach(x -> {
                if (x.isActive()) {
                    if(!(eligibleList.contains(x.getEmployeeId()))) {
                        response.add(generateUserResponse(x));
                    }
                }
            });

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/user/eligible/" + id + "\": " + e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        
    }
    @GetMapping(value = "trainer")
    public ResponseEntity getTrainer() {
        try {
            List<Employee> employeeList = employeeRepository.findAll();
            List<UserResponse> response = new ArrayList<>();
            employeeList.forEach(e -> {
                List<UserRole> roleList = userRoleRepository.findByEmployeeId(e.getEmployeeId());
                roleList.forEach(x -> {
                    if(x.getRoleId() == 2) {
                        response.add(generateUserResponse(e));
                    }
                });
            });
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/user/eligible/trainer\": " + e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }
    
    @GetMapping(value = "grade")
    public ResponseEntity getAllGrade() {
        try {
            List<Grade> gradeList = gradeRepository.findAll();
            List<GradeResponse> response = new ArrayList<>();
            gradeList.forEach(e -> {
                response.add(generateGradeResponse(e));
            });
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/user/eligible/grade\": " + e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        
    }
    @GetMapping(value = "location")
    public ResponseEntity getAllLocation() {
        try {
            List<Location> response = locationRepository.findAll();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/user/eligible/location\": " + e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }
    
    private UserResponse generateUserResponse(Employee data) {
        UserResponse result = new UserResponse();
        
        result.setEmployeeId(data.getEmployeeId());
        result.setFullName(data.getFullName());
        result.setEmail(data.getEmail());
        result.setAccountName(data.getAccountName());
        
        if(jobFamily.isEmpty() || !(jobFamily.containsKey(data.getGradeId())) ) {
            jobFamily.put(data.getGradeId(), gradeRepository.findOne(data.getGradeId()).getJobFamily());
        }
        
        if(data.getStream()== null) {
            result.setJobFamilyStream(jobFamily.get(data.getGradeId()));
        } else {
            result.setJobFamilyStream(jobFamily.get(data.getGradeId()) + "." + data.getStream());
        }
        
        if(grade.isEmpty() || !(grade.containsKey(data.getGradeId()))) {
            grade.put(data.getGradeId(), gradeRepository.findOne(data.getGradeId()).getGrade());
        }
        result.setGrade(grade.get(data.getGradeId()));
        result.setActive(data.isActive());
        result.setRole(getEmployeeRole(data.getEmployeeId()));
        
        if(location.isEmpty() || !(location.containsKey(data.getGradeId()))) {
            location.put(data.getLocationId(), locationRepository.findOne(data.getLocationId()).getLocation());
        }
        result.setLocation(location.get(data.getLocationId()));
        
        return result;
    }
    
    private List<Long> getEmployeeRole(Long id) {
        List<UserRole> userRole = userRoleRepository.findByEmployeeId(id);
        List<Long> x = new ArrayList<>();
        userRole.forEach(role -> {
            x.add(role.getRoleId());
        });
        return x;
    }

    private GradeResponse generateGradeResponse(Grade e) {
        GradeResponse result = new GradeResponse();
        
        result.setGradeId(e.getGradeId());
        
        if(jobFamily.isEmpty() || !(jobFamily.containsKey(e.getGradeId())) ) {
            jobFamily.put(e.getGradeId(), e.getJobFamily());
        }
        
        if(grade.isEmpty() || !(grade.containsKey(e.getGradeId()))) {
            grade.put(e.getGradeId(), e.getGrade());
        }
        
        result.setGrade(jobFamily.get(e.getGradeId()) + "-" +grade.get(e.getGradeId()));
        
        return result;
    }
}
