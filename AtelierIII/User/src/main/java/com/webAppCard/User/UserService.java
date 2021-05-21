package com.webAppCard.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class UserService {
	
	@Autowired
	UserRepository uRepository;
	
	private final RestTemplate restTemplate;

//////////////////////////////////////
// Rest template
//////////////////////////////////////
	
	public UserService(RestTemplateBuilder restTemplateBuilder) { // Gestion du rest template
        this.restTemplate = restTemplateBuilder.build();
    }
	
//////////////////////////////////////
// User Init
//////////////////////////////////////
	
	public void addUser(String name, String pwd, String surname) { // Cr�er un user s'il n'existe pas d�j�
		Optional<User> oUser=uRepository.findByName(name);
		if(!oUser.isPresent()) {
			User newUser=new User(name, pwd, surname);
			initCollec(newUser);
			updateMoney(500,newUser.getId());
			uRepository.save(newUser);
		}
	}
	
	public void initCollec(User u) { //Une collection est assign� � un user quand il est cr��
		int[] array = {0,1,2};	
		for (int i : array) {u.addCard(i, u.getId());}		
		}
	
	public void randomInitCollec(User u) { //fonction potentiellement utile pour attribuer des cartes al�atoirement
		Random rng = new Random(); // Ideally just create one instance globally
		int numbersNeeded = 5;
		int max=20;
		while (u.getCollectionSize()< numbersNeeded)
		{
		    Integer next = rng.nextInt(max) + 1;
		    // As we're adding to a set, this will automatically do a containment check
		    u.addCard(next, u.getId());
		}
	}

//////////////////////////////////////
// Get User
//////////////////////////////////////
	
	public User getUserById(int idUser) { // renvoie l'utilistauer, s'il existe, gr�ce � son id
		User res = null;
		Optional<User> oUser=uRepository.findById(idUser);
		if(oUser.isPresent()) {
			res = oUser.get();
		}
		return res;
	}

	public User getUserByName(String name) { // renvoie l'utilistauer, s'il existe, gr�ce � son id
		User res=null;
		Optional<User> oUser=uRepository.findByName(name);
		if (oUser.isPresent()) {
			res=oUser.get();
		}
		return res;
	}
	
	public List<User> getAllUsers(){ //retourne tout les utilisateurs
		List<User> LUser;
		LUser=uRepository.findAll();
		return LUser;
	}
	
//////////////////////////////////////
// Delete existence
//////////////////////////////////////
	
	public void deleteUser(User u) { // supprime un utilisateur
		uRepository.delete(u);
	}
	

//////////////////////////////////////
// Verification existence
//////////////////////////////////////
	
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
	
//////////////////////////////////////
// Cards
//////////////////////////////////////	
	
	public void addCard(int idCard, int idUser) {// rajoute une carte dans la collection de l'utilisateur
		User u=getUserById(idUser);
		int energy = this.restTemplate.getForObject("/Card/Energy/{id}",Integer.class, idCard);
		u.addCard(idCard, energy);
		uRepository.save(getUserById(idUser));
	}
	
	public List<Integer> getCollection(int idUser) {
		Optional<User> oUser=uRepository.findById(idUser);
		List<Integer> List = new ArrayList<Integer>();
		if (oUser.isPresent()) {
			User u = oUser.get();
			for (pseudoCard pC:u.getCollection()) {
				List.add(pC.getIdCard());
			}
		}
		return List;
	}

//////////////////////////////////////
// Money
//////////////////////////////////////
	
	public int getMoney(int idUser) {
		int res=0;
		Optional<User> oUser=uRepository.findById(idUser);
		if (oUser.isPresent()) {
			User u = oUser.get();
			res = u.getMoney();
		}
		return res;
	}
	
	public void updateMoney(int idUser, int nb) { // change la valeur d'argent de l'utilisateur
		User u=getUserById(idUser);
		int money=u.getMoney();
		money+=nb;
		u.setMoney(money);
		uRepository.save(getUserById(idUser));
	}
	
//////////////////////////////////////
// Energy
//////////////////////////////////////

	
	public int getEnergy(int idUser, int idCard) {
		Optional<User> oUser=uRepository.findById(idUser);
		int res=-1;
		if (oUser.isPresent()) {
			User u=oUser.get();
			for (pseudoCard pC:u.getCollection()) {
				if (pC.getIdCard()==idCard) {
					res=pC.getEnergy();
				}
			}						
		}	
		return res;	
	}
	
	public void updateEnergy(int idUser, int idCard) {
		int Effort=10;
		Optional<User> oUser=uRepository.findById(idUser);
		if (oUser.isPresent()) {
			User u = oUser.get();			
			for (pseudoCard pC:u.getCollection()) {
				if (pC.getIdCard()==idCard) {
					pC.setEnergy(pC.getEnergy()-Effort);
				}
			}
		}
	}
}