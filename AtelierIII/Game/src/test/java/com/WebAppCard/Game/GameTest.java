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
private List<Affinity> Affinities;

@Autowired
private GameService gs;

@Before
public void setUp() {
    idCard = new ArrayList<Integer>();
    Affinities = new ArrayList<Affinity>();
    idCard.add(17);
    idCard.add(18);
    List<Affinity> Affinities = Arrays.asList(Affinity.values());
}

@After
public void tearDown() {
    idCard = null;
    Affinities = null;
}

//@Test
//public void getCardTest() {
//    int id;
//    Card res = getCard(id);
//    assertTrue(res.instanceof(Card));	//on retourne bien un élément Card
//    assertTrue(res.id.equals(id));	//c'est la bonne carte
//}
  
@Test
public void matchupAffinityTest() {
    
    Affinity aff1 = Affinity.EAU;
    Affinity aff2 = Affinity.PLANTE;
    Affinity aff3 = Affinity.FEU;
    
    assertTrue(gs.matchupAffinity(aff1,aff1)==2);  //Egalité
    assertTrue(gs.matchupAffinity(aff1,aff2)==1);  //Eau battue par Plante
    assertTrue(gs.matchupAffinity(aff1,aff3)==3);  //Eau bat Feu
    
    assertTrue(gs.matchupAffinity(aff2,aff1)==3);  //Plante bat Eau
    assertTrue(gs.matchupAffinity(aff2,aff2)==2);  //Egalité
    assertTrue(gs.matchupAffinity(aff2,aff3)==1);  //Plante battue par Feu

    assertTrue(gs.matchupAffinity(aff3,aff1)==1);  //Feu battu par Eau
    assertTrue(gs.matchupAffinity(aff3,aff2)==3);  //Feu bat Plante
    assertTrue(gs.matchupAffinity(aff3,aff3)==2);  //Egalité
}
	
@Test
public void JeuTest() {
    int id1=0;
    int id2=1;
    String res = gs.Jeu(id1,id2);
    assertTrue(res instanceof String);
    
     
}
	
}
