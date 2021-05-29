package com.webAppCard.Market;


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
	private Integer cardId;
	private Integer sellerId;
	
	public Transaction() {}
	public Transaction(int cardId,int sellerId) 
	{
		this.cardId = cardId;
		this.sellerId = sellerId;
	}


	public Integer getSellerId() {
		return sellerId;
	}


	public Integer getIdCard() {
		return cardId;
	}


}
