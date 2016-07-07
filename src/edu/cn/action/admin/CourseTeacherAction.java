package edu.cn.action.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.Action;

import edu.cn.domain.CourseInfo;
import edu.cn.domain.Teacher;
import edu.cn.service.CourseService;
import edu.cn.service.UserService;
import edu.cn.serviceImpl.CourseServiceImpl;
import edu.cn.serviceImpl.UserServiceImpl;

public class CourseTeacherAction implements Action {
	private int id;
	private Teacher teacherToAdd;
	private Teacher teacherToRemove;
	private JSONObject json;
	private JSONArray jsonArray;
	private CourseInfo courseInfo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Teacher getTeacherToAdd() {
		return teacherToAdd;
	}

	public void setTeacherToAdd(Teacher teacherToAdd) {
		this.teacherToAdd = teacherToAdd;
	}

	public Teacher getTeacherToRemove() {
		return teacherToRemove;
	}

	public void setTeacherToRemove(Teacher teacherToRemove) {
		this.teacherToRemove = teacherToRemove;
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

	@Override
	public String execute() throws Exception {// 获得课程教师列表
		CourseService cs = new CourseServiceImpl();
		List<Teacher> teachers = new ArrayList<Teacher>();
		List<CourseInfo> courseInfos = cs.getCourseInfoList();
		for (CourseInfo c : courseInfos) {
			if (c.getId() == id) {
				teachers = c.getTeacherList();
				for (Teacher t : teachers) {
					t.setCourseList(null);
				}
			}
		}
		jsonArray = JSONArray.fromObject(teachers);
		return SUCCESS;

	}

	public String getAvailableTeachers() throws Exception {// 获得课程可分配教师列表
		UserService us = new UserServiceImpl();
		CourseService cs = new CourseServiceImpl();
		List<Teacher> teacherList = us.getTeacherList();
		List<Teacher> courseTeachers = new ArrayList<Teacher>();
		for (CourseInfo c : cs.getCourseInfoList()) {
			if (c.getId() == id) {
				courseTeachers = c.getTeacherList();
			}
		}
		for (int i = 0; i < courseTeachers.size(); i++) {
			this.removeTeacherFromList(teacherList, courseTeachers.get(i));
		}
		for (Teacher t : teacherList) {
			t.setCourseList(null);
		}
		jsonArray = JSONArray.fromObject(teacherList);
		return SUCCESS;
	}

	public String addTeacherToCourse() throws Exception {// 为课程添加教师
		CourseService cs = new CourseServiceImpl();
		cs.addCourseTeacher(courseInfo, teacherToAdd);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	public String deleteTeacherFromCourse() throws Exception {// 将教师从课程教师列表中删除
		CourseService cs = new CourseServiceImpl();
		cs.deleteCourseTeacher(courseInfo, teacherToRemove);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	public void removeTeacherFromList(List<Teacher> list, Teacher teacher) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == teacher.getId()) {
				list.remove(i);
				i--;
			}
		}

	}

}
