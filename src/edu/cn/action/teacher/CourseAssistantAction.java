package edu.cn.action.teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.Action;

import edu.cn.domain.Assistant;
import edu.cn.domain.CourseInfo;
import edu.cn.service.CourseService;
import edu.cn.service.UserService;
import edu.cn.serviceImpl.CourseServiceImpl;
import edu.cn.serviceImpl.UserServiceImpl;

public class CourseAssistantAction implements Action {
	private int id;
	private JSONObject json;
	private JSONArray jsonArray;
	private CourseInfo courseInfo;
	private Assistant assistantToAdd;
	private Assistant assistantToRemove;

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

	public JSONArray getJsonArray() {
		return jsonArray;
	}

	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}

	public CourseInfo getCourseInfo() {
		return courseInfo;
	}

	public void setCourseInfo(CourseInfo courseInfo) {
		this.courseInfo = courseInfo;
	}

	public Assistant getAssistantToAdd() {
		return assistantToAdd;
	}

	public void setAssistantToAdd(Assistant assistantToAdd) {
		this.assistantToAdd = assistantToAdd;
	}

	public Assistant getAssistantToRemove() {
		return assistantToRemove;
	}

	public void setAssistantToRemove(Assistant assistantToRemove) {
		this.assistantToRemove = assistantToRemove;
	}

	@Override
	public String execute() throws Exception {
		CourseService cs = new CourseServiceImpl();
		List<Assistant> assistants = new ArrayList<Assistant>();
		List<CourseInfo> courseInfos = cs.getCourseInfoList();
		for (CourseInfo c : courseInfos) {
			if (c.getId() == id) {
				assistants = c.getAssList();
				for (Assistant a : assistants) {
					a.setCourseAssistList(null);
					a.setCourseList(null);
				}
			}
		}
		jsonArray = JSONArray.fromObject(assistants);
		return SUCCESS;
	}

	public String getAvailableAssistats() throws Exception {// 获得可分配的助教列表
		UserService us = new UserServiceImpl();
		CourseService cs = new CourseServiceImpl();
		List<Assistant> assistantList = us.getAssistantList();
		List<Assistant> courseAssistants = new ArrayList<Assistant>();
		for (CourseInfo c : cs.getCourseInfoList()) {
			if (c.getId() == id) {
				courseAssistants = c.getAssList();
			}
		}
		for (int i = 0; i < courseAssistants.size(); i++) {
			this.removeAssistantFromList(assistantList, courseAssistants.get(i));
		}
		for (Assistant a : assistantList) {
			a.setCourseList(null);
			a.setCourseAssistList(null);
		}
		jsonArray = JSONArray.fromObject(assistantList);
		return SUCCESS;
	}

	public String addCourseAssistant() throws Exception {// 增加课程助教
		CourseService cs = new CourseServiceImpl();
		cs.addCourseAssistant(courseInfo, assistantToAdd);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	public String deleteCourseAssistant() throws Exception {// 删除课程助教
		CourseService cs = new CourseServiceImpl();
		cs.deleteCourseAssistant(courseInfo, assistantToRemove);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	private void removeAssistantFromList(List<Assistant> list,
			Assistant assistant) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == assistant.getId()) {
				list.remove(i);
				i--;
			}
		}

	}

}
