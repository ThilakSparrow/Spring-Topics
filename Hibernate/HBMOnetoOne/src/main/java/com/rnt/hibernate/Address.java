package com.rnt.hibernate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "Addr")
@Table(name = "new_add")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressid;
    private String street;
    private String city;
    private String state;
    private int pincode;

    @ManyToOne(cascade = CascadeType.ALL) // Changed cascade options here
    @JoinColumn(name = "emp_id")
    private Employee employee;

    public Address() {
        super();
    }

    public Address(String street, String city, String state, int pincode, Employee employee) {
        super();
        this.street = street;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.employee = employee;
    }

    public int getAddressid() {
        return addressid;
    }

    public void setAddressid(int addressid) {
        this.addressid = addressid;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Address [addressid=" + addressid + ", street=" + street + ", city=" + city + ", state=" + state
				+ ", pincode=" + pincode + /* ", employee=" + employee + */ "]";
    }
}
