package service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.User;
import repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository uRepository;
	public boolean addUser(String username, String pwd, String mail) {
		//if username/mail used, no adding the dude
		User newUser=new User(username, pwd, mail);
		initCollec(newUser);
		uRepository.save(newUser);
		return true;
	}
	
	public boolean addCard(int idCard, int idUser) {
		User u=getUser(idUser);
		// if user has card, then no adding it
		u.addCard(idCard);
		return true;
	}
	
	public void initCollec(User u) {
		int[] array = {0,1,2,3,4};	
		for (int i : array) {u.addCard(i);}		
		}
	
	public void randomInitCollec(User u) {
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
	
	public boolean updateMoney(int nb, int idUser) {
		User u=getUser(idUser);
		int money=u.getMoney();
		// if money not enough, no buying cards 
		money+=nb;
		u.setMoney(money);
		return true;
	}

	private User getUser(int idUser) {
		// TODO Auto-generated method stub
		return null;
	}

}

