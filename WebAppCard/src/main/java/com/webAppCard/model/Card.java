package com.webAppCard.model;

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
	private String Family;
	private String Affinity;
	private int Energy;
	private int HP;
	private int Strength;
	private int Price;
	private String imgUrl;
	
	public Card() {
	}

	public Card(String name, String Family, String Affinity, int Energy, int HP, int Strength, int Price, String imgUrl) {
		super();
		this.name = name;
		this.Family = Family;
		this.Affinity = Affinity;
		this.Energy = Energy;
		this.HP = HP;
		this.Strength = Strength;
		this.Price = Price;
		this.imgUrl = imgUrl;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFamily() {
		return Family;
	}

	public void setFamily(String Family) {
		this.Family = Family;
	}
	
	public String getAffinity() {
		return Affinity;
	}

	public void setAffinity(String Affinity) {
		this.Affinity = Affinity;
	}

	public int getEnergy() {
		return Energy;
	}

	public void setEnergy(int Energy) {
		this.Energy = Energy;
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int HP) {
		this.HP = HP;
	}
	
	public int getStrength() {
		return Strength;
	}

	public void setStrength(int Strength) {
		this.Strength = Strength;
	}
	
	public int getPrice() {
		return Price;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public void setPrice(int Price) {
		this.Price = Price;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CARD ["+this.id+"]: name:"+this.name+"]: Family:"+this.Family+", Affinity:"+this.Affinity+", Energy:"+this.Energy+", HP:"+this.HP+", Price:"+this.Price;
	}
}
