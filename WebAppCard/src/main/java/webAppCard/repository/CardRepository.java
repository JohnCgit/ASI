package webAppCard.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import webAppCard.model.Card;

public interface CardRepository extends CrudRepository<Card, Integer> {

	public List<Card> findByName(String name);

}
