package dev.application.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.application.entite.Collegue;
import dev.application.repository.CollegueRepository;


@Controller
public class CollegueController {
	
	@Autowired
	CollegueRepository cr;
	
	@RequestMapping(method = RequestMethod.GET, path = "/collegues")
	@ResponseBody
	public List<Collegue> listerCollegues() {
		
		return cr.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/collegues")
	@ResponseBody
	public Collegue sauvegarder(@RequestBody Collegue collegue) {
		
		cr.save(collegue);
		return cr.findByNom(collegue.getNom());
	}
	
	@RequestMapping(method = RequestMethod.PATCH, path="/collegues/{nom}")
	 @ResponseBody
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