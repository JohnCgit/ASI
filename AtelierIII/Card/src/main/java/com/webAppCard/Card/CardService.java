package com.webAppCard.Card;

  import java.util.Optional;

  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.stereotype.Service;

//Gestion d'une carte
  @Service
  public class CardService {
	@Autowired
  	CardRepository cRepository;
  	public void createCard(Card c) {
  		Card createdCard=cRepository.save(c);
  		System.out.println(createdCard);
  	}
  	
  	public Card getCard(int id) {
  		Optional<Card> cOpt =cRepository.findById(id);
  		if (cOpt.isPresent()) {
  			return cOpt.get();
  		}else {
  			return null;
  		}
  	}
  	
	//Permet de récupérer le prix de la carte dont on a fourni l'id
	public int getPrice(Integer cardId) {
		return getCard(cardId).getPrice();
	}
	
	public int getEnergy(Integer cardId) {
		return getCard(cardId).getEnergy();
	}

	//Permet de récupérer l'id d'une carte
	public int getId(Card card) {
		return card.getId();
	}

  }
