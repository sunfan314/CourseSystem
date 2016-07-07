package edu.cn.action.student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.Action;

import edu.cn.domain.Assistant;
import edu.cn.domain.CourseInfo;
import edu.cn.domain.Student;
import edu.cn.domain.Teacher;
import edu.cn.service.UserService;
import edu.cn.serviceImpl.UserServiceImpl;

public class StudentCourseAction implements Action {
	private int id;
	private JSONObject json;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

	@Override
	public String execute() throws Exception {
		UserService us=new UserServiceImpl();
		List<Student> students=us.getStudentList();
		List<CourseInfo> courseInfos=new ArrayList<CourseInfo>();
		for(Student s:students){
			if(s.getId()==id){
				courseInfos=s.getCourseList();
			}
		}
		for(CourseInfo c:courseInfos){
			String teachers="";//设置课程的教师字符串
			for(Teacher t:c.getTeacherList()){
				teachers+=t.getUsername();
				teachers+=" , ";
			}
			if(teachers.length()>0){
				teachers = teachers.substring(0,teachers.length() - 2);
			}
			else{
				teachers="NOT ALLOCATED";
			}
			String assistants = "";// 设置课程的助教字符串
			for (Assistant a : c.getAssList()) {
				assistants += a.getUsername();
				assistants += " , ";
			}
			if (assistants.length() > 0) {
				assistants = assistants.substring(0, assistants.length() - 2);
			} else {
				assistants = "NOT ALLOCATED";
			}
			c.setAssistants(assistants);
			c.setTeachers(teachers);
			c.setAssList(null);
			c.setHomeworkList(null);
			c.setStudentList(null);
			c.setTeacherList(null);
		}
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", courseInfos);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

}
