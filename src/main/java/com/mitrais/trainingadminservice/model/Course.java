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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * Class Description
 * 
 */
@Entity
public class Course {
    
    @Id
    @GeneratedValue
    private Integer courseId; //PK
    private int courseNameId; //FK = CourseName
    private int trainingPeriodId; //FK = TrainingPeriod
    private int capacity;
    private boolean openEnrollment;

    public int getCourseId() {
        return courseId;
    }

    public int getCourseNameId() {
        return courseNameId;
    }

    public void setCourseNameId(int courseNameId) {
        this.courseNameId = courseNameId;
    }

    public int getTrainingPeriodId() {
        return trainingPeriodId;
    }

    public void setTrainingPeriodId(int trainingPeriodId) {
        this.trainingPeriodId = trainingPeriodId;
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
