package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Card {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String Family;
	private String Affinity;
	private String Energy;
	private int HP;
	private int Price;
	
	public Card() {
	}

	public Card(String name, String Family, String Affinity, String Energy, int HP, int Price) {
		super();
		this.name = name;
		this.Family = Family;
		this.Affinity = Affinity;
		this.Energy = Energy;
		this.HP = HP;
		this.Price = Price;
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

	public String getEnergy() {
		return Energy;
	}

	public void setEnergy(String Energy) {
		this.Energy = Energy;
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int HP) {
		this.HP = HP;
	}
	
	public int getPrice() {
		return Price;
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
