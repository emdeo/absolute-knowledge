package com.absoluteknowledge.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cours {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@OneToMany(mappedBy ="cours")
	List<Chapitre> chapitres;
	@OneToMany(mappedBy ="cours")
	List<Quizz> quizzs;
}
