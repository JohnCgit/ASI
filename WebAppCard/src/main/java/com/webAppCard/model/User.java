package com.webAppCard.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.ArrayList;

@Entity
public class User {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String surname;
	public String password;

	@ElementCollection
	private List<Integer> Collection;
	private int Money;
	
	public User() {
	}
	
	public User(String name, String password, String surname) {
		super();
		this.name=name;
		this.password=password;
		this.surname=surname;
		this.Collection=new ArrayList<Integer>();	
		this.Money=0;
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
		this.Collection.add(id);
	}
	
	public List<Integer> getCollection() {
		return this.Collection;
	}
	
	public int getMoney() {
		return this.Money;
		
	}
	public void setMoney(int Money) {
		this.Money=Money;
	}
	
	public int getCollectionSize() {
		return this.Collection.size();
	}
	
	public int getId() {
		return id;
	}
}
