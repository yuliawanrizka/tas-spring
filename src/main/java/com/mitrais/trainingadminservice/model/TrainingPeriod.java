/*
 * @(#) TrainingPeriod.java, v 1.0 2017/09/19 14:14:34
 * 
 * Copyright (c) 2017, PT. Mitrais, Bali, Indonesia.
 * All rights reserved.
 * 
 * Revision History
 * 
 * 19-Sep-2017 Yuliawan Rizka Syafaat             [1.0]-Initial Coding
 * 
 */

package com.mitrais.trainingadminservice.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * Class Description
 * 
 */
@Entity
public class TrainingPeriod {

    @Id
    @GeneratedValue
    private Long trainingPeriodId;
    private String trainingName;
    private boolean active;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long creatorId;
    private LocalDateTime createdAt;
    private Long updaterId;
    private LocalDateTime updatedAt;
}
