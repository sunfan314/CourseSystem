package edu.cn.action.assistant;

import com.opensymphony.xwork2.Action;

public class AssistantInfoAction implements Action {
private int id;// ��ҳ�洫��assistant��id��Ϣ
	
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
