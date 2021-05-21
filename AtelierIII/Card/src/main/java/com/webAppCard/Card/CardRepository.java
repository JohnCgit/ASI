package com.webAppCard.Card;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.webAppCard.Card.Card;

@Repository
public interface CardRepository extends CrudRepository<Card, Integer> {

	public List<Card> findByName(String name);

}
