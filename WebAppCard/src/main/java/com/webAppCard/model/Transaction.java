package com.webAppCard.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Transaction {
	@Id
	@GeneratedValue
	private Integer id;
	private Integer cardId;
	private Integer sellerId;
	
	public Transaction(int cardId,int sellerId) 
	{
		super();
		this.cardId = cardId;
		this.sellerId = sellerId;
	}

	public Integer getCardId() {
		return cardId;
	}

	public Integer getSellerId() {
		return sellerId;
	}

}
