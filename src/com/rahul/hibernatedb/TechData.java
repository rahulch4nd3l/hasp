package com.rahul.hibernatedb;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Scope;

import com.rahul.technician.Technician;

@Scope("prototype")
public class TechData {
	SessionFactory factory = null;

	private void connection() {
		factory = new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Technician.class).buildSessionFactory();
	}

	public Technician getData(String email) {

		TechData techConnection = new TechData();

		// System.out.println("Establishing Connection...");
		techConnection.connection();

		// System.out.println("Getting Connection...");
		Session sess = techConnection.factory.getCurrentSession();

		// System.out.println("Starting Transaction...");
		sess.beginTransaction();

		// System.out.println("Getting Data...");
		Technician user = (Technician) sess.get(Technician.class, email);
		sess.getTransaction().commit();
		sess.close();
		techConnection.factory.close();
		return user;
	}

	public void setData(String email, Technician user) {

		TechData techConnection = new TechData();

		// System.out.println("Establishing Connection...");
		techConnection.connection();

		// System.out.println("Getting Connection...");
		Session sess = techConnection.factory.getCurrentSession();

		// System.out.println("Transaction Start...");
		sess.beginTransaction();

		// sess.save(user);
		sess.saveOrUpdate(email, user);

		sess.getTransaction().commit();
		// System.out.println("Transaction Complete...");

		sess.close();
		techConnection.factory.close();

	}
		
	public String getSpecificTechName(String service) {
		
		TechData techdb = new TechData();
		techdb.connection();
		
		Session sess = techdb.factory.getCurrentSession();
		
		sess.beginTransaction();
		
		Query query = sess.createQuery("FROM Technician s WHERE s.service= '" + service +"'");
		
		Technician tech = (Technician) query.getSingleResult();
		
		return tech.getFirstName() +" " + tech.getLastName();
		
	}
	
	
}
