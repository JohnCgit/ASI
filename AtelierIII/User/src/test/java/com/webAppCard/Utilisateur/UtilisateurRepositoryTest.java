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
import com.webAppCard.Utilisateur.Utilisateur;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UtilisateurRepositoryTest {
	
	 @Autowired
	    UtilisateurRepository urepo;

	    @Before
	    public void setUp() {
	    	System.out.println("[BEFORE TEST]");
	        urepo.save(new Utilisateur("User","Pwd","Surname"));
	    }

	    @After
	    public void cleanUp() {
	    	System.out.println("[AFTER TEST]");
	        urepo.deleteAll();
	    }

	    @Test
	    public void saveUtilisateur() {
	        urepo.save(new Utilisateur("User","Pwd","Surname"));
	        assertTrue(true);
	    }

	    @Test
	    public void saveAndGetUtilisateur() {
	        urepo.deleteAll();
	        urepo.save(new Utilisateur("User","Pwd","Surname"));
	        List<Utilisateur> userList = new ArrayList<>();
	        urepo.findAll().forEach(userList::add);
	        assertTrue(userList.size() == 1);
	        assertTrue(userList.get(0).getName().equals("User"));
	        assertTrue(userList.get(0).getPassword().equals("Pwd"));
	        assertTrue(userList.get(0).getSurname().equals("Surname"));
	    }

	    @Test
	    public void getUtilisateur() {
	        List<Utilisateur> userList=new ArrayList<>();
	        userList.add(urepo.findByName("User").get());
	        assertTrue(userList.size() == 1);
	        assertTrue(userList.get(0).getName().equals("User"));
	        assertTrue(userList.get(0).getPassword().equals("Pwd"));
	        assertTrue(userList.get(0).getSurname().equals("Surname"));
	    }

	    @Test
	    public void findByName() {
	        urepo.save(new Utilisateur("test1", "testpwd1", "oui"));
	        urepo.save(new Utilisateur("test2", "testpwd2", "oui"));
	        List<Utilisateur> userList = new ArrayList<>();
	        userList.add(urepo.findByName("test1").get());
	        assertTrue(userList.size()==1);
	    }
}



   
