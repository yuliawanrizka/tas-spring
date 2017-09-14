/*
 * @(#) TrainingPeriod.java, v 1.0 2017/09/13 13:39:54
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

import java.sql.Timestamp;
import java.time.LocalDate;
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
public class TrainingPeriod {

    @OneToMany(mappedBy = "trainingPeriod")
    private List<Course> courses;

    @Id
    @GeneratedValue
    private Integer trainingPeriodId; //PK
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToOne
    private UserRole creator;
    private Timestamp createdDate;
    @ManyToOne
    private UserRole updater;
    private Timestamp updateDate;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Integer getTrainingPeriodId() {
        return trainingPeriodId;
    }

    public void setTrainingPeriodId(Integer trainingPeriodId) {
        this.trainingPeriodId = trainingPeriodId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public UserRole getCreator() {
        return creator;
    }

    public void setCreator(UserRole creator) {
        this.creator = creator;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public UserRole getUpdater() {
        return updater;
    }

    public void setUpdater(UserRole updater) {
        this.updater = updater;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }
}
