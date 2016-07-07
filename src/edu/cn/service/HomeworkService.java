package edu.cn.service;

import java.util.List;

import edu.cn.domain.Homework;
import edu.cn.domain.SubmitHomework;

public interface HomeworkService {
	public List<Homework> getHomeworkList();

	public void addHomeworkRequirement(int homeworkId, String requirement);

	public void addHomeworkSummary(int homeworkId, String summary);

	public String getHomeworkFileFoler(int courseInfoId, int homeworkId);//根据课程id和作业id创建对应的文件夹

	public void addHomeworkExamplePath(int homeworkId, String filePath);//添加课程example的文件路径

	public void addSubmitHomeworkPath(int homeworkId, int studentId,String filePath);//添加学生提交作业信息
	
	public List<SubmitHomework> getSubmitHomeworkList();
	
	public void correctSubmitHomework(int id,int score,String remark);//助教为学生作业进行打分评价

	public void publishHomeworkScore(int homeworkId);//发布作业成绩

	public void recorrectHomeworkScore(int homeworkId);//要求助教重新批阅

	public boolean isScorePublished(int homeworkId);//判断课程作业是否已经发布成绩
}
