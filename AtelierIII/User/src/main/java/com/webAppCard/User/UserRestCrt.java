package com.webAppCard.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class UserRestCrt {
	
	@Autowired
	UserService uService;
	// CardService cService;

//////////////////////////////////////
// GetUser
//////////////////////////////////////
	
	@RequestMapping(method=RequestMethod.GET, value="/user/id/{id}")
	public User getUserById(@PathVariable int id){
		User u=uService.getUserById(Integer.valueOf(id));
	    return u;
	}

	@RequestMapping(method=RequestMethod.GET, value="/user/name/{name}")
	public User getUserByName(@PathVariable String name) {
		User u=uService.getUserByName(name);
		System.out.println(u);
		return u;
	}

	@RequestMapping(method=RequestMethod.GET, value="/user/getAll")
	public List<User> getAllUsers() {
		List<User> LUser;
		LUser=uService.getAllUsers();
		return LUser;
	}
	
//////////////////////////////////////
// Modif User
//////////////////////////////////////
	
	@RequestMapping(method=RequestMethod.POST, value="/user/create/{name}/{surname}/{password}")
	public void  createUser(@PathVariable String name,@PathVariable String surname,@PathVariable String password) {
		uService.addUser(name, password, surname);
	}

	@RequestMapping(method=RequestMethod.POST, value="/user/delete/{name}")
	public void deleteUser(@PathVariable String name) {
		User u = getUserByName(name);
		uService.deleteUser(u);
	}

//////////////////////////////////////
// Collection
//////////////////////////////////////
	
	@RequestMapping(method=RequestMethod.GET, value="/user/getCollection/{idUser}")
	public List<Integer> getCollection(int idUser) {
		List<Integer> res =uService.getCollection(idUser);
		return res;
	}
	
//////////////////////////////////////
// Money
//////////////////////////////////////
	
	@RequestMapping(method=RequestMethod.GET, value="/user/UpdateMoney/{id}/{money}")
	public void UpdateMoney(@PathVariable Integer id, @PathVariable Integer money) {
		uService.updateMoney(id, money);
	}	
	
	@RequestMapping(method=RequestMethod.GET, value="user/getMoney/{idUser}")
	public int getMoney(@PathVariable Integer idUser) {
		return uService.getMoney(idUser);
	}
	
//////////////////////////////////////
// Card
//////////////////////////////////////
	
	@RequestMapping(method=RequestMethod.GET, value="user/getEnergy/{idUser}/{idCard}")
	public int GetEnergy(@PathVariable Integer idUser,@PathVariable Integer idCard) {
		return uService.getEnergy(idUser, idCard);
	}

	
	@RequestMapping(method=RequestMethod.GET, value="user/UpdateEnergy/{idUser}/{idCard}")
	public void updateEnergy(@PathVariable int idUser, @PathVariable int idCard) {
		uService.updateEnergy(idUser, idCard);
	}
}
