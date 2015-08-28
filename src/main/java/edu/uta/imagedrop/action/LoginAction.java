	package edu.uta.imagedrop.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.uta.imagedrop.model.User;
import edu.uta.imagedrop.mysql.impl.DaoMysqlImpl;

public class LoginAction extends ActionSupport implements SessionAware{
	final String SESSION_NAME="ImageDropUser";
	private String email;
	private String password;
	
	private User user;
	private Map<String, Object> sessionAttributes;
	
	
	private static final long serialVersionUID = 1L;
	
	public String authenticate() throws Exception{
		System.out.println("Authenticate");
		DaoMysqlImpl daoMysqlImpl = new DaoMysqlImpl();
		int userId = daoMysqlImpl.authenticate(email, password);
		
		if(userId != 0)
		{
			user = new User();
			user.setEmail(email);
			user.setUserId(userId);
			user.setName("");
			sessionAttributes.put(SESSION_NAME, user);
			System.out.println("Success");
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	public String execute() throws Exception {
         
	        return SUCCESS;
	}
	
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionAttributes = sessionMap;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
