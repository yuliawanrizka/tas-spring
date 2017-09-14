/*
 * @(#) Location.java, v 1.0 2017/09/13 11:50:13
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
public class Location {

    @OneToMany(mappedBy = "location")
    private List<EmployeeData> employeeDatas;

    @OneToMany(mappedBy = "location")
    private List<Classroom> classrooms;
    @Id
    @GeneratedValue
    private Integer locationId; //PK
    private String cityName;

    public List<EmployeeData> getEmployeeDatas() {
        return employeeDatas;
    }

    public void setEmployeeDatas(List<EmployeeData> employeeDatas) {
        this.employeeDatas = employeeDatas;
    }

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
