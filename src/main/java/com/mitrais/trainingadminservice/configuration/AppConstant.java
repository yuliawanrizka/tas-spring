/*
 * @(#) AppConstant.java, v 1.0 2017/09/22 15:38:14
 * 
 * Copyright (c) 2017, PT. Mitrais, Bali, Indonesia.
 * All rights reserved.
 * 
 * Revision History
 * 
 * 22-Sep-2017 Yuliawan Rizka Syafaat             [1.0]-Initial Coding
 * 
 */

package com.mitrais.trainingadminservice.configuration;


/**
 * Class Description
 * 
 */
public class AppConstant {

    //JWT CONFIGURATION

    /**
     * <b>CONSTANT VALUE :</b> JWT signature key
     */
    public static final String JWT_SIGNATURE_KEY = "3f0f203f52b2900e069f7865d9f256d3";
    
    //CORS CONFIGURATION

    /**
     *<b>CONSTANT VALUE :</b> CORS value for Access-Control-Allow-Origin
     */
    public static final String CORS_ALLOW_ORIGIN = "*";

    /**
     *<b>CONSTANT VALUE :</b> CORS value for Access-Control-Allow-Methods
     */
    public static final String CORS_ALLOW_METHODS = "POST, GET, OPTIONS, DELETE";

    /**
     *<b>CONSTANT VALUE :</b> CORS value for Access-Control-Max-Age
     */
    public static final String CORS_MAX_AGE = "3600";

    /**
     *<b>CONSTANT VALUE :</b> CORS value for Access-Control-Allow-Headers
     */
    public static final String CORS_ALLOW_HEADERS = "x-requested-with, authorization, charset, content-type";
    
    //REMEMBER ME CONFIGURATION
    
    /**
     *<b>CONSTANT VALUE :</b> Value if remember me is true, 3 Days.
     */
    public static final int REMEMBER_ME_TRUE_VALID_TIME = 3 * 24 * 60 * 60 * 1000;
    
    /**
     *<b>CONSTANT VALUE :</b> Value if remember me is true, 3 Hours.
     */
    public static final int REMEMBER_ME_FALSE_VALID_TIME = 3 * 60 * 60 * 1000;
}
