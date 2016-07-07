package edu.cn.action.login;

import java.util.List;

import com.opensymphony.xwork2.Action;

import edu.cn.domain.User;
import edu.cn.service.LoginService;
import edu.cn.service.UserService;
import edu.cn.serviceImpl.LoginServiceImpl;
import edu.cn.serviceImpl.UserServiceImpl;


public class LoginProAction implements Action {
	private User user;
	
	private String userType;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String execute() throws Exception {
		UserService us=new UserServiceImpl();
		LoginService ls=new LoginServiceImpl();
		List<User> userList=us.getUserList();
		if(!ls.userLogin(user).equals("fail")){
			for(User u:userList){
				if(u.getUsername().equals(user.getUsername())&&u.getPassword().equals(user.getPassword()))
					this.setUser(u);
			}
		}
		this.setUserType(ls.userLogin(user));
		return userType;//�����û�����
	}

	
	
	
}
