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
import javax.persistence.ManyToOne;


/**
 * Class Description
 * 
 */
@Entity
public class Trainer {
    @Id
    @GeneratedValue
    private Integer trainerId; //PK
    @ManyToOne
    private UserRole userRole;
    @ManyToOne
    private Course course;
    private int trainerRank;

    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getTrainerRank() {
        return trainerRank;
    }

    public void setTrainerRank(int trainerRank) {
        this.trainerRank = trainerRank;
    }
}
