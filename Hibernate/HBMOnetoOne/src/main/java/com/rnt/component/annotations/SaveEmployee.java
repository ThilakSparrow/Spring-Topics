package com.rnt.component.annotations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class SaveEmployee {
	public static void main(String[] args) {
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		Address address = new Address("622 Broadway Ste 6","New York","USA",10012);
		
		Employee emp = new Employee();
		emp.setName("Thilak");	
		emp.setAddress(address);
		
		session.persist(emp);
		tx.commit();
		
		System.out.println("Records inserted");
		System.out.println(emp);
		
		session.close();
	}
}
