package com.webAppCard.Game;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameTest {
private List<Integer> idCard;
private List<String> Affinities;

@Before
public void setUp() {
    idCard = new ArrayList<Integer>();
    Affinities = new ArrayList<String>();
    idCard.add(17);
    idCard.add(18);
    Affinities.add(Affinity.EAU)
    Affinities.add(Affinity.PLANTE)
    Affinities.add(Affinity.FEU)
}

@After
public void tearDown() {
    idCard = null;
    Affinities = null;
}

@Test
public void getCardTest() {
    int id;
    res = getCard(id);
    assertTrue(res.isInstance(Card) && res.id == id);
}
  
@Test
public void matchupAffinityTest() {
    
    aff1 = Affinities[0];
    aff2 = Affinities[1];
    aff3 = Affinities[2];
    
    assertTrue(matchupAffinity(aff1,aff1) == 2);  //Egalité
    assertTrue(matchupAffinity(aff1,aff2) == 1);  //Eau battue par Plante
    assertTrue(matchupAffinity(aff1,aff3) == 3);  //Eau bat Feu
    
    assertTrue(matchupAffinity(aff2,aff1) == 3);  //Plante bat Eau
    assertTrue(matchupAffinity(aff2,aff2) == 2);  //Egalité
    assertTrue(matchupAffinity(aff2,aff3) == 1);  //Plante battue par Feu

    assertTrue(matchupAffinity(aff3,aff1) == 1);  //Feu battu par Eau
    assertTrue(matchupAffinity(aff3,aff2) == 3);  //Feu bat Plante
    assertTrue(matchupAffinity(aff3,aff3) == 2);  //Egalité
	}
	
}
