package edu.cn.serviceImpl;

import java.io.File;
import java.util.List;

import edu.cn.dao.CourseSystemDao;
import edu.cn.daoImpl.CourseSystemDaoImpl;
import edu.cn.domain.CourseInfo;
import edu.cn.domain.Homework;
import edu.cn.domain.SubmitHomework;
import edu.cn.service.HomeworkService;

public class HomeworkServiceImpl implements HomeworkService {
	private CourseSystemDao myDao = CourseSystemDaoImpl.getInstance();

	public List<Homework> getHomeworkList() {
		return myDao.getHomeworks();
	}

	public void addHomeworkRequirement(int homeworkId, String requirement) {
		myDao.addHomeworkRequirement(homeworkId, requirement);
	}

	public void addHomeworkSummary(int homeworkId, String summary) {
		myDao.addHomeworkSummary(homeworkId, summary);
	}

	public String getHomeworkFileFoler(int courseInfoId, int homeworkId){//根据课程id和作业id创建对应的文件夹
		List<CourseInfo> courseInfoList=myDao.getCourseInfos();
		CourseInfo courseInfo=new CourseInfo();
		for(CourseInfo c:courseInfoList){
			if(c.getId()==courseInfoId){
				courseInfo=c;
			}
		}
		String fileFolderPath="/Users/sunfan314/desktop/HomeworkFile/"+courseInfo.getCourseName()+"_"+courseInfoId+"/homework"+homeworkId;
		File fileFolder = new File(fileFolderPath);
		if(!fileFolder.exists()){
			fileFolder.mkdirs();
		}
		return fileFolderPath;
	}
	
	public void addHomeworkExamplePath(int homeworkId, String filePath){//添加课程example的文件路径
		myDao.addHomeworkExamplePath(homeworkId,filePath);
	}
	
	public void addSubmitHomeworkPath(int homeworkId, int studentId,String filePath){//添加学生提交作业信息
		List<SubmitHomework> submitHomeworkList=myDao.getSubmitHomeworks();
		for(SubmitHomework s:submitHomeworkList){
			if(s.getHomework().getId()==homeworkId&&s.getStudentId()==studentId){//已存在提交记录则更新
				SubmitHomework submit=new SubmitHomework();
				Homework homework=new Homework();
				homework.setId(homeworkId);
				submit.setStudentId(studentId);
				submit.setHomework(homework);
				submit.setFilePath(filePath);
				myDao.updateSubmitHomeworkInfo(submit);
				return;
			}
		}
		myDao.addSubmitHomework(homeworkId,studentId,filePath);//不存在提交记录则添加提交记录
	}
	
	public List<SubmitHomework> getSubmitHomeworkList(){
		return myDao.getSubmitHomeworks();
	}
	
	public void correctSubmitHomework(int id,int score,String remark){//助教为学生作业进行打分评价
		myDao.correctSubmitHomeworkInfo(id,score,remark);
	}
	
	public void publishHomeworkScore(int homeworkId){//发布作业成绩
		myDao.publishHomeworkScore(homeworkId);
	}
	
	public void recorrectHomeworkScore(int homeworkId){//要求助教重新批阅
		myDao.recorrectHomeworkScore(homeworkId);
	}
	
	public boolean isScorePublished(int homeworkId){//判断课程作业是否已经发布成绩
		List<Homework> homeworkList=myDao.getHomeworks();
		int published=0;
		for(Homework h:homeworkList){
			if(h.getId()==homeworkId){
				published=h.getScorePublished();
			}
		}
		if(published==0){
			return false;
		}
		return true;
		
	}
}
