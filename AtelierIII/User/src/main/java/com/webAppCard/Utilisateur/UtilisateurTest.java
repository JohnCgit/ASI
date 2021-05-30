package com.webAppCard.Utilisateur;

import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UtilisateurTest {
  
  private List<String> stringList;

  @Before
  public void setUp() {
    System.out.println("[BEFORE TEST] -- Add user to test");
    stringList = new ArrayList<String>();
    intList = new ArrayList<Integer>();
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
      msg2=stringList[1];
      msg3=StringList[0];
      Utilisateur u = new Utilisateur( msg, msg2, msg3);
      assertTrue(u.getName() == msg);
      assertTrue(u.getPassword() == msg2);
      assertTrue(u.getSurname() == msg3);
    }
  }

  @Test
  public void updateUtilisateurTest() {
    addUser("User","Pwd","Surname");
    Utilisateur u = getUserByName("User");
    assertTrue(u.getName() == "User"); // Test le constructeur
    assertTrue(u.getPassword() == "Pwd"); // Test le constructeur
    assertTrue(u.getSurname() == "Surname"); // Test le constructeur
    assertTrue(u.getMoney() == 500); // Test updateMoney
    Utilisateur v = getUserById(u.getId());
    assertTrue(v.getId() == u.getId()) // Test getId
    Utilisateur w = getUserByName();
    assertTrue(w.getId() == u.getId());
    List<Integer> L_Card = new ArrayList<Integer>(1,2,3);
    assertTrue(u.getCollection == L_Card); // Test initCollec & addCard
    L_Card.remove(1);
    removeCard(u.getId(), 1);
    assertTrue(u.getCollection == L_Card); //Test removeCard
  }
}
