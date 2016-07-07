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

	public List<User> getUsers() {// 获得所有用户列表
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

	public List<Student> getStudents() {// 获得所有学生列表
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

	public List<Assistant> getAssistants() {// 获得助教列表
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

	public List<Teacher> getTeachers() {// 获得教师列表
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

	public List<CourseInfo> getCourseInfos() {// 获得所有课程信息
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

	public List<Homework> getHomeworks() {// 获得课程作业列表
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

	public List<Course> getCourses() {// 获得课程列表
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

	public void addUser(User user) {// 向数据库添加或修改用户信息
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();
		session.close();

	}

	public void updateUser(User user, int type) {// 更新用户信息
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

	public void deleteUser(int id) {// 向数据库删除用户信息
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete User where id='" + id + "'";
		Query query = session.createQuery(hql);
		int ret = query.executeUpdate();
		tx.commit();
		session.close();

	}

	public void addCourseInfo(CourseInfo courseInfo) {// 向数据库添加课程信息
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(courseInfo);
		tx.commit();
		session.close();

	}

	public void updateCourseInfo(CourseInfo courseInfo) {// 更新课程信息
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

	public void deleteCourseInfo(int id) {// 向数据库删除课程信息
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete CourseInfo where id='" + id + "'";
		Query query = session.createQuery(hql);
		int ret = query.executeUpdate();
		tx.commit();
		session.close();

	}

	public void deleteCourseTeacher(CourseInfo courseInfo, Teacher teacher) {// 删除课程教师
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
						c.getTeacherList().remove(i);// 删除课程中指定的教师
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
						t.getCourseList().remove(i);// 删除教师对应的课程
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

	public void addCourseTeacher(CourseInfo courseInfo, Teacher teacher) {// 添加课程教师
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

	public void deleteCourseAssistant(CourseInfo courseInfo, Assistant assistant) {// 删除课程助教
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
						c.getAssList().remove(i);// 删除课程中指定的助教
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
						a.getCourseAssistList().remove(i);// 删除助教对应的课程
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

	public void addCourseAssistant(CourseInfo courseInfo, Assistant assistant) {// 添加课程助教
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

	public void addCourseHomework(CourseInfo courseInfo, Homework homework) {// 添加课程作业
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

	public void deleteCourseHomework(int courseInfoId, int homeworkId) {// 删除课程作业
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
						c.getHomeworkList().remove(i);// 删除课程中指定的作业
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

	public void updateHomework(Homework homework) {// 更新课程作业信息
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

	public void addCourseStudent(int courseInfoId, int studentId) {// 添加课程学生信息
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

	public void deleteCourseStudent(int courseInfoId, int studentId) {// 删除课程学生信息
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
						c.getAssList().remove(i);// 删除课程中学生
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
						s.getCourseList().remove(i);// 删除学生对应的课程
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

	public void addHomeworkRequirement(int homeworkId, String requirement) {// 添加作业要求信息
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

	public void addHomeworkSummary(int homeworkId, String summary) {// 添加作业总结信息
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

	public void addHomeworkExamplePath(int homeworkId, String filePath) {// 添加课程样例路径
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

	public void addSubmitHomework(int homeworkId, int studentId, String filePath) {// 添加学生作业添加记录
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

	public List<SubmitHomework> getSubmitHomeworks() {// 获得作业提交信息列表
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

	public void updateSubmitHomeworkInfo(SubmitHomework submitHomework) {// 更新作业提交信息
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

	public void correctSubmitHomeworkInfo(int id, int score, String remark) {// 为作业评分和评价
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

	public void publishHomeworkScore(int homeworkId) {// 发布作业成绩
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

	public void recorrectHomeworkScore(int homeworkId) {// 重新批阅
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
