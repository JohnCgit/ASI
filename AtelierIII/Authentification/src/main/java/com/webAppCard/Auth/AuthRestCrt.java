package com.webAppCard.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthRestCrt {
	
	@Autowired
	AuthService aService;
	
	@RequestMapping(method=RequestMethod.GET,value="/{user}/{pwd}")
	public boolean isUser(@PathVariable String user, @PathVariable String pwd) {
		System.out.print("here");
		return aService.verifUser(user, pwd);
	}
}
