package com.webAppCard.User;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String surname;
	public String password;

	@ElementCollection
	private List<pseudoCard> collection;
	private int money;
	
	public User() {
	}
	
	public User(String name, String password, String surname) {
		super();
		this.name=name;
		this.surname=surname;
		this.password=password;
		this.money=0;
		this.collection = new ArrayList<pseudoCard>();
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return this.password;
	}
	
	public String getSurname() {
		return this.surname;
	}

	public void addCard(int idCard, int energy) {
		pseudoCard c = new pseudoCard(idCard, energy, energy);
		this.collection.add(c);
	}
	
	public List<pseudoCard> getCollection() {
		return this.collection;
	}
	
	public int getMoney() {
		return this.money;
		
	}
	public void setMoney(int Money) {
		this.money=Money;
	}
	
	public int getCollectionSize() {
		return this.collection.size();
	}
	
	public int getId() {
		return id;
	}
}