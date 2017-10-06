/*
 * @(#) AttendanceRequest.java, v 1.0 2017/10/04 10:44:07
 * 
 * Copyright (c) 2017, PT. Mitrais, Bali, Indonesia.
 * All rights reserved.
 * 
 * Revision History
 * 
 * 04-Oct-2017 Yuliawan Rizka Syafaat             [1.0]-Initial Coding
 * 
 */

package com.mitrais.trainingadminservice.request;


/**
 * Class Description
 * 
 */
public class AttendanceRequest {
    private Long enrolledId;
    private int status;

    public Long getEnrolledId() {
        return enrolledId;
    }

    public void setEnrolledId(Long enrolledId) {
        this.enrolledId = enrolledId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
