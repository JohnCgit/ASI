package com.webAppCard.Utilisateur;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UtilisateurTest {
  
  private List<String> stringList;

  @Autowired
  UtilisateurService uService;
  
  @Before
  public void setUp() {
    System.out.println("[BEFORE TEST] -- Add user to test");
    stringList = new ArrayList<String>();
    stringList.add("User1");
    stringList.add("User2");
    stringList.add("User3");
  }

  @After
  public void tearDown() {
    System.out.println("[AFTER TEST] -- CLEAN user list");
    stringList = null;
  }

  @Test
  public void createUtilisateurTest() {
    for(String msg:stringList) {
      String msg2=stringList.get(1);
      String msg3=stringList.get(0);
      Utilisateur u = new Utilisateur( msg, msg2, msg3);
      assertTrue(u.getName() == msg);
      assertTrue(u.getPassword() == msg2);
      assertTrue(u.getSurname() == msg3);
    }
  }

  @Test
  public void updateUtilisateurTest() {
	uService.addUser("User","Pwd","Surname");
    Utilisateur u = uService.getUserByName("User");
    assertTrue(u.getName() == "User"); // Test le constructeur
    assertTrue(u.getPassword() == "Pwd"); // Test le constructeur
    assertTrue(u.getSurname() == "Surname"); // Test le constructeur
    assertTrue(u.getMoney() == 500); // Test updateMoney
    Utilisateur v = uService.getUserById(u.getId());
    assertTrue(v.getId() == u.getId()); // Test getId
    Utilisateur w = uService.getUserByName(u.getName());
    assertTrue(w.getId() == u.getId());
    List<Integer> L_Card = new ArrayList<Integer>();
    L_Card.add(1);
    L_Card.add(2);
    L_Card.add(3);
    assertTrue(u.getCollection() == L_Card); // Test initCollec & addCard
    L_Card.remove(1);
    uService.removeCard(u.getId(), 1);
    assertTrue(u.getCollection() == L_Card); //Test removeCard
  }
}
