package edu.cn.action.admin;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.Action;

import edu.cn.domain.Course;
import edu.cn.domain.CourseInfo;
import edu.cn.domain.Teacher;
import edu.cn.service.CourseService;
import edu.cn.serviceImpl.CourseServiceImpl;

public class CourseInfoAction implements Action {
	private int id;
	private JSONObject json;
	private CourseInfo courseInfo;

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

	public CourseInfo getCourseInfo() {
		return courseInfo;
	}

	public void setCourseInfo(CourseInfo courseInfo) {
		this.courseInfo = courseInfo;
	}

	@Override
	public String execute() throws Exception {// 获得课程信息列表
		CourseService cs = new CourseServiceImpl();
		List<CourseInfo> courseInfos = cs.getCourseInfoList();
		for (CourseInfo c : courseInfos) {
			String teachers = "";// 设置课程的教师字符串
			for (Teacher t : c.getTeacherList()) {
				teachers += t.getUsername();
				teachers += " , ";
			}
			if (teachers.length() > 0) {
				teachers = teachers.substring(0, teachers.length() - 2);
			} else {
				teachers = "NOT ALLOCATED";
			}
			c.setTeachers(teachers);
			c.setTeacherList(null);
			c.setAssList(null);
			c.setStudentList(null);
			c.setHomeworkList(null);
		}
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", courseInfos);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	public String addCourseInfo() throws Exception {// 添加课程信息
		CourseService cs = new CourseServiceImpl();
		List<Course> courseList = cs.getCourseList();
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		for (Course c : courseList) {
			if (c.getId() == courseInfo.getCourse().getId())
				courseInfo.getCourse().setCourseName(c.getCourseName());
		}
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		if (year>courseInfo.getYear()) {
			map.put("success", false);
		} else {
			cs.addCourseInfo(courseInfo);
			map.put("success", true);
		}
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	public String deleteCourseInfo() throws Exception {// 删除课程信息
		CourseService cs = new CourseServiceImpl();
		cs.deleteCourseInfo(id);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	public String updateCourseInfo() throws Exception {// 修改课程信息
		CourseService cs = new CourseServiceImpl();
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		if (year>courseInfo.getYear()) {
			map.put("success", false);
		} else {
			cs.addCourseInfo(courseInfo);
			map.put("success", true);
		}
		cs.updateCourseInfo(courseInfo);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

}
