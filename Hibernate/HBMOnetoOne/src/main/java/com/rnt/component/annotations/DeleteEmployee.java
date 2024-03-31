package com.rnt.component.annotations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DeleteEmployee {
	public static void main(String[] args) {
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		Employee emp = session.get(Employee.class, 4);
		if(emp != null)
			session.remove(emp);
		
		tx.commit();
		
		System.out.println("Records inserted");
		System.out.println(emp);
		
		session.close();
	}
}
