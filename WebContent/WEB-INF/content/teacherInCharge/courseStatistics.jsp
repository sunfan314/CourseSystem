<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教学负责人管理界面</title>
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
	<table id="dg" class="easyui-datagrid" title="Course Statistics"
		style="width: 100%; height: auto" toolbar="#toolbar"
		data-options="singleSelect:true,collapsible:true,url:'getCourseStatistics',method:'get'">
		<thead>
			<tr>
				<th data-options="field:'id',width:150">Course Id</th>
				<th data-options="field:'courseName',width:200">Course Name</th>
				<th data-options="field:'year',width:150">Year</th>
				<th data-options="field:'term',width:150">Term</th>
				<th data-options="field:'studentNumber',width:200">Student
					Number</th>
				<th data-options="field:'homeworkNumber',width:200">Homework
					Number</th>
			</tr>
		</thead>
	</table>

	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
			onclick="createCourseExcelFile()">Create Excel File</a>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 300px; height: 140px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<label id="dialogInfo" style="font-size: 15px;"></label>
	</div>

	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="javascript:$('#dlg').dialog('close')">OK</a>
	</div>

</body>
<script>
	function createCourseExcelFile() {
		var row = $('#dg').datagrid('getSelected');
		var id = row.id;
		if (row) {
			$.post("createCourseExcelFile", {
				courseInfoId : id
			},
					function(result) {
						if (result.success) {
							$('#dialogInfo').text(
									"Success! You have create course_students file successfully!");
							$('#dlg').dialog('open').dialog('setTitle',
									'SUCCESS');
						}
					}, 'json');
		}
	}
</script>
</html>