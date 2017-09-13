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

import java.time.Clock;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * Class Description
 * 
 */
@Entity
public class TrainingPeriod {

    @Id
    @GeneratedValue
    private int trainingPeriodId; //PK
    private LocalDate startDate;
    private LocalDate endDate;
    private int creatorId; //FK = UserRole
    private Clock createdDate;
    private int updaterId;
    private Clock updateDate; //FK = UserRole

    public int getTrainingPeriodId() {
        return trainingPeriodId;
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

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public Clock getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Clock createdDate) {
        this.createdDate = createdDate;
    }

    public int getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(int updaterId) {
        this.updaterId = updaterId;
    }

    public Clock getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Clock updateDate) {
        this.updateDate = updateDate;
    }
}
