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


/**
 * Class Description
 * 
 */
@Entity
public class TrainingAchievement {
    
    @Id
    @GeneratedValue
    private int trainingAchievementId; //PK
    private int courseNameId; //FK = CourseName
    private int courseParticipantsId; //FK = CourseParticipants
    private int achievement;

    public int getTrainingAchievementId() {
        return trainingAchievementId;
    }

    public int getCourseNameId() {
        return courseNameId;
    }

    public void setCourseNameId(int courseNameId) {
        this.courseNameId = courseNameId;
    }

    public int getCourseParticipantsId() {
        return courseParticipantsId;
    }

    public void setCourseParticipantsId(int courseParticipantsId) {
        this.courseParticipantsId = courseParticipantsId;
    }

    public int getAchievement() {
        return achievement;
    }

    public void setAchievement(int achievement) {
        this.achievement = achievement;
    }
}
