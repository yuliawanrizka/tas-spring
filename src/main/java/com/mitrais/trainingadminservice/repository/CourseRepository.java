/*
 * @(#) CourseRepository.java, v 1.0 2017/09/19 14:22:36
 * 
 * Copyright (c) 2017, PT. Mitrais, Bali, Indonesia.
 * All rights reserved.
 * 
 * Revision History
 * 
 * 19-Sep-2017 Yuliawan Rizka Syafaat             [1.0]-Initial Coding
 * 
 */

package com.mitrais.trainingadminservice.repository;

import com.mitrais.trainingadminservice.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


/**
 * Interface Description
 * 
 */
@Component
public interface CourseRepository extends JpaRepository<Course, Long> {

}
