package com.rahul.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;


@Scope("prototype")
@Entity
@Table(name="user")
public class User {
	
	@Column(name = "fname")
	private String firstName;
	
	@Column(name ="lname")
	private String lastName;
	
	@Id
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String pass;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="address")
	private String add;
	
	@Column(name="city")
	private String city;
	
	@Column(name="country")
	private String country;
	
	public User () {
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		this.pass = "";
		this.phone = "";
		this.add = "";
		this.city = "";
		this.country = "";
	}
	public User(String firstName, String lastName, String email, String pass, String phone, String add, String city,
			String country) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.pass = pass;
		this.phone = phone;
		this.add = add;
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

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
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
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", pass="
				+ pass + ", phone=" + phone + ", add=" + add + ", city=" + city + ", country=" + country + "]";
	}
	
}
