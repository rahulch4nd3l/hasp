package com.rahul.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "service")
public class userService {
	
	@Id
	@GenericGenerator(name="service" , strategy="increment")
	@GeneratedValue(generator="service")
	@Column(name= "serviceID")
	private int serviceID;
	

	@Column(name = "email")
	private String email;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "city")
	private String city;
	
	
	@Column(name = "techname")
	private String techName;
	
	@Column(name = "service")
	private String service;
	
	@Column(name = "date")
	private String date;
	
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "status")
	private String status = "Pending";

	public userService() {
		
	}
	
	public userService(String email, String name, String city, String techName, String service, String date, String phone) {
		this.email = email;
		this.name = name;
		this.city = city;
		this.techName = techName;
		this.service = service;
		this.date = date;
		this.phone = phone;
	}
	
	public userService(int id, String email, String city,String techName, String service, String date, String phone) {
		this.serviceID = id;
		this.email = email;
		this.city = city;
		this.techName = techName;
		this.service = service;
		this.date = date;
		this.phone = phone;
	}
		
	public String getTechName() {
		return techName;
	}

	public void setTechName(String techName) {
		this.techName = techName;
	}

	
	public int getServiceID() {
		return serviceID;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "userService [email=" + email + ", city=" + city + ", techName=" + techName
				+ ", service=" + service + ", date=" + date + "]";
	}

	
	
	
}
