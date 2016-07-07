package edu.cn.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@DiscriminatorValue(value="3")
public class Teacher extends User{
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(
			name = "course_teacher",
			joinColumns=@JoinColumn(name="teacherId"),
			inverseJoinColumns=@JoinColumn(name="courseInfoId")
			
			)
	private List<CourseInfo> courseList;//½ÌÊÚµÄ¿Î³Ì

	public Teacher() {
		super();
	}

	public Teacher(String username, String password) {
		super(username, password);
	}
	
	public Teacher(int id,String username,String password){
		super(id,username,password);
	}

	public List<CourseInfo> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<CourseInfo> courseList) {
		this.courseList = courseList;
	}

	
	

}
