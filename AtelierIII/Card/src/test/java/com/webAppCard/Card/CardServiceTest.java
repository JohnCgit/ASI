package com.webAppCard.Card;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.webAppCard.Card.Card;
import com.webAppCard.Card.CardRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CardService.class)
public class CardServiceTest {

	@Autowired
	private CardService cService;

	@MockBean
	private CardRepository cRepo;
	
	Card tmpCard=new Card("Salamaiche", "Lezard", null, 40, 60, 10, 200, "https://i.imgur.com/szoTQIA.png");
	
	@Test
	public void getCard() {
		Mockito.when(
				cRepo.findById(Mockito.any())
				).thenReturn(Optional.ofNullable(tmpCard));
		Card cardInfo=cService.getCard(45);
		assertTrue(cardInfo.toString().equals(tmpCard.toString()));
	}
	
	@Test
	public void createCard() {
		
	}
}
