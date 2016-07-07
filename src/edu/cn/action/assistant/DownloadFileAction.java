package edu.cn.action.assistant;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.opensymphony.xwork2.ActionSupport;

import edu.cn.domain.SubmitHomework;
import edu.cn.service.HomeworkService;
import edu.cn.serviceImpl.HomeworkServiceImpl;

public class DownloadFileAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int submitHomeworkId;
	private String fileName;

	public int getSubmitHomeworkId() {
		return submitHomeworkId;
	}

	public void setSubmitHomeworkId(int submitHomeworkId) {
		this.submitHomeworkId = submitHomeworkId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getInputStream() throws Exception {
		HomeworkService hs=new HomeworkServiceImpl();
		String filePath="";
		for(SubmitHomework s:hs.getSubmitHomeworkList()){
			if(s.getId()==submitHomeworkId){
				filePath=s.getFilePath();
			}
		}
		
		File file = new File(filePath);
		this.fileName = "Student Homework";
		return new FileInputStream(file);
	}

}
