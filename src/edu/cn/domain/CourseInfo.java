package edu.cn.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "courseInfo")
public class CourseInfo {
	@Id
	private int id;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "courseId", unique = true)
	private Course course;
	private int year;
	private int term;
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "courseList", cascade = CascadeType.ALL)
	private List<Teacher> teacherList;
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "courseAssistList", cascade = CascadeType.ALL)
	private List<Assistant> assList;
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "courseList", cascade = CascadeType.ALL)
	private List<Student> studentList;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "courseInfo", cascade = CascadeType.ALL)
	private List<Homework> homeworkList;
	private String teachers;
	private String assistants;
	private int studentNumber;
	private int homeworkNumber;

	public CourseInfo() {
		super();
	}

	public CourseInfo(Course course, int year, int term) {
		super();
		this.course = course;
		this.year = year;
		this.term = term;
	}

	public CourseInfo(int id, Course course, int year, int term) {
		super();
		this.id = id;
		this.course = course;
		this.year = year;
		this.term = term;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCourseId() {// 获取课程id
		return course.getId();
	}

	public String getCourseName() {// 获取课程名
		return course.getCourseName();
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public List<Teacher> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<Teacher> teacherList) {
		this.teacherList = teacherList;
	}

	public List<Assistant> getAssList() {
		return assList;
	}

	public void setAssList(List<Assistant> assList) {
		this.assList = assList;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public List<Homework> getHomeworkList() {
		return homeworkList;
	}

	public void setHomeworkList(List<Homework> homeworkList) {
		this.homeworkList = homeworkList;
	}

	public String getTeachers() {
		return teachers;
	}

	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}

	public String getAssistants() {
		return assistants;
	}

	public void setAssistants(String assistants) {
		this.assistants = assistants;
	}

	public int getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}

	public int getHomeworkNumber() {
		return homeworkNumber;
	}

	public void setHomeworkNumber(int homeworkNumber) {
		this.homeworkNumber = homeworkNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + term;
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {// 课程与设置学期相同的课程信息被视为相同的课程信息
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CourseInfo other = (CourseInfo) obj;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (term != other.term)
			return false;
		if (year != other.year)
			return false;
		return true;
	}

}
