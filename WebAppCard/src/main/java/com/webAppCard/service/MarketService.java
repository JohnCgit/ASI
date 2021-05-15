package com.webAppCard.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webAppCard.model.Transaction;
import com.webAppCard.repository.TransactionRepository;

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

	public List<Integer> getAll() {
		int id = 0;
		Transaction t = getTransaction(id);
		List<Integer> res = new LinkedList<Integer>();
		while(t != null) 
		{
			res.add(t.getCardId());
			t = getTransaction(id++);
		}
		return res;
	}

	public void buyCard(int idTransaction, int idBuyer) {
		Transaction t = getTransaction(idTransaction);
		uService.addCard(t.getCardId(),idBuyer);
		uService.updateMoney(t.getSellerId(),cService.getPrice(t.getCardId()));
		uService.updateMoney(idBuyer,-cService.getPrice(t.getCardId()));
		
	}
}
