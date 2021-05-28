package com.webAppCard.Utilisateur;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class UtilisateurService {
	
	@Autowired
	UtilisateurRepository uRepository;
	
	private final RestTemplate restTemplate;

//////////////////////////////////////
// Rest template
//////////////////////////////////////
	
	public UtilisateurService(RestTemplateBuilder restTemplateBuilder) { // Gestion du rest template
        this.restTemplate = restTemplateBuilder.build();
    }
	
//////////////////////////////////////
// User Init
//////////////////////////////////////
	
	public void addUser(String name, String pwd, String surname) { // Cr�er un user s'il n'existe pas d�j�
		Optional<Utilisateur> oUser=uRepository.findByName(name);
		System.out.println("iwasthere");
		if(!oUser.isPresent()) {
			Utilisateur newUser=new Utilisateur(name, pwd, surname);
			initCollec(newUser);
			newUser.setMoney(500);
			uRepository.save(newUser);
			//updateMoney(500,newUser.getId());
			//uRepository.save(newUser);
			System.out.println("iwasthere?");
		}
	}
	
	public void initCollec(Utilisateur u) { //Une collection est assign� � un user quand il est cr��
		int[] array = {1,2,3};	
		System.out.println("iamdone?");
		for (int i : array) {u.addCard(i);}		
		System.out.println("itseemsso?");
		}
	
	public void randomInitCollec(Utilisateur u) { //fonction potentiellement utile pour attribuer des cartes al�atoirement
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

//////////////////////////////////////
// Get User
//////////////////////////////////////
	
	public Utilisateur getUserById(int idUser) { // renvoie l'utilistauer, s'il existe, gr�ce � son id
		Utilisateur res = null;
		Optional<Utilisateur> oUser=uRepository.findById(idUser);
		if(oUser.isPresent()) {
			res = oUser.get();
		}
		return res;
	}

	public Utilisateur getUserByName(String name) { // renvoie l'utilistauer, s'il existe, gr�ce � son id
		Utilisateur res=null;
		Optional<Utilisateur> oUser=uRepository.findByName(name);
		if (oUser.isPresent()) {
			res=oUser.get();
		}
		return res;
	}
	
	public List<Utilisateur> getAllUsers(){ //retourne tout les utilisateurs
		List<Utilisateur> LUser;
		LUser=uRepository.findAll();
		return LUser;
	}
	
//////////////////////////////////////
// Delete existence
//////////////////////////////////////
	
	public void deleteUser(Utilisateur u) { // supprime un utilisateur
		uRepository.delete(u);
	}
	

//////////////////////////////////////
// Verification existence
//////////////////////////////////////
	
	public boolean verifUser(String username, String password) { 	// renvoie true si l'utilisateur existe, 
		//et si le mot de passe est bon
		boolean res=false;
		Optional<Utilisateur> oUser=uRepository.findByName(username);
		if (oUser.isPresent()) {
		Utilisateur u=oUser.get();
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
		Utilisateur u=getUserById(idUser);
//		int energy = this.restTemplate.getForObject("/Card/Energy/{id}",Integer.class, idCard);
		u.addCard(idCard);
		uRepository.save(getUserById(idUser));
	}
	
		public void removeCard(int idCard, int idUser) {// enleve une carte dans la collection de l'utilisateur
		Utilisateur u=getUserById(idUser);
		u.removeCard(idCard);
		uRepository.save(getUserById(idUser));
	}
	
	public List<Integer> getCollection(int idUser) {
		Optional<Utilisateur> oUser=uRepository.findById(idUser);
		List<Integer> List = new ArrayList<Integer>();
		if (oUser.isPresent()) {
			Utilisateur u = oUser.get();
			for (Integer Card:u.getCollection()) {
				List.add(Card);
			}
		}
		return List;
	}

//////////////////////////////////////
// Money
//////////////////////////////////////
	
	public int getMoney(int idUser) {
		int res=0;
		Optional<Utilisateur> oUser=uRepository.findById(idUser);
		if (oUser.isPresent()) {
			Utilisateur u = oUser.get();
			res = u.getMoney();
		}
		return res;
	}
	
	public void updateMoney(int idUser, int nb) { // change la valeur d'argent de l'utilisateur
		Utilisateur u=getUserById(idUser);
		int money=u.getMoney();
		money+=nb;
		u.setMoney(money);
		uRepository.save(u);
	}
	
//////////////////////////////////////
// Energy
//////////////////////////////////////

	
	
//	public int getEnergy(int idUser, int idCard) {
//		Optional<User>
//		oUser=uRepository.findById(idUser);
//		int res=-1; 
//		if (oUser.isPresent()) {
//			User u=oUser.get(); 
//			for (pseudoCard pC:u.getCollection()) { 
//				if (pC.getIdCard()==idCard) {
//					res=pC.getEnergy(); 
//					} 
//				} 
//			} 
//		return res; 
//		}
	 
	
//	public void updateEnergy(int idUser, int idCard) {
//		int Effort=10;
//		Optional<User> oUser=uRepository.findById(idUser);
//		if (oUser.isPresent()) {
//			User u = oUser.get();			
//			for (pseudoCard pC:u.getCollection()) {
//				if (pC.getIdCard()==idCard) {
//					pC.setEnergy(pC.getEnergy()-Effort);
//				}
//			}
//		}
//	}
}
