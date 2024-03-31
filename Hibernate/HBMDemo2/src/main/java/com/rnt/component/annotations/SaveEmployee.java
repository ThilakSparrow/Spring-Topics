package com.rnt.component.annotations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.rnt.component.Address;
import com.rnt.component.Employee;

public class SaveEmployee {
	public static void main(String[] args) {
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		Address address = new Address("Haitian Second Road","Shenzhen","China",132567);
		
		Employee emp = new Employee();
		emp.setName("P.E.K.K.A");	
		emp.setAddress(address);
		
		session.persist(emp);
		tx.commit();
		
		System.out.println("Records inserted");
		System.out.println(emp);
		
		session.close();
	}
}
