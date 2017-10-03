/*
 * @(#) Attendance.java, v 1.0 2017/10/03 14:05:31
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
public class Attendance {
    @Id
    @GeneratedValue
    private Long attendanceId;
    private Long scheduleId;
    private Long enrolledParticipantsId;
    private int attendanceStatus;

    public Long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Long getEnrolledParticipantsId() {
        return enrolledParticipantsId;
    }

    public void setEnrolledParticipantsId(Long enrolledParticipantsId) {
        this.enrolledParticipantsId = enrolledParticipantsId;
    }

    public int getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(int attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
}
