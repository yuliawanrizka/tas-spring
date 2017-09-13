/*
 * @(#) Trainer.java, v 1.0 2017/09/13 13:33:45
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
public class Trainer {
    @Id
    @GeneratedValue
    private Integer trainerId; //PK
    private int userRoleId; //FK = UserRole
    private int courseId; //FK = Course
    private int trainerRank;

    public int getTrainerId() {
        return trainerId;
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

    public int getTrainerRank() {
        return trainerRank;
    }

    public void setTrainerRank(int trainerRank) {
        this.trainerRank = trainerRank;
    }
}
