package com.absoluteknowledge.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cours {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cours")
	List<Chapitre> chapitres = new ArrayList<Chapitre>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cours")
	List<Quizz> quizzs = new ArrayList<Quizz>();
	public String titre = "";

	public List<Chapitre> getChapitres() {
		return chapitres;
	}

	public void setChapitres(List<Chapitre> chapitres) {
		this.chapitres = chapitres;
	}

	public List<Quizz> getQuizzs() {
		return quizzs;
	}

	public void setQuizzs(List<Quizz> quizzs) {
		this.quizzs = quizzs;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cours [id=");
		builder.append(id);
		builder.append(", chapitres=");
		builder.append(chapitres);
		builder.append(", quizzs=");
		builder.append(quizzs);
		builder.append(", titre=");
		builder.append(titre);
		builder.append(", getChapitres()=");
		builder.append(getChapitres());
		builder.append(", getQuizzs()=");
		builder.append(getQuizzs());
		builder.append(", getTitre()=");
		builder.append(getTitre());
		builder.append("]");
		return builder.toString();
	}

}
