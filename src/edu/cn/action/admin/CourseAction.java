package edu.cn.action.admin;

import java.util.List;

import net.sf.json.JSONArray;

import com.opensymphony.xwork2.Action;

import edu.cn.domain.Course;
import edu.cn.service.CourseService;
import edu.cn.serviceImpl.CourseServiceImpl;

public class CourseAction implements Action {
	private JSONArray  json;

	public JSONArray getJson() {
		return json;
	}

	public void setJson(JSONArray json) {
		this.json = json;
	}
	
	@Override
	public String execute() throws Exception {
		CourseService cs=new CourseServiceImpl();
		List<Course> courses=cs.getCourseList();
		json = JSONArray.fromObject(courses); 
		return SUCCESS;
	}

}
