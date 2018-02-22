package dev.application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.application.entite.Collegue;
import dev.application.entite.Commentaire;
import dev.application.entite.Vote;
import dev.application.repository.CollegueRepository;
import dev.application.repository.CommentaireRepository;
import dev.application.repository.VoteRepository;


@RestController
@CrossOrigin
public class CollegueController {
	
	@Autowired
	CollegueRepository cr;
	@Autowired
	VoteRepository vr;
	@Autowired
	CommentaireRepository comr;
	
	@RequestMapping(method = RequestMethod.GET, path = "/collegues")
	public List<Collegue> listerCollegues() {
		
		return cr.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/collegues/{nom}")
	public Collegue detailCollegue(@PathVariable String nom) {

		return cr.findByNom(nom);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/collegues")
	public ResponseEntity<Collegue> sauvegarder(@RequestBody Collegue collegue) {
		if(cr.findByNom(collegue.getNom()) == null){
			cr.save(collegue);
			return ResponseEntity.status(HttpStatus.OK).body(cr.findByNom(collegue.getNom()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@RequestMapping(method = RequestMethod.PATCH, path="/collegues/{nom}")
	 public Collegue reaction(@PathVariable String nom, @RequestBody Map<String,String> action) {
		
		Collegue c = cr.findByNom(nom);
		
		if(action.get("action").equals("aimer")){
			c.setScore(c.getScore()+10);
		}else if(action.get("action").equals("detester")){
			c.setScore(c.getScore()-5);
		}
		
		cr.save(c);
		return c;
		 
	 }
	
	@RequestMapping(method = RequestMethod.POST, path = "/commenter")
	public void commenter(@RequestBody Commentaire commentaire) {
			comr.save(commentaire);
			
	}
	
	@RequestMapping(value = "/commentaires/{nom}", method = RequestMethod.GET)
	public List<Commentaire> afficher(@PathVariable String nom) {
		Collegue c = cr.findByNom(nom);
		return comr.findByCollegue(c);
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/votes")
	 public List<Vote> historique(@RequestParam(value="since") Optional<Integer> id) {
		
		List<Vote> votes = vr.findAll();
		int sinon = votes.size() - 2;
		List<Vote> historique = new ArrayList();
		
		if (id.isPresent()) {
			sinon = id.get();
		}
		
		for (int i = sinon; i <= votes.size(); i++) {
			historique.add(vr.findOne(i));
		}

		return historique;

		 
	 }
}