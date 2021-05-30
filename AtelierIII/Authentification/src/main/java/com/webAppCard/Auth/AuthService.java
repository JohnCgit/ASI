package com.webAppCard.Auth;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class AuthService {


	
		
	public boolean verifUser(String username, String password) {
		RestTemplate restTemplate = new RestTemplate();
		return  restTemplate.getForObject("http://127.0.0.1:8050/verif/"+username+"/"+password, Boolean.class);
	}
	


} 