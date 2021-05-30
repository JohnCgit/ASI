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
public class UtilisateurServTest {

	@Autowired
	private UtilisateurService uService;

	@MockBean
	private UtilisateurRepository uRepo;
	
	Utilisateur tmpUser=new Utilisateur("User","Pwd","Surname");
	
	@Test
	public void getHero() {
		Mockito.when(
				uRepo.findById(Mockito.any())
				).thenReturn(Optional.ofNullable(tmpUser));
		Utilisateur userInfo=uService.getUserByName("User");
		assertTrue(userInfo.toString().equals(tmpUser.toString()));
	}
}
