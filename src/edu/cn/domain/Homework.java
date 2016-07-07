package edu.cn.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "homework")
public class Homework {
	@Id
	private int id;
	private int homeworkId;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "courseInfoId", unique = true)
	private CourseInfo courseInfo;
	private Date submitDeadline;
	private Date correctDeadline;
	private String format;
	private int total;
	private int difficulty;
	private String requirement;
	private String summary;
	private String exampleFilePath;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "homework", cascade = CascadeType.ALL)
	private List<SubmitHomework> submitHomeworks;
	private boolean deadlineOver;
	private boolean exampleSubmitted;
	private boolean homeworkSubmitted;
	private int checked;// 老师是否审核过作业
	private int scorePublished;// 作业成绩是否发布
	private int score;
	private String scoreToString;
	private String homeworkStatus;
	private String courseName;

	public Homework() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHomeworkId() {
		return homeworkId;
	}

	public void setHomeworkId(int homeworkId) {
		this.homeworkId = homeworkId;
	}

	public CourseInfo getCourseInfo() {
		return courseInfo;
	}

	public void setCourseInfo(CourseInfo courseInfo) {
		this.courseInfo = courseInfo;
	}

	public String getSubmitDeadlineToString() {
		String date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
				.format(this.submitDeadline);
		return date;
	}

	public void setSubmitDeadlineToString(String date) throws ParseException {
		submitDeadline = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
				.parse(date);
	}

	public Date getSubmitDeadline() {
		return submitDeadline;
	}

	public void setSubmitDeadline(Date submitDeadline) {
		this.submitDeadline = submitDeadline;
	}

	public String getCorrectDeadlineToString() {
		String date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
				.format(this.correctDeadline);
		return date;
	}

	public void setCorrectDeadlineToString(String date) throws ParseException {
		correctDeadline = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
				.parse(date);
	}

	public Date getCorrectDeadline() {
		return correctDeadline;
	}

	public void setCorrectDeadline(Date correctDeadline) {
		this.correctDeadline = correctDeadline;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getExampleFilePath() {
		return exampleFilePath;
	}

	public void setExampleFilePath(String exampleFilePath) {
		this.exampleFilePath = exampleFilePath;
	}

	public List<SubmitHomework> getSubmitHomeworks() {
		return submitHomeworks;
	}

	public void setSubmitHomeworks(List<SubmitHomework> submitHomeworks) {
		this.submitHomeworks = submitHomeworks;
	}

	public boolean isDeadlineOver() {
		return deadlineOver;
	}

	public void setDeadlineOver(boolean deadlineOver) {
		this.deadlineOver = deadlineOver;
	}

	public boolean isExampleSubmitted() {
		return exampleSubmitted;
	}

	public void setExampleSubmitted(boolean exampleSubmitted) {
		this.exampleSubmitted = exampleSubmitted;
	}

	public boolean isHomeworkSubmitted() {
		return homeworkSubmitted;
	}

	public void setHomeworkSubmitted(boolean homeworkSubmitted) {
		this.homeworkSubmitted = homeworkSubmitted;
	}

	public int getChecked() {
		return checked;
	}

	public void setChecked(int checked) {
		this.checked = checked;
	}

	public int getScorePublished() {
		return scorePublished;
	}

	public void setScorePublished(int scorePublished) {
		this.scorePublished = scorePublished;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getScoreToString() {
		return scoreToString;
	}

	public void setScoreToString(String scoreToString) {
		this.scoreToString = scoreToString;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getHomeworkStatus() {
		return homeworkStatus;
	}

	public void setHomeworkStatus(String homeworkStatus) {
		this.homeworkStatus = homeworkStatus;
	}

}
