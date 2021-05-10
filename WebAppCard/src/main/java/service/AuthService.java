package service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {
	
	public void addUser(User u) {
		User createdUser=hRepository.save(u);
		return UserRepository.addUser(createdUser)
	}

}