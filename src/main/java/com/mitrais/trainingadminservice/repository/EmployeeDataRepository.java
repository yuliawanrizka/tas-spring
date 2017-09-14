/*
 * @(#) EmployeeDataRepository.java, v 1.0 2017/09/13 14:55:33
 * 
 * Copyright (c) 2017, PT. Mitrais, Bali, Indonesia.
 * All rights reserved.
 * 
 * Revision History
 * 
 * 13-Sep-2017 Yuliawan Rizka Syafaat             [1.0]-Initial Coding
 * 
 */

package com.mitrais.trainingadminservice.repository;

import com.mitrais.trainingadminservice.model.EmployeeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


/**
 * Interface Description
 * 
 */
@Component
public interface EmployeeDataRepository extends JpaRepository<EmployeeData, Integer> {
    EmployeeData findByAccountName(String AccountName);
}
