package com.rahul.user;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.rahul.hibernatedb.TechData;
import com.rahul.hibernatedb.UserData;
import com.rahul.service.serviceDB;
import com.rahul.service.userService;

@Scope("request")
@Controller
@SessionAttributes("user")
public class LinkController {
	User user = new User();
	String email = "";
	boolean check = true;
	boolean da = true;
	
	@RequestMapping("/")
	public String home() {
		
		return "home";
	}
	
	@RequestMapping("/login")
	public String loginPage(Model model) {
		if(check == false) {
			model.addAttribute("check", false);
		}
		check = true;
		return "login";
	}
	
	@RequestMapping(value = "/loginVerify", method = RequestMethod.POST)
	public String loginVerification(@RequestParam("email") String ema, @RequestParam("password") String pass, Model model) {
		UserData db = new UserData();
		email = ema;
		try {
			this.user = db.getData(email);
			if(user.getPass().equals(pass)) {
				
				
				email = user.getEmail();
				
				return "redirect:dashboard";
			} else {
				email = null;
				check = false;
				return "redirect:login";
			}
			
		} catch(Exception e) {
			
			check = false;
			return "redirect:login";
		} 
		
	}
	
	@RequestMapping(value = "/register")
	public String registerPage() {
		
		
		return "register";
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/addNew", method = RequestMethod.POST)
	public String addingNew(@ModelAttribute("first_name") String fname, @ModelAttribute("last_name") String lname, 
			@ModelAttribute("email") String email, @ModelAttribute("password") String password, @ModelAttribute("accountType") String accountType) {
		this.email= email;
		User user1 = new User();
		
		user1.setFirstName(fname.substring(0,1).toUpperCase() + fname.substring(1).toLowerCase());
		user1.setLastName(lname.substring(0,1).toUpperCase() + lname.substring(1).toLowerCase());
		user1.setEmail(email);
		user1.setPass(password);
		UserData db = new UserData();
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
		UserData db = new UserData();
		if(this.email == null) {
			return "redirect:/";
		}
		
		
//		if(user==null) {
//			
//			user = db.getData(email);
//			
//		}
		
		if(user.getPhone() == "") {
			return "redirect:profilepage";
		}
		
		
		model.addAttribute("user",user);
		if(check == false) {
			
			model.addAttribute("da", false);
			model.addAttribute("msg", "Request Raised Successfully...");
			
		}
		check = true;
		
		if(da == false) {
			
			model.addAttribute("da", false);
			model.addAttribute("msg", "Invalid Date: Request date can't be less than today.");
		}
		da = true;
		
		return "dashboard";
	}
		
	

	/*
	 * Booking Service Appointment
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value= "/bookservice")
	public String submitBookRequest(@ModelAttribute("city") String city, @ModelAttribute("serviceType") String service, 
			@ModelAttribute("date") String date) throws ParseException {
		
		if(user==null) {
			UserData db = new UserData();
			user = db.getData(this.email);
		}
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate validate = LocalDate.parse(date, format);
		System.out.println(validate.isBefore(LocalDate.now()));
		
		if(validate.isBefore(LocalDate.now())) {
			da = false;
			
			System.out.println("Redirecting " + da);
			return "redirect:dashRedirect";
		}
		
		TechData techdb = new TechData();
		String techName = techdb.getSpecificTechName(service);
		String name = user.getFirstName() + " " + user.getLastName();
		userService ser = new userService(user.getEmail(), name, city, techName, service, date, user.getPhone());
		
		serviceDB serDB = new serviceDB();
		serDB.setServiceData(ser);
		check = false;
		return "redirect:dashRedirect";
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
		UserData db = new UserData();
		if(this.email == null) {
			return "redirect:/";
		}
		try {
			
			user = db.getData(email);
			model.addAttribute("user", user);
			if(check == false) {
				model.addAttribute("check", false);
				model.addAttribute("msg", "Data is Saved..");
			}
			check = true;
			
			
		} catch (Exception e){
			return "redirect:login";
		}
		
		
		
		return "profile";
	}
	
	
	/*
	 * For Saving Data from Profile Page
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value= "/saveBasic")
	public String saveBasicData(@ModelAttribute("first_name") String fname, @ModelAttribute("last_name") String lname, 
			@ModelAttribute("email") String email, @ModelAttribute("phone") String phone) {
		
		
		
		UserData db = new UserData();
		user = db.getData(this.email);
		user.setFirstName(fname);
		user.setLastName(lname);
		user.setPhone(phone);
		db.setData(this.email, user);
		check = false;
		return "redirect:profilepage";
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value= "/saveLocation")
	public String saveLocationData(@ModelAttribute("address") String add, @ModelAttribute("city") String city,
			@ModelAttribute("country") String country) {
		UserData db = new UserData();
		if(user == null)
			user = db.getData(this.email);
		
		user.setCity(city);
		user.setAdd(add);
		user.setCountry(country);
		
		db.setData(this.email, user);
		check = false;
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
		UserData db = new UserData();
		if(user == null)
			user = db.getData(this.email);
		model.addAttribute("user", user);
		if(check == false) {
			model.addAttribute("check", false);
			model.addAttribute("msg", "Password Updated..");
			check = true;
		}
		if(da == false) {
			model.addAttribute("check", false);
			model.addAttribute("msg", "Old Password doesn't match..");
			da = true;
		}
		
		
		return "settings";
	}
	
	@RequestMapping(value = "/settingspage")
	public String settingsRedirect() {
		return "redirect:settings";
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/changePass")
	public String changePassword(@ModelAttribute("oldpass") String old, @ModelAttribute("newPass") String newPass, @ModelAttribute("repeatnewPass") String rNewPass, Model model) {
		
		if(!rNewPass.equals(newPass)) {
			model.addAttribute("check", false);
			model.addAttribute("msg", "New Passwords doesn't match.");
			return "settings";
		}
		UserData db = new UserData();
		if(user == null) {
			user = db.getData(this.email);
		}
		
		if(user.getPass().equals(old)) {
			user.setPass(newPass);
			db.setData(user.getEmail(), user);
			check = false;
			return "redirect:settingspage";
		} 
		da = false;
		return "redirect:settingspage";
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
		
		List<userService> list = db.getServiceData(this.email);
		model.addAttribute("serviceData", list);
		model.addAttribute("user", user);
		return "service";
	}
	
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/logout")
	public String logout() {
		this.email = null;
		return "redirect:/";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	
//	@RequestMapping("/new")
//	public String page() {
//		return "NewFile";
//	}
// 	 
//	@RequestMapping("/validate")
//	public String validate(@ModelAttribute("name") String name, BindingResult bindingResult) {
//		
//		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "name", "invalid name");
//		return "redirect:new";
//		
//	}
	
}
