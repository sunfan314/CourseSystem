package edu.cn.dao;

import java.util.List;

import edu.cn.domain.Assistant;
import edu.cn.domain.Course;
import edu.cn.domain.CourseInfo;
import edu.cn.domain.Homework;
import edu.cn.domain.Student;
import edu.cn.domain.SubmitHomework;
import edu.cn.domain.Teacher;
import edu.cn.domain.TeacherInCharge;
import edu.cn.domain.User;

public interface CourseSystemDao {
	public List<User> getUsers();//获得所有用户列表
	
	public List<Student> getStudents();//获得所有学生列表
	
	public List<Assistant> getAssistants();//获得助教列表
	
	public List<Teacher> getTeachers();//获得教师列表
	
	public List<CourseInfo> getCourseInfos();//获得所有课程信息
	
	public List<Homework> getHomeworks();//获得课程作业列表
	
	public List<Course> getCourses();//获得课程列表
	
	public void addUser(User user);//向数据库添加或修改用户信息
	
	public void updateUser(User user,int type);//更新用户信息
	
	public void deleteUser(int id);//向数据库删除用户信息
	
	public void addCourseInfo(CourseInfo courseInfo);//向数据库添加课程信息
	
	public void updateCourseInfo(CourseInfo courseInfo);//更新课程信息
	
	public void deleteCourseInfo(int id);//向数据库删除课程信息
	
	public void deleteCourseTeacher(CourseInfo courseInfo,Teacher teacher);//删除课程教师
	
	public void addCourseTeacher(CourseInfo courseInfo,Teacher teacher);//添加课程教师
	
	public void deleteCourseAssistant(CourseInfo courseInfo,Assistant assistant);//删除课程助教

	public void addCourseAssistant(CourseInfo courseInfo, Assistant assistant);//添加课程助教

	public void addCourseHomework(CourseInfo courseInfo, Homework homework);//添加课程作业

	public void deleteCourseHomework(int courseInfoId, int homeworkId);//删除课程作业

	public void updateHomework(Homework homework);//更新课程作业信息

	public void addCourseStudent(int courseInfoId, int studentId);//添加课程学生信息

	public void deleteCourseStudent(int courseInfoId, int studentId);//删除课程学生信息

	public void addHomeworkRequirement(int homeworkId, String requirement);//添加作业要求信息

	public void addHomeworkSummary(int homeworkId, String summary);//添加作业总结信息

	public void addHomeworkExamplePath(int homeworkId, String filePath);//添加课程样例路径

	public void addSubmitHomework(int homeworkId, int studentId, String filePath);//添加学生作业添加记录

	public List<SubmitHomework> getSubmitHomeworks();// 获得作业提交信息列表
	
	public void updateSubmitHomeworkInfo(SubmitHomework submitHomework);// 更新作业提交信息

	public void correctSubmitHomeworkInfo(int id, int score, String remark);//为作业评分和评价

	public void publishHomeworkScore(int homeworkId);//发布作业成绩

	public void recorrectHomeworkScore(int homeworkId);//重新批阅
	
	
}
