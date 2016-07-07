package edu.cn.action.admin;

import java.util.HashMap;
import java.util.List;

import org.dom4j.io.STAXEventReader;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.Action;

import edu.cn.domain.Assistant;
import edu.cn.domain.Student;
import edu.cn.domain.Teacher;
import edu.cn.domain.TeacherInCharge;
import edu.cn.domain.User;
import edu.cn.service.UserService;
import edu.cn.serviceImpl.UserServiceImpl;

public class UserAction implements Action {
	private int id;

	private User user;

	private JSONObject json;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

	@Override
	public String execute() throws Exception {//获得学生信息列表
		UserService us=new UserServiceImpl();
		List<Student> students=us.getStudentList();
		for(Student s:students){
			s.setCourseList(null);
			if(s instanceof Student){
				s.setUserType("student");
			}	
			if(s instanceof Assistant){
				((Assistant) s).setCourseAssistList(null);
				s.setUserType("assistant");
			}
				
		}
		java.util.Map<String, Object> map=new HashMap<String, Object>();
		map.put("rows",students);
		json = JSONObject.fromObject(map); 
		return SUCCESS;
	}
	
	public String getTeachers() throws Exception{//获得教师信息列表
		UserService us=new UserServiceImpl();
		List<Teacher> teachers=us.getTeacherList();
		for(Teacher t:teachers){
			t.setCourseList(null);
			if(t instanceof Teacher)
				t.setUserType("teacher");
			if(t instanceof TeacherInCharge)
				t.setUserType("teacherInCharge");
		}
		java.util.Map<String, Object> map=new HashMap<String, Object>();
		map.put("rows",teachers);
		json = JSONObject.fromObject(map);  
		return SUCCESS;
	}

	public String addStudent() throws Exception {// 添加学生
		UserServiceImpl us = new UserServiceImpl();
		if (user.getUserType().equals("student")) {
			us.addUser(new Student(user.getUsername(), user.getPassword()));
		}
		if (user.getUserType().equals("assistant")) {
			us.addUser(new Assistant(user.getUsername(), user.getPassword()));
		}
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	public String addTeacher() throws Exception {// 添加教师
		UserServiceImpl us = new UserServiceImpl();
		if (user.getUserType().equals("teacher")) {
			us.addUser(new Teacher(user.getUsername(), user.getPassword()));
		}
		if (user.getUserType().equals("teacherInCharge")) {
			us.addUser(new TeacherInCharge(user.getUsername(), user
					.getPassword()));
		}
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	public String deleteUser() throws Exception{//删除用户
		UserService us=new UserServiceImpl();
		us.deleteUser(id);
		java.util.Map<String, Object> map=new HashMap<String, Object>();
		map.put("success", true);
		json=JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	public String updateStudent() throws Exception{//更新学生信息
		UserService us = new UserServiceImpl();
		List<User> list=us.getUserList();
		for(User u:list){
			if(u.getId()==user.getId()){
				u.setUsername(user.getUsername());
				u.setPassword(user.getPassword());
				int type=-1;
				if(user.getUserType().equals("student"))
					type=1;
				if(user.getUserType().equals("assistant"))
					type=2;
				us.updateUser(u, type);
			}
		}
		java.util.Map<String, Object> map=new HashMap<String, Object>();
		map.put("success", true);
		json=JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	public String updateTeacher() throws Exception{//更新教师信息
		UserService us = new UserServiceImpl();
		List<User> list=us.getUserList();
		for(User u:list){
			if(u.getId()==user.getId()){
				u.setUsername(user.getUsername());
				u.setPassword(user.getPassword());
				int type=-1;
				if(user.getUserType().equals("teacher"))
					type=3;
				if(user.getUserType().equals("teacherInCharge"))
					type=4;
				us.updateUser(u, type);
			}
		}
		java.util.Map<String, Object> map=new HashMap<String, Object>();
		map.put("success", true);
		json=JSONObject.fromObject(map);
		return SUCCESS;
	}

}
