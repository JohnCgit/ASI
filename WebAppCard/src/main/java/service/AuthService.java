package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import model.User;

	@Service
	public class AuthService {
	
	@Autowired
	UserService uService;
	
	public void verifUser(String username, String password) {
		uService.verifUser(username, password);
	}

}