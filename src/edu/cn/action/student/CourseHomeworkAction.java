package edu.cn.action.student;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.Action;

import edu.cn.domain.CourseInfo;
import edu.cn.domain.Homework;
import edu.cn.domain.SubmitHomework;
import edu.cn.service.CourseService;
import edu.cn.service.HomeworkService;
import edu.cn.serviceImpl.CourseServiceImpl;
import edu.cn.serviceImpl.HomeworkServiceImpl;

public class CourseHomeworkAction implements Action {
	private int studentId;
	private int courseInfoId;
	private String courseName;
	private List<Homework> courseHomeworkList;
	private int studentScore;
	private String homeworkComment;

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getCourseInfoId() {
		return courseInfoId;
	}

	public void setCourseInfoId(int courseInfoId) {
		this.courseInfoId = courseInfoId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public List<Homework> getCourseHomeworkList() {
		return courseHomeworkList;
	}

	public void setCourseHomeworkList(List<Homework> courseHomeworkList) {
		this.courseHomeworkList = courseHomeworkList;
	}

	public int getStudentScore() {
		return studentScore;
	}

	public void setStudentScore(int studentScore) {
		this.studentScore = studentScore;
	}

	public String getHomeworkComment() {
		return homeworkComment;
	}

	public void setHomeworkComment(String homeworkComment) {
		this.homeworkComment = homeworkComment;
	}

	@Override
	public String execute() throws Exception {
		CourseService cs = new CourseServiceImpl();
		HomeworkService hs = new HomeworkServiceImpl();
		List<SubmitHomework> submitHomeworks = hs.getSubmitHomeworkList();
		List<CourseInfo> courseInfos = cs.getCourseInfoList();
		for (CourseInfo c : courseInfos) {
			if (c.getId() == courseInfoId) {
				courseName = c.getCourseName();
				courseHomeworkList = c.getHomeworkList();
			}
		}
		for (Homework h : courseHomeworkList) {
			if (h.getExampleFilePath() == null) {
				h.setExampleSubmitted(false);
			}

			else {
				h.setExampleSubmitted(true);
			}
			for (SubmitHomework sh : submitHomeworks) {
				if (sh.getHomework().getId() == h.getId()
						&& sh.getStudentId() == studentId) {
					if (sh.getFilePath() == null) {
						h.setHomeworkSubmitted(false);
					} else {
						h.setHomeworkSubmitted(true);
						studentScore = sh.getScore();
						homeworkComment=sh.getRemark();
					}
				}
			}
			Date today = new Date();
			int i = today.compareTo(h.getSubmitDeadline());
			if (i < 0) {
				h.setDeadlineOver(false);
			} else {
				h.setDeadlineOver(true);
			}

		}
		return SUCCESS;
	}

}
