package com.webAppCard.Utilisateur;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepUtilisateurTest {

	@Autowired 
	UtilisateurRepository uRepo;
	
	@Before
	public void setUp() {
		System.out.println("[BEFORE TEST]");
		uRepo.save(new Utilisateur("User","Pwd","Surname")) ;
	}
	
	@After
	public void cleanUp() {
		System.out.println("[AFTER TEST]");
		uRepo.deleteAll();
	}

	@Test
    public void saveUtilisateur() {
        uRepo.save(new Utilisateur("test","test","test"));
        assertTrue(true);
    }

	@Test
	public void saveAndGetUtilisateur() {
		uRepo.deleteAll();
		uRepo.save(new Utilisateur("User1","Pwd1","Surname1"));
		List<Utilisateur> userList = new ArrayList<>();
		uRepo.findAll().forEach(userList::add);
		assertTrue(userList.size()==1);
		assertTrue(userList.get(0).getName()=="User1");
		assertTrue(userList.get(0).getPassword()=="Pwd1");
		assertTrue(userList.get(0).getSurname()=="Surname1");
	}
	
	@Test
	public void getUser() {
		List<Utilisateur> userList = new ArrayList<>();
		userList.add(uRepo.findByName("User").get());
		assertTrue(userList.size()==1);
		assertTrue(userList.get(0).getName().equals("User"));
		assertTrue(userList.get(0).getPassword().equals("Pwd"));
		assertTrue(userList.get(0).getSurname().equals("Surname"));
	}
	
	@Test
	public void findByName() {
		uRepo.save(new Utilisateur("Amine","echec","Berrada"));
		uRepo.save(new Utilisateur("Thomas","absent","Perreyron"));
		uRepo.save(new Utilisateur("Jean","John","Jaune"));
		uRepo.save(new Utilisateur("Sylvain","LeBG","Gehin"));
		List<Utilisateur> userList = new ArrayList<>();
		userList.add(uRepo.findByName("Thomas").get());
		assertTrue(userList.size()==1);
	}
}
