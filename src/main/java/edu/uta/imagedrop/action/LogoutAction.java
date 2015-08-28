package edu.uta.imagedrop.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;

	private Map<String, Object> sessionAttributes;
	
	public String Logout(){
		return "SUCCESS";
	}
	
	@Override
	public void setSession(Map<String, Object> sessionAttributes) {
		this.sessionAttributes = null;
		sessionAttributes = this.sessionAttributes;
	}
}