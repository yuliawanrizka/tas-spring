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

import java.time.Clock;
import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * Class Description
 * 
 */
@Entity
public class Token {
    @Id
    private String token; //PK
    private int employeeId; //FK = Employee
    private Clock expiry;
    private boolean rememberMe;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Clock getExpiry() {
        return expiry;
    }

    public void setExpiry(Clock expiry) {
        this.expiry = expiry;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
