/*
 * @(#) CourseName.java, v 1.0 2017/09/13 10:41:08
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
import javax.persistence.OneToMany;


/**
 * Class Description
 * 
 */
@Entity
public class CourseName {

    @OneToMany(mappedBy = "courseName")
    private List<TrainingAchievement> trainingAchievements;

    @OneToMany(mappedBy = "courseName")
    private List<Course> courses;
    
    @Id
    @GeneratedValue
    private Integer courseNameId; //PK
    private String courseName;

    public List<TrainingAchievement> getTrainingAchievements() {
        return trainingAchievements;
    }

    public void setTrainingAchievements(List<TrainingAchievement> trainingAchievements) {
        this.trainingAchievements = trainingAchievements;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Integer getCourseNameId() {
        return courseNameId;
    }

    public void setCourseNameId(Integer courseNameId) {
        this.courseNameId = courseNameId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
