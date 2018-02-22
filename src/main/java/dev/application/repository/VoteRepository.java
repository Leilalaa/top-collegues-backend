package dev.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.application.entite.Vote;

public interface VoteRepository extends JpaRepository<Vote, Integer>{
	
	List<Vote> findAll();
	Vote findById(int id);

}
