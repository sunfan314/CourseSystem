package edu.cn.action.assistant;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.Action;

import edu.cn.domain.Homework;
import edu.cn.service.HomeworkService;
import edu.cn.serviceImpl.HomeworkServiceImpl;

public class CorrectHomeworkAction implements Action {
	private int homeworkId;
	private int courseInfoId;
	private JSONObject json;

	public int getCourseInfoId() {
		return courseInfoId;
	}

	public void setCourseInfoId(int courseInfoId) {
		this.courseInfoId = courseInfoId;
	}

	public int getHomeworkId() {
		return homeworkId;
	}

	public void setHomeworkId(int homeworkId) {
		this.homeworkId = homeworkId;
	}

	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String checkHomeworkStatus() throws Exception{
		HomeworkService hs=new HomeworkServiceImpl();
		Homework homework=new Homework();
		Map<String,Object> map=new HashMap<String, Object>();
		for(Homework h:hs.getHomeworkList()){
			if(h.getId()==homeworkId){
				homework=h;
			}
		}
		Date today=new Date();
		int i=today.compareTo(homework.getCorrectDeadline());
		if(i<0){
			map.put("deadlineOver", false);
		}
		else{
			map.put("deadlineOver", true);
		}
		int checked=homework.getChecked();
		if(checked==1){
			map.put("teacherChecked", true);
		}
		else{
			map.put("teacherChecked", false);
		}
		int published=homework.getScorePublished();
		if(published==1){
			map.put("scorePublished",true);
		}
		else{
			map.put("scorePublished", false);
		}
		json=JSONObject.fromObject(map);
		return SUCCESS;
	}
}
