package edu.cn.action.student;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.opensymphony.xwork2.ActionSupport;

import edu.cn.domain.Homework;
import edu.cn.service.HomeworkService;
import edu.cn.serviceImpl.HomeworkServiceImpl;

public class DownloadFileAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int homeworkId;
	private String fileName;

	public int getHomeworkId() {
		return homeworkId;
	}

	public void setHomeworkId(int homeworkId) {
		this.homeworkId = homeworkId;
	}

	public String getFileName() throws Exception {
		fileName = new String(fileName.getBytes(), "ISO8859-1");
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getInputStream() throws Exception {
		HomeworkService hs=new HomeworkServiceImpl();
		String filePath="";
		for(Homework h:hs.getHomeworkList()){
			if(h.getId()==homeworkId){
				filePath=h.getExampleFilePath();
			}
		}
		
		File file = new File(filePath);
		this.fileName = "example";
		return new FileInputStream(file);
	}
}
