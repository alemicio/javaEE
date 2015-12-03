package it.screwdrivers.payroll.bean;

import it.screwdrivers.payroll.controller.EmployeeController;
import it.screwdrivers.payroll.pojo.employee.Employee;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named("login")
@SessionScoped
public class LogBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	EmployeeController e_controller;

	private String username;
	private String password;
	private String type;
	private Employee retrived_employee;

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

	public Employee getRetrived_employee() {
		return retrived_employee;
	}

	public void setRetrived_employee(Employee retrived_employee) {
		this.retrived_employee = retrived_employee;
	}

	public String performLogin() {

		retrived_employee = e_controller.checkLogin(username, password);

		if (retrived_employee == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_WARN,
					"Uncorrect username or password", "Invalid credentials"));
		} else {
			type= e_controller.findType(retrived_employee);
			System.out.println(type);
			
			// This line will set a session attribute that we will check before rendering the pages. 
			// --> DOPO CI RIPASSIAMO XD <--
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user_id", retrived_employee.getId());
		}
		
		return type;
	}
	
	public String performLogout() {
		
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    ec.invalidateSession();
	    
	    return "Logout";
	}
	
	public void checkIfLogged() {
		
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession)ec.getSession(false);
		
		Object current_user_id = session.getAttribute("user_id");
		
		if(current_user_id == null){
		
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null, "/xhtml/index.xhtml");
		}			
	}
}
