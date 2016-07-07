package edu.cn.service;

import java.util.List;

import edu.cn.domain.Assistant;
import edu.cn.domain.CourseInfo;
import edu.cn.domain.Student;
import edu.cn.domain.Teacher;
import edu.cn.domain.User;

public interface UserService {
	public void addUser(User user);
	
	public void updateUser(User user,int type);
	
	public void deleteUser(int id);
	
	public List<User> getUserList();
	
	public List<Student> getStudentList();
	
	public List<Teacher> getTeacherList();
	
	public List<Assistant> getAssistantList();

}
