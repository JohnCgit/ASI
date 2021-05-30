package com.webAppCard.Market;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
	public List<Transaction> findAll();
}
