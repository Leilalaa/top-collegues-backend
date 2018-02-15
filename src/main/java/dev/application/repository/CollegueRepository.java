package dev.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.application.entite.Collegue;


public interface CollegueRepository extends JpaRepository<Collegue, Integer> {
	
	List<Collegue> findAll();
	Collegue findByNom(String nom);
	
}