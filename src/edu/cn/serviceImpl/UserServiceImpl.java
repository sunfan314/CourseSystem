package edu.cn.serviceImpl;

import java.util.List;

import edu.cn.dao.CourseSystemDao;
import edu.cn.daoImpl.CourseSystemDaoImpl;
import edu.cn.domain.Assistant;
import edu.cn.domain.Student;
import edu.cn.domain.Teacher;
import edu.cn.domain.User;
import edu.cn.service.UserService;

public class UserServiceImpl implements UserService {
	private CourseSystemDao myDao=CourseSystemDaoImpl.getInstance();
	
	public void addUser(User user){
		myDao.addUser(user);
		
	}
	
	public void updateUser(User user,int type){
		myDao.updateUser(user, type);
	}
	
	public void deleteUser(int id){
		myDao.deleteUser(id);
	}
	
	public List<User> getUserList(){
		return myDao.getUsers();
	}
	
	public List<Student> getStudentList(){
		return myDao.getStudents();
	}
	
	public List<Teacher> getTeacherList(){
		return myDao.getTeachers();
	}
	
	public List<Assistant> getAssistantList(){
		return myDao.getAssistants();
	}
	
	
}
