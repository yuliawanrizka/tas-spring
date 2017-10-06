/*
 * @(#) AchievementRequest.java, v 1.0 2017/10/05 15:40:48
 * 
 * Copyright (c) 2017, PT. Mitrais, Bali, Indonesia.
 * All rights reserved.
 * 
 * Revision History
 * 
 * 05-Oct-2017 Yuliawan Rizka Syafaat             [1.0]-Initial Coding
 * 
 */

package com.mitrais.trainingadminservice.request;


/**
 * Class Description
 * 
 */
public class AchievementRequest {
    private Long bccId;
    private int status;
    private Long term;    

    public Long getBccId() {
        return bccId;
    }

    public void setBccId(Long bccId) {
        this.bccId = bccId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getTerm() {
        return term;
    }

    public void setTerm(Long term) {
        this.term = term;
    }
}
