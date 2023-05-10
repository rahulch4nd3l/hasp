package com.rahul.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



///Update Query
///UPDATE `service` SET `status`='Completed' WHERE serviceType='TV' AND email ='rahulchandel@gmail.com';


public class Navigation {
	
	User user;
	//List<Service> service;
	Service[] service = new Service[10];
	String url = "jdbc:mysql://localhost:3306/test";
	String username = "root";
	String password = "";
	Connection conn;
	Statement stmt;
	
	
	
	/*			
	**	Method for home page	
	*/	
	@RequestMapping("/")
	public String mainPage() {
		return "login";
	}
	
	
	/*			
	**	Method for user profile page	
	*/	
	@RequestMapping("/profile")
	public String profile(Model model) {
		model.addAttribute("user", user);
		return "profile";
	}
	
	
	
	/*			
	**	Method for user login verification	
	*/	
	@RequestMapping("/login")
	public String verify(@RequestParam("email") String email, @RequestParam("password") String pass, Model model) {
		
		
		try {
			//Class.forName("com.mysql.cj.jdbc.Driver")
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);
			
			System.out.println("Connection Checking");
			
			
			System.out.println("Connection Successful!!!");
			stmt = conn.createStatement();
			//String query = SELECT * FROM `user` WHERE email= "rahulchandel@gmail.com;";
			ResultSet rs = stmt.executeQuery("Select * from user where email = \"" + email +"\";");
			
			rs.next();
			
			if(rs.getString("password").equals(pass)) {
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				String email1 = rs.getString("email");
				String phone  = rs.getString("phone");
				String add = rs.getString("address");
				String city = rs.getString("city");
				String country = rs.getString("country");
				
				user = new User(fname, lname, email1, pass, phone, add, city, country);
				
				
				//model.addAttribute("user", user);
				return "redirect:/profile";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "redirect:/";
	}
	
	
	/*			
	**	Method for getting data for user Schedule Info page	
	*/	
	
	@RequestMapping("/view")
	public String viewdata() throws SQLException {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(user.getEmail());
		System.out.println();
		System.out.println();
		System.out.println();
		
		System.out.println();System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		
		String query = "SELECT * FROM `service` WHERE email = \"" +user.getEmail()+ "\";";
		ResultSet rs = stmt.executeQuery(query);
		//service  = new ArrayList<>();
		int i=0;
		while(rs.next()) {
			String serviceType = rs.getString("serviceType");
			String techName = rs.getString("techName");
			String schedule = rs.getString("schedule");
			String status = rs.getString("status");
			//service.add(new Service(serviceType, techName, schedule, status));
			service[i] = new Service(serviceType, techName, schedule, status);
			i++;
		}
		
		return "redirect:/schedule";
				
	}
	
	
/*			
**	Method for user Schedule Info page	
*/	
	@RequestMapping("/schedule")
	public String scheduleTable(Model model) {
		model.addAttribute("user" , service);
		return "table";
	}
	
	
}
