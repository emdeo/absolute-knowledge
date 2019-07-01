package com.absoluteknowledge.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Quizz implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@ManyToOne
    @JoinColumn(name ="fk_cours")
    private Cours cours;
	private String titre="";
	private int indexee;
	public int getIndexee() {
		return indexee;
	}
	public void setIndexee(int indexee) {
		this.indexee = indexee;
	}
	@OneToMany(cascade=CascadeType.ALL,mappedBy ="quizz")
	List<QuestionQcm> questionQcms= new ArrayList<QuestionQcm>();
	public Cours getCours() {
		return cours;
	}
	public void setCours(Cours cours) {
		this.cours = cours;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public List<QuestionQcm> getQuestionQcms() {
		return questionQcms;
	}
	public void setQuestionQcms(List<QuestionQcm> questionQcms) {
		this.questionQcms = questionQcms;
	}
	
}

