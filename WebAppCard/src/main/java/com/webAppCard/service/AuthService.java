package com.webAppCard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
	@Service
	public class AuthService {
	
	@Autowired
	UserService uService;
	
	public void verifUser(String username, String password) {
		uService.verifUser(username, password);
	}

}