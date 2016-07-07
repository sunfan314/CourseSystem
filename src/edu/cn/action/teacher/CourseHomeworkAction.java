package edu.cn.action.teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.Action;

import edu.cn.domain.CourseInfo;
import edu.cn.domain.Homework;
import edu.cn.service.CourseService;
import edu.cn.service.HomeworkService;
import edu.cn.serviceImpl.CourseServiceImpl;
import edu.cn.serviceImpl.HomeworkServiceImpl;

public class CourseHomeworkAction implements Action {

	private JSONObject json;
	private int homeworkId;
	private int courseInfoId;
	private String requirement;
	private String summary;
	public Homework homework;

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

	public int getHomeworkId() {
		return homeworkId;
	}

	public void setHomeworkId(int homeworkId) {
		this.homeworkId = homeworkId;
	}

	public Homework getHomework() {
		return homework;
	}

	public void setHomework(Homework homework) {
		this.homework = homework;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Override
	public String execute() throws Exception {// ��ÿγ̵���ҵ�б�
		CourseService cs = new CourseServiceImpl();
		List<CourseInfo> courseInfos = cs.getCourseInfoList();
		List<Homework> homeworks = new ArrayList<Homework>();
		for (CourseInfo c : courseInfos) {
			if (c.getId() == courseInfoId) {
				homeworks = c.getHomeworkList();
			}
		}
		for (Homework h : homeworks) {
			h.setCourseInfo(null);
			h.setSubmitHomeworks(null);
		}
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", homeworks);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	public String addCourseHomework() throws Exception {// ��ӿγ���ҵ
		CourseService cs = new CourseServiceImpl();
		CourseInfo courseInfo = new CourseInfo();
		courseInfo.setId(courseInfoId);
		cs.addCourseHomework(courseInfo, homework);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	public String deleteCourseHomework() throws Exception {// ɾ���γ���ҵ
		CourseService cs = new CourseServiceImpl();
		cs.deleteCourseHomework(courseInfoId, homeworkId);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	public String updateCourseHomework() throws Exception {// ���¿γ���ҵ��Ϣ
		CourseService cs = new CourseServiceImpl();
		cs.updateHomework(homework);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	public String getHomeworkInfo() throws Exception {// ��ÿγ���Ϣ
		HomeworkService hs = new HomeworkServiceImpl();
		List<Homework> homeworks = hs.getHomeworkList();
		Homework homework = new Homework();
		for (Homework h : homeworks) {
			if (h.getId() == homeworkId) {
				homework = h;
			}
		}
		String summary = homework.getSummary();
		String requirement = homework.getRequirement();
		String exampleFilePath = homework.getExampleFilePath();
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		if (summary == null) {
			summary = "";
		}
		if (requirement == null) {
			requirement = "";
		}
		if (exampleFilePath == null) {
			exampleFilePath = "";
		}
		map.put("requirement", requirement);
		map.put("summary", summary);
		map.put("exampleFilePath", exampleFilePath);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	public String addHomeworkRequirement() throws Exception {// �����ҵҪ����Ϣ
		HomeworkService hs = new HomeworkServiceImpl();
		hs.addHomeworkRequirement(homeworkId, requirement);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	public String addHomeworkSummary() throws Exception {// �����ҵ�ܽ���Ϣ
		HomeworkService hs=new HomeworkServiceImpl();
		hs.addHomeworkSummary(homeworkId,summary);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	public String  publishScore() throws Exception {//������ҵ�ɼ�
		HomeworkService  hs=new HomeworkServiceImpl();
		hs.publishHomeworkScore(homeworkId);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	public String recorrectScore() throws Exception{//Ҫ�������������Ŀγ���ҵ
		HomeworkService  hs=new HomeworkServiceImpl();
		hs.recorrectHomeworkScore(homeworkId);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	public String isScorePublished() throws Exception{//�жϿγ���ҵ�Ƿ��Ѿ������ɼ�
		HomeworkService hs=new HomeworkServiceImpl();
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		if(hs.isScorePublished(homeworkId)){
			map.put("scorePublished", true);
		}
		else{
			map.put("scorePublished", false);
		}
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

}
