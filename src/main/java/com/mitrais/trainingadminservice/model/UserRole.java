/*
 * @(#) UserRole.java, v 1.0 2017/09/13 13:44:44
 * 
 * Copyright (c) 2017, PT. Mitrais, Bali, Indonesia.
 * All rights reserved.
 * 
 * Revision History
 * 
 * 13-Sep-2017 Yuliawan Rizka Syafaat             [1.0]-Initial Coding
 * 
 */

package com.mitrais.trainingadminservice.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


/**
 * Class Description
 * 
 */
@Entity
public class UserRole {

    @OneToMany(mappedBy = "updater")
    private List<TrainingPeriod> TrainingPeriodUpdater;

    @OneToMany(mappedBy = "creator")
    private List<TrainingPeriod> trainingPeriodCreator;

    @OneToMany(mappedBy = "userRole")
    private List<Trainer> trainers;

    @OneToMany(mappedBy = "userRole")
    private List<CourseParticipants> courseParticipantss;

    @Id
    @GeneratedValue
    private Integer userRoleId; //PK
    @ManyToOne
    private EmployeeData employee;
    @ManyToOne
    private Roles role;

    public List<TrainingPeriod> getTrainingPeriodUpdater() {
        return TrainingPeriodUpdater;
    }

    public void setTrainingPeriodUpdater(List<TrainingPeriod> TrainingPeriodUpdater) {
        this.TrainingPeriodUpdater = TrainingPeriodUpdater;
    }

    public List<TrainingPeriod> getTrainingPeriodCreator() {
        return trainingPeriodCreator;
    }

    public void setTrainingPeriodCreator(List<TrainingPeriod> trainingPeriodCreator) {
        this.trainingPeriodCreator = trainingPeriodCreator;
    }

    public List<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<Trainer> trainers) {
        this.trainers = trainers;
    }

    public List<CourseParticipants> getCourseParticipantss() {
        return courseParticipantss;
    }

    public void setCourseParticipantss(List<CourseParticipants> courseParticipantss) {
        this.courseParticipantss = courseParticipantss;
    }

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public EmployeeData getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeData employee) {
        this.employee = employee;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
