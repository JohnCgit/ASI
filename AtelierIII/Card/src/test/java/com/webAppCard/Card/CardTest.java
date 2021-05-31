package com.webAppCard.Card;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CardTest {
	private List<String> stringList;
	private List<Integer> intList;
	private List<Affinity> affinityList;
	
	@Autowired
	CardServiceTest cService;

    @Before
  	public void setUp() {
	    System.out.println("[BEFORE TEST] -- Add card to test");
	    stringList = new ArrayList<String>();
	    stringList.add("Salamaiche");
	    stringList.add("Lezard");
	    stringList.add("https://i.imgur.com/szoTQIA.png");
		intList = new ArrayList<Integer>();
	    intList.add(40);
	    intList.add(60);
	    intList.add(10);
	    intList.add(200);
		affinityList = new ArrayList<Affinity>();
		affinityList.add(Affinity.EAU); //IMPOSSIBLE DE SELECTIONNER UNE AFFINITE DE TYPE AFFINITE
  }

    @After
    public void tearDown() {
		System.out.println("[AFTER TEST] -- CLEAN card list");
		stringList = null;
    }

    @Test
    public void createCardTest() {
    	String info1 = stringList.get(0);
	    String info2 = stringList.get(1);
	    Affinity info3 = affinityList.get(0);
	    Integer info4 = intList.get(0);
	    Integer info5 = intList.get(1);
	    Integer info6 = intList.get(2);
	    Integer info7 = intList.get(3);
	    String info8=stringList.get(2);
	    Card c = new Card(info1,info2,info3,info4,info5,info6,info7,info8);
	    assertTrue(c.getName() == info1);
	    assertTrue(c.getFamily() == info2);
	    assertTrue(c.getAffinity() == info3);
	    assertTrue(c.getEnergy() == info4);
	    assertTrue(c.getHP() == info5);
	    assertTrue(c.getStrength() == info6);
	    assertTrue(c.getPrice() == info7);
	    assertTrue(c.getImgUrl() == info8);
    }
}
