package com.webAppCard.Market;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


// Gestion de l'achat/vente de carte
@RestController
public class MarketRestCrt {
	
	@Autowired
	MarketService mService;
	
	
	// Permet de récupérer l'ensemble des cartes en vente sur le marché
	@RequestMapping(method=RequestMethod.GET,value="/getAll")
	public List<Transaction> getAllCard() 
	{
		return mService.getAll();
	}
	
	// Réalise la mise sur le marché de la carte d'id idCard par l'utilisateur d'id idSeller
	@RequestMapping(method=RequestMethod.POST,value="/sell/{idCard}/{idSeller}")
	public void sellCard(@PathVariable int idCard, @PathVariable int idSeller) 
	{
		mService.addTransaction(new Transaction(idCard,idSeller));
	}
	
	// Réalise l'achat  de la carte contenue dans l'objet Transaction d'id idtransaction  par l'utilisateur d'id idBuyer
	@RequestMapping(method=RequestMethod.POST,value="/buy/{idTransaction}/{idBuyer}")
	public void buyCard(@PathVariable int idTransaction, @PathVariable int idBuyer) 
	{
		mService.buyCard(idTransaction,idBuyer);
	}
	
}
