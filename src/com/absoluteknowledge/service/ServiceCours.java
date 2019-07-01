package com.absoluteknowledge.service;

import java.util.ArrayList;
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
import com.absoluteknowledge.model.Cours;
import com.absoluteknowledge.utils.HibernateUtil;

public class ServiceCours {
	//Changer pour passer par entity manager
	//cf https://thoughts-on-java.org/hibernate-best-practices/
	public Cours getCoursById(long id) throws IllegalStateException, SystemException {
		Session session = null;
		Transaction transaction = null;
		Cours cour2 = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			cour2 = (Cours) session.get(Cours.class, id);
			//C'est pas utile ici, mais quand on fait un save, ca peut forcer la sauvegarde sur la db immediatement
//			session.flush();
//			session.clear();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				//Je ne ferme pas la session parceque dans le main ou je test le service, quand je tente d'acceder a
				//une relation de type lazyy j'ai une erreur failed to lazily initialize a collection of role: could not initialize proxy - no Session
				// vu que je ne suis plus dans transacitonal j'ai plus acces a la session 
				//session.close();
			}
		}
		return cour2;
	}
	//Utilisation de criteriaQuery pour essayer
	public List<Cours> getAllCours(){
		List<Cours> result = new ArrayList<Cours>();
		Session session = HibernateUtil.getSessionFactory().openSession(); 
		EntityManager em = session.getEntityManagerFactory().createEntityManager();
		
		 final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	        CriteriaQuery<Cours> crit = criteriaBuilder.createQuery(Cours.class);
	        Root<Cours> root = crit.from(Cours.class);
//	        crit.where(criteriaBuilder.equal(root.get("user_name"), userName))
//	            .distinct(true);
	        CriteriaQuery<Cours> all = crit.select(root);
	        result = em.createQuery( all ).getResultList();
	        
		return result;
	}
//	public Chapitre getOneChapitreByTitle(String title){
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		Query query = session.createQuery("from user where titre =:title ")
//	            .setParameter("title ", title);
//	}
}
