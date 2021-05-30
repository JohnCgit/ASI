package com.webAppCard.Utilisateur;


import static org.junit.Assert.assertTrue;

import java.util.Optional;

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
	private UtilisateurRepository urepo;
	
	Utilisateur tmpUser=new Utilisateur("User","Pwd","Surname");
	
	@Test
	public void getUtilisateurName() {
		Mockito.when(
				urepo.findByName(Mockito.any())
				).thenReturn(Optional.ofNullable(tmpUser));
		Utilisateur userInfo=uService.getUserByName("User");
		assertTrue(userInfo.getName().equals(tmpUser.getName()));
	}
	
	@Test
	public void getUtilisateurId() {
		Mockito.when(
				urepo.findById(Mockito.any())
				).thenReturn(Optional.ofNullable(tmpUser));
		Utilisateur userInfo=uService.getUserById(tmpUser.getId());
		assertTrue(userInfo.getName().equals(tmpUser.getName()));
	}
	
	@Test
	public void initCollec() {
		uService.initCollec(tmpUser);
		assertTrue(tmpUser.getCollectionSize()==3);
	}
	
	@Test
	public void verifUser() {
		Mockito.when(
				urepo.findByName(Mockito.any())
				).thenReturn(Optional.ofNullable(tmpUser));
		assertTrue(uService.verifUser("User","Pwd"));
	}
	
	@Test
	public void addCard() {
		
	}
}