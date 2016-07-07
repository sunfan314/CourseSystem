<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的课程</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/easyui/themes/color.css">
<script
	src="${pageContext.request.contextPath}/jquery/jquery-2.1.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>

</head>
<body>
	<table id="dg" class="easyui-datagrid" title="My Courses"
		style="width: 950px; height: 475px" url="getStudentCourses?id=${id}"
		rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="id" width="20">Id</th>
				<th field="courseId" width="20">CourseId</th>
				<th field="courseName" width="50">CourseName</th>
				<th field="teachers" width="70">Course Teacher</th>
				<th field="assistants" width="70">Course Assistant</th>
			</tr>
		</thead>
	</table>

	<script type="text/javascript">
		$(function() {
			$('#dg').datagrid({
				onClickRow : function() {
					var row = $('#dg').datagrid('getSelected');
					if (row) {
						var text=row.courseName;
						var url="courseHomework?studentId=${id}&courseInfoId="+row.id;
						window.parent.Open(text,url);
					}
				}
			});
		});
	</script>
</body>
</html>