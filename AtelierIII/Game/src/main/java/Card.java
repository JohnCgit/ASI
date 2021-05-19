import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//Objet Carte avec différents attributs

@Entity
public class Card {
	@Id
	@GeneratedValue
	public int id;
	public String name;
	public String Family;
	public String Affinity;
	public int Energy;
	public int HP;
	public int Price;
	public int Strength;
	
	public Card(String name, String Family, String Affinity, int Energy, int HP, int Price, int Strength) {
		super();
		this.name = name;
		this.Family = Family;
		this.Affinity = Affinity;
		this.Energy = Energy;
		this.HP = HP;
		this.Price = Price;
		this.Strength = Strength;
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
	
	public int getStrength() {
		return Strength;
	}


	@Override
	public String toString() {
		return "CARD ["+this.id+"]: name:"+this.name+"]: Family:"+this.Family+", Affinity:"+this.Affinity+", Energy:"+this.Energy+", HP:"+this.HP+", Price:"+this.Price;
	}
}