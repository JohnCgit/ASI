package com.webAppCard.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// Objet permettant de stocker une transaction sur le marché.
// Une transcation consiste en une carte et l'identifiant du vendeur
@Entity
public class Transaction {
	@Id
	@GeneratedValue
	private Integer id;
	private Card card;
	private Integer sellerId;
	
	public Transaction(Card card,int sellerId) 
	{
		super();
		this.card = card;
		this.sellerId = sellerId;
	}


	public Integer getSellerId() {
		return sellerId;
	}


	public Card getCard() {
		return card;
	}

}
