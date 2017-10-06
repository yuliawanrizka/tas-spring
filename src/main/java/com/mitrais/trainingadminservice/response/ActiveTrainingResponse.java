/*
 * @(#) ActiveTrainingResponse.java, v 1.0 2017/10/05 11:24:59
 * 
 * Copyright (c) 2017, PT. Mitrais, Bali, Indonesia.
 * All rights reserved.
 * 
 * Revision History
 * 
 * 05-Oct-2017 Yuliawan Rizka Syafaat             [1.0]-Initial Coding
 * 
 */

package com.mitrais.trainingadminservice.response;


/**
 * Class Description
 * 
 */
public class ActiveTrainingResponse {
    private String courseName;
    private String mainTrainer;
    private String backupTrainer;
    private String startDate;
    private String endDate;
    private String location;

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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
