package rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestCtr {
	@RequestMapping(method=RequestMethod.GET, value="/user/getOne")
	public boolean getUser(){
		return true;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/user/getAll")
	public boolean getAllUsers() {
		return true;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/user/create")
	public boolean createUser() {
		return true;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/user/update")
	public boolean updateUser() {
		return true;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/user/delete")
	public boolean deleteUser() {
		return true;
	}
}
