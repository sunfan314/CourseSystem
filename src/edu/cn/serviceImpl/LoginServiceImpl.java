package edu.cn.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import edu.cn.dao.CourseSystemDao;
import edu.cn.daoImpl.CourseSystemDaoImpl;
import edu.cn.domain.Admin;
import edu.cn.domain.Assistant;
import edu.cn.domain.Student;
import edu.cn.domain.Teacher;
import edu.cn.domain.TeacherInCharge;
import edu.cn.domain.User;
import edu.cn.service.LoginService;

public class LoginServiceImpl implements LoginService {
	private static List<User> userList;
	static{
		CourseSystemDao myDao=CourseSystemDaoImpl.getInstance();
		userList=myDao.getUsers();
	}
	
	
	@Override
	public String userLogin(User user) {//根据用户名和密码查询数据库返回用户类型
		for(User u:userList){
			if(u.getUsername().equals(user.getUsername())&&u.getPassword().equals(user.getPassword())){
				if(u instanceof Admin)
					return "admin";
				if(u instanceof TeacherInCharge)
					return "teacherInCharge";
				if(u instanceof Teacher)
					return "teacher";
				if(u instanceof Assistant)
					return "assistant";
				if(u instanceof Student)
					return "student";
			}
		}
		return "fail";//用户登录失败
	}

}
