package edu.cn.action.student;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.Action;

import edu.cn.domain.CourseInfo;
import edu.cn.domain.Homework;
import edu.cn.domain.Student;
import edu.cn.domain.SubmitHomework;
import edu.cn.service.UserService;
import edu.cn.serviceImpl.UserServiceImpl;

public class HomeworkStatusAction implements Action {
	private int studentId;
	private JSONObject json;

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
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
		Student student = new Student();
		for (Student s : us.getStudentList()) {
			if (s.getId() == studentId) {
				student = s;
			}
		}
		List<CourseInfo> studentCourseInfos = student.getCourseList();
		List<Homework> homeworkList = new ArrayList<Homework>();
		for (CourseInfo c : studentCourseInfos) {
			homeworkList.addAll(c.getHomeworkList());
		}
		for (Homework h : homeworkList) {
			h.setCourseName(h.getCourseInfo().getCourseName());
			h.setCourseInfo(null);
			List<SubmitHomework> submitHomeworks = h.getSubmitHomeworks();
			h.setSubmitHomeworks(null);
			Date today = new Date();
			int i = today.compareTo(h.getSubmitDeadline());
			if (i > 0) {
				h.setHomeworkStatus("Deadline is Over!");
			} else {
				h.setHomeworkStatus("Not Submitted Yet!");
			}

			h.setScoreToString("Homework Not Submitted Yet.");
			for (SubmitHomework sh : submitHomeworks) {
				if (sh.getStudentId() == studentId) {
					h.setHomeworkStatus("Homework Submitted.");
					if (h.getScorePublished() == 1) {
						h.setScoreToString(Integer.toString(sh.getScore()));
					} else {
						h.setScoreToString("Homework Not Published Yet.");
					}
					break;
				}
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", homeworkList);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	public String getUnsubmittedHomework() throws Exception{
		UserService us = new UserServiceImpl();
		Student student = new Student();
		for (Student s : us.getStudentList()) {
			if (s.getId() == studentId) {
				student = s;
			}
		}
		List<CourseInfo> studentCourseInfos = student.getCourseList();
		List<Homework> homeworkList = new ArrayList<Homework>();
		List<Homework> listToSubmit=new ArrayList<Homework>();
		for (CourseInfo c : studentCourseInfos) {
			homeworkList.addAll(c.getHomeworkList());
		}
		for (Homework h : homeworkList) {
			h.setCourseName(h.getCourseInfo().getCourseName());
			h.setCourseInfo(null);
			List<SubmitHomework> submitHomeworks = h.getSubmitHomeworks();
			h.setSubmitHomeworks(null);
			Date today = new Date();
			int i = today.compareTo(h.getSubmitDeadline());
			if (i > 0) {
				h.setHomeworkStatus("Deadline is Over!");
			} else {
				h.setHomeworkStatus("Not Submitted Yet!");
			}

			h.setScoreToString("Homework Not Submitted Yet.");
			for (SubmitHomework sh : submitHomeworks) {
				if (sh.getStudentId() == studentId) {
				}
				else{
					listToSubmit.add(h);
				}
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", listToSubmit);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

}
