package com.WebAppCard.Game;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class GameTest {
private List<Integer> idCard;
private List<String> Affinities;

@org.aspectj.lang.annotation.Before
public void setUp() {
    idCard = new ArrayList<Integer>();
    Affinities = new ArrayList<String>();
    idCard.add(17);
    idCard.add(18);
    List<Affinity> Affinities = Arrays.asList(Affinity.values());
}

@org.aspectj.lang.annotation.After
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
    
    assertTrue(GameService.matchupAffinity(aff1,aff1)==2);  //Egalité
    assertTrue(GameService.matchupAffinity(aff1,aff2)==1);  //Eau battue par Plante
    assertTrue(GameService.matchupAffinity(aff1,aff3)==3);  //Eau bat Feu
    
    assertTrue(GameService.matchupAffinity(aff2,aff1)==3);  //Plante bat Eau
    assertTrue(GameService.matchupAffinity(aff2,aff2)==2);  //Egalité
    assertTrue(GameService.matchupAffinity(aff2,aff3)==1);  //Plante battue par Feu

    assertTrue(GameService.matchupAffinity(aff3,aff1)==1);  //Feu battu par Eau
    assertTrue(GameService.matchupAffinity(aff3,aff2)==3);  //Feu bat Plante
    assertTrue(GameService.matchupAffinity(aff3,aff3)==2);  //Egalité
}
	
@Test
public void JeuTest() {
	
    int id1;
    int id2;
    Card res1 = getCard(id1);
    Card res2 = getCard(id2);
    
    
}
	
}
