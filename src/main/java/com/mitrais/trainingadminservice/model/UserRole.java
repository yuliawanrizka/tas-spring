/*
 * @(#) UserRole.java, v 1.0 2017/09/13 13:44:44
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


/**
 * Class Description
 * 
 */
@Entity
public class UserRole {

    @Id
    @GeneratedValue
    private int userRoleId; //PK
    private int employeeId; //FK = Employee
    private int roleId;//FK = Roles
}
