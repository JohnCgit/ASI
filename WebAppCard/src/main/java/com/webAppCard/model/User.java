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
	private String username;
	public String pwd;
	private String mail;
	@ElementCollection
	private List<Integer> Collection;
	private int Money;
	
	public User() {
	}
	
// PWD where does it goes????
	public User(String username, String pwd, String mail) {
		super();
		this.username=username;
		this.pwd=pwd;
		this.mail=mail;
		this.Collection=new ArrayList<Integer>();	
		this.Money=0;
	}

	public String getUsername() {
		return username;
	}

	public String getMail() {
		return this.mail;
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
}
