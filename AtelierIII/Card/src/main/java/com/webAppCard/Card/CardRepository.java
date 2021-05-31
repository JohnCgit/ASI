package com.webAppCard.Card;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card, Integer> {

	public List<Card> findByName(String Name);
	public List<Card> findAll();
}
