package com.rntbci;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "department")
public class Department {
	
	@Id
	private int dno;
	private String dname;
	private String loc;
	
	public Department() {
		super();
	
	}
	public Department(int dno, String dname, String loc) {
		super();
	    this.dno = dno;
		this.dname = dname;
		this.loc = loc;
	}
	public int getdno() {
		return dno;
	}
	public void setdno(int dno) {
		this.dno = dno;
	}
	public String getdname() {
		return dname;
	}
	public void setdname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	
	@Override
	public String toString() {
		return "Department [dno=" + dno + ", dname=" + dname + ", loc=" + loc + "]";
	}
}
