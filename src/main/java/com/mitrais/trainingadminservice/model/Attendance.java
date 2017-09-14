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
import javax.persistence.ManyToOne;


/**
 * Class Description
 * 
 */
@Entity
public class Attendance {
    
    @Id
    @GeneratedValue
    private Integer attendanceId; //PK
    @ManyToOne
    private CourseSchedule courseSchedule;
    @ManyToOne
    private CourseParticipants courseParticipants;
    private int status;

    public Integer getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Integer attendanceId) {
        this.attendanceId = attendanceId;
    }

    public CourseSchedule getCourseSchedule() {
        return courseSchedule;
    }

    public void setCourseSchedule(CourseSchedule courseSchedule) {
        this.courseSchedule = courseSchedule;
    }

    public CourseParticipants getCourseParticipants() {
        return courseParticipants;
    }

    public void setCourseParticipants(CourseParticipants courseParticipants) {
        this.courseParticipants = courseParticipants;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
