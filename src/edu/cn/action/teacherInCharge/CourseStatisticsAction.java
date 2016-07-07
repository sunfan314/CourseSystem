package edu.cn.action.teacherInCharge;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.Action;

import edu.cn.domain.CourseInfo;
import edu.cn.domain.Student;
import edu.cn.service.CourseService;
import edu.cn.serviceImpl.CourseServiceImpl;

public class CourseStatisticsAction implements Action {
	private JSONObject json;
	private int courseInfoId;

	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

	public int getCourseInfoId() {
		return courseInfoId;
	}

	public void setCourseInfoId(int courseInfoId) {
		this.courseInfoId = courseInfoId;
	}

	@Override
	public String execute() throws Exception {
		CourseService cs = new CourseServiceImpl();
		List<CourseInfo> courseInfos = cs.getCourseInfoList();
		for (CourseInfo c : courseInfos) {
			c.setStudentNumber(c.getStudentList().size());
			c.setHomeworkNumber(c.getHomeworkList().size());
			c.setAssList(null);
			c.setHomeworkList(null);
			c.setStudentList(null);
			c.setTeacherList(null);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", courseInfos);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	public String createCourseExcelFile() throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		CourseService cs=new CourseServiceImpl();
		List<CourseInfo> courseInfos=cs.getCourseInfoList();
		List<Student> courseStudents = new ArrayList<Student>();
		for(CourseInfo c:courseInfos){
			if(c.getId()==courseInfoId);
			courseStudents=c.getStudentList();
		}
		
		// 选课学生数据
		{
			String[] excelHeader = { "学号", "姓名"};

			HSSFSheet sheet = workbook.createSheet("选课学生");
			HSSFRow row = sheet.createRow((int) 0);
			HSSFCellStyle style = workbook.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			for (int i = 0; i < excelHeader.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(excelHeader[i]);
				cell.setCellStyle(style);
			}
			
			sheet.setColumnWidth(0,3500 );
			sheet.setColumnWidth(1,2500 );
			sheet.setColumnWidth(2,2500);

			for (int i = 0; i < courseStudents.size(); i++) {
				row = sheet.createRow(i + 1);
				Student student = courseStudents.get(i);
				row.createCell(0).setCellValue(""+student.getId());
				row.createCell(1).setCellValue(""+student.getUsername());
			}
		}


		String outputFile = getCourseExcelFilePath(courseInfoId)+"/courseStudents.xls";
		FileOutputStream fOut;
		try {
			fOut = new FileOutputStream(outputFile);
			workbook.write(fOut);
			workbook.close();
			fOut.flush();
			fOut.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	public String getCourseExcelFilePath(int courseInfoId){
		CourseService cs=new CourseServiceImpl();
		CourseInfo courseInfo=new CourseInfo();
		for(CourseInfo c:cs.getCourseInfoList()){
			if(c.getId()==courseInfoId){
				courseInfo=c;
			}
		}
		String fileFolderPath="/Users/sunfan314/desktop/courses/"+courseInfo.getCourseName()+"_"+courseInfoId;
		File fileFolder = new File(fileFolderPath);
		if(!fileFolder.exists()){
			fileFolder.mkdirs();
		}
		return fileFolderPath;
	}

}
