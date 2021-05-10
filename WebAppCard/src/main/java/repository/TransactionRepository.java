package repository;

import org.springframework.data.repository.CrudRepository;

import model.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

}
