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
    Card res = getCard(id);
    assertTrue(res.isInstance(Card));	//on retourne bien un élément Card
    assertTrue(res.id.equals(id));	//c'est la bonne carte
}
  
@Test
public void matchupAffinityTest() {
    
    Affinity aff1 = Affinities.get(0);
    Affinity aff2 = Affinities.get(1);
    Affinity aff3 = Affinities.get(2);
    
    assertTrue(matchupAffinity(aff1,aff1).equals(2));  //Egalité
    assertTrue(matchupAffinity(aff1,aff2).equals(1));  //Eau battue par Plante
    assertTrue(matchupAffinity(aff1,aff3).equals(3));  //Eau bat Feu
    
    assertTrue(matchupAffinity(aff2,aff1).equals(3));  //Plante bat Eau
    assertTrue(matchupAffinity(aff2,aff2).equals(2));  //Egalité
    assertTrue(matchupAffinity(aff2,aff3).equals(1));  //Plante battue par Feu

    assertTrue(matchupAffinity(aff3,aff1).equals(1));  //Feu battu par Eau
    assertTrue(matchupAffinity(aff3,aff2).equals(3));  //Feu bat Plante
    assertTrue(matchupAffinity(aff3,aff3).equals(2));  //Egalité
}
	
@Test
public void JeuTest() {
	
    int id1;
    int id2;
    Card res1 = getCard(id1);
    Card res2 = getCard(id2);
    
    
}
	
}
