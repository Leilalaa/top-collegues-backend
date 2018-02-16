package dev.application.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.application.entite.Collegue;
import dev.application.repository.CollegueRepository;


@RestController
@CrossOrigin
public class CollegueController {
	
	@Autowired
	CollegueRepository cr;
	
	@RequestMapping(method = RequestMethod.GET, path = "/collegues")
	public List<Collegue> listerCollegues() {
		
		return cr.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/collegues/{nom}")
	public Collegue detailCollegue(@PathVariable String nom) {

		return cr.findByNom(nom);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/collegues")
	public Collegue sauvegarder(@RequestBody Collegue collegue) {
		if(cr.findByNom(collegue.getNom()) == null){
			cr.save(collegue);
			return cr.findByNom(collegue.getNom());
		}
		return null;
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
}