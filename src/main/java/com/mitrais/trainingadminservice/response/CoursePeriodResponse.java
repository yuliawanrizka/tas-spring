/*
 * @(#) CoursePeriodResponse.java, v 1.0 2017/09/27 14:38:32
 * 
 * Copyright (c) 2017, PT. Mitrais, Bali, Indonesia.
 * All rights reserved.
 * 
 * Revision History
 * 
 * 27-Sep-2017 Yuliawan Rizka Syafaat             [1.0]-Initial Coding
 * 
 */

package com.mitrais.trainingadminservice.response;


/**
 * Class Description
 * 
 */
public class CoursePeriodResponse {
    private Long coursePeriodId;
    private String courseName;
    private String mainTrainer;
    private String backupTrainer;
    private String classroom;
    private int capacity;

    public Long getCoursePeriodId() {
        return coursePeriodId;
    }

    public void setCoursePeriodId(Long coursePeriodId) {
        this.coursePeriodId = coursePeriodId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getMainTrainer() {
        return mainTrainer;
    }

    public void setMainTrainer(String mainTrainer) {
        this.mainTrainer = mainTrainer;
    }

    public String getBackupTrainer() {
        return backupTrainer;
    }

    public void setBackupTrainer(String backupTrainer) {
        this.backupTrainer = backupTrainer;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
