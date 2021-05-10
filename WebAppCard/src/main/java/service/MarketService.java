package service;

import org.springframework.beans.factory.annotation.Autowired;

import repository.TransactionRepository;

public class MarketService {

	@Autowired
	TransactionRepository tRepository;
}
