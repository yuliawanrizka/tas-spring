package com.mitrais.trainingadminservice;

import com.mitrais.trainingadminservice.configuration.AuthenticationFilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrainingadminserviceApplication {
    
        @Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new AuthenticationFilter());
		registrationBean.addUrlPatterns("/api/secure/*");

		return registrationBean;
        }
	public static void main(String[] args) {
		SpringApplication.run(TrainingadminserviceApplication.class, args);
	}
}
