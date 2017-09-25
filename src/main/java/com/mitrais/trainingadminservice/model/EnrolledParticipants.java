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
    private Long elogibleParticipantsId;
    private Long coursePeriodId;

    public Long getEnrolledParticipantsId() {
        return enrolledParticipantsId;
    }

    public void setEnrolledParticipantsId(Long enrolledParticipantsId) {
        this.enrolledParticipantsId = enrolledParticipantsId;
    }

    public Long getElogibleParticipantsId() {
        return elogibleParticipantsId;
    }

    public void setElogibleParticipantsId(Long elogibleParticipantsId) {
        this.elogibleParticipantsId = elogibleParticipantsId;
    }

    public Long getCoursePeriodId() {
        return coursePeriodId;
    }

    public void setCoursePeriodId(Long coursePeriodId) {
        this.coursePeriodId = coursePeriodId;
    }
}
