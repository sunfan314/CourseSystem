package edu.cn.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import edu.cn.dao.CourseSystemDao;
import edu.cn.domain.Assistant;
import edu.cn.domain.Course;
import edu.cn.domain.CourseInfo;
import edu.cn.domain.Homework;
import edu.cn.domain.Student;
import edu.cn.domain.SubmitHomework;
import edu.cn.domain.Teacher;
import edu.cn.domain.User;
import edu.cn.util.HibernateUtil;

public class CourseSystemDaoImpl implements CourseSystemDao {
	private static CourseSystemDaoImpl myDao;

	public CourseSystemDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	public static CourseSystemDaoImpl getInstance() {
		if (myDao == null) {
			myDao = new CourseSystemDaoImpl();
		}
		return myDao;
	}

	public List<User> getUsers() {// ��������û��б�
		List<User> userList = new ArrayList<User>();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from User");
		userList = query.list();
		tx.commit();
		session.close();
		return userList;
	}

	public List<Student> getStudents() {// �������ѧ���б�
		List<Student> stuList = new ArrayList<Student>();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Student");
		stuList = query.list();
		tx.commit();
		session.close();
		return stuList;
	}

	public List<Assistant> getAssistants() {// ��������б�
		List<Assistant> assList = new ArrayList<Assistant>();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Assistant");
		assList = query.list();
		tx.commit();
		session.close();
		return assList;
	}

	public List<Teacher> getTeachers() {// ��ý�ʦ�б�
		List<Teacher> teaList = new ArrayList<Teacher>();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Teacher");
		teaList = query.list();
		tx.commit();
		session.close();

		return teaList;
	}

	public List<CourseInfo> getCourseInfos() {// ������пγ���Ϣ
		List<CourseInfo> courseInfoList = new ArrayList<CourseInfo>();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from CourseInfo");
		courseInfoList = query.list();
		tx.commit();
		session.close();

		return courseInfoList;
	}

	public List<Homework> getHomeworks() {// ��ÿγ���ҵ�б�
		List<Homework> homeworkList = new ArrayList<Homework>();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Homework");
		homeworkList = query.list();
		tx.commit();
		session.close();

		return homeworkList;

	}

	public List<Course> getCourses() {// ��ÿγ��б�
		List<Course> courseList = new ArrayList<Course>();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Course");
		courseList = query.list();
		tx.commit();
		session.close();

		return courseList;
	}

	public void addUser(User user) {// �����ݿ���ӻ��޸��û���Ϣ
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();
		session.close();

	}

	public void updateUser(User user, int type) {// �����û���Ϣ
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String hql = "update User set username='" + user.getUsername()
				+ "', password='" + user.getPassword() + "',type='" + type
				+ "' where id='" + user.getId() + "'";
		Query query = session.createQuery(hql);
		int ret = query.executeUpdate();
		tx.commit();
		session.close();

	}

	public void deleteUser(int id) {// �����ݿ�ɾ���û���Ϣ
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete User where id='" + id + "'";
		Query query = session.createQuery(hql);
		int ret = query.executeUpdate();
		tx.commit();
		session.close();

	}

	public void addCourseInfo(CourseInfo courseInfo) {// �����ݿ���ӿγ���Ϣ
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(courseInfo);
		tx.commit();
		session.close();

	}

	public void updateCourseInfo(CourseInfo courseInfo) {// ���¿γ���Ϣ
		List<CourseInfo> courseInfoList = new ArrayList<CourseInfo>();
		List<Course> courseList = new ArrayList<Course>();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from CourseInfo");
		Query query_1 = session.createQuery("from Course");
		courseInfoList = query.list();
		courseList = query_1.list();
		for (CourseInfo c : courseInfoList) {
			if (c.getId() == courseInfo.getId()) {
				Course course = new Course();
				for (Course c1 : courseList) {
					if (c1.getId() == courseInfo.getCourseId()) {
						course = c1;
						break;
					}
				}
				c.setCourse(course);
				c.setYear(courseInfo.getYear());
				c.setTerm(courseInfo.getTerm());
				session.save(c);
				break;
			}
		}
		tx.commit();
		session.close();

	}

	public void deleteCourseInfo(int id) {// �����ݿ�ɾ���γ���Ϣ
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete CourseInfo where id='" + id + "'";
		Query query = session.createQuery(hql);
		int ret = query.executeUpdate();
		tx.commit();
		session.close();

	}

	public void deleteCourseTeacher(CourseInfo courseInfo, Teacher teacher) {// ɾ���γ̽�ʦ
		List<CourseInfo> courseInfoList = new ArrayList<CourseInfo>();
		List<Teacher> teacherList = new ArrayList<Teacher>();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from CourseInfo");
		courseInfoList = query.list();
		for (CourseInfo c : courseInfoList) {
			if (c.getId() == courseInfo.getId()) {
				for (int i = 0; i < c.getTeacherList().size(); i++) {
					if (c.getTeacherList().get(i).getId() == teacher.getId()) {
						c.getTeacherList().remove(i);// ɾ���γ���ָ���Ľ�ʦ
						i--;
						session.save(c);
						break;
					}

				}

			}
		}
		Query query_1 = session.createQuery("from Teacher");
		teacherList = query_1.list();
		for (Teacher t : teacherList) {
			if (t.getId() == teacher.getId()) {
				for (int i = 0; i < t.getCourseList().size(); i++) {
					if (t.getCourseList().get(i).getId() == courseInfo.getId()) {
						t.getCourseList().remove(i);// ɾ����ʦ��Ӧ�Ŀγ�
						i--;
						session.save(t);
						break;
					}
				}
			}
		}
		tx.commit();
		session.close();

	}

	public void addCourseTeacher(CourseInfo courseInfo, Teacher teacher) {// ��ӿγ̽�ʦ
		List<CourseInfo> courseInfoList = new ArrayList<CourseInfo>();
		List<Teacher> teacherList = new ArrayList<Teacher>();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from CourseInfo");
		courseInfoList = query.list();
		Query query_1 = session.createQuery("from Teacher");
		teacherList = query_1.list();
		for (CourseInfo c : courseInfoList) {
			if (c.getId() == courseInfo.getId()) {
				for (Teacher t : teacherList) {
					if (t.getId() == teacher.getId()) {
						c.getTeacherList().add(t);
						session.save(c);
						break;
					}
				}
			}
		}
		for (Teacher t : teacherList) {
			if (t.getId() == teacher.getId()) {
				for (CourseInfo c : courseInfoList) {
					if (c.getId() == courseInfo.getId()) {
						t.getCourseList().add(c);
						session.save(t);
						break;
					}
				}
			}
		}
		tx.commit();
		session.close();

	}

	public void deleteCourseAssistant(CourseInfo courseInfo, Assistant assistant) {// ɾ���γ�����
		List<CourseInfo> courseInfoList = new ArrayList<CourseInfo>();
		List<Assistant> assistantList = new ArrayList<Assistant>();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from CourseInfo");
		courseInfoList = query.list();
		for (CourseInfo c : courseInfoList) {
			if (c.getId() == courseInfo.getId()) {
				for (int i = 0; i < c.getAssList().size(); i++) {
					if (c.getAssList().get(i).getId() == assistant.getId()) {
						c.getAssList().remove(i);// ɾ���γ���ָ��������
						i--;
						session.save(c);
						break;
					}

				}

			}
		}
		Query query_1 = session.createQuery("from Assistant");
		assistantList = query_1.list();
		for (Assistant a : assistantList) {
			if (a.getId() == assistant.getId()) {
				for (int i = 0; i < a.getCourseAssistList().size(); i++) {
					if (a.getCourseAssistList().get(i).getId() == courseInfo
							.getId()) {
						a.getCourseAssistList().remove(i);// ɾ�����̶�Ӧ�Ŀγ�
						i--;
						session.save(a);
						break;
					}
				}
			}
		}
		tx.commit();
		session.close();

	}

	public void addCourseAssistant(CourseInfo courseInfo, Assistant assistant) {// ��ӿγ�����
		List<CourseInfo> courseInfoList = new ArrayList<CourseInfo>();
		List<Assistant> assistantList = new ArrayList<Assistant>();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from CourseInfo");
		courseInfoList = query.list();
		Query query_1 = session.createQuery("from Assistant");
		assistantList = query_1.list();
		for (CourseInfo c : courseInfoList) {
			if (c.getId() == courseInfo.getId()) {
				for (Assistant a : assistantList) {
					if (a.getId() == assistant.getId()) {
						c.getAssList().add(a);
						session.save(c);
						break;
					}
				}
			}
		}
		for (Assistant a : assistantList) {
			if (a.getId() == assistant.getId()) {
				for (CourseInfo c : courseInfoList) {
					if (c.getId() == courseInfo.getId()) {
						a.getCourseAssistList().add(c);
						session.save(a);
						break;
					}
				}
			}
		}
		tx.commit();
		session.close();

	}

	public void addCourseHomework(CourseInfo courseInfo, Homework homework) {// ��ӿγ���ҵ
		List<CourseInfo> courseInfoList = new ArrayList<CourseInfo>();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from CourseInfo");
		courseInfoList = query.list();
		for (CourseInfo c : courseInfoList) {
			if (c.getId() == courseInfo.getId()) {
				homework.setCourseInfo(c);
				session.save(homework);
				c.getHomeworkList().add(homework);
				session.save(c);
			}
		}

		tx.commit();
		session.close();

	}

	public void deleteCourseHomework(int courseInfoId, int homeworkId) {// ɾ���γ���ҵ
		List<CourseInfo> courseInfoList = new ArrayList<CourseInfo>();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from CourseInfo");
		courseInfoList = query.list();
		for (CourseInfo c : courseInfoList) {
			if (c.getId() == courseInfoId) {
				for (int i = 0; i < c.getHomeworkList().size(); i++) {
					if (c.getHomeworkList().get(i).getId() == homeworkId) {
						c.getHomeworkList().remove(i);// ɾ���γ���ָ������ҵ
						i--;
						session.save(c);
						break;
					}

				}

			}
		}
		String hql = "delete Homework where id='" + homeworkId + "'";
		Query query_1 = session.createQuery(hql);
		int ret = query_1.executeUpdate();
		tx.commit();
		session.close();

	}

	public void updateHomework(Homework homework) {// ���¿γ���ҵ��Ϣ
		List<Homework> homeworkList = new ArrayList<Homework>();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Homework");
		homeworkList = query.list();
		for (Homework h : homeworkList) {
			if (h.getId() == homework.getId()) {
				h.setHomeworkId(homework.getHomeworkId());
				h.setSubmitDeadline(homework.getSubmitDeadline());
				h.setCorrectDeadline(homework.getCorrectDeadline());
				h.setFormat(homework.getFormat());
				h.setTotal(homework.getTotal());
				h.setDifficulty(homework.getDifficulty());
				session.save(h);
				break;
			}
		}
		tx.commit();
		session.close();

	}

	public void addCourseStudent(int courseInfoId, int studentId) {// ��ӿγ�ѧ����Ϣ
		List<CourseInfo> courseInfoList = new ArrayList<CourseInfo>();
		List<Student> studentList = new ArrayList<Student>();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from CourseInfo");
		courseInfoList = query.list();
		Query query_1 = session.createQuery("from Student");
		studentList = query_1.list();
		for (CourseInfo c : courseInfoList) {
			if (c.getId() == courseInfoId) {
				for (Student s : studentList) {
					if (s.getId() == studentId) {
						c.getStudentList().add(s);
						session.save(c);
						break;
					}
				}
			}
		}
		for (Student s : studentList) {
			if (s.getId() == studentId) {
				for (CourseInfo c : courseInfoList) {
					if (c.getId() == courseInfoId) {
						s.getCourseList().add(c);
						session.save(s);
						break;
					}
				}
			}
		}
		tx.commit();
		session.close();

	}

	public void deleteCourseStudent(int courseInfoId, int studentId) {// ɾ���γ�ѧ����Ϣ
		List<CourseInfo> courseInfoList = new ArrayList<CourseInfo>();
		List<Student> StudentList = new ArrayList<Student>();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from CourseInfo");
		courseInfoList = query.list();
		for (CourseInfo c : courseInfoList) {
			if (c.getId() == courseInfoId) {
				for (int i = 0; i < c.getStudentList().size(); i++) {
					if (c.getStudentList().get(i).getId() == studentId) {
						c.getAssList().remove(i);// ɾ���γ���ѧ��
						i--;
						session.save(c);
						break;
					}

				}

			}
		}
		Query query_1 = session.createQuery("from Student");
		StudentList = query_1.list();
		for (Student s : StudentList) {
			if (s.getId() == studentId) {
				for (int i = 0; i < s.getCourseList().size(); i++) {
					if (s.getCourseList().get(i).getId() == courseInfoId) {
						s.getCourseList().remove(i);// ɾ��ѧ����Ӧ�Ŀγ�
						i--;
						session.save(s);
						break;
					}
				}
			}
		}
		tx.commit();
		session.close();

	}

	public void addHomeworkRequirement(int homeworkId, String requirement) {// �����ҵҪ����Ϣ
		List<Homework> homeworkList = new ArrayList<Homework>();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Homework");
		homeworkList = query.list();
		for (Homework h : homeworkList) {
			if (h.getId() == homeworkId) {
				h.setRequirement(requirement);
				session.save(h);
				break;
			}
		}
		tx.commit();
		session.close();

	}

	public void addHomeworkSummary(int homeworkId, String summary) {// �����ҵ�ܽ���Ϣ
		List<Homework> homeworkList = new ArrayList<Homework>();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Homework");
		homeworkList = query.list();
		for (Homework h : homeworkList) {
			if (h.getId() == homeworkId) {
				h.setSummary(summary);
				session.save(h);
				break;
			}
		}
		tx.commit();
		session.close();

	}

	public void addHomeworkExamplePath(int homeworkId, String filePath) {// ��ӿγ�����·��
		List<Homework> homeworkList = new ArrayList<Homework>();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Homework");
		homeworkList = query.list();
		for (Homework h : homeworkList) {
			if (h.getId() == homeworkId) {
				h.setExampleFilePath(filePath);
				session.save(h);
				break;
			}
		}
		tx.commit();
		session.close();

	}

	public void addSubmitHomework(int homeworkId, int studentId, String filePath) {// ���ѧ����ҵ��Ӽ�¼
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		SubmitHomework homework = new SubmitHomework();
		homework.setStudentId(studentId);
		homework.setFilePath(filePath);
		List<Homework> homeworkList = new ArrayList<Homework>();
		Query query = session.createQuery("from Homework");
		homeworkList = query.list();
		for (Homework h : homeworkList) {
			if (h.getId() == homeworkId) {
				homework.setHomework(h);
			}
		}
		session.save(homework);
		tx.commit();
		session.close();

	}

	public List<SubmitHomework> getSubmitHomeworks() {// �����ҵ�ύ��Ϣ�б�
		List<SubmitHomework> submitHomeworkList = new ArrayList<SubmitHomework>();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from SubmitHomework");
		submitHomeworkList = query.list();
		tx.commit();
		session.close();

		return submitHomeworkList;
	}

	public void updateSubmitHomeworkInfo(SubmitHomework submitHomework) {// ������ҵ�ύ��Ϣ
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<SubmitHomework> submitHomeworkList = new ArrayList<SubmitHomework>();
		Query query = session.createQuery("from SubmitHomework");
		submitHomeworkList = query.list();
		for (SubmitHomework s : submitHomeworkList) {
			if (s.getHomework().getId() == submitHomework.getHomework().getId()
					&& s.getStudentId() == submitHomework.getStudentId()) {
				if (!(submitHomework.getFilePath() == null)) {
					s.setFilePath(submitHomework.getFilePath());
				}
				session.save(s);
				break;
			}
		}
		tx.commit();
		session.close();

	}

	public void correctSubmitHomeworkInfo(int id, int score, String remark) {// Ϊ��ҵ���ֺ�����
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<SubmitHomework> submitHomeworkList = new ArrayList<SubmitHomework>();
		Query query = session.createQuery("from SubmitHomework");
		submitHomeworkList = query.list();
		for (SubmitHomework s : submitHomeworkList) {
			if (s.getId() == id) {
				s.setScore(score);
				s.setRemark(remark);
				session.save(s);
				break;
			}
		}
		tx.commit();
		session.close();

	}

	public void publishHomeworkScore(int homeworkId) {// ������ҵ�ɼ�
		List<Homework> homeworkList = new ArrayList<Homework>();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Homework");
		homeworkList = query.list();
		for (Homework h : homeworkList) {
			if (h.getId() == homeworkId) {
				h.setChecked(1);
				h.setScorePublished(1);
				session.save(h);
				break;
			}
		}
		tx.commit();
		session.close();

	}

	public void recorrectHomeworkScore(int homeworkId) {// ��������
		List<Homework> homeworkList = new ArrayList<Homework>();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Homework");
		homeworkList = query.list();
		for (Homework h : homeworkList) {
			if (h.getId() == homeworkId) {
				h.setChecked(1);
				h.setScorePublished(0);
				session.save(h);
				break;
			}
		}
		tx.commit();
		session.close();

	}

}
