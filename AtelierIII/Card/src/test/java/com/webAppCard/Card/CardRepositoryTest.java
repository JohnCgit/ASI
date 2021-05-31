package com.webAppCard.Card;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.webAppCard.Card.Card;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CardRepositoryTest {

    @Autowired
    CardRepository crepo;

    @Before
    public void setUp() {
        crepo.save(new Card("Salamaiche", "Lezard", Affinity.FEU, 40, 60, 10, 200, "https://i.imgur.com/szoTQIA.png"));
    }

    @After
    public void cleanUp() {
        crepo.deleteAll();
    }

    @Test
    public void saveCard() {
        crepo.save(new Card("Salamaiche", "Lezard", Affinity.FEU, 40, 60, 10, 200, "https://i.imgur.com/szoTQIA.png"));
        assertTrue(true);
    }

    @Test
    public void saveAndGetCard() {
        crepo.deleteAll();
        crepo.save(new Card("Salamaiche", "Lezard", Affinity.FEU, 40, 60, 10, 200, "https://i.imgur.com/szoTQIA.png"));
        List<Card> cardList = new ArrayList<>();
        crepo.findAll().forEach(cardList::add);
        assertTrue(cardList.size() == 1);
        assertTrue(cardList.get(0).getName().equals("Salamaiche"));
        assertTrue(cardList.get(0).getFamily().equals("Lezard"));
        assertTrue(cardList.get(0).getAffinity() == Affinity.FEU);
        assertTrue(cardList.get(0).getEnergy() == 40);
        assertTrue(cardList.get(0).getHP() == 60);
        assertTrue(cardList.get(0).getStrength() == 10);
        assertTrue(cardList.get(0).getPrice() == 200);
        assertTrue(cardList.get(0).getImgUrl().equals("https://i.imgur.com/szoTQIA.png"));
    }

    @Test
    public void getCard() {
        List<Card> cardList = crepo.findByName("Salamaiche");
        assertTrue(cardList.size() == 1);
        assertTrue(cardList.get(0).getName().equals("Salamaiche"));
        assertTrue(cardList.get(0).getFamily().equals("Lezard"));
        assertTrue(cardList.get(0).getAffinity() == Affinity.FEU);
        assertTrue(cardList.get(0).getEnergy() == 40);
        assertTrue(cardList.get(0).getHP() == 60);
        assertTrue(cardList.get(0).getStrength() == 10);
        assertTrue(cardList.get(0).getPrice() == 200);
        assertTrue(cardList.get(0).getImgUrl().equals("https://i.imgur.com/szoTQIA.png"));
    }

    @Test
    public void findByName() {
    	crepo.save(new Card("Salamaiche", "Lezard", Affinity.FEU, 40, 60, 10, 200, "https://i.imgur.com/szoTQIA.png"));
    	crepo.save(new Card("Carapusse", "Tortue", Affinity.EAU, 30, 70, 10, 200, "https://www.pokebip.com/membres/galeries/1241/1241601942064347200.jpg"));
    	crepo.save(new Card("Boulebizarre", "Grenouille", Affinity.PLANTE, 40, 50, 15, 200, "https://www.hurluberlu.fr/wp-content/uploads/2014/09/N%C2%B0_1_Bulbizarre1.png"));
        List<Card> cardList = new ArrayList<>();
        crepo.findByName("Salamaiche").forEach(cardList::add);
        assertTrue(cardList.size() == 2);
    }
}
