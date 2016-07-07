package edu.cn.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value = "1")
public class Student extends User {
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "course_student", joinColumns = @JoinColumn(name = "studentId"), inverseJoinColumns = @JoinColumn(name = "courseInfoId")

	)
	private List<CourseInfo> courseList;// ѧϰ�Ŀγ�
	public Student() {
		super();
	}

	public Student(String username, String password) {
		super(username, password);
	}

	public Student(int id, String username, String password) {
		super(id, username, password);
	}

	public List<CourseInfo> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<CourseInfo> courseList) {
		this.courseList = courseList;
	}

}
