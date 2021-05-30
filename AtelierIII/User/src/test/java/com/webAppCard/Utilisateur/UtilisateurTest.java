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
  UtilisateurServiceTest uService;
  
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
}
