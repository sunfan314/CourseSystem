package edu.cn.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="4")
public class TeacherInCharge extends Teacher {
	public TeacherInCharge(){
		super();
	}

	public TeacherInCharge(String username, String password) {
		super(username, password);
		// TODO Auto-generated constructor stub
	}
	
	public TeacherInCharge(int id,String username,String password){
		super(id,username,password);
	}
	

}
