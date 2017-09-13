/*
 * @(#) Attendance.java, v 1.0 2017/09/13 10:26:50
 * 
 * Copyright (c) 2017, PT. Mitrais, Bali, Indonesia.
 * All rights reserved.
 * 
 * Revision History
 * 
 * 13-Sep-2017 Yuliawan Rizka Syafaat             [1.0]-Initial Coding
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
    private int attendanceId; //PK
    private int courseScheduleId; //FK = CourseSchedule
    private int courseParticipantsId; //FK = CourseParticipants
    private int status;

    public int getAttendanceId() {
        return attendanceId;
    }

    public int getCourseScheduleId() {
        return courseScheduleId;
    }

    public void setCourseScheduleId(int courseScheduleId) {
        this.courseScheduleId = courseScheduleId;
    }

    public int getCourseParticipantsId() {
        return courseParticipantsId;
    }

    public void setCourseParticipantsId(int courseParticipantsId) {
        this.courseParticipantsId = courseParticipantsId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
