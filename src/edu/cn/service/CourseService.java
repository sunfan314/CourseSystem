package edu.cn.service;

import java.util.List;

import edu.cn.domain.Assistant;
import edu.cn.domain.Course;
import edu.cn.domain.CourseInfo;
import edu.cn.domain.Homework;
import edu.cn.domain.Teacher;

public interface CourseService {
	public List<CourseInfo> getCourseInfoList();
	
	public List<Course> getCourseList();
	
	public void addCourseInfo(CourseInfo courseInfo);
	
	public void updateCourseInfo(CourseInfo courseInfo);
	
	public void deleteCourseInfo(int id);
	
	public void deleteCourseTeacher(CourseInfo courseInfo,Teacher teacher);

	public void addCourseTeacher(CourseInfo courseInfo,Teacher teacher);
	
	public void deleteCourseAssistant(CourseInfo courseInfo, Assistant assistant);

	public void addCourseAssistant(CourseInfo courseInfo,Assistant assistant);

	public void addCourseHomework(CourseInfo courseInfo, Homework homework);

	public void deleteCourseHomework(int courseInfoId, int homeworkId);

	public void updateHomework(Homework homework);

	public void addCourseStudent(int courseInfoId, int studentId);

	public void deleteCourseStudent(int courseInfoId, int studentId);

	
}
