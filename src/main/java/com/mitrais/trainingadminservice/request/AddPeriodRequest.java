/*
 * @(#) AddPeriodRequest.java, v 1.0 2017/09/25 13:40:58
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
public class AddPeriodRequest {
    private String trainingName;
    private Date startDate;
    private Date endDate;
    private boolean openEnrollment;

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
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
}
