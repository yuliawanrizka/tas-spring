/*
 * @(#) CoursePeriodRequest.java, v 1.0 2017/09/28 11:31:05
 * 
 * Copyright (c) 2017, PT. Mitrais, Bali, Indonesia.
 * All rights reserved.
 * 
 * Revision History
 * 
 * 28-Sep-2017 Yuliawan Rizka Syafaat             [1.0]-Initial Coding
 * 
 */

package com.mitrais.trainingadminservice.request;

import java.sql.Date;
import java.sql.Time;


/**
 * Class Description
 * 
 */
public class CoursePeriodRequest {
    private Long coursePeriodId;
    private Long courseId;
    private Long mainTrainerId;
    private Long backupTrainerId;
    private Long classroomId;
    private int capacity;
    private Date startDate;
    private Date endDate;
    private String startTime;
    private String endTime;
    private int day;            

    public Long getCoursePeriodId() {
        return coursePeriodId;
    }

    public void setCoursePeriodId(Long coursePeriodId) {
        this.coursePeriodId = coursePeriodId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getMainTrainerId() {
        return mainTrainerId;
    }

    public void setMainTrainerId(Long mainTrainerId) {
        this.mainTrainerId = mainTrainerId;
    }

    public Long getBackupTrainerId() {
        return backupTrainerId;
    }

    public void setBackupTrainerId(Long backupTrainerId) {
        this.backupTrainerId = backupTrainerId;
    }

    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
