/*
 * @(#) EmployeeRepository.java, v 1.0 2017/09/15 10:21:38
 * 
 * Copyright (c) 2017, PT. Mitrais, Bali, Indonesia.
 * All rights reserved.
 * 
 * Revision History
 * 
 * 15-Sep-2017 Yuliawan Rizka Syafaat             [1.0]-Initial Coding
 * 
 */

package com.mitrais.trainingadminservice.repository;

import com.mitrais.trainingadminservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


/**
 * Interface Description
 * 
 */
@Component
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
