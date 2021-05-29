package com.webAppCard.Market;



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
	 public static int ReverseProxyPort = 8082;
	
	
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
	
	public void removeTransaction(Transaction t) {
		tRepository.delete(t);
	}
	
	// Permet de récupérer l'ensemble des transactions sur le marché
	public List<Transaction> getAll() {
		return tRepository.findAll();
	}

	
    // Réalise l'achat  de la carte stockée dans la Transaction idTransaction par l'utilisateur d'id idBuyer
	// Mise à jour de la cagnotte de l'acheteur et du vendeur
	public boolean buyCard(int idTransaction, int idBuyer) {
		Transaction t = getTransaction(idTransaction);
		int idCard = t.getIdCard();
		int idSeller = t.getSellerId();
		boolean res = false;
		
		int price = this.restTemplate.getForObject("http://127.0.0.1:"+ReverseProxyPort+"/card/Price/"+idCard, Integer.class);
		int moneyBuyer = this.restTemplate.getForObject("http://127.0.0.1:"+ReverseProxyPort+"/user/getMoney/"+idBuyer, Integer.class);
		
		if(moneyBuyer>=price) {
			this.restTemplate.put("http://127.0.0.1:"+ReverseProxyPort+"/user/updateMoney/"+idBuyer+"/"+-price,null);
			this.restTemplate.put("http://127.0.0.1:"+ReverseProxyPort+"/user/updateMoney/"+idSeller+"/"+price,null);
			this.restTemplate.put("http://127.0.0.1:"+ReverseProxyPort+"/user/removeCard/"+idSeller+"/"+idCard,null);
			this.restTemplate.put("http://127.0.0.1:"+ReverseProxyPort+"/user/addCard/"+idBuyer+"/"+idCard,null);
			this.removeTransaction(t);
			res = true;
		}
		return res;
	}

	public boolean sellTransaction(int idCard, int idSeller) {
		boolean res=false;
		List<Integer> collection= (List<Integer>)this.restTemplate.getForObject("http://127.0.0.1:"+ReverseProxyPort+"/user/getCollection/"+idSeller, List.class);
		if(collection.contains(idCard)) {
			addTransaction(new Transaction(idCard,idSeller));
			res=true;
		}
		return res;
		
	}
}
