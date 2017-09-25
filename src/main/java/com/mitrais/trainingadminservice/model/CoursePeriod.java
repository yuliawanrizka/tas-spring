/*
 * @(#) CoursePeriod.java, v 1.0 2017/09/19 13:56:15
 * 
 * Copyright (c) 2017, PT. Mitrais, Bali, Indonesia.
 * All rights reserved.
 * 
 * Revision History
 * 
 * 19-Sep-2017 Yuliawan Rizka Syafaat             [1.0]-Initial Coding
 * 
 */

package com.mitrais.trainingadminservice.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * Class Description
 * 
 */
@Entity
public class CoursePeriod {

    @Id
    @GeneratedValue
    private Long coursePeriodId;
    private Long courseId;
    private Long trainingPeriodId;
    private Long mainTrainer;
    private Long backupTrainer;
    private Long classroomId;
    private Long creatorId;
    private LocalDateTime createdAt;
    private Long updaterId;
    private LocalDateTime updatedAt;
    private boolean onlineClass;

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

    public Long getTrainingPeriodId() {
        return trainingPeriodId;
    }

    public void setTrainingPeriodId(Long trainingPeriodId) {
        this.trainingPeriodId = trainingPeriodId;
    }

    public Long getMainTrainer() {
        return mainTrainer;
    }

    public void setMainTrainer(Long mainTrainer) {
        this.mainTrainer = mainTrainer;
    }

    public Long getBackupTrainer() {
        return backupTrainer;
    }

    public void setBackupTrainer(Long backupTrainer) {
        this.backupTrainer = backupTrainer;
    }

    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Long updaterId) {
        this.updaterId = updaterId;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isOnlineClass() {
        return onlineClass;
    }

    public void setOnlineClass(boolean onlineClass) {
        this.onlineClass = onlineClass;
    }
}
