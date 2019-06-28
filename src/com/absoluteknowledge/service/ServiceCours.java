package com.absoluteknowledge.service;

import java.util.List;

import javax.persistence.Query;
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
				session.close();
			}
		}
		return cour2;
	}
//	public Chapitre getOneChapitreByTitle(String title){
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		Query query = session.createQuery("from user where titre =:title ")
//	            .setParameter("title ", title);
//	}
}
