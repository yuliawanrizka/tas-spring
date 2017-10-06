/*
 * @(#) AchievementRepository.java, v 1.0 2017/10/04 16:59:35
 * 
 * Copyright (c) 2017, PT. Mitrais, Bali, Indonesia.
 * All rights reserved.
 * 
 * Revision History
 * 
 * 04-Oct-2017 Yuliawan Rizka Syafaat             [1.0]-Initial Coding
 * 
 */

package com.mitrais.trainingadminservice.repository;

import com.mitrais.trainingadminservice.model.Achievement;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


/**
 * Interface Description
 * 
 */
@Component
public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    List<Achievement> findByUserRoleId(Long userRoleId);
    Achievement findByUserRoleIdAndCourseId(Long userRoleId, Long courseId);
}
