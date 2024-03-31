package com.rnt.component;

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
		
		Address address = new Address("Whitefield Rd","Bengaluru","Karnataka",560048);
		
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
