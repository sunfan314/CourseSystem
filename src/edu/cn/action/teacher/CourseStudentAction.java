package edu.cn.action.teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.Action;

import edu.cn.domain.Assistant;
import edu.cn.domain.CourseInfo;
import edu.cn.domain.Student;
import edu.cn.service.CourseService;
import edu.cn.service.UserService;
import edu.cn.serviceImpl.CourseServiceImpl;
import edu.cn.serviceImpl.UserServiceImpl;

public class CourseStudentAction implements Action {
	private String students;
	private String actionType;
	private int courseInfoId;
	private JSONObject json;

	public String getStudents() {
		return students;
	}

	public void setStudents(String students) {
		this.students = students;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public int getCourseInfoId() {
		return courseInfoId;
	}

	public void setCourseInfoId(int courseInfoId) {
		this.courseInfoId = courseInfoId;
	}

	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

	@Override
	public String execute() throws Exception {// 根据操作类型（添加或是删除学生）选择返回对应学生列表
		if (actionType.equals("delete")) {
			CourseService cs = new CourseServiceImpl();
			List<CourseInfo> courseInfos = new ArrayList<CourseInfo>();
			List<Student> students = new ArrayList<Student>();
			courseInfos = cs.getCourseInfoList();
			for (CourseInfo c : courseInfos) {
				if (c.getId() == courseInfoId) {
					students = c.getStudentList();
				}
			}
			for (Student s : students) {
				s.setUserType("student");
				s.setCourseList(null);
				if (s instanceof Assistant) {
					s.setUserType("assistant");
					((Assistant) s).setCourseAssistList(null);
				}

			}
			java.util.Map<String, Object> map = new HashMap<String, Object>();
			map.put("rows", students);
			json = JSONObject.fromObject(map);
		}
		if (actionType.equals("add")) {
			CourseService cs = new CourseServiceImpl();
			UserService us = new UserServiceImpl();
			List<CourseInfo> courseInfos = new ArrayList<CourseInfo>();
			List<Student> studentList = new ArrayList<Student>();
			List<Student> courseStudents = new ArrayList<Student>();
			studentList = us.getStudentList();
			courseInfos = cs.getCourseInfoList();
			for (CourseInfo c : courseInfos) {
				if (c.getId() == courseInfoId) {
					courseStudents = c.getStudentList();
				}
			}
			for (Student s : courseStudents) {
				this.removeStudentFromList(studentList, s);
			}
			for (Student s : studentList) {
				s.setUserType("student");
				s.setCourseList(null);
				if (s instanceof Assistant) {
					s.setUserType("assistant");
					((Assistant) s).setCourseAssistList(null);
				}
			}
			java.util.Map<String, Object> map = new HashMap<String, Object>();
			map.put("rows", studentList);
			json = JSONObject.fromObject(map);
		}
		return SUCCESS;
	}

	public String addCourseStudents() throws Exception {// 添加课程学生
		CourseService cs = new CourseServiceImpl();
		List<Integer> list = this.getStudentIdList(students);
		for (int studentId : list) {
			cs.addCourseStudent(courseInfoId, studentId);
		}
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	public String deleteCourseStudents() throws Exception {// 删除课程学生
		CourseService cs = new CourseServiceImpl();
		List<Integer> list = this.getStudentIdList(students);
		for (int studentId : list) {
			cs.deleteCourseStudent(courseInfoId, studentId);
		}
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	public List<Integer> getStudentIdList(String list) {// list为以分号分隔的学生Id列表
		List<Integer> studentIdList = new ArrayList<Integer>();
		list = list.substring(0, list.length() - 1);
		String[] idList = list.split(":");
		for (int i = 0; i < idList.length; i++) {
			int id = Integer.parseInt(idList[i]);
			studentIdList.add(id);
		}
		return studentIdList;
	}

	public void removeStudentFromList(List<Student> list, Student student) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == student.getId()) {
				list.remove(i);
				i--;
			}
		}
	}

}
