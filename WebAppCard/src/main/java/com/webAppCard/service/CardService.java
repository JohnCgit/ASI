package com.webAppCard.service;

  import java.util.Optional;

  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.stereotype.Service;

import com.webAppCard.model.Card;
import com.webAppCard.repository.CardRepository;

  @Service
  public class CardService {
  	@Autowired
  	CardRepository cRepository;
  	public void addCard(Card c) {
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

	public int getPrice(Integer cardId) {
		return getCard(cardId).getPrice();
	}

  }
