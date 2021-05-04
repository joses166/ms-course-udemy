package com.udemy.hroauth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.hroauth.entities.User;
import com.udemy.hroauth.feignclients.UserFeignClient;

@Service
public class UserService {

	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserFeignClient feignClient;
	
	public User findByEmail(String email) {
		User user = this.feignClient.findByEmail(email).getBody();
		
		if (user == null) {
			log.error("Email not found: " + email);
			throw new IllegalArgumentException("Email not found.");
		}
		log.info("Email found: " + email);
		return user;
	}
	
}
