package webAppCard.repository;

import org.springframework.data.repository.CrudRepository;

import webAppCard.model.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

}
