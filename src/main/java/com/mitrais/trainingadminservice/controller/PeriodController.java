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

import com.mitrais.trainingadminservice.model.Classroom;
import com.mitrais.trainingadminservice.model.Course;
import com.mitrais.trainingadminservice.model.CoursePeriod;
import com.mitrais.trainingadminservice.model.EligibleParticipants;
import com.mitrais.trainingadminservice.model.Employee;
import com.mitrais.trainingadminservice.model.EnrolledParticipants;
import com.mitrais.trainingadminservice.model.Schedule;
import com.mitrais.trainingadminservice.model.TrainingPeriod;
import com.mitrais.trainingadminservice.model.UserRole;
import com.mitrais.trainingadminservice.repository.ClassroomRepository;
import com.mitrais.trainingadminservice.repository.CoursePeriodRepository;
import com.mitrais.trainingadminservice.repository.CourseRepository;
import com.mitrais.trainingadminservice.repository.EligibleParticipantsRepository;
import com.mitrais.trainingadminservice.repository.EmployeeRepository;
import com.mitrais.trainingadminservice.repository.EnrolledParticipantsRepository;
import com.mitrais.trainingadminservice.repository.GradeRepository;
import com.mitrais.trainingadminservice.repository.LocationRepository;
import com.mitrais.trainingadminservice.repository.ScheduleRepository;
import com.mitrais.trainingadminservice.repository.TrainingPeriodRepository;
import com.mitrais.trainingadminservice.repository.UserRoleRepository;
import com.mitrais.trainingadminservice.request.CoursePeriodRequest;
import com.mitrais.trainingadminservice.request.EligibleEnrollRequest;
import com.mitrais.trainingadminservice.request.PeriodRequest;
import com.mitrais.trainingadminservice.response.ClassroomResponse;
import com.mitrais.trainingadminservice.response.CoursePeriodResponse;
import com.mitrais.trainingadminservice.response.CourseResponse;
import com.mitrais.trainingadminservice.response.EligibleResponse;
import com.mitrais.trainingadminservice.response.PeriodResponse;
import com.mitrais.trainingadminservice.response.UserResponse;
import io.jsonwebtoken.Claims;
import java.sql.Date;
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
    
    @Autowired
    private CoursePeriodRepository coursePeriodRepository;
    
    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private ClassroomRepository classroomRepository;
    
    @Autowired
    private LocationRepository locationRepository;
    
    @Autowired
    private ScheduleRepository scheduleRepository;
    
    @Autowired
    private EnrolledParticipantsRepository enrolledParticipantsRepository;
    
    @Autowired
    private GradeRepository gradeRepository;
    
    private HashMap<Long,String> employeeNameList = new HashMap<>();
    private HashMap<Long,String> classroomList = new HashMap<>();
    private HashMap<Long,String> courseList = new HashMap<>();
    private HashMap<Long,String> jobFamily = new HashMap<>();
    private HashMap<Long,String> grade = new HashMap<>();
    
    @GetMapping(value = "")
    public ResponseEntity getAllPeriod() {
        try{
            List<PeriodResponse> response = new ArrayList<>();

            List<TrainingPeriod> trainingPeriod = trainingPeriodRepository.findAll();
            trainingPeriod.forEach( data -> {
                response.add(generatePeriodResponse(data));
            });
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/period/\": " + e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }
    
    @PostMapping(value = "add")
    public ResponseEntity addPeriod(@RequestBody final PeriodRequest request, @RequestAttribute Claims claims) {
        try {
            TrainingPeriod data = new TrainingPeriod();
            
            TrainingPeriod dataInserted = generateTrainingPeriod(data, request);
            dataInserted.setActive(true);
            dataInserted.setPeriodical(request.isPeriodical());
            List<UserRole> userRoleData = userRoleRepository.findByEmployeeId(new Long(claims.get("userId").toString()));
            userRoleData.forEach(e -> {
                if(e.getRoleId() == 1) {
                    dataInserted.setCreatorId(e.getUserRoleId());
                }
            });
            dataInserted.setCreatedAt(new Timestamp(Calendar.getInstance().getTime().getTime()));

            trainingPeriodRepository.save(dataInserted);
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/period/add\": " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
    
    @GetMapping(value = "{id}")
    public ResponseEntity findPeriod(@PathVariable ("id") final Long id) {
        try {
            TrainingPeriod data = trainingPeriodRepository.findOne(id);

            PeriodResponse response = generatePeriodResponse(data);

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
            
            TrainingPeriod dataInserted = generateTrainingPeriod(data, request);
            List<UserRole> userRoleData = userRoleRepository.findByEmployeeId(new Long(claims.get("userId").toString()));
            userRoleData.forEach(e -> {
                if(e.getRoleId() == 1) {
                    dataInserted.setUpdaterId(e.getUserRoleId());
                }
            });
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
            if(data.isEmpty()){
                throw new NullPointerException();
            }
            data.forEach(x -> {
                response.add(new EligibleResponse(getEmployeeId(x.getUserRoleId()), getEmployeeFullName(x.getUserRoleId())));
            });
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/period/" + id + "/eligible\": " + e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        
    }
    
    @PostMapping(value = "{id}/eligible/add")
    public ResponseEntity addEligibleParticipants(@RequestBody final List<EligibleEnrollRequest> request, @PathVariable("id") final Long id ) {
        try {
            request.forEach(x -> {
                addEligibleUser(x, id);
            });
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/period/" + id + "/eligible/add\": " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
    @DeleteMapping(value = "{id}/eligible/delete/{idUser}")
    public ResponseEntity deleteEligibleParticipants(@PathVariable("id") final Long id, @PathVariable("idUser") final Long idUser) {
        try {
            deleteEligibleUser(idUser, id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/period/" + id + "/eligible/delete/"+ idUser +"\": " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
    
    @GetMapping(value = "{id}/course")
    public ResponseEntity getAllCoursePeriod(@PathVariable("id") final Long id) {
        try {
            List<CoursePeriod> scheduleList = coursePeriodRepository.findByTrainingPeriodId(id);
            List<CoursePeriodResponse> response = new ArrayList<>();
            if(scheduleList.isEmpty()) {
                throw new NullPointerException();
            }
            scheduleList.forEach(data -> {
                response.add(generateCoursePeriodResponse(data));
            });
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/period/" + id + "/course\": " + e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }  
    
    @PostMapping(value = "{id}/course/add")
    public ResponseEntity addCoursePeriod(@RequestBody final CoursePeriodRequest request, @PathVariable ("id") Long id, @RequestAttribute Claims claims) {
        try {
            CoursePeriod data = new CoursePeriod();
            
            data.setCourseId(request.getCourseId());
            data.setTrainingPeriodId(id);
            data.setMainTrainer(request.getMainTrainerId());
            if(request.getBackupTrainerId() != null){
                data.setBackupTrainer(request.getBackupTrainerId());
            }
            
            data.setClassroomId(request.getClassroomId());
            data.setCapacity(request.getCapacity());
            
            List<UserRole> userRoleData = userRoleRepository.findByEmployeeId(new Long(claims.get("userId").toString()));
            userRoleData.forEach(e -> {
                if(e.getRoleId() == 1) {
                    data.setCreatorId(e.getUserRoleId());
                }
            });
            data.setCreatedAt(new Timestamp(Calendar.getInstance().getTime().getTime()));
            
            coursePeriodRepository.save(data);
            
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/"+id+"/period/add\": " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
    
    @PostMapping(value = "{id}/course/edit")
    public ResponseEntity editCoursePeriod(@RequestBody final List<CoursePeriodRequest> request, @PathVariable ("id") Long id, @RequestAttribute Claims claims) {
        try {
            request.forEach(e -> {
                editCoursePeriodData(e, new Long(claims.get("userId").toString()), id);
            });
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/"+id+"/period/add\": " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
    
    @DeleteMapping(value = "{id}/course/delete/{idCoursePeriod}")
    public ResponseEntity deleteCoursePeriod(@PathVariable("id") final Long id, @PathVariable("idCoursePeriod") final Long idCoursePeriod) {
        try {
            scheduleRepository.deleteByCoursePeriodId(idCoursePeriod);
            coursePeriodRepository.delete(idCoursePeriod);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/period/" + id + "/eligible/delete/"+ idCoursePeriod +"\": " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
    
    @GetMapping(value = "{id}/course/{idCoursePeriod}")
    public ResponseEntity getCoursePeriod(@PathVariable("id") final Long id, @PathVariable("idCoursePeriod") final Long idCoursePeriod) {
        try {
            CoursePeriod course = coursePeriodRepository.findOne(idCoursePeriod);
            CoursePeriodResponse response = generateCoursePeriodResponse(course);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/period/" + id + "/course/"+idCoursePeriod+"\": " + e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }
    @GetMapping(value = "{id}/course/{idCoursePeriod}/eligible")
    public ResponseEntity getEligibleCoursePeriod(@PathVariable("id") final Long id, @PathVariable("idCoursePeriod") final Long idCoursePeriod) {
        try {
            List<EligibleParticipants> eligibleList = eligibleParticipantsRepository.findByTrainingPeriodId(id);
            List<EnrolledParticipants> enrolledData = enrolledParticipantsRepository.findByCoursePeriodId(idCoursePeriod);
            List<Long> enrolledList = new ArrayList<>();
            List<UserResponse> response = new ArrayList<>();
            
            enrolledData.forEach(e -> {
                enrolledList.add(e.getEligibleParticipantsId());
            });
            
            eligibleList.forEach(e -> {
                if(!(enrolledList.contains(e.getEligibleParticipantsId()))) {
                    response.add(generateUserResponse(e));
                }
            });
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/period/" + id + "/course/"+idCoursePeriod+"\": " + e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }
    
    @PostMapping(value = "{id}/course/{idCoursePeriod}/eligible/add")
    public ResponseEntity addEligibleCoursePeriod(@RequestBody final List<EligibleEnrollRequest> request, @PathVariable("id") final Long id, @PathVariable("idCoursePeriod") final Long idCoursePeriod) {
      try {
            request.forEach(e -> {
                enrolledParticipantsRepository.save(new EnrolledParticipants(e.getEmployeeId(), idCoursePeriod));
            });
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/period/" + id + "/course/"+idCoursePeriod+"\": " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
    
    @GetMapping(value = "course")
    public ResponseEntity getAllCourse() {
        try {
            List<Course> course = courseRepository.findAll();
            List<CourseResponse> response = new ArrayList<>();
            course.forEach(e -> {
                response.add(generateCourseResponse(e));
            });
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/period/course\": " + e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        
    }
    @GetMapping(value = "classroom")
    public ResponseEntity getClassroom() {
        try {
            List<Classroom> classroom = classroomRepository.findAll();
            List<ClassroomResponse> response = new ArrayList<>();
            classroom.forEach(e -> {
                response.add(generateClassroomResponse(e));
            });
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("ERROR at \"api/secure/period/course\": " + e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }
    
    private Long getEmployeeId (Long id) {
        return userRoleRepository.getOne(id).getEmployeeId();
    }
    
    private String getEmployeeFullName (Long id) {
        return employeeRepository.getOne(userRoleRepository.getOne(id).getEmployeeId()).getFullName();
    }
    
    private ClassroomResponse generateClassroomResponse(Classroom data){
        ClassroomResponse result = new ClassroomResponse();
        
        result.setClassroomId(data.getClassroomId());
        
        if (classroomList.isEmpty() || !(classroomList.containsKey(data.getClassroomId()))) {
            classroomList.put(data.getClassroomId(),
                    classroomRepository.findOne(data.getClassroomId()).getClassroom() 
                            + ", " +
                            locationRepository.findOne(classroomRepository.findOne(data.getClassroomId()).getLocationId()).getLocation());
        }
        
        result.setClassroomName(classroomList.get(data.getClassroomId()));
        
        return result;
    }
    
    private CourseResponse generateCourseResponse (Course data) {
        CourseResponse result = new CourseResponse();
        result.setCourseId(data.getCourseId());
        result.setCourseName(data.getCourseName());
        return result;
    }
    
   private CoursePeriodResponse generateCoursePeriodResponse (CoursePeriod data) {
        CoursePeriodResponse result = new CoursePeriodResponse();
        
        result.setCoursePeriodId(data.getCoursePeriodId());
        
        if(courseList.isEmpty() || !(courseList.containsKey(data.getCourseId()))){
            courseList.put(data.getCourseId(), courseRepository.findOne(data.getCourseId()).getCourseName());
        }
        result.setCourseName(courseList.get(data.getCourseId()));
        
        if(employeeNameList.isEmpty() || !(employeeNameList.containsKey(data.getMainTrainer()))){
            employeeNameList.put(data.getMainTrainer(), getEmployeeFullName(data.getMainTrainer()));
        }
        result.setMainTrainer(employeeNameList.get(data.getMainTrainer()));

        if(data.getBackupTrainer()!= null ) {
            if(!(employeeNameList.containsKey(data.getBackupTrainer()))) {
                employeeNameList.put(data.getBackupTrainer(), getEmployeeFullName(data.getBackupTrainer()));
            }
            result.setBackupTrainer(employeeNameList.get(data.getBackupTrainer()));
        } else {
            result.setBackupTrainer("-");
        }
        
        if (classroomList.isEmpty() || !(classroomList.containsKey(data.getClassroomId()))) {
            classroomList.put(data.getClassroomId(),
                    classroomRepository.findOne(data.getClassroomId()).getClassroom() 
                            + ", " +
                            locationRepository.findOne(classroomRepository.findOne(data.getClassroomId()).getLocationId()).getLocation());
        }
        
        result.setClassroom(classroomList.get(data.getClassroomId()));
        if(data.getDayOfTraining() != null) {
            result.setDay(data.getDayOfTraining().toString());
        } else {
            result.setDay("");
        }
        
        result.setStartTime("?");
        result.setEndTime("?");
        result.setCapacity(data.getCapacity());
        result.setApList(enrolledParticipantsRepository.findByCoursePeriodId(data.getCoursePeriodId()).size());
        
        if(!(employeeNameList.containsKey(data.getCreatorId()))){
            employeeNameList.put(data.getCreatorId(), getEmployeeFullName(data.getCreatorId()));
        }
        result.setCreatedBy(employeeNameList.get(data.getCreatorId()));
        
        if (data.getUpdaterId() != null) {
           if(!(employeeNameList.containsKey(data.getUpdaterId()))){
                employeeNameList.put(data.getUpdaterId(), getEmployeeFullName(data.getUpdaterId()));
            }
            result.setUpdatedBy(employeeNameList.get(data.getUpdaterId()));
        } else {
            result.setUpdatedBy("-");
        }
        
        result.setCreatedAt(data.getCreatedAt().toString());
        if(data.getUpdatedAt() != null) {
            result.setUpdatedAt(data.getUpdatedAt().toString());
        } else {
            result.setUpdatedAt("-");
        }
        
        
        return result;
    }
    
    private PeriodResponse generatePeriodResponse(TrainingPeriod data){
        PeriodResponse result = new PeriodResponse();
        
        result.setTrainingPeriodId(data.getTrainingPeriodId());
        result.setTrainingName(data.getTrainingName());
        result.setActiveStatus(data.isActive());
        result.setCoursesCount(coursePeriodRepository.findByTrainingPeriodId(data.getTrainingPeriodId()).size());
        result.setStartDate(data.getStartDate());
        result.setEndDate(data.getEndDate());

        if(employeeNameList.isEmpty() || !(employeeNameList.containsKey(data.getCreatorId()))){
            employeeNameList.put(data.getCreatorId(), getEmployeeFullName(data.getCreatorId()));
        }
        result.setCreatedBy(employeeNameList.get(data.getCreatorId()));

        if(data.getUpdaterId() != null ) {
            if(!(employeeNameList.containsKey(data.getUpdaterId()))) {
                employeeNameList.put(data.getUpdaterId(), getEmployeeFullName(data.getUpdaterId()));
            }
        result.setEditedBy(employeeNameList.get(data.getUpdaterId()));
        } else {
            result.setEditedBy("-");
        }
        result.setOpenEnrollment(data.isOpenEnrollment());
        result.setPeriodical(data.isPeriodical());
        return result;
    }
    private TrainingPeriod generateTrainingPeriod(TrainingPeriod data, PeriodRequest source) {
        data.setTrainingName(source.getTrainingName());
        data.setActive(source.isActive());
        data.setStartDate(source.getStartDate());
        data.setEndDate(source.getEndDate());
        data.setOpenEnrollment(source.isOpenEnrollment());
        return data;
    }
    
    private void addEligibleUser(EligibleEnrollRequest data, Long id) {
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
    
    private void deleteEligibleUser (Long data, Long id) {
        List<UserRole> role = userRoleRepository.findByEmployeeId(data);
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

    private void editCoursePeriodData(CoursePeriodRequest e, Long id, Long periodId) {
        CoursePeriod data = coursePeriodRepository.findOne(e.getCoursePeriodId());
        
        data.setPeriodical(trainingPeriodRepository.findOne(periodId).isPeriodical());
        if (data.isPeriodical()) {
            data.setDayOfTraining(e.getDay());
            int offset;
            Calendar cal = Calendar.getInstance();
            cal.setTime(trainingPeriodRepository.findOne(data.getTrainingPeriodId()).getStartDate());
            int startDayPeriod = cal.get(Calendar.DAY_OF_WEEK);
            if( startDayPeriod > e.getDay() ) {
                offset = ( 7 - startDayPeriod ) + e.getDay();
            } else {
                offset = e.getDay() - startDayPeriod;
            }
            cal.add(Calendar.DATE,offset);
            Date scheduleDate = new Date(cal.getTimeInMillis());
            Date endPeriod = trainingPeriodRepository.findOne(data.getTrainingPeriodId()).getEndDate();
            while (endPeriod.after(scheduleDate)) {
                Schedule schedule = new Schedule();
            
                schedule.setCoursePeriodId(e.getCoursePeriodId());
                schedule.setStartDate(scheduleDate);
                schedule.setStartTime(e.getStartTime());
                schedule.setEndDate(scheduleDate);
                schedule.setEndTime(e.getEndTime());
            
                scheduleRepository.save(schedule);
                cal.add(Calendar.DATE,7);
                scheduleDate.setTime(cal.getTimeInMillis());
            }
        } else {
            Schedule schedule = new Schedule();
            
            schedule.setCoursePeriodId(e.getCoursePeriodId());
            schedule.setStartDate(e.getStartDate());
            schedule.setStartTime(e.getStartTime());
            schedule.setEndDate(e.getEndDate());
            schedule.setEndTime(e.getEndTime());
            
            scheduleRepository.save(schedule);
        }
        List<UserRole> userRoleData = userRoleRepository.findByEmployeeId(id);
        userRoleData.forEach(x -> {
            if(x.getRoleId() == 1) {
                data.setUpdaterId(x.getUserRoleId());
            }
        });
        data.setUpdatedAt(new Timestamp(Calendar.getInstance().getTime().getTime()));
        
        coursePeriodRepository.save(data);
    }

    private UserResponse generateUserResponse(EligibleParticipants e) {
        UserResponse result = new UserResponse();
        Employee data = employeeRepository.findOne(userRoleRepository.findOne(e.getUserRoleId()).getEmployeeId());
        
        result.setEmployeeId(e.getEligibleParticipantsId());
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
}
