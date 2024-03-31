package com.rnt.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Configuration cfg = new Configuration().configure();
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        //Insert Value
    	/* Employee emp = new Employee("Ethan", "huntethan@gmail.com", "9876543210");
		 * 
		 * List<Address> addresslist = new ArrayList<>(); addresslist.add(new
		 * Address("Downtown", "ViceCity", "USA", 540053, emp)); addresslist.add(new
		 * Address("Little Haiti", "ViceCity", "USA", 540053, emp)); addresslist.add(new
		 * Address("Starfish Island", "ViceCity", "USA", 540053, emp));
		 * 
		 * emp.setAddresses(addresslist);
		 */
        
        //Get Value
		/* Employee emp = session.get(Employee.class, 1); System.out.println(emp);
		 * for(Address addr:emp.getAddresses()) System.out.println(addr);
		 */
        
        Employee emp = session.get(Employee.class,1);
        session.remove(emp);
        
		/* session.persist(emp); */
        tx.commit();
        session.close();
		/* System.out.println("Records inserted"); */
		/* System.out.println("Records Retrieved"); */
        System.out.println("Records Deleted");
    }
}
