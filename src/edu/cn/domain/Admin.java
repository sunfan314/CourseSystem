package edu.cn.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="5")
public class Admin extends User {
	public Admin(){
		super();
	}

}
