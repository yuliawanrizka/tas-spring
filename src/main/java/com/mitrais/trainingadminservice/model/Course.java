/*
 * @(#) Course.java, v 1.0 2017/09/19 13:54:26
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
public class Course {
    @Id
    @GeneratedValue
    private Long courseId;
    private String courseName;
    private boolean bccRelated;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public boolean isBccRelated() {
        return bccRelated;
    }

    public void setBccRelated(boolean bccRelated) {
        this.bccRelated = bccRelated;
    }
}
