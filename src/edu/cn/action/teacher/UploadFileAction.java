package edu.cn.action.teacher;

import java.io.File;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.Action;

import edu.cn.service.HomeworkService;
import edu.cn.serviceImpl.HomeworkServiceImpl;

public class UploadFileAction implements Action {
	private int courseInfoId;
	private int homeworkId;
	private File example;
	private String exampleFileName;
	private String exampleContentType;
	private JSONObject json;

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

	public File getExample() {
		return example;
	}

	public void setExample(File example) {
		this.example = example;
	}

	public String getExampleFileName() {
		return exampleFileName;
	}

	public void setExampleFileName(String exampleFileName) {
		this.exampleFileName = exampleFileName;
	}

	public String getExampleContentType() {
		return exampleContentType;
	}

	public void setExampleContentType(String exampleContentType) {
		this.exampleContentType = exampleContentType;
	}

	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

	@Override
	public String execute() throws Exception {
		if (example == null) {
			java.util.Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", false);
			json = JSONObject.fromObject(map);

		} else {
			HomeworkService hs=new HomeworkServiceImpl();
			//��ÿγ���ҵ�ļ���·��,���ļ��в������򴴽��ļ���
			String fileFolderPath=hs.getHomeworkFileFoler(courseInfoId,homeworkId);
			File fileFolder=new File(fileFolderPath+"/example");
			if(!fileFolder.exists()){
				fileFolder.mkdirs();
			}
			for(File f:fileFolder.listFiles()){
				//��ÿ����example�ļ����ϴ��ļ�֮ǰ��������ļ���
				f.delete();
			}
			String filePath = fileFolderPath+"/example/"+ exampleFileName;
			File saveFile = new File(filePath);
			FileUtils.copyFile(example, saveFile);
			hs.addHomeworkExamplePath(homeworkId,filePath);
			java.util.Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", true);
			json = JSONObject.fromObject(map);
		}
		return SUCCESS;
	}

}
