package com.webAppCard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.webAppCard.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	public Optional<User> findByName(String name);
	public Optional<User> findById(int id);
	public List<User> findAll();
	public void delete(User u);

}
