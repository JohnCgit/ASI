package webAppCard.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import webAppCard.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	public List<User> findByName(String name);
	//public List<User> findById(int id);

}
