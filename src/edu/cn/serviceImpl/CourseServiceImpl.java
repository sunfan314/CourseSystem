package edu.cn.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import edu.cn.dao.CourseSystemDao;
import edu.cn.daoImpl.CourseSystemDaoImpl;
import edu.cn.domain.Assistant;
import edu.cn.domain.Course;
import edu.cn.domain.CourseInfo;
import edu.cn.domain.Homework;
import edu.cn.domain.Teacher;
import edu.cn.service.CourseService;

public class CourseServiceImpl implements CourseService {
	private CourseSystemDao myDao=CourseSystemDaoImpl.getInstance();
	
	public List<CourseInfo> getCourseInfoList(){
		return myDao.getCourseInfos();
	}
	
	public List<Course> getCourseList(){
		return myDao.getCourses();
	}
	
	public void addCourseInfo(CourseInfo courseInfo){
		myDao.addCourseInfo(courseInfo);
	}

	public void updateCourseInfo(CourseInfo courseInfo){
		myDao.updateCourseInfo(courseInfo);
	}
	
	public void deleteCourseInfo(int id){
		myDao.deleteCourseInfo(id);
	}
	
	public void deleteCourseTeacher(CourseInfo courseInfo,Teacher teacher){
		myDao.deleteCourseTeacher(courseInfo, teacher);
	}
	
	public void addCourseTeacher(CourseInfo courseInfo,Teacher teacher){
		myDao.addCourseTeacher(courseInfo, teacher);
	}
	
	public void deleteCourseAssistant(CourseInfo courseInfo, Assistant assistant){
		myDao.deleteCourseAssistant(courseInfo, assistant);
	}
	
	public void addCourseAssistant(CourseInfo courseInfo,Assistant assistant){
		myDao.addCourseAssistant(courseInfo,assistant);
	}
	
	public void addCourseHomework(CourseInfo courseInfo, Homework homework){
		myDao.addCourseHomework(courseInfo,homework);
	}
	
	public void deleteCourseHomework(int courseInfoId, int homeworkId){
		myDao.deleteCourseHomework(courseInfoId,homeworkId);
	}
	
	public void updateHomework(Homework homework){
		myDao.updateHomework(homework);
	}
	
	public void addCourseStudent(int courseInfoId, int studentId){
		myDao.addCourseStudent(courseInfoId,studentId);
	}
	
	public void deleteCourseStudent(int courseInfoId, int studentId){
		myDao.deleteCourseStudent(courseInfoId,studentId);
	}
}
