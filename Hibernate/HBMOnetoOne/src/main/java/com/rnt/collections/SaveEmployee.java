package com.rnt.collections;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class SaveEmployee {
	public static void main(String[] args) {
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		List<String> skills = new ArrayList<>();
		skills.add("Graphics Design");
		skills.add("3D Animation");
		skills.add("Cloud Computing");
		
		EmployeeList emp = new EmployeeList();
		emp.setName("Cygnus");
		emp.setSkills(skills);
		
		session.persist(emp);
		tx.commit();
		
		System.out.println("Records inserted");
		System.out.println(emp);
		
		tx = session.beginTransaction();
		Query<EmployeeList> depts = session.createQuery("From EmployeeList");
		System.out.println(depts.list());
		
		session.close();
	}
}
