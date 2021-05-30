package com.webAppCard.Utilisateur;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer> {
	
	public Optional<Utilisateur> findByName(String name);
	public Optional<Utilisateur> findById(int id);
	public List<Utilisateur> findAll();
	public void delete(Utilisateur u);

}