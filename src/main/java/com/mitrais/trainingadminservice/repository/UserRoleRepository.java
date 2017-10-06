/*
 * @(#) UserRoleRepository.java, v 1.0 2017/09/15 10:23:59
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

import com.mitrais.trainingadminservice.model.UserRole;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


/**
 * Interface Description
 * 
 */
@Component
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findByEmployeeId(Long employeeId);
    List<UserRole> findByRoleId(Long roleId);
    UserRole findByEmployeeIdAndRoleId(Long employeeId, Long roleId);
}
