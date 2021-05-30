package com.webAppCard.Utilisateur;


import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.webAppCard.Utilisateur.Utilisateur;
import com.webAppCard.Utilisateur.UtilisateurRepository;


@RunWith(SpringRunner.class)
@WebMvcTest(value = UtilisateurService.class)
public class UtilisateurServiceTest {

	@Autowired
	private UtilisateurService uService;

	@MockBean
	private UtilisateurRepository uRepository;

	Utilisateur tmpUser=new Utilisateur("User","Pwd","Surname");
	
	@Before
	public void setUp() {
		System.out.println("[BEFORE TEST Serivce]");
	}
	
	@After
	public void cleanUp() {
		System.out.println("[AFTER TEST Service]");
	}
	
	@Test
	public void initCollec() {
		uService.initCollec(tmpUser);
		assertTrue(tmpUser.getCollectionSize()==3);
	}

	@Test
	public void getUtilisateurId() {
		Mockito.when(
				uRepository.findById(Mockito.anyInt())
				).thenReturn(Optional.ofNullable(tmpUser));
		Utilisateur userInfo=uService.getUserById(1);
		assertTrue(userInfo.getName().equals(tmpUser.getName()));
	}
	
	@Test
	public void getUtilisateurName() {
		Mockito.when(
				uRepository.findByName(Mockito.any())
				).thenReturn(Optional.ofNullable(tmpUser));
		Utilisateur userInfo=uService.getUserByName("User");
		assertTrue(userInfo.getName().equals(tmpUser.getName()));
	}

	@Test
	public void getAllUsers() {
		List<Utilisateur> LUser = new ArrayList<>();
		LUser.add(tmpUser);
		Mockito.when(
				uRepository.findAll()
				).thenReturn(LUser);
		List<Utilisateur> LUserInfo=uService.getAllUsers();
		assertTrue(LUserInfo.size()==1);		
	}
	
	@Test
	public void verifUser() {
		Mockito.when(
				uRepository.findByName(Mockito.any())
				).thenReturn(Optional.ofNullable(tmpUser));
		assertTrue(uService.verifUser("User","Pwd"));
	}
	
	@Test
	public void addCard() {
		Mockito.when(
				uRepository.findById(Mockito.anyInt())
				).thenReturn(Optional.ofNullable(tmpUser));
		uService.addCard(32, 4);
		System.out.println(tmpUser.getCollection());
		assertTrue(true);
	}
	
	@Test
	public void getMoney() {
		Mockito.when(
				uRepository.findById(Mockito.anyInt())
				).thenReturn(Optional.ofNullable(tmpUser));
		assertTrue(uService.getMoney(27)==0);
	}
	
	@Test
	public void updateMoney() {
		Mockito.when(
				uRepository.findById(Mockito.anyInt())
				).thenReturn(Optional.ofNullable(tmpUser));
		uService.updateMoney(44, 500);
		assertTrue(tmpUser.getMoney()==500);
	}
	

}