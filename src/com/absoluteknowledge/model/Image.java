package com.absoluteknowledge.model;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Image implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3533151447516932507L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private int indexee;
	public int getIndexee() {
		return indexee;
	}
	public void setIndex(int index) {
		this.indexee = index;
	}
	@ManyToOne
    @JoinColumn(name ="fk_partie")
    private Partie partie;
	public String titre="";
	private String legende="";
	private String url="";
	
	public String getLegende() {
		return legende;
	}
	public void setLegende(String legende) {
		this.legende = legende;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Partie getPartie() {
		return partie;
	}
	public void setPartie(Partie partie) {
		this.partie = partie;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
}
