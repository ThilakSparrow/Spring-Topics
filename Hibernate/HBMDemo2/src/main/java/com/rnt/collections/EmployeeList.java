package com.rnt.collections;

import java.util.List;

public class EmployeeList {
	
	private int empid;
	private String name;
	private List<String> Skills;
	
	public EmployeeList() {
		super();
	}
	
	public EmployeeList(int empid, String name, List<String> skills) {
		super();
		this.empid = empid;
		this.name = name;
		Skills = skills;
	}
	
	public int getEmpid() {
		return empid;
	}
	
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<String> getSkills() {
		return Skills;
	}
	
	public void setSkills(List<String> skills) {
		Skills = skills;
	}
	
	@Override
	public String toString() {
		return "EmployeeList [empid=" + empid + ", name=" + name + ", Skills=" + Skills + "]";
	}
}
