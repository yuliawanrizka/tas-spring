/*
 * @(#) Course.java, v 1.0 2017/09/13 10:35:15
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
public class Course {

    @OneToMany(mappedBy = "course")
    private List<Trainer> trainers;

    @OneToMany(mappedBy = "course")
    private List<CourseSchedule> courseSchedules;

    @OneToMany(mappedBy = "course")
    private List<CourseParticipants> courseParticipantss;
    
    @Id
    @GeneratedValue
    private Integer courseId; //PK
    @ManyToOne
    private CourseName courseName;
    @ManyToOne
    private TrainingPeriod trainingPeriod;
    private int capacity;
    private boolean openEnrollment;

    public List<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<Trainer> trainers) {
        this.trainers = trainers;
    }

    public List<CourseSchedule> getCourseSchedules() {
        return courseSchedules;
    }

    public void setCourseSchedules(List<CourseSchedule> courseSchedules) {
        this.courseSchedules = courseSchedules;
    }

    public List<CourseParticipants> getCourseParticipantss() {
        return courseParticipantss;
    }

    public void setCourseParticipantss(List<CourseParticipants> courseParticipantss) {
        this.courseParticipantss = courseParticipantss;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public CourseName getCourseName() {
        return courseName;
    }

    public void setCourseName(CourseName courseName) {
        this.courseName = courseName;
    }

    public TrainingPeriod getTrainingPeriod() {
        return trainingPeriod;
    }

    public void setTrainingPeriod(TrainingPeriod trainingPeriod) {
        this.trainingPeriod = trainingPeriod;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isOpenEnrollment() {
        return openEnrollment;
    }

    public void setOpenEnrollment(boolean openEnrollment) {
        this.openEnrollment = openEnrollment;
    }
}
