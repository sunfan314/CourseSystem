package edu.cn.service;

import java.util.List;

import edu.cn.domain.Homework;
import edu.cn.domain.SubmitHomework;

public interface HomeworkService {
	public List<Homework> getHomeworkList();

	public void addHomeworkRequirement(int homeworkId, String requirement);

	public void addHomeworkSummary(int homeworkId, String summary);

	public String getHomeworkFileFoler(int courseInfoId, int homeworkId);//���ݿγ�id����ҵid������Ӧ���ļ���

	public void addHomeworkExamplePath(int homeworkId, String filePath);//��ӿγ�example���ļ�·��

	public void addSubmitHomeworkPath(int homeworkId, int studentId,String filePath);//���ѧ���ύ��ҵ��Ϣ
	
	public List<SubmitHomework> getSubmitHomeworkList();
	
	public void correctSubmitHomework(int id,int score,String remark);//����Ϊѧ����ҵ���д������

	public void publishHomeworkScore(int homeworkId);//������ҵ�ɼ�

	public void recorrectHomeworkScore(int homeworkId);//Ҫ��������������

	public boolean isScorePublished(int homeworkId);//�жϿγ���ҵ�Ƿ��Ѿ������ɼ�
}
