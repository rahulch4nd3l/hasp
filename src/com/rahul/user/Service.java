package com.rahul.user;

public class Service {
	private String serviceType;
	private String techName;
	private String schedule;
	private String status;
	public Service(String serviceType, String techName, String schedule, String status) {
		super();
		this.serviceType = serviceType;
		this.techName = techName;
		this.schedule = schedule;
		this.status = status;
	}
	
	public String getServiceType() {
		return serviceType;
	}
	
	public String getTechName() {
		return techName;
	}
	
	public String getSchedule() {
		return schedule;
	}
	
	public String getStatus() {
		return status;
	}
	
}
