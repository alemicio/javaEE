package it.screwdrivers.payroll.bean;

import it.screwdrivers.payroll.controller.EmployeeController;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("login")
@SessionScoped
public class LogBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject 
	EmployeeController e_controller;
	
	private String username;
	private String password;
	private String type_log;

	
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String performLogin(){
		
		
		
		System.out.println(type_log);
		
		return type_log;
		
    }

}
