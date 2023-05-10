package com.rahul.technician;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
@Entity
@Table(name="technician")
public class Technician {
	
	@Column(name = "fname")
	private String firstName;
	
	@Column(name = "lname")
	private String lastName;
	
	@Id
	@Column(name = "email")
	private String email;
	
	@Column(name = "pass")
	private String pass;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "serviceType")
	private String service;
	
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "country")
	private String country;
	
	public Technician() {
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		this.pass = "";
		this.phone = "";
		this.service = "";
		this.city = "";
		this.country = "";
	}
	
	public Technician(String firstName, String lastName, String email, String pass, String phone, String service, String city,
			String country) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.pass = pass;
		this.phone = phone;
		this.service = service;
		this.city = city;
		this.country = country;
	}
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Technician [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", pass=" + pass
				+ ", phone=" + phone + ", service=" + service + ", city=" + city + ", country=" + country + "]";
	}

	
	
}
