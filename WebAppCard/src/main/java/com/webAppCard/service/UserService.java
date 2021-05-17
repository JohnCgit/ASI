package com.webAppCard.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webAppCard.model.User;
import com.webAppCard.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository uRepository;
	
	public boolean addUser(String name, String pwd, String surname) { // Créer un user s'il n'existe pas déjà
		boolean res=false;
		Optional<User> oUser=uRepository.findByName(name);
		if(!oUser.isPresent()) {
			User newUser=new User(name, pwd, surname);
			initCollec(newUser);
			uRepository.save(newUser);
			updateMoney(500,newUser.getId());
			res= true;
		}
		return res;
	}
	
	public void addCard(int idCard, int idUser) {// rajoute une carte dans la collection de l'utilisateur
		User u=getUserById(idUser);
		u.addCard(idCard);
		uRepository.save(getUserById(idUser));
		}
	
	public void initCollec(User u) { //Une collection est assigné à un user quand il est créé
		int[] array = {0,1,2,3,4};	
		for (int i : array) {u.addCard(i);}		
		}
	
	public void randomInitCollec(User u) { //fonction potentiellement utile pour attribuer des cartes aléatoirement
		Random rng = new Random(); // Ideally just create one instance globally
		int numbersNeeded = 5;
		int max=20;
		while (u.getCollectionSize()< numbersNeeded)
		{
		    Integer next = rng.nextInt(max) + 1;
		    // As we're adding to a set, this will automatically do a containment check
		    u.addCard(next);
		}
	}
	
	public void updateMoney(int nb, int idUser) { // change la valeur d'argent de l'utilisateur
		User u=getUserById(idUser);
		int money=u.getMoney();
		money+=nb;
		u.setMoney(money);
		uRepository.save(getUserById(idUser));
	}

	public User getUserById(int idUser) { // renvoie l'utilistauer, s'il existe, grâce à son id
		User res = null;
		Optional<User> oUser=uRepository.findById(idUser);
		if(oUser.isPresent()) {
			res = oUser.get();
		}
		return res;
	}

	public User getUserByName(String name) { // renvoie l'utilistauer, s'il existe, grâce à son id
		User res=null;
		Optional<User> oUser=uRepository.findByName(name);
		if (oUser.isPresent()) {
			res=oUser.get();
		}
		return res;
	}
	
	public boolean verifUser(String username, String password) { 	// renvoie true si l'utilisateur existe, 
																	//et si le mot de passe est bon
		boolean res=false;
		Optional<User> oUser=uRepository.findByName(username);
		if (oUser.isPresent()) {
			User u=oUser.get();
			if (u.getPassword().equals(password)) {
				res=true;
			}
		}
		return res;		
	}
	
	public void deleteUser(User u) { // supprime un utilisateur
		uRepository.delete(u);
	}
	
	public List<User> getAllUsers(){ //retourne tout les utilisateurs
		List<User> LUser;
		LUser=uRepository.findAll();
		return LUser;
	}
}
