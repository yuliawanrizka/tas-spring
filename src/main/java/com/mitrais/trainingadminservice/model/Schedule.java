/*
 * @(#) Schedule.java, v 1.0 2017/09/19 14:12:47
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

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * Class Description
 * 
 */
@Entity
public class Schedule {
    @Id
    @GeneratedValue
    private Long scheduleId;
    private Long coursePeriodId;
    private Date date;

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Long getCoursePeriodId() {
        return coursePeriodId;
    }

    public void setCoursePeriodId(Long coursePeriodId) {
        this.coursePeriodId = coursePeriodId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
