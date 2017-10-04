/*
 * @(#) Assessment.java, v 1.0 2017/10/03 14:05:14
 * 
 * Copyright (c) 2017, PT. Mitrais, Bali, Indonesia.
 * All rights reserved.
 * 
 * Revision History
 * 
 * 03-Oct-2017 Yuliawan Rizka Syafaat             [1.0]-Initial Coding
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
public class Assessment {
    @Id
    @GeneratedValue
    private Long assessmentId;
    private Long enrolledParticipantsId;
    private Long coursePeriodId;
    private boolean pass;

    public Long getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Long assessmentId) {
        this.assessmentId = assessmentId;
    }

    public Long getEnrolledParticipantsId() {
        return enrolledParticipantsId;
    }

    public void setEnrolledParticipantsId(Long enrolledParticipantsId) {
        this.enrolledParticipantsId = enrolledParticipantsId;
    }

    public Long getCoursePeriodId() {
        return coursePeriodId;
    }

    public void setCoursePeriodId(Long coursePeriodId) {
        this.coursePeriodId = coursePeriodId;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }
}
