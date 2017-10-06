/*
 * @(#) AssessmentRequest.java, v 1.0 2017/10/03 17:35:30
 * 
 * Copyright (c) 2017, PT. Mitrais, Bali, Indonesia.
 * All rights reserved.
 * 
 * Revision History
 * 
 * 03-Oct-2017 Yuliawan Rizka Syafaat             [1.0]-Initial Coding
 * 
 */

package com.mitrais.trainingadminservice.request;


/**
 * Class Description
 * 
 */
public class AssessmentRequest {
    private Long enrolledId;
    private boolean pass;

    public Long getEnrolledId() {
        return enrolledId;
    }

    public void setEnrolledId(Long enrolledId) {
        this.enrolledId = enrolledId;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }
}
