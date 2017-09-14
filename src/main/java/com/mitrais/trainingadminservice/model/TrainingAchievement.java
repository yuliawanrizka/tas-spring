/*
 * @(#) TrainingAchievement.java, v 1.0 2017/09/13 13:36:28
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
public class TrainingAchievement {
    
    @Id
    @GeneratedValue
    private Integer trainingAchievementId; //PK
    @ManyToOne
    private CourseName courseName;
    @ManyToOne
    private CourseParticipants courseParticipants;
    private int achievement;

    public Integer getTrainingAchievementId() {
        return trainingAchievementId;
    }

    public void setTrainingAchievementId(Integer trainingAchievementId) {
        this.trainingAchievementId = trainingAchievementId;
    }

    public CourseName getCourseName() {
        return courseName;
    }

    public void setCourseName(CourseName courseName) {
        this.courseName = courseName;
    }

    public CourseParticipants getCourseParticipants() {
        return courseParticipants;
    }

    public void setCourseParticipants(CourseParticipants courseParticipants) {
        this.courseParticipants = courseParticipants;
    }

    public int getAchievement() {
        return achievement;
    }

    public void setAchievement(int achievement) {
        this.achievement = achievement;
    }
}
