package com.rntbci;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

public class Main4NativeSQL {
	public static void main(String[] args) {
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
	
		String qry = "Select * from department where dno > 1 and dno < 3";
		NativeQuery<Department> query = session.createNativeQuery(qry,Department.class);
		System.out.println(query.getResultList());
		
		tx.commit();
		session.close();
	}
}
