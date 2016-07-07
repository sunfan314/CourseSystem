package edu.cn.action.teacher;

import com.opensymphony.xwork2.Action;

public class GetTeacherAction implements Action {
	private int id;// 向页面传递teacher的id信息

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
}
