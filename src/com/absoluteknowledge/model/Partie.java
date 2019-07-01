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
public class Partie implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4684065521693617174L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private int index;
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	@ManyToOne
    @JoinColumn(name ="fk_chapitre")
    private Chapitre chapitre;
	@OneToMany(cascade=CascadeType.ALL,mappedBy ="partie")
	List<Image> images = new ArrayList<Image>();
	@OneToMany(cascade=CascadeType.ALL,mappedBy ="partie")
	List<Code> codes= new ArrayList<Code>();
	@OneToMany(cascade=CascadeType.ALL,mappedBy ="partie")
	List<Paragraphe> paragraphes= new ArrayList<Paragraphe>();
	public String titre=""; 
	public Chapitre getChapitre() {
		return chapitre;
	}
	public void setChapitre(Chapitre chapitre) {
		this.chapitre = chapitre;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public List<Code> getCodes() {
		return codes;
	}
	public void setCodes(List<Code> codes) {
		this.codes = codes;
	}
	public List<Paragraphe> getParagraphes() {
		return paragraphes;
	}
	public void setParagraphes(List<Paragraphe> paragraphes) {
		this.paragraphes = paragraphes;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
}
