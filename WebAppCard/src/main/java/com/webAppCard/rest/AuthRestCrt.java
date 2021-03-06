package com.webAppCard.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.webAppCard.service.AuthService;

@RestController
public class AuthRestCrt {
	
	@Autowired
	AuthService aService;
	
	@RequestMapping(method=RequestMethod.POST,value="/login/{user}/{pwd}")
	public boolean isUser(@PathVariable String user, @PathVariable String pwd) {
		return aService.verifUser(user, pwd);
	}


}
