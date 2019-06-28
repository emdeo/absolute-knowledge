package com.absoluteknowledge.model;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Partie {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@ManyToOne
    @JoinColumn(name ="fk_chapitre")
    private Chapitre chapitre;
	@OneToMany(mappedBy ="partie")
	List<Image> images;
	@OneToMany(mappedBy ="partie")
	List<Code> codes;
	@OneToMany(mappedBy ="partie")
	List<Paragraphe> paragraphes;
}
