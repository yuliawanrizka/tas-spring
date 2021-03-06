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

import com.mitrais.trainingadminservice.model.Assessment;
import com.mitrais.trainingadminservice.model.Attendance;
import com.mitrais.trainingadminservice.model.CoursePeriod;
import com.mitrais.trainingadminservice.model.EnrolledParticipants;
import com.mitrais.trainingadminservice.model.Schedule;
import com.mitrais.trainingadminservice.model.TrainingPeriod;
import com.mitrais.trainingadminservice.model.UserRole;
import com.mitrais.trainingadminservice.repository.AssessmentRepository;
import com.mitrais.trainingadminservice.repository.AttendanceRepository;
import com.mitrais.trainingadminservice.repository.ClassroomRepository;
import com.mitrais.trainingadminservice.repository.CoursePeriodRepository;
import com.mitrais.trainingadminservice.repository.CourseRepository;
import com.mitrais.trainingadminservice.repository.EligibleParticipantsRepository;
import com.mitrais.trainingadminservice.repository.EmployeeRepository;
import com.mitrais.trainingadminservice.repository.EnrolledParticipantsRepository;
import com.mitrais.trainingadminservice.repository.LocationRepository;
import com.mitrais.trainingadminservice.repository.ScheduleRepository;
import com.mitrais.trainingadminservice.repository.TrainingPeriodRepository;
import com.mitrais.trainingadminservice.repository.UserRoleRepository;
import com.mitrais.trainingadminservice.request.AssessmentRequest;
import com.mitrais.trainingadminservice.request.AttendanceRequest;
import com.mitrais.trainingadminservice.response.AssessmentResponse;
import com.mitrais.trainingadminservice.response.AttendanceResponse;
import com.mitrais.trainingadminservice.response.MaintenanceResponse;
import com.mitrais.trainingadminservice.response.ScheduleListResponse;
import io.jsonwebtoken.Claims;
import java.util.ArrayList;
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
    @Autowired
    private EligibleParticipantsRepository eligibleParticipantsRepository;
    @Autowired
    private AssessmentRepository assessmentRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private AttendanceRepository attendanceRepository;

    private HashMap<Long,String> classroomList = new HashMap<>();
    
    @GetMapping(value = "{activeRole}")
    public ResponseEntity getTrainingMaintenance(@PathVariable ("activeRole") final Long activeRole, @RequestAttribute final Claims claims) {
        try {
            UserRole validate = userRoleRepository.findByEmployeeIdAndRoleId(new Long(claims.get("userId").toString()), activeRole);
            List<MaintenanceResponse> response = new ArrayList<>();
            if(validate.isActive() && (validate.getRoleId() == 1 || validate.getRoleId() == 2) ) {
                List<CoursePeriod> data;
                if (validate.getRoleId()== 2) {
                    data = coursePeriodRepository.findByMainTrainerOrBackupTrainer(validate.getUserRoleId(), validate.getUserRoleId());
                    
                } else {
                    data = coursePeriodRepository.findAll();
                }
                data.forEach(e -> {
                    if(e.isPeriodical() != null) {
                        response.add(generateMaintenanceResponse(e));
                    }
                });
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/maintenance/"+activeRole+"\": " + e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }
    
    @GetMapping(value = "{id}/assessment")
    public ResponseEntity getAssessment(@PathVariable ("id") final Long id) {
        try {
            List<EnrolledParticipants> enrolledData = enrolledParticipantsRepository.findByCoursePeriodId(id);
            List<Assessment> assessmentData = assessmentRepository.findByCoursePeriodId(id);
            List<AssessmentResponse> response = new ArrayList<>();
            enrolledData.forEach(e -> {
                AssessmentResponse dataForResponse = new AssessmentResponse();
                dataForResponse.setId(e.getEnrolledParticipantsId());
                dataForResponse.setEmployeeName(getEmployeeFullName(eligibleParticipantsRepository.findOne(e.getEligibleParticipantsId()).getUserRoleId()));
                dataForResponse.setStatus(null);
                assessmentData.forEach(n -> {
                    if(n.getEnrolledParticipantsId().equals(e.getEnrolledParticipantsId())) {
                        dataForResponse.setStatus(n.isPass());
                    }
                });
                response.add(dataForResponse);
            });
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/maintenance/"+id+"/assessment\": " + e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }
    @PostMapping(value = "{id}/assessment/edit")
    public ResponseEntity editAssessment(@RequestBody final List<AssessmentRequest> request, @PathVariable("id") final Long id) { 
        try {
            request.forEach(e -> {
                Assessment data = assessmentRepository.findByCoursePeriodIdAndEnrolledParticipantsId(id, e.getEnrolledId());
                if(data == null) {
                    Assessment createNew = new Assessment();
                    createNew.setCoursePeriodId(id);
                    createNew.setEnrolledParticipantsId(e.getEnrolledId());
                    createNew.setPass(e.isPass());
                    assessmentRepository.save(createNew);
                } else {
                    data.setPass(e.isPass());
                    assessmentRepository.save(data);
                }
                
            });
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/maintenance/"+id+"/assessment\": " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
    @GetMapping(value = "{id}/attendance")
    public ResponseEntity getSchedule(@PathVariable ("id") final Long id) {
        try {
            List<Schedule> data = scheduleRepository.findByCoursePeriodId(id);
            List<ScheduleListResponse> response = new ArrayList<>();
            data.forEach(e -> {
                ScheduleListResponse dataForResponse = new ScheduleListResponse();
                dataForResponse.setScheduleId(e.getScheduleId());
                dataForResponse.setDateAndTime(
                        e.getStartDate().toString() + " (" + e.getStartTime() + ") - " +
                                e.getEndDate().toString() + " (" + e.getEndTime() + ")"
                );
                response.add(dataForResponse);
            });
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/maintenance/"+id+"/attendance\": " + e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }
    
    @GetMapping(value = "{id}/attendance/{idSchedule}")
    public ResponseEntity getAttendance(@PathVariable ("id") final Long id, @PathVariable ("idSchedule") final Long idSchedule) {
        try {
            
            List<AttendanceResponse> response = new ArrayList<>();
            List<Attendance> attendanceData = attendanceRepository.findByScheduleId(idSchedule);
            List<EnrolledParticipants> enrolledData = enrolledParticipantsRepository.findByCoursePeriodId(id);
            if(scheduleRepository.findByScheduleIdAndCoursePeriodId(idSchedule, id) != null) {
                enrolledData.forEach(e -> {
                    AttendanceResponse dataForResponse = new AttendanceResponse();
                    dataForResponse.setId(e.getEnrolledParticipantsId());
                    dataForResponse.setEmployeeName(getEmployeeFullName(eligibleParticipantsRepository.findOne(e.getEligibleParticipantsId()).getUserRoleId()));
                    dataForResponse.setAttendanceStatus(null);
                    attendanceData.forEach(n ->{
                        if(n.getEnrolledParticipantsId().equals(e.getEnrolledParticipantsId())) {
                            dataForResponse.setAttendanceStatus(n.getAttendanceStatus());
                        }
                    });
                    response.add(dataForResponse);
                });
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/maintenance/"+id+"/attendance"+idSchedule+"\": " + e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }
    
    @PostMapping(value = "{id}/attendance/{idSchedule}/edit")
    public ResponseEntity editAttendance(@RequestBody final List<AttendanceRequest> request, @PathVariable ("id") final Long id, @PathVariable ("idSchedule") final Long idSchedule) {
        try {
            request.forEach(e -> {
                Attendance data = attendanceRepository.findByScheduleIdAndEnrolledParticipantsId(idSchedule, e.getEnrolledId());
                if(data == null) {
                    Attendance createnew = new Attendance();
                    createnew.setScheduleId(idSchedule);
                    createnew.setEnrolledParticipantsId(e.getEnrolledId());
                    createnew.setAttendanceStatus(e.getStatus());
                    attendanceRepository.save(createnew);
                } else {
                    data.setAttendanceStatus(e.getStatus());
                    attendanceRepository.save(data);
                }
            });
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/maintenance/"+id+"/attendance/"+idSchedule+"/edit\": " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
    
    private MaintenanceResponse generateMaintenanceResponse(CoursePeriod e){
        MaintenanceResponse dataForResponse = new MaintenanceResponse();
        TrainingPeriod trainingPeriodData = trainingPeriodRepository.findOne(e.getTrainingPeriodId());
        dataForResponse.setCoursePeriodId(e.getCoursePeriodId());
        dataForResponse.setPeriodName(trainingPeriodData.getTrainingName());
        dataForResponse.setCourseName(courseRepository.findOne(e.getCourseId()).getCourseName());
        dataForResponse.setTrainer(employeeRepository.findOne(userRoleRepository.findOne(e.getMainTrainer()).getEmployeeId()).getFullName());
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
        if(!(trainingPeriodData.getStartDate() == null)) {
            dataForResponse.setStartDate(trainingPeriodData.getStartDate().toString());
            dataForResponse.setEndDate(trainingPeriodData.getEndDate().toString());
        } else {
            dataForResponse.setStartDate("-");
            dataForResponse.setEndDate("-");
        }
        
        dataForResponse.setNumberOfParticipants(enrolledParticipantsRepository.findByCoursePeriodId(e.getCoursePeriodId()).size());
        return dataForResponse;
    }
    
    private String getEmployeeFullName (Long id) {
        return employeeRepository.getOne(userRoleRepository.getOne(id).getEmployeeId()).getFullName();
    }
}

