package com.rntbci;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class Main2HQL {
	public static void main(String[] args) {
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		Query<Department> query1 = session.createQuery("From Department dept where dept.dno = ?1");
		query1.setParameter(1,2);
		List<Department> list1 = query1.getResultList();
		System.out.println(list1);
		
		Query<Department> query2 = session.createQuery("From Department");
		System.out.println(query2.getResultList());
		
		Query<Department> query3 = session.createQuery("select dname From Department");
		System.out.println(query3.getResultList());
		
		tx.commit();
		session.close();
	}
}
