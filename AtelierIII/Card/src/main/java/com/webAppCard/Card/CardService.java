package com.webAppCard.Card;

  import java.util.Optional;
  import java.util.List;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.stereotype.Service;

//Gestion d'une carte
  @Service
  public class CardService {
	@Autowired
  	CardRepository cRepository;
	
  	//Créer une carte
  	public void createCard(Card c) {
  		Card createdCard=cRepository.save(c);
  		System.out.println(createdCard);
  	}
  	
  	//Récupère une carte d'id fourni
  	public Card getCard(int id) {
  		Optional<Card> cOpt =cRepository.findById(id);
  		if (cOpt.isPresent()) {
  			return cOpt.get();
  		}else {
  			return null;
  		}
  	}
  	
  	//Récupère la liste des cartes existantes
	public List<Card> getAllCard() {
		return cRepository.findAll();
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
