package com.rntbci;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class Main3NativeSQL {
	public static void main(String[] args) {
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
	
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Department> cr = cb.createQuery(Department.class);
		Root<Department> root = cr.from(Department.class);
		cr.select(root);
		
		Predicate predicate1 = cb.greaterThan(root.get("dno"), 1);
		Predicate predicate2 = cb.lessThan(root.get("dno"), 3);
		Predicate predicate = cb.and(predicate1, predicate2);
		
		cr.where(predicate);
		
		Query<Department> query = session.createQuery(cr);
		System.out.println(query.getResultList());
		
		tx.commit();
		session.close();
	}
}

