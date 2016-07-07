package edu.cn.action.teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.Action;

import edu.cn.domain.CourseInfo;
import edu.cn.domain.Teacher;
import edu.cn.service.UserService;
import edu.cn.serviceImpl.UserServiceImpl;

public class TeacherCourseAction implements Action {
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
		UserService us = new UserServiceImpl();
		List<Teacher> teachers = us.getTeacherList();
		List<CourseInfo> teacherCourses = new ArrayList<CourseInfo>();
		for (Teacher t : teachers) {
			if (t.getId() == id)
				teacherCourses = t.getCourseList();
		}
		for (CourseInfo c : teacherCourses) {
			c.setStudentList(null);
			c.setAssList(null);
			c.setTeacherList(null);
			c.setHomeworkList(null);
		}
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", teacherCourses);
		json = JSONObject.fromObject(map);
		return SUCCESS;

	}

}
