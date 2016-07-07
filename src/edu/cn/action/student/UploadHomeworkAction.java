package edu.cn.action.student;

import java.io.File;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.Action;

import edu.cn.service.HomeworkService;
import edu.cn.serviceImpl.HomeworkServiceImpl;

public class UploadHomeworkAction implements Action {
	private int studentId;
	private int courseInfoId;
	private int homeworkId;
	private File homework;
	private String homeworkFileName;
	private String homeworkContentType;
	private JSONObject json;

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getCourseInfoId() {
		return courseInfoId;
	}

	public void setCourseInfoId(int courseInfoId) {
		this.courseInfoId = courseInfoId;
	}

	public int getHomeworkId() {
		return homeworkId;
	}

	public void setHomeworkId(int homeworkId) {
		this.homeworkId = homeworkId;
	}

	public File getHomework() {
		return homework;
	}

	public void setHomework(File homework) {
		this.homework = homework;
	}

	public String getHomeworkFileName() {
		return homeworkFileName;
	}

	public void setHomeworkFileName(String homeworkFileName) {
		this.homeworkFileName = homeworkFileName;
	}

	public String getHomeworkContentType() {
		return homeworkContentType;
	}

	public void setHomeworkContentType(String homeworkContentType) {
		this.homeworkContentType = homeworkContentType;
	}

	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

	@Override
	public String execute() throws Exception {
		if (homework == null) {
			java.util.Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", false);
			json = JSONObject.fromObject(map);

		} else {
			HomeworkService hs=new HomeworkServiceImpl();
			//获得课程作业文件夹路径,若文件夹不存在则创建文件夹
			String fileFolderPath=hs.getHomeworkFileFoler(courseInfoId,homeworkId)+"/homework/"+Integer.toString(studentId);
			File fileFolder=new File(fileFolderPath);
			if(!fileFolder.exists()){
				fileFolder.mkdirs();
			}
			for(File f:fileFolder.listFiles()){
				//在每次向学生文件夹上传文件之前先清空先文件夹
				f.delete();
			}
			String filePath = fileFolderPath+"/"+ homeworkFileName;
			File saveFile = new File(filePath);
			FileUtils.copyFile(homework, saveFile);
			hs.addSubmitHomeworkPath(homeworkId,studentId,filePath);
			java.util.Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", true);
			json = JSONObject.fromObject(map);
		}
		return SUCCESS;
	}
}
