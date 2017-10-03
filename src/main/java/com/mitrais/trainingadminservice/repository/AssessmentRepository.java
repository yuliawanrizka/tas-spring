/*
 * @(#) AssessmentRepository.java, v 1.0 2017/10/03 14:14:33
 * 
 * Copyright (c) 2017, PT. Mitrais, Bali, Indonesia.
 * All rights reserved.
 * 
 * Revision History
 * 
 * 03-Oct-2017 Yuliawan Rizka Syafaat             [1.0]-Initial Coding
 * 
 */

package com.mitrais.trainingadminservice.repository;

import com.mitrais.trainingadminservice.model.Assessment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


/**
 * Interface Description
 * 
 */
@Component
public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
    List<Assessment> findByCoursePeriodId(Long coursePeriodId);
    Assessment findByCoursePeriodIdAndEnrolledParticipantsId(Long coursePeriodId, Long enrolledParticipantsId);
}
