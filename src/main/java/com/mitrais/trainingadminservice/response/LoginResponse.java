/*
 * @(#) LoginResponse.java, v 1.0 2017/09/15 14:05:37
 * 
 * Copyright (c) 2017, PT. Mitrais, Bali, Indonesia.
 * All rights reserved.
 * 
 * Revision History
 * 
 * 15-Sep-2017 Yuliawan Rizka Syafaat             [1.0]-Initial Coding
 * 
 */

package com.mitrais.trainingadminservice.response;

import java.util.List;


/**
 * Class Description
 * 
 */
public class LoginResponse {
    private String fullName;
    private List<Long> role;
    private String token;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Long> getRole() {
        return role;
    }

    public void setRole(List<Long> role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
