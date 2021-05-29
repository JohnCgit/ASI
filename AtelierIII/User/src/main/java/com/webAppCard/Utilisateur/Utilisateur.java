package com.webAppCard.Utilisateur;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Utilisateur {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String surname;
	public String password;

	@ElementCollection
	private List<Integer> collection;
	private int money;
	
	public Utilisateur() {
	}
	
	public Utilisateur(String name, String password, String surname) {
		super();
		this.name=name;
		this.surname=surname;
		this.password=password;
		this.money=0;
		this.collection = new ArrayList<Integer>();
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

	public void addCard(int idCard) {
		this.collection.add(idCard);
	}
	
	public void removeCard(int idCard) {
		this.collection.remove(idCard);
	}
	
	public List<Integer> getCollection() {
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
