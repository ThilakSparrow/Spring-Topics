package com.rnt.component.annotations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainDemo2Delete {
	public static void main(String[] args) {
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		Address addr = session.get(Address.class, 1);
		if(addr != null)
			session.remove(addr);
		
		tx.commit();
		
		System.out.println("Record deleted");
		System.out.println(addr);
		
		session.close();
	}
}
