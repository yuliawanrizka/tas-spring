/*
 * @(#) EnrolledParticipants.java, v 1.0 2017/09/19 14:07:37
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
public class EnrolledParticipants {

    @Id
    @GeneratedValue
    private Long enrolledParticipantsId;
    private Long eligibleParticipantsId;
    private Long coursePeriodId;
    private boolean active;

    public EnrolledParticipants() {
    }

    public EnrolledParticipants(Long eligibleParticipantsId, Long coursePeriodId) {
        this.eligibleParticipantsId = eligibleParticipantsId;
        this.coursePeriodId = coursePeriodId;
    }

    public Long getEnrolledParticipantsId() {
        return enrolledParticipantsId;
    }

    public void setEnrolledParticipantsId(Long enrolledParticipantsId) {
        this.enrolledParticipantsId = enrolledParticipantsId;
    }

    public Long getEligibleParticipantsId() {
        return eligibleParticipantsId;
    }

    public void setEligibleParticipantsId(Long eligibleParticipantsId) {
        this.eligibleParticipantsId = eligibleParticipantsId;
    }

    public Long getCoursePeriodId() {
        return coursePeriodId;
    }

    public void setCoursePeriodId(Long coursePeriodId) {
        this.coursePeriodId = coursePeriodId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
