/*
 * @(#) Grade.java, v 1.0 2017/09/13 11:23:10
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
public class Grade {

    @OneToMany(mappedBy = "grade")
    private List<EmployeeData> employeeDatas;
    @Id
    @GeneratedValue
    private Integer gradeId; //PK
    private String jobFamily;
    private String grade;

    public List<EmployeeData> getEmployeeDatas() {
        return employeeDatas;
    }

    public void setEmployeeDatas(List<EmployeeData> employeeDatas) {
        this.employeeDatas = employeeDatas;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getJobFamily() {
        return jobFamily;
    }

    public void setJobFamily(String jobFamily) {
        this.jobFamily = jobFamily;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
