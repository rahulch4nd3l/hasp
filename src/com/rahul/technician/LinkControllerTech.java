package com.rahul.technician;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.rahul.hibernatedb.TechData;
import com.rahul.service.serviceDB;
import com.rahul.service.userService;


@Scope("request")
@Controller
@RequestMapping("/tech")
public class LinkControllerTech {
	
	Technician user;
	
	static String email;
	static boolean sta = false;
	static boolean check = false;
	
	@RequestMapping("/login")
	public String login(Model model) {
		if(check == true) {
			model.addAttribute("check", false);
			check = false;
		}
		
		return "technician/login";
	}
	
	@RequestMapping(value = "/loginVerify", method = RequestMethod.POST)
	public String loginVerification(@RequestParam("email") String ema, @RequestParam("password") String pass, Model model) {
		TechData db = new TechData();
		email = ema;
		try {
			user = db.getData(email);
			if(user.getPass().equals(pass)) {
				//System.out.println(true);
				
				email = user.getEmail();
				//System.out.println(email);
				return "redirect:dashboard";
			} else {
				email = null;
				check = true;
				
				return "redirect:login";
			}
			
		} catch(Exception e) {
			
			check = true;
			return "redirect:login";
		} 
		
	}
	
	
	@SuppressWarnings("static-access")
	@RequestMapping("/addNew")
	public String addingNew(@ModelAttribute("first_name") String fname, @ModelAttribute("last_name") String lname, 
			@ModelAttribute("email") String email, @ModelAttribute("password") String password, @ModelAttribute("accountType") String accountType) {
		this.email= email;
		Technician user1 = new Technician();
		user1.setFirstName(fname);
		user1.setLastName(lname);
		user1.setEmail(email);
		user1.setPass(password);
		TechData db = new TechData();
		try {
		
			user = db.getData(email);
			
			if(user != null) {
				
				return "redirect:register";
			} else {
				user = user1;
				
				db.setData(this.email, user);
				
				return "redirect:profilepage"; 
			}
		} catch(Exception e) {
			return "redirect:register";
		}
		 
	}
	
	
	/*
	 * Redirect Dashboard
	*/
	@RequestMapping(value = "/dashRedirect")
	public String dashBoardPageRedirect() {
		
		return "redirect:dashboard";
	}
	
	/*
	 *	For Dashboard 
	*/
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/dashboard")
	public String dashboardPage(Model model) {
		TechData db = new TechData();
		if(this.email == null) {
			return "redirect:/";
		}
		
		if(user==null) {
			//System.out.println("User is null..");
			//System.out.println(email);
			user = db.getData(email);
			
		}
		
		serviceDB dbSer = new serviceDB();
		
		List<userService> list = dbSer.getSpecificTechData(user.getService(), "Pending");
		
		model.addAttribute("serviceData", list);
		model.addAttribute("tech", user);
		if(sta== true) {
			model.addAttribute("up", false);
			model.addAttribute("message", "Requested Service Status Updated.");
			sta = false;
		}
		return "technician/dashboard";
	}
	
	/*
	 * Technician to change status of Service appointment
	 */
	@RequestMapping(value = "/updateStatus")
	public String updateStatus(@ModelAttribute("client") String name, @ModelAttribute("serviceType") String serv,
			@ModelAttribute("date") String date) {
		serviceDB dbSer = new serviceDB();
		try {
			dbSer.setSpecific(name, serv, date);
			sta = true;
		} catch(Exception e) {
			
		}
		return "redirect:dashRedirect";
		
	}
	
	
	
	@RequestMapping(value = "/servicepage")
	public String redirectService() {
		
		return "redirect:service";
	}
	
	/*
	**For Service page Mapping
	*/
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/service")
	public String servicePage(Model model) {
		serviceDB db = new serviceDB();
		if(this.email == null) {
			return "redirect:/";
		}
		if(user == null) {
			TechData db1 = new TechData();
			user = db1.getData(email);
			
		}
		
		List<userService> list = db.getSpecificTechData(user.getService(), "Completed");
		model.addAttribute("serviceData", list);
		model.addAttribute("tech", user);
		return "technician/service";
	}
	
	
	/*
	**Redirecting to this for URL Cleaning for Profile Page
	*/
	@RequestMapping(value = "/profilepage")
	public String redirectProfile() {
		
		return "redirect:profile";
	}
	
	
	/*
	**For Profile page mapping
	*/
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/profile")
	public String profilePage(Model model) {
		TechData db = new TechData();
		if(this.email == null) {
			return "redirect:/";
		}
		try {
			user = db.getData(email);
			model.addAttribute("user", user);
		} catch (Exception e){
			return "redirect:login";
		}
		
		model.addAttribute("tech",user);
		if(sta == true) {
			model.addAttribute("check", false);
			model.addAttribute("msg", "Data is saved..");
			sta = false;
		}
		
		
		return "technician/profile";
	}
	
	
	/*
	 * For Saving Data from Profile Page
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value= "/saveBasic")
	public String saveBasicData(@ModelAttribute("first_name") String fname, @ModelAttribute("last_name") String lname, 
			@ModelAttribute("email") String email, @ModelAttribute("phone") String phone) {
		
		TechData db = new TechData();
		user = db.getData(this.email);
		user.setFirstName(fname);
		user.setLastName(lname);
		user.setPhone(phone);
		db.setData(this.email, user);
		sta = true;
		
		return "redirect:profilepage";
	}
	
	
	@SuppressWarnings("static-access")
	@RequestMapping(value= "/saveLocation")
	public String saveLocationData(@ModelAttribute("serviceType") String service, @ModelAttribute("city") String city,
			@ModelAttribute("country") String country) {
		TechData db = new TechData();
		if(user == null)
			user = db.getData(this.email);
		
		user.setCity(city);
		user.setService(service);
		user.setCountry(country);
		
		db.setData(this.email, user);
		sta = true;
		return "redirect:profilepage";
		
	}
	
	
	

	/*
	**For Settings page Mapping
	*/
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/settings")
	public String settingsPage(Model model) {
		if(this.email == null) {
			return "redirect:/";
		}
		TechData db = new TechData();
		if(user == null)
			user = db.getData(this.email);
		model.addAttribute("tech", user);
		if(check == true) {
			model.addAttribute("check", false);
			model.addAttribute("msg", "Password Updated..");
			check = false;
		}
		if(sta == true) {
			model.addAttribute("check", false);
			model.addAttribute("msg", "Old Password doesn't match..");
			sta = false;
		}
		
		
		
		
		return "technician/settings";
	}
	
	@RequestMapping(value ="/settingspage")
	public String settingRedirect() {
		return "redirect:settings";
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/changePass")
	public String changePassword(@ModelAttribute("oldpass") String old, @ModelAttribute("newPass") String newPass, @ModelAttribute("repeatnewPass") String rNewPass, Model model) {
		
		
		if(!rNewPass.equals(newPass)) {
			model.addAttribute("check", false);
			model.addAttribute("msg", "New Passwords doesn't match.");
			return "technician/settings";
		}
		TechData db = new TechData();
		if(user == null) {
			user = db.getData(this.email);
		}
		
		if(user.getPass().equals(old)) {
			user.setPass(newPass);
			db.setData(user.getEmail(), user);
			check = true;
			return "redirect:settingspage";
		} 
		sta = true;
		return "redirect:settingspage";
	}
	
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/logout")
	public String logout() {
		this.email = null;
		return "redirect:/";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
