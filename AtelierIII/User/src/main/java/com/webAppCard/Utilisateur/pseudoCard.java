package com.webAppCard.Utilisateur;

public class pseudoCard {
	private int idCard; // reference vers le modele de la carte
	private int energy;
	private int energymax; // qte d'energie de la carte
	
	public pseudoCard(int idCard, int Energy, int EnergyMax) {		
		this.idCard=idCard;
		this.energy=Energy;
		this.energymax=EnergyMax;
	}

	public int getIdCard() {
		return idCard;
	}

	public void setIdCard(int idCard) {
		this.idCard = idCard;
	}

	public int getEnergymax() {
		return energymax;
	}

	public void setEnergymax(int energymax) {
		this.energymax = energymax;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getEnergy() {
		return energy;
	}
}
