package model;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import service.CardService;
import la joie ah bah nn

public class User {
	private int id;
	private String username;
	private String pwd;
	private String mail;
	private List<Integer> Collection;
	
	public User() {
	}

	public User(int id,String username, String pwd, String mail) {
		super();
		this.id=id;
		this.username=username;
		this.pwd=pwd;
		this.mail=mail;
		this.Collection=new ArrayList<Integer>();		
	}

	public String getUsername() {
		return username;
	}

	public void setName(String username) {
		this.username = username;
	}

	public String getMail() {
		return this.mail;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void addCard(int idCard) {
		this.Collection.add(id);
	}
	
	public void getCollection() {
		for (int id : this.Collection) {
			System.out.println(CardService.getCard(id));
		}
	}
	@Override
	public String toString() {
		return "Username: "+this.username+" Pwd: "+this.pwd+" Mail: "+this.mail+" Id: "+this.id;
	}
}
