<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>助教管理界面</title>
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
		style="width: 950px; height: 475px"
		url="getTeacherAssistants?id=${id}" toolbar="#toolbar"
		rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="id" width="20">Id</th>
				<th field="courseId" width="20">CourseId</th>
				<th field="courseName" width="50">CourseName</th>
				<th field="assistants" width="70">Course Assistant</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
			onclick="addAssistant()">Add Assistant To Course</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-remove" plain="true"
			onclick="deleteAssistant()">Remove Assistant From Course</a>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">Course Information</div>
		<form id="fm" method="post">
			<div class="fitem" style="padding: 5px;">
				<label>id:</label> <input id="id" name="courseInfo.id"
					class="easyui-validatebox" style="margin-left: 38px;" readonly>
			</div>
			<div class="fitem" style="padding: 5px;">
				<label>courseId:</label> <input id="courseId"
					name="courseInfo.course.id" class="easyui-validatebox" readonly>
			</div>
			<div class="fitem" style="padding: 5px;">
				<label>name:</label> <input id="courseName"
					name="courseInfo.course.courseName" class="easyui-validatebox"
					style="margin-left: 16px;" readonly>
			</div>
			<div class="fitem" style="padding: 5px;">
				<label>assistants:</label> <input id="courseAssistants"
					name="courseInfo.assistants" class="easyui-validatebox" readonly>
			</div>
			<div id="addAssistantDiv" class="fitem" style="padding: 5px;">
				<label style="margin-bottom: -3px; display: block;">selete
					the assistant you want to</label> <br /> <label
					style="margin-right: 28px;">add:</label> <input
					id="couresAssistantList" class="easyui-combobox"
					name="assistantToAdd.id" panelHeight="auto">
			</div>

			<div id="deleteAssistantDiv" class="fitem" style="padding: 5px;">
				<label style="margin-bottom: -3px; display: block;">selete
					the assistant you want to</label> <br /> <label style="margin-right: 8px;">remove:</label>
				<input id="couresAssistantList_1" class="easyui-combobox"
					name="assistantToRemove.id" panelHeight="auto">
			</div>

		</form>
	</div>

	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="saveCourseAssistant()">Save</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
	</div>

	<script>
		function addAssistant() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$('#deleteAssistantDiv').hide();
				$('#addAssistantDiv').show();
				$('#couresAssistantList').combobox({
					url : 'getOtherAssistantList?id='+row.id,
					valueField : 'id',
					textField : 'username'
					
				});
				$('#dlg').dialog('open').dialog('setTitle',
						'Add Assistant to Course');
				$("#id").val(row.id);
				$("#courseId").val(row.courseId);
				$('#courseName').val(row.courseName);
				$('#courseAssistants').val(row.assistants);
				url = 'addCourseAssistant';
			}
		}
	</script>

	<script>
		function deleteAssistant() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$('#addAssistantDiv').hide();
				$('#deleteAssistantDiv').show();
				$('#couresAssistantList_1').combobox({
					url : 'getCourseAssistantList?id='+row.id,
					valueField : 'id',
					textField : 'username'
					
				});
				$('#dlg').dialog('open').dialog('setTitle',
						'Remove Assistant From Course');
				$("#id").val(row.id);
				$("#courseId").val(row.courseId);
				$('#courseName').val(row.courseName);
				$('#courseAssistants').val(row.assistants);
				url = 'deleteCourseAssistant';
			}
		}
	</script>

	<script>
		function saveCourseAssistant() {
			$('#fm').form('submit', {
				url : url,
				success : function() {
					$('#dlg').dialog('close'); // close the dialog
					$('#dg').datagrid('reload'); // reload the user data
				}
			});
		}
	</script>
</body>
</html>