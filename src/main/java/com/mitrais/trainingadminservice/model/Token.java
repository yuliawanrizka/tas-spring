/*
 * @(#) Token.java, v 1.0 2017/09/13 13:28:02
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

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;


/**
 * Class Description
 * 
 */
@Entity
public class Token {
    @Id
    private String token; //PK
    @OneToOne
    private EmployeeData employee;
    private Timestamp expiry;
    private boolean rememberMe;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public EmployeeData getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeData employee) {
        this.employee = employee;
    }

    public Timestamp getExpiry() {
        return expiry;
    }

    public void setExpiry(Timestamp expiry) {
        this.expiry = expiry;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
