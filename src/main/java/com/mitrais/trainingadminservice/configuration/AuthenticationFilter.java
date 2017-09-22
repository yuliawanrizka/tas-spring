/*
 * @(#) AuthenticationFilter.java, v 1.0 2017/09/15 14:27:38
 * 
 * Copyright (c) 2017, PT. Mitrais, Bali, Indonesia.
 * All rights reserved.
 * 
 * Revision History
 * 
 * 15-Sep-2017 Yuliawan Rizka Syafaat             [1.0]-Initial Coding
 * 
 */

package com.mitrais.trainingadminservice.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;


/**
 * Class Description
 * 
 */
public class AuthenticationFilter extends GenericFilterBean{
    
    //for implementaton, please change the key, also the key on AuthenticationController.java
    private final String signatureKey = "3f0f203f52b2900e069f7865d9f256d3";
    
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws ServletException, IOException, ExpiredJwtException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        final String authHeader = request.getHeader("authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ServletException("Missing or invalid Authorization header");
        }

        final String token = authHeader.substring(7);

        try {
            final Claims claims = Jwts.parser()
                                    .setSigningKey(signatureKey)
                                    .parseClaimsJws(token)
                                    .getBody();
            request.setAttribute("claims", claims);
        } catch (final SignatureException | ExpiredJwtException e) {
            throw new ServletException("Invalid or Expired Session");
        }
        
        chain.doFilter(req, res);
    }
}
