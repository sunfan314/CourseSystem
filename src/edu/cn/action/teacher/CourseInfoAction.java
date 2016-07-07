package edu.cn.action.teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.Action;

import edu.cn.domain.Assistant;
import edu.cn.domain.CourseInfo;
import edu.cn.domain.Teacher;
import edu.cn.service.UserService;
import edu.cn.serviceImpl.UserServiceImpl;

public class CourseInfoAction implements Action {
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private JSONObject json;

	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

	@Override
	public String execute() throws Exception {//获得课程_助教信息
		UserService us = new UserServiceImpl();
		List<Teacher> teachers = us.getTeacherList();
		List<CourseInfo> courseInfos = new ArrayList<CourseInfo>();
		for (Teacher t : teachers) {
			if (t.getId() == id)
				courseInfos = t.getCourseList();
		}
		for (CourseInfo c : courseInfos) {
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
			c.setHomeworkList(null);
			c.setAssistants(assistants);
			c.setTeacherList(null);
			c.setAssList(null);
			c.setStudentList(null);
		}
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", courseInfos);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

}
