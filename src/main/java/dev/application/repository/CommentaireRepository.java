package dev.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.application.entite.Collegue;
import dev.application.entite.Commentaire;

public interface CommentaireRepository extends JpaRepository<Commentaire, Integer>{
	List<Commentaire> findAll();
	List<Commentaire> findByCollegue(Collegue collegue);
}
