/*
 * @(#) UserEditRequest.java, v 1.0 2017/09/29 14:15:01
 * 
 * Copyright (c) 2017, PT. Mitrais, Bali, Indonesia.
 * All rights reserved.
 * 
 * Revision History
 * 
 * 29-Sep-2017 Yuliawan Rizka Syafaat             [1.0]-Initial Coding
 * 
 */

package com.mitrais.trainingadminservice.request;

import java.util.List;


/**
 * Class Description
 * 
 */
public class UserEditRequest {

    private boolean active;
    private List<RoleList> role;

    public static class RoleList {
        private Long roleId;

        public Long getRoleId() {
            return roleId;
        }

        public void setRoleId(Long roleId) {
            this.roleId = roleId;
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<RoleList> getRole() {
        return role;
    }

    public void setRole(List<RoleList> role) {
        this.role = role;
    }

}
