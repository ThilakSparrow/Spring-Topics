package com.rntbci;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DepartmentDAO {

	SessionFactory factory;

	public DepartmentDAO() {
		Configuration cfg = new Configuration().configure();
		factory = cfg.buildSessionFactory();
	}

	public String insertDepartment(Department department) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(department);
		tx.commit();
		session.close();
		return "inserted successfully";
	}

	public String updateDepartment(Department department) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.merge(department);
		tx.commit();
		session.close();
		return " successfully updated";
	}

	public Department getDepartment(int dno) {
		Session session = factory.openSession();
		Department deptobj = new Department();
		deptobj = session.get(Department.class, dno);
		session.close();
		return deptobj;
	}

	public String deleteDepartment(int dno) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(dno);
		tx.commit();
		session.close();
		return " successfully deleted";
	}
}
