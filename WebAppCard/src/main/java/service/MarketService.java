package service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import model.Transaction;
import repository.TransactionRepository;

public class MarketService {

	@Autowired
	TransactionRepository tRepository;
	
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
}
