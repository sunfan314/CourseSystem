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

	public String getHomeworkFileFoler(int courseInfoId, int homeworkId){//���ݿγ�id����ҵid������Ӧ���ļ���
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
	
	public void addHomeworkExamplePath(int homeworkId, String filePath){//��ӿγ�example���ļ�·��
		myDao.addHomeworkExamplePath(homeworkId,filePath);
	}
	
	public void addSubmitHomeworkPath(int homeworkId, int studentId,String filePath){//���ѧ���ύ��ҵ��Ϣ
		List<SubmitHomework> submitHomeworkList=myDao.getSubmitHomeworks();
		for(SubmitHomework s:submitHomeworkList){
			if(s.getHomework().getId()==homeworkId&&s.getStudentId()==studentId){//�Ѵ����ύ��¼�����
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
		myDao.addSubmitHomework(homeworkId,studentId,filePath);//�������ύ��¼������ύ��¼
	}
	
	public List<SubmitHomework> getSubmitHomeworkList(){
		return myDao.getSubmitHomeworks();
	}
	
	public void correctSubmitHomework(int id,int score,String remark){//����Ϊѧ����ҵ���д������
		myDao.correctSubmitHomeworkInfo(id,score,remark);
	}
	
	public void publishHomeworkScore(int homeworkId){//������ҵ�ɼ�
		myDao.publishHomeworkScore(homeworkId);
	}
	
	public void recorrectHomeworkScore(int homeworkId){//Ҫ��������������
		myDao.recorrectHomeworkScore(homeworkId);
	}
	
	public boolean isScorePublished(int homeworkId){//�жϿγ���ҵ�Ƿ��Ѿ������ɼ�
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
