package com.webAppCard.Utilisateur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


//TODO Create method removeCard from collection 

@RestController
public class UtilisateurRestCrt {
	
	@Autowired
	UtilisateurService uService;
	// CardService cService;

//////////////////////////////////////
// GetUser
//////////////////////////////////////
	
	@RequestMapping(method=RequestMethod.GET, value="/id/{id}")
	public Utilisateur getUserById(@PathVariable int id){
		Utilisateur u=uService.getUserById(Integer.valueOf(id));
	    return u;
	}

	@RequestMapping(method=RequestMethod.GET, value="/name/{name}")
	public Utilisateur getUserByName(@PathVariable String name) {
		Utilisateur u=uService.getUserByName(name);
		System.out.println(u);
		return u;
	}

	@RequestMapping(method=RequestMethod.GET, value="/getAll")
	public List<Utilisateur> getAllUsers() {
		List<Utilisateur> LUser;
		LUser=uService.getAllUsers();
		return LUser;
	}
	
//////////////////////////////////////
// Modif User
//////////////////////////////////////
	
	@RequestMapping(method=RequestMethod.POST, value="/create/{name}/{surname}/{password}")
	public void  createUser(@PathVariable String name,@PathVariable String surname,@PathVariable String password) {
		System.out.print(name);
		uService.addUser(name, password, surname);
	}

	@RequestMapping(method=RequestMethod.POST, value="/delete/{name}")
	public void deleteUser(@PathVariable String name) {
		Utilisateur u = getUserByName(name);
		uService.deleteUser(u);
	}

//////////////////////////////////////
// Collection
//////////////////////////////////////
	
	@RequestMapping(method=RequestMethod.GET, value="/getCollection/{idUser}")
	public List<Integer> getCollection(@PathVariable int idUser) {
		List<Integer> res =uService.getCollection(idUser);
		return res;
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/removeCard/{idUser}/{idCard}")
	public void removeCard(@PathVariable int idUser,@PathVariable int idCard) {
		uService.removeCard(idCard, idUser);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/addCard/{idUser}/{idCard}")
	public void addCard(@PathVariable int idUser,@PathVariable int idCard) {
		uService.addCard(idCard, idUser);
	}
//////////////////////////////////////
// Money
//////////////////////////////////////
	
	@RequestMapping(method=RequestMethod.PUT, value="/UpdateMoney/{id}/{money}")
	public void UpdateMoney(@PathVariable Integer id, @PathVariable Integer money) {
		uService.updateMoney(id, money);
	}	
	
	@RequestMapping(method=RequestMethod.GET, value="/getMoney/{idUser}")
	public int getMoney(@PathVariable Integer idUser) {
		return uService.getMoney(idUser);
	}
	
//////////////////////////////////////
// Card
//////////////////////////////////////
	
//	@RequestMapping(method=RequestMethod.GET, value="/getEnergy/{idUser}/{idCard}")
//	public int GetEnergy(@PathVariable Integer idUser,@PathVariable Integer idCard) {
//		return uService.getEnergy(idUser, idCard);
//	}
//
//	
//	@RequestMapping(method=RequestMethod.GET, value="/UpdateEnergy/{idUser}/{idCard}")
//	public void updateEnergy(@PathVariable int idUser, @PathVariable int idCard) {
//		uService.updateEnergy(idUser, idCard);
//	}
//
	}
