package com.absoluteknowledge.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.SystemException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.absoluteknowledge.model.Chapitre;
import com.absoluteknowledge.model.Code;
import com.absoluteknowledge.model.Cours;
import com.absoluteknowledge.model.Image;
import com.absoluteknowledge.model.Paragraphe;
import com.absoluteknowledge.model.Partie;
import com.absoluteknowledge.model.QuestionQcm;
import com.absoluteknowledge.model.Quizz;
import com.absoluteknowledge.utils.HibernateUtil;

//Verifier la structure, il est peut etre possible d'avoir un chapitre sans cours
//Le @NotNull serait bien, mais il est dans hibernate validator, et c'est chiant a importer via un Jar, via le pom.xml c'est simple
//Le @Column(nullable = false) est fait au niveua de la db, le @notnull au niveau java
public class ServiceCours {
	// Changer pour passer par entity manager
	// cf https://thoughts-on-java.org/hibernate-best-practices/
	public void orderByIndex(Cours cours) {
		int titre1=cours.getChapitres().get(0).getIndexee();
		int titre2=cours.getChapitres().get(1).getIndexee();
		cours.getChapitres().sort(Comparator.comparing(Chapitre::getIndexee));
		titre1=cours.getChapitres().get(0).getIndexee();
		titre2=cours.getChapitres().get(1).getIndexee();
		cours.getQuizzs().sort(Comparator.comparing(Quizz::getIndexee));
		cours.getQuizzs().forEach(qcm -> qcm.getQuestionQcms().sort(Comparator.comparing(QuestionQcm::getIndexee)));
		
		for (Chapitre chap : cours.getChapitres()) {
			chap.getParties().sort(Comparator.comparing(Partie::getIndexee));
			for (Partie partie : chap.getParties()) {
				partie.getCodes().sort(Comparator.comparing(Code::getIndexee));
				partie.getImages().sort(Comparator.comparing(Image::getIndexee));
				partie.getParagraphes().sort(Comparator.comparing(Paragraphe::getIndexee));
			}
		}
	}

	
	/**
	 * Récupérer un cours dans la BDD
	 * (changer pour passer par entity manager)
	 * cf https://thoughts-on-java.org/hibernate-best-practices/
	 * 
	 * @param id
	 * @return
	 * @throws IllegalStateException
	 * @throws SystemException
	 */
	public Cours getCoursById(long id) throws IllegalStateException, SystemException {
		Session session = null;
		Transaction transaction = null;
		Cours cour2 = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			cour2 = (Cours) session.get(Cours.class, id);

			// C'est pas utile ici, mais quand on fait un save, ca peut forcer la sauvegarde
			// sur la db immediatement
//			session.flush();
//			session.clear();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				// Je ne ferme pas la session parceque dans le main ou je test le service, quand
				// je tente d'acceder a
				// une relation de type lazyy j'ai une erreur failed to lazily initialize a
				// collection of role: could not initialize proxy - no Session
				// vu que je ne suis plus dans transacitonal j'ai plus acces a la session
				// session.close();
			}
		}
		orderByIndex(cour2);
		return cour2;
	}

	// Utilisation de criteriaQuery pour essayer
	//Maintenant ce n'est plus fait avec hibernate, mais avec jpa, c'est depreciated via hibernate
	/**
	 * Récupérer tous les cours de la BDD
	 * Utilisation de criteriaQuery pour essayer
	 * 
	 * @return
	 */
	public List<Cours> getAllCours() {
		List<Cours> result = new ArrayList<Cours>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		EntityManager em = session.getEntityManagerFactory().createEntityManager();

		final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Cours> crit = criteriaBuilder.createQuery(Cours.class);
		Root<Cours> root = crit.from(Cours.class);
//	        crit.where(criteriaBuilder.equal(root.get("user_name"), userName))
//	            .distinct(true);
		CriteriaQuery<Cours> all = crit.select(root);
		result = em.createQuery(all).getResultList();
		
		result.forEach(elem->orderByIndex(elem));

		return result;
	}
	public List<Chapitre> getChapitreByTitle(String title) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		EntityManager em = session.getEntityManagerFactory().createEntityManager();

		final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Chapitre> crit = criteriaBuilder.createQuery(Chapitre.class);
		Root<Chapitre> root = crit.from(Chapitre.class);
	    crit.where(criteriaBuilder.equal(root.get("titre"), title)).distinct(true);
	    
		
		//Besoin d'ordonner le resultat de cette fonction par index ?
		return em.createQuery(crit).getResultList();
	}
}
