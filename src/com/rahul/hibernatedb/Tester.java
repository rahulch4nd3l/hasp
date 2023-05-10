package com.rahul.hibernatedb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.rahul.service.Email;
import com.rahul.service.serviceDB;
import com.rahul.service.userService;
import com.rahul.user.User;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		UserData db1  = new UserData();
//		User user = new User();
//		user.setFirstName("Rahul");
//		user.setLastName("Chandel");
//		user.setEmail("rahul@gmail.com");
//		user.setPass("rahulchandel");
//		String email = user.getEmail();
//		
//		db1.setData(user.getEmail(), user);
////		
////		
//		System.out.println("Getting Data...");
//		User user1 = db1.getData("princerahul@gmail.com");
////		
//		System.out.println("Printing Data....");
//		System.out.println(user1);
		
		//String date = "2022-12-20";
		//userService ser = new userService("rahulchandel@gmail.com", "Moradabad", "AC", date);
		
//		serviceDB db = new serviceDB();
//		
//		List<userService> out = db.getServiceData("rahulchandel@gmail.com");
//		
//		for(int i=0; i<out.size(); i++) {
//			System.out.println(out.get(i).toString());
//		}
		
//		TechData techdb = new TechData();
//		Technician tech = new Technician("Joseph", "Dan", "fan@gmail.com", "fan", "9049029013", "Fan", "Moradabad", "india");
//		Technician tech1 = new Technician("Daniel", "Park", "ac@gmail.com", "ac", "8938498239", "AC", "Moradabad", "india");
//		Technician tech2 = new Technician("Harry", "Smack", "tv@gmail.com", "tv", "4564654654", "TV", "Moradabad", "india");
//		Technician tech3 = new Technician("Jason", "Josh", "washing@gmail.com", "washing", "1323243424", "Washing", "Moradabad", "india");
//		techdb.setData(tech.getEmail(), tech);
//		techdb.setData(tech1.getEmail(), tech1);
//		techdb.setData(tech2.getEmail(), tech2);
//		techdb.setData(tech3.getEmail(), tech3);
		
//		String name = techdb.getSpecificTechName("AC");
//		
		
		
//		userService serv = new userService();
//		serv.setEmail("princerahul42@gmail.com");
//		serv.setName("Rahul Chandel");
//		serv.setTechName("John Doe");
//		serv.setService("ac");
//		serv.setDate("2022-11-27");
//		serv.setCity("moradabad");
//		serv.setPhone("9923884233");
//		serv.setStatus("Pending");
	
//		Email email = new Email();
//		String msg = "Hello " + serv.getName() + "! Your appointment is scheduled. Mr. " + serv.getTechName() + " will connect with you soon on phone for the appointment of " 
//				+ serv.getService() + " service at " + serv.getDate() + " ."
//				+ " Thanks!";
// 		
//		String sub = "HASP! Appointment Schedule.";
//		email.sendMail(serv.getEmail(), msg, sub);
		
//		serviceDB db = new serviceDB();
//		db.setServiceData(serv);
		
//		System.out.println(name);
		
//		try {
//			Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2022-12-11");
//			System.out.println(date);
//			System.out.println(date.before(new Date()));
//			
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate date = LocalDate.parse("2022-12-11", format);
		
		System.out.println(date.isEqual(LocalDate.now()));

		
	}

}
