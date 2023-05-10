package com.rahul.service;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Scope;;

@Scope("prototype")
public class serviceDB {

	SessionFactory factory = null;
	
	private void connection() {
		factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(userService.class).buildSessionFactory();
	}
	
	public List<userService> getServiceData(String email) {
		serviceDB service = new serviceDB();
		service.connection();
		
		Session sess = service.factory.getCurrentSession();
		
		sess.beginTransaction();
		
		Query query = sess.createQuery("FROM userService s WHERE s.email= '" + email +"' order by s.date desc");
		
		@SuppressWarnings("unchecked")
		List<userService> dataList = query.getResultList();
		sess.getTransaction().commit();
		sess.close();
		service.factory.close();
		return dataList;
	}
	
	public void setServiceData(userService serv) {
		serviceDB service = new serviceDB();
		service.connection();
		
		Session sess = service.factory.getCurrentSession();
		
		sess.beginTransaction();
		String q = "FROM userService s WHERE s.email='" + serv.getEmail() +"' AND s.date = '" +serv.getDate() +"' AND s.service ='" +serv.getService()+ "'";
		Query query = sess.createQuery(q);
		userService temp = null;
		try {
			temp = (userService) query.getSingleResult();
		} catch(Exception e) {
		
			if(temp == null) 
				sess.saveOrUpdate(serv);
		}
		sess.getTransaction().commit();
		sess.close();
		service.factory.close();
		Email email = new Email();
		String msg = "Hello " + serv.getName() + "! Your appointment is scheduled. Mr. " + serv.getTechName() + " will connect with you soon on phone for the appointment of " 
				+ serv.getService() + " service at " + serv.getDate() + " ."
				+ " Thanks!";
 		
		String sub = "HASP! Appointment Schedule.";
		email.sendMail(serv.getEmail(), msg, sub);
		
	}
	
	public List<userService> getSpecificTechData(String serv, String status) {
		serviceDB service = new serviceDB();
		service.connection();
		
		Session sess = service.factory.getCurrentSession();
		
		sess.beginTransaction();
		Query query = sess.createQuery("FROM userService s WHERE s.service= '" + serv +"' AND s.status = '" +status + "' order by s.date asc");
		
		@SuppressWarnings("unchecked")
		List<userService> dataList = query.getResultList();
		sess.getTransaction().commit();
		sess.close();
		service.factory.close();
		return dataList;
		
	}
	
	public void setSpecific(String name, String serv, String date) {
		serviceDB service = new serviceDB();
		service.connection();
		
		Session sess = service.factory.getCurrentSession();
		
		sess.beginTransaction();
		
		Query query = sess.createQuery("FROM userService s WHERE s.service= '" + serv +"' AND s.name = '" +name + "' "
				+ "AND s.date = '" + date + "'");
		
		userService serviceData = (userService) query.getSingleResult();
		serviceData.setStatus("Completed");
		sess.update(serviceData);
		Email email = new Email();
		String msg = "Hello " + name + "! Your Service Request of " + serv + " is completed By . Mr. " + serviceData.getTechName() +  
				". Thanks!";
 		
		String sub = "HASP! Request Completed.";
		email.sendMail(serviceData.getEmail(), msg, sub);
		
		
		sess.getTransaction().commit();
		sess.close();
		service.factory.close();
		
		
		
	}
	
	
	
}
