package com.webAppCard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
	@Service
	public class AuthService {
	
	@Autowired
	UserService uService;
	
	public boolean verifUser(String username, String password) {
		return uService.verifUser(username, password);
	}

}