package com.webAppCard.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.webAppCard.model.Card;
import com.webAppCard.model.User;
import com.webAppCard.service.CardService;
import com.webAppCard.service.UserService;

@RestController
public class UserRestCtr {
	
	@Autowired
    UserService uService;
	CardService cService;
	
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
	
	@RequestMapping(method=RequestMethod.GET, value="/user/getAllCard/{idUser}")
	public List<Card> getAllCard(int idUser) {
		List<Card> res = new ArrayList<Card>();
		List<Integer> Li =uService.getUserById(idUser).getCollection();
		for(int id: Li) {
			res.add(cService.getCard(id));
		}
		return res;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/user/create/{name}/{surname}/{password}")
	public boolean  createUser(@PathVariable String name,@PathVariable String surname,@PathVariable String password) {
		return uService.addUser(name, password, surname);
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/user/delete/{name}")
	public void deleteUser(@PathVariable String name) {
		User u = getUserByName(name);
		uService.deleteUser(u);
	}
}
