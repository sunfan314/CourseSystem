<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员界面</title>
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
	<table id="dg" class="easyui-datagrid"
		style="width: 950px; height: 475px" url="getCourseInfos"
		toolbar="#toolbar" rownumbers="true" fitColumns="true"
		singleSelect="true">
		<thead>
			<tr>
				<th field="id" width="20">Id</th>
				<th field="courseId" width="20">CourseId</th>
				<th field="courseName" width="70">CourseName</th>
				<th field="year" width="30">Year</th>
				<th field="term" width="30">Term</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
			onclick="newCourse()">New Course</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-edit" plain="true"
			onclick="editCourse()">Edit Course</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-remove" plain="true"
			onclick="destroyCourse()">Remove Course</a>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">Course Information</div>
		<form id="fm" method="post">
			<div class="fitem" style="padding: 5px; display: none;">
				<label>id:</label> <input id="id" name="courseInfo.id"
					class="easyui-validatebox">
			</div>
			<div class="fitem" style="padding: 5px">
				<label>course:</label> <input id="course" class="easyui-combobox"
					name="courseInfo.course.id" panelHeight="auto"
					data-options="valueField:'id',textField:'courseName',url:'getCourseList'">
			</div>
			<div class="fitem" style="padding: 5px">
				<label>year:&nbsp;&nbsp;&nbsp;&nbsp;</label> <input id="year"
					name="courseInfo.year" class="easyui-numberspinner" value="2016"
					data-options="min:2014,increment:1">
			</div>
			<div class="fitem" style="padding: 5px">
				<label>term:&nbsp;&nbsp;&nbsp;&nbsp;</label> <input id="term"
					name="courseInfo.term" class="easyui-numberspinner" value="2"
					data-options="min:1,max:3,increment:1">
			</div>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="saveCourse()">Save</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
	</div>

	<div id="dlg1" class="easyui-dialog"
		style="width: 300px; height: 140px; padding: 10px 20px" closed="true"
		buttons="#dlg1-buttons">
		<label id="dialogInfo" style="font-size: 15px;"></label>
	</div>

	<div id="dlg1-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="javascript:$('#dlg1').dialog('close')">OK</a>
	</div>

	<script>
		function newCourse() {
			$('#dlg').dialog('open').dialog('setTitle', 'New Course');
			$('#fm').form('clear');
			url = 'addCourseInfo';
		}
	</script>

	<script>
		function editCourse() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$('#dlg').dialog('open').dialog('setTitle', 'Edit Course');
				$("#id").val(row.id);
				$('#course').combobox('setValue', row.courseId);
				$("#year").numberspinner('setValue',row.year);
				$('#term').numberspinner('setValue',row.term);
				url = 'editCourseInfo';
			}
		}
	</script>

	<script>
		function saveCourse() {
			$('#fm').form('submit', {
				url : url,
				success : function(result) {
					result=JSON.parse(result);
					if(result.success){
						$('#dlg').dialog('close'); // close the dialog
						$('#dg').datagrid('reload'); // reload the user data
					}else{
						$('#dialogInfo')
						.text(
								"Warning! Coursetime Setting Error!");
				$('#dlg1').dialog('open')
						.dialog('setTitle',
								'WARNING');
					}
				}
			});
		}
	</script>

	<script>
		function destroyCourse() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('Confirm',
						'Are you sure you want to destroy this course?',
						function(r) {
							if (r) {
								$.post('deleteCourseInfo', {
									id : row.id
								}, function(result) {
									if (result.success) {
										$('#dg').datagrid('reload');
									}

								}, 'json');

							}

						});
			}
		}
	</script>

</body>
</html>