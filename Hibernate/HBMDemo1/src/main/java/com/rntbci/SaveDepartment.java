package com.rntbci;

public class SaveDepartment {

	public static void main(String[] arg) {

		 DepartmentDAO dao = new DepartmentDAO();
		
		  Department department = new Department(108,"Chemistry", "Goa"); 
		  String result = dao.insertDepartment(department); 
		  System.out.println(result);
		 
		
		/*
		 * Department department = new
		 * Department(107, "CLoud", "Congo"); String result =
		 * dao.updateDepartment(department);
		 */
		
		/*
		 * System.out.println(dao.getDepartment(1));
		 * 
		 * String result = dao.deleteDepartment(1); System.out.println(result);
		 */
	}
}
