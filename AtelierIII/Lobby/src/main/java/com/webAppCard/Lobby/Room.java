package com.webAppCard.Lobby;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
	public class Room {
	@Id
	@GeneratedValue
	int id;
	int idPlayer1;
	int idCardPlayer1;
	int idPlayer2;
	int idCardPlayer2;
	int mise;

	public Room() {}
	public Room(int idPlayer1,int idCardPlayer1,int mise) {
		this.idPlayer1=idPlayer1;
		this.idCardPlayer1=idCardPlayer1;
		this.mise = mise;
	}
	
	public void setPlayer2(int idPlayer2,int idCardPlayer2) {
		this.idPlayer2=idPlayer2;
		this.idCardPlayer2=idCardPlayer2;
	}


	public int getIdPlayer1() {
		return idPlayer1;
	}
	
	public int getIdCardPlayer1() {
		return idCardPlayer1;
	}

	public int getIdCardPlayer2() {
		return idCardPlayer2;
	}

	public int getIdPlayer2() {
		return idPlayer2;
	}
	public int getMise() {
		return mise;
	}
	public int getId() {
		return id;
	}
	

}
