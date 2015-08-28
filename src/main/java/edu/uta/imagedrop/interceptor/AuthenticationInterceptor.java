package edu.uta.imagedrop.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import edu.uta.imagedrop.model.User;

public class AuthenticationInterceptor implements Interceptor {

	private static final long serialVersionUID = 1L;
	
	Map<String, Object> sessionAttributes = null;
	
	User user;
	final String SESSION_NAME="ImageDropUser";
	Action action;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		sessionAttributes = actionInvocation.getInvocationContext().getSession();
		
		user = (User) sessionAttributes.get(SESSION_NAME);
		
		if(user == null){
			return Action.LOGIN;
		}else{
			action = (Action) actionInvocation.getAction();
			if(action instanceof UserAware){
				((UserAware) action).setUser(user);
			}
			return actionInvocation.invoke();
		}
	}

}
