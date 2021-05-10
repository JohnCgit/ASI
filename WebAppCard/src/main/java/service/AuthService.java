package service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {
	
	public void addUser(User h) {
		User createdUser=hRepository.save(h);
		System.out.println("Bienvenue" +createdUser);
	}

}