package com.absoluteknowledge.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class QuestionQcm implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@ManyToOne
    @JoinColumn(name ="fk_quizz")
    private Quizz quizz;
	private String question="";
	@ElementCollection
	private List<String> choix = new ArrayList<String>();
	//Indice de la reponse dans la lsite choix
	private int reponseIndex;
	public Quizz getQuizz() {
		return quizz;
	}
	public void setQuizz(Quizz quizz) {
		this.quizz = quizz;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public List<String> getChoix() {
		return choix;
	}
	public void setChoix(List<String> choix) {
		this.choix = choix;
	}
	public int getReponseIndex() {
		return reponseIndex;
	}
	public void setReponseIndex(int reponseIndex) {
		this.reponseIndex = reponseIndex;
	}
	
}
