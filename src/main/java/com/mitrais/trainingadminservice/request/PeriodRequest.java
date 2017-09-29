/*
 * @(#) PeriodRequest.java, v 1.0 2017/09/25 13:40:58
 * 
 * Copyright (c) 2017, PT. Mitrais, Bali, Indonesia.
 * All rights reserved.
 * 
 * Revision History
 * 
 * 25-Sep-2017 Yuliawan Rizka Syafaat             [1.0]-Initial Coding
 * 
 */

package com.mitrais.trainingadminservice.request;

import java.sql.Date;


/**
 * Class Description
 * 
 */
public class PeriodRequest {
    private String trainingName;
    private boolean active;
    private Date startDate;
    private Date endDate;
    private boolean openEnrollment;
    private boolean periodical;

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public boolean isOpenEnrollment() {
        return openEnrollment;
    }

    public void setOpenEnrollment(boolean openEnrollment) {
        this.openEnrollment = openEnrollment;
    }

    public boolean isPeriodical() {
        return periodical;
    }

    public void setPeriodical(boolean periodical) {
        this.periodical = periodical;
    }
}
