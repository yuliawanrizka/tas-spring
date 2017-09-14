/*
 * @(#) AuthenticationResponse.java, v 1.0 2017/09/14 09:19:28
 * 
 * Copyright (c) 2017, PT. Mitrais, Bali, Indonesia.
 * All rights reserved.
 * 
 * Revision History
 * 
 * 14-Sep-2017 Yuliawan Rizka Syafaat             [1.0]-Initial Coding
 * 
 */

package com.mitrais.trainingadminservice.response;


/**
 * Class Description
 * 
 */
public class AuthenticationResponse {
    private int id;
    private String username;
    private int[] role;
    private String token;

    public int[] getRole() {
        return role;
    }

    public void setRole(int[] role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
