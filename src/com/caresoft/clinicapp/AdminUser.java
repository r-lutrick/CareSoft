package com.caresoft.clinicapp;

import java.util.ArrayList;
import java.util.Date;

public class AdminUser extends User implements HIPAACompliantUser, HIPAACompliantAdmin {
	// MEMBERS
    private Integer employeeID;
    private String role;
    private ArrayList<String> securityIncidents = new ArrayList<String>();
    
    // CONSTRUCTORS
	public AdminUser(Integer id) {
		super(id);
	}
	public AdminUser(Integer id, String role) {
		super(id);
		this.role = role;
	}
	
    // GETTERS
	public Integer getEmployeeID() {
		return employeeID;
	}
	public String getRole() {
		return role;
	}
	public ArrayList<String> getSecurityIncidents() {
		return securityIncidents;
	}
	// SETTERS
	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setSecurityIncidents(ArrayList<String> securityIncidents) {
		this.securityIncidents = securityIncidents;
	}
	
	// METHOD OVERRIDES
	@Override
	public ArrayList<String> reportSecurityIncidents() {
		return securityIncidents;
	}
	@Override
	public boolean assignPin(int pin) {
		String sPin = String.valueOf(pin);
		if (sPin.length() >= 6) {
			setPin(pin);
			return true;
		}
		return false;
	}
	@Override
	public boolean accessAuthorized(Integer confirmedAuthID) {
		
		if (getId() == confirmedAuthID) {
			return true;
		}
		authIncident();
		return false;
	}
	
	// INCIDENT METHODS
	public void newIncident(String notes) {
        String report = String.format(
            "Datetime Submitted: %s \n,  Reported By ID: %s\n Notes: %s \n", 
            new Date(), this.id, notes
        );
        securityIncidents.add(report);
    }
    public void authIncident() {
        String report = String.format(
            "Datetime Submitted: %s \n,  ID: %s\n Notes: %s \n", 
            new Date(), this.id, "AUTHORIZATION ATTEMPT FAILED FOR THIS USER"
        );
        securityIncidents.add(report);
    }
}
