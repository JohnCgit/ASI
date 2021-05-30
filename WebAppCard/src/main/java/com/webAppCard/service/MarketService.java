package com.webAppCard.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webAppCard.model.Transaction;
import com.webAppCard.repository.TransactionRepository;

//Gestion de l'achat/vente de carte
@Service
public class MarketService {

	@Autowired
	TransactionRepository tRepository;
	
	UserService uService;
	CardService cService;
	
	public void addTransaction(Transaction t) {
		tRepository.save(t);
	}
	
	public Transaction getTransaction(int id) {
		Optional<Transaction> tOpt = tRepository.findById(id);
		if (tOpt.isPresent()) {
			return tOpt.get();
		}else {
			return null;
		}
	}

	// Permet de récupérer l'ensemble des transactions sur le marché
	public List<Transaction> getAll() {
		int id = 0;
		Transaction t = getTransaction(id);
		List<Transaction> res = new LinkedList<Transaction>();
		while(t != null) 
		{
			res.add(t);
			t = getTransaction(id++);
		}
		return res;
	}

    // Réalise l'achat  de la carte stockée dans la Transaction idTransaction par l'utilisateur d'id idBuyer
	// Mise à jour de la cagnotte de l'acheteur et du vendeur
	public void buyCard(int idTransaction, int idBuyer) {
		Transaction t = getTransaction(idTransaction);
		uService.addCard(cService.getId(t.getCard()),idBuyer);
		uService.updateMoney(t.getSellerId(),cService.getPrice(cService.getId(t.getCard())));
		uService.updateMoney(idBuyer,-cService.getPrice(cService.getId(t.getCard())));
		
	}
}
