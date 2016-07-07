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
@DiscriminatorValue(value="2")
public class Assistant extends Student {
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(
			name = "course_assistant",
			joinColumns=@JoinColumn(name="assistantId"),
			inverseJoinColumns=@JoinColumn(name="courseInfoId")
			
			)
	private List<CourseInfo> courseAssistList;//担任助教的课程
	
	public Assistant() {
		super();
	}

	public Assistant(String username, String password) {
		super(username, password);
		// TODO Auto-generated constructor stub
	}
	
	public Assistant(int id,String username,String password){
		super(id,username,password);
	}
	
	public List<CourseInfo> getCourseAssistList() {
		return courseAssistList;
	}

	public void setCourseAssistList(List<CourseInfo> courseAssistList) {
		this.courseAssistList = courseAssistList;
	}
	
	

}
