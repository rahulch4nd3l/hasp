package com.rahul.hibernatedb;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Scope;

import com.rahul.user.User;

@Scope("prototype")
public class UserData {
	SessionFactory factory = null;
	
	private void connection() {
		factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(User.class).buildSessionFactory();
	}
	
	
	public User getData(String email) {
		
		UserData userConnection = new UserData();
		
		//System.out.println("Establishing Connection...");
		userConnection.connection();
		//System.out.println("Connection sucesssfull ..");
		
		//System.out.println("Getting Connection...");
		Session sess=  userConnection.factory.getCurrentSession();
		
		
		//System.out.println("Starting Transaction...");
		sess.beginTransaction();
		//System.out.println("Getting data...");
		//System.out.println("Getting Data...");
		User user = (User) sess.get(User.class, email);
		sess.getTransaction().commit();
		sess.close();
		userConnection.factory.close();
		return user;
	}
	
	public void setData(String email, User user) {
		
		UserData userConnection = new UserData();
		
		//System.out.println("Establishing Connection...");
		userConnection.connection();
		
		//System.out.println("Getting Connection...");
		Session sess = userConnection.factory.getCurrentSession();
		
		//System.out.println("Transaction Start...");
		sess.beginTransaction();
		
		//sess.save(user);
		sess.saveOrUpdate(email, user);
		
		sess.getTransaction().commit();
		//System.out.println("Transaction Complete...");
		
		sess.close();
		userConnection.factory.close();
		
	}
	
}
