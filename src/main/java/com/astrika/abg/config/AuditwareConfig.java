package com.astrika.abg.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import com.astrika.abg.model.User;
import com.astrika.abg.service.UserService;

@Configuration
public class AuditwareConfig implements AuditorAware<User> {
	
	Logger log = Logger.getLogger(AuditwareConfig.class);
	
	@Autowired
	private UserService userService;

	@Override
	public User getCurrentAuditor() {
		log.info("AuditwareConfig Reached");
		return userService.getLoggedInUser();
	}

	 
}