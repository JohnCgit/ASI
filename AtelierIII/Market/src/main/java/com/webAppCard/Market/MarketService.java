package com.webAppCard.Market;


import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


//Gestion de l'achat/vente de carte
@Service
public class MarketService {
	@Autowired
	TransactionRepository tRepository;
	 private final RestTemplate restTemplate;

	
	
	public MarketService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
	
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
		this.restTemplate.put("/user/addCard/{id}",t.getCard());
		int price = this.restTemplate.getForObject("/card/price/{id}", Integer.class,t.getCard());
		this.restTemplate.put("/user/updateMoney/{id}/{balance}",idBuyer,-price);
		this.restTemplate.put("/user/updateMoney/{id}/{balance}",t.getSellerId(),price);
	}
}
