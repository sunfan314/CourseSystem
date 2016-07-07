package edu.cn.action.assistant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.Action;

import edu.cn.domain.Student;
import edu.cn.domain.SubmitHomework;
import edu.cn.service.HomeworkService;
import edu.cn.service.UserService;
import edu.cn.serviceImpl.HomeworkServiceImpl;
import edu.cn.serviceImpl.UserServiceImpl;

public class SubmittedHomeworkAction implements Action {
	private int homeworkId;
	private JSONObject json;
	private String data;

	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

	public int getHomeworkId() {
		return homeworkId;
	}

	public void setHomeworkId(int homeworkId) {
		this.homeworkId = homeworkId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String execute() throws Exception {
		HomeworkService hs = new HomeworkServiceImpl();
		UserService us = new UserServiceImpl();
		List<Student> stuList = us.getStudentList();
		List<SubmitHomework> submitHomeworkList = hs.getSubmitHomeworkList();
		List<SubmitHomework> result = new ArrayList<SubmitHomework>();
		for (SubmitHomework sh : submitHomeworkList) {
			if (sh.getHomework().getId() == homeworkId) {
				result.add(sh);
			}
		}
		for (SubmitHomework sh : result) {
			sh.setHomework(null);
			for (Student s : stuList) {
				if (s.getId() == sh.getStudentId()) {
					sh.setStudentName(s.getUsername());
				}
			}
		}
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", result);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	public String updateSubmitHomework() throws Exception {
		JSONArray array = JSONArray.fromObject(data);
		for (Object object : array) {
			JSONObject json = (JSONObject) object;
			SubmitHomework myData = (SubmitHomework) JSONObject.toBean(json, SubmitHomework.class);
			int id=myData.getId();
			int score=myData.getScore();
			String remark=myData.getRemark();
			HomeworkService hs=new HomeworkServiceImpl();
			hs.correctSubmitHomework(id,score, remark);
		}
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	
}
