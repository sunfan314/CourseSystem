package edu.cn.dao;

import java.util.List;

import edu.cn.domain.Assistant;
import edu.cn.domain.Course;
import edu.cn.domain.CourseInfo;
import edu.cn.domain.Homework;
import edu.cn.domain.Student;
import edu.cn.domain.SubmitHomework;
import edu.cn.domain.Teacher;
import edu.cn.domain.TeacherInCharge;
import edu.cn.domain.User;

public interface CourseSystemDao {
	public List<User> getUsers();//��������û��б�
	
	public List<Student> getStudents();//�������ѧ���б�
	
	public List<Assistant> getAssistants();//��������б�
	
	public List<Teacher> getTeachers();//��ý�ʦ�б�
	
	public List<CourseInfo> getCourseInfos();//������пγ���Ϣ
	
	public List<Homework> getHomeworks();//��ÿγ���ҵ�б�
	
	public List<Course> getCourses();//��ÿγ��б�
	
	public void addUser(User user);//�����ݿ���ӻ��޸��û���Ϣ
	
	public void updateUser(User user,int type);//�����û���Ϣ
	
	public void deleteUser(int id);//�����ݿ�ɾ���û���Ϣ
	
	public void addCourseInfo(CourseInfo courseInfo);//�����ݿ���ӿγ���Ϣ
	
	public void updateCourseInfo(CourseInfo courseInfo);//���¿γ���Ϣ
	
	public void deleteCourseInfo(int id);//�����ݿ�ɾ���γ���Ϣ
	
	public void deleteCourseTeacher(CourseInfo courseInfo,Teacher teacher);//ɾ���γ̽�ʦ
	
	public void addCourseTeacher(CourseInfo courseInfo,Teacher teacher);//��ӿγ̽�ʦ
	
	public void deleteCourseAssistant(CourseInfo courseInfo,Assistant assistant);//ɾ���γ�����

	public void addCourseAssistant(CourseInfo courseInfo, Assistant assistant);//��ӿγ�����

	public void addCourseHomework(CourseInfo courseInfo, Homework homework);//��ӿγ���ҵ

	public void deleteCourseHomework(int courseInfoId, int homeworkId);//ɾ���γ���ҵ

	public void updateHomework(Homework homework);//���¿γ���ҵ��Ϣ

	public void addCourseStudent(int courseInfoId, int studentId);//��ӿγ�ѧ����Ϣ

	public void deleteCourseStudent(int courseInfoId, int studentId);//ɾ���γ�ѧ����Ϣ

	public void addHomeworkRequirement(int homeworkId, String requirement);//�����ҵҪ����Ϣ

	public void addHomeworkSummary(int homeworkId, String summary);//�����ҵ�ܽ���Ϣ

	public void addHomeworkExamplePath(int homeworkId, String filePath);//��ӿγ�����·��

	public void addSubmitHomework(int homeworkId, int studentId, String filePath);//���ѧ����ҵ��Ӽ�¼

	public List<SubmitHomework> getSubmitHomeworks();// �����ҵ�ύ��Ϣ�б�
	
	public void updateSubmitHomeworkInfo(SubmitHomework submitHomework);// ������ҵ�ύ��Ϣ

	public void correctSubmitHomeworkInfo(int id, int score, String remark);//Ϊ��ҵ���ֺ�����

	public void publishHomeworkScore(int homeworkId);//������ҵ�ɼ�

	public void recorrectHomeworkScore(int homeworkId);//��������
	
	
}
