package edu.cn.action.student;

import com.opensymphony.xwork2.Action;

public class StudentInfoAction implements Action {
	private int id;// ��ҳ�洫��student��id��Ϣ
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

}
