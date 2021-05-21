package com.webAppCard.Card;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//Objet Carte avec différents attributs

@Entity
public class Card {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String family;
	private String affinity;
	private int energy;
	private int hp;
	private int strength;
	private int price;
	private String imgUrl;
	
	public Card() {
	}

	public Card(String name, String Family, String Affinity, int Energy, int hp, int Strength, int Price, String imgUrl) {
		super();
		this.name = name;
		this.family = Family;
		this.affinity = Affinity;
		this.energy = Energy;
		this.hp = hp;
		this.strength = Strength;
		this.price = Price;
		this.imgUrl = imgUrl;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String Family) {
		this.family = Family;
	}
	
	public String getAffinity() {
		return affinity;
	}

	public void setAffinity(String Affinity) {
		this.affinity = Affinity;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int Energy) {
		this.energy = Energy;
	}

	public int getHP() {
		return hp;
	}

	public void setHP(int hp) {
		this.hp = hp;
	}
	
	public int getStrength() {
		return strength;
	}

	public void setStrength(int Strength) {
		this.strength = Strength;
	}
	
	public int getPrice() {
		return price;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public void setPrice(int Price) {
		this.price = Price;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CARD ["+this.id+"]: name:"+this.name+"]: Family:"+this.family+", Affinity:"+this.affinity+", Energy:"+this.energy+", HP:"+this.hp+", Price:"+this.price;
	}
}
