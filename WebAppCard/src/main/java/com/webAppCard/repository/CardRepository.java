package com.webAppCard.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.webAppCard.model.Card;

@Repository
public interface CardRepository extends CrudRepository<Card, Integer> {

	public List<Card> findByName(String name);

}
