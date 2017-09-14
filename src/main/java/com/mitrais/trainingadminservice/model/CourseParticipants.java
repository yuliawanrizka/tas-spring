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

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


/**
 * Class Description
 * 
 */
@Entity
public class CourseParticipants {

    @OneToMany(mappedBy = "courseParticipants")
    private List<TrainingAchievement> trainingAchievements;

    @OneToMany(mappedBy = "courseParticipants")
    private List<Attendance> attendances;
    
    @Id
    @GeneratedValue
    private Integer courseParticipantsId; //PK
    @ManyToOne
    private UserRole userRole;
    @ManyToOne
    private Course course;

    public List<TrainingAchievement> getTrainingAchievements() {
        return trainingAchievements;
    }

    public void setTrainingAchievements(List<TrainingAchievement> trainingAchievements) {
        this.trainingAchievements = trainingAchievements;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public Integer getCourseParticipantsId() {
        return courseParticipantsId;
    }

    public void setCourseParticipantsId(Integer courseParticipantsId) {
        this.courseParticipantsId = courseParticipantsId;
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
}
