package dev.application.entite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Vote {
	

	public enum AVIS {
		LIKE, DISLIKE
		}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@OneToOne
	private Collegue collegue;
	@Column(name = "AVIS")
	@Enumerated(EnumType.STRING)
	private AVIS avis;

	public Vote() {
	}
		
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Collegue getCollegue() {
		return collegue;
	}



	public void setCollegue(Collegue collegue) {
		this.collegue = collegue;
	}


	public AVIS getAvis() {
		return avis;
	}


	public void setAvis(AVIS avis) {
		this.avis = avis;
	}
	

}
