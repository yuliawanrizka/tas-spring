/*
 * @(#) EligibleParticipants.java, v 1.0 2017/09/19 14:04:20
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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * Class Description
 * 
 */
@Entity
public class EligibleParticipants {

    @Id
    @GeneratedValue
    private Long eligibleParticipantsId;
    private Long trainingPeriodId;
    private Long userRoleId;

    public Long getEligibleParticipantsId() {
        return eligibleParticipantsId;
    }

    public void setEligibleParticipantsId(Long eligibleParticipantsId) {
        this.eligibleParticipantsId = eligibleParticipantsId;
    }

    public Long getTrainingPeriodId() {
        return trainingPeriodId;
    }

    public void setTrainingPeriodId(Long trainingPeriodId) {
        this.trainingPeriodId = trainingPeriodId;
    }

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }
}
