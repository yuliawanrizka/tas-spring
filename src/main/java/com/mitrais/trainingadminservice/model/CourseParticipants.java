/*
 * @(#) CourseParticipants.java, v 1.0 2017/09/13 10:46:25
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
public class CourseParticipants {
    
    @Id
    @GeneratedValue
    private Integer courseParticipantsId; //PK
    private int userRoleId; //FK = UserRole
    private int courseId; // FK = Course

    public int getCourseParticipantsId() {
        return courseParticipantsId;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
