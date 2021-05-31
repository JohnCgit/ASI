package com.WebAppCard.Game;


import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@WebMvcTest(value = GameService.class)
public class GameServiceTest {
	private List<Integer> idCard;
	
	Card tmpCard = new Card("name", "family", Affinity.EAU, 1, 1, 1, 1, "http://oui.com");

	@Autowired
	private GameService gService;

	@Before
	public void setUp() {
	  idCard = new ArrayList<Integer>();
	  idCard.add(17);
	  idCard.add(18);
	  	}
	
	@Test
	public void matchupAffinityTest() {
	
	Affinity aff1 = Affinity.EAU;
	Affinity aff2 = Affinity.PLANTE;
	Affinity aff3 = Affinity.FEU;
	
	assertTrue(gService.matchupAffinity(aff1,aff1)==2);  //Egalité
	assertTrue(gService.matchupAffinity(aff1,aff2)==1);  //Eau battue par Plante
	assertTrue(gService.matchupAffinity(aff1,aff3)==3);  //Eau bat Feu
	
	assertTrue(gService.matchupAffinity(aff2,aff1)==3);  //Plante bat Eau
	assertTrue(gService.matchupAffinity(aff2,aff2)==2);  //Egalité
	assertTrue(gService.matchupAffinity(aff2,aff3)==1);  //Plante battue par Feu
	
	assertTrue(gService.matchupAffinity(aff3,aff1)==1);  //Feu battu par Eau
	assertTrue(gService.matchupAffinity(aff3,aff2)==3);  //Feu bat Plante
	assertTrue(gService.matchupAffinity(aff3,aff3)==2);  //Egalité
	}
	
//	@Test
//	public void getCardTest() throws URISyntaxException  {
//		URI uri = new URI(url);
//
//		Mockito.when(
//				resttemplate.getForObject("http://127.0.0.1:8082/card/62", Card.class)
//				).thenReturn(tmpCard);
//
//	  Card res = gService.getCard(62);
//	  assertTrue(res instanceof Card);	//on retourne bien un élément Card
//	  assertTrue(res.getId() == tmpCard.getId());	//c'est la bonne carte
//	}
	
//	@Test
//	public void Jeu() {
//		Mockito.when(
//				gService.getCard(Mockito.anyInt())
//				).thenReturn(tmpCard);
//		String result = gService.Jeu(56,84);
//		if (result.contains("HP1:-")&&result.contains("HP2:-")) {
//			assertTrue(result.contains("End:0"));
//		}		
//		if (result.contains("HP1:-")) {
//			assertTrue(result.contains("End:2"));
//		}		
//		if (result.contains("HP2:-")) {
//			assertTrue(result.contains("End:1"));
//		}
//	}

}	