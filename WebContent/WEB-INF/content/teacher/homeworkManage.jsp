<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>作业管理界面</title>
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
		style="width: 950px; height: 475px" url="getCourseHomework"
		toolbar="#toolbar" rownumbers="true" fitColumns="true"
		singleSelect="true">
		<thead>
			<tr>
				<th field="id" hidden="true">Id</th>
				<th field="homeworkId" width="20">Homework Id</th>
				<th field="submitDeadlineToString" width="40">Submit Deadline</th>
				<th field="correctDeadlineToString" width="40">Correct Deadline</th>
				<th field="format" width="30">Format</th>
				<th field="total" width="20">Total Score</th>
				<th field="difficulty" width="20">Difficulty</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<div style="margin: 5px;">
			<label>course:</label> <select id="teacherCourse"
				class="easyui-combogrid" name="courseInfoId" style="width: 200px"
				panelHeight="auto" onChange="updateDataGrid()"
				data-options="
			panelWidth: 500,
			idField: 'id',
			textField: 'courseName',
			url: 'getTeacherCourses?id=${id}',
			method: 'get',
			columns: [[
				{field:'id',title:'Id',width:40},
				{field:'courseId',title:'Course Id',width:80},
				{field:'courseName',title:'Course Name',width:120},
				{field:'year',title:'Year',width:40},
				{field:'term',title:'Term',width:40},
			]],
			fitColumns: true,
			onChange:function(newValue, oldValue){ updateDataGrid(newValue)}
			
		">
			</select>
		</div>
		<div style="margin-bottom: 5px;">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
				onclick="newHomework()">New Homework</a> <a href="#"
				class="easyui-linkbutton" iconCls="icon-edit" plain="true"
				onclick="editHomework()">Edit Homework</a> <a href="#"
				class="easyui-linkbutton" iconCls="icon-remove" plain="true"
				onclick="destroyHomework()">Remove Homework</a>
		</div>

	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 300px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">Homework Information</div>
		<form id="fm" method="post">
			<div class="fitem" style="padding: 5px; display: none;">
				<label>id:</label> <input id="id" name="homework.id"
					class="easyui-validatebox">
			</div>
			<div class="fitem" style="padding: 5px">
				<label style="margin-right: 26px;">homework id:</label> <input
					id="homeworkId" name="homework.homeworkId"
					class="easyui-numberspinner" data-options="min:1,increment:1">
			</div>
			<div class="fitem" style="padding: 5px">
				<label style="margin-right: 9px;">submit deadline:</label> <input
					id="submitDeadline" name="homework.submitDeadlineToString"
					class="easyui-datetimebox">
			</div>
			<div class="fitem" style="padding: 5px">
				<label style="margin-right: 5px;">correct deadline:</label> <input
					id="correctDeadline" name="homework.correctDeadlineToString"
					class="easyui-datetimebox">
			</div>
			<div class="fitem" style="padding: 5px">
				<label style="margin-right: 64px;">format:</label> <select
					id="format" class="easyui-combobox" name="homework.format"
					style="width: 151px;" panelHeight="auto">
					<option value="doc">doc</option>
					<option value="docx">docx</option>
					<option value="pdf">pdf</option>
				</select>
			</div>
			<div class="fitem" style="padding: 5px">
				<label style="margin-right: 41px;">total score:</label> <input
					id="total" name="homework.total" class="easyui-numberspinner"
					data-options="min:1,increment:1">
			</div>
			<div class="fitem" style="padding: 5px">
				<label style="margin-right: 54px;">difficulty:</label> <input
					id="difficulty" name="homework.difficulty"
					class="easyui-numberspinner" data-options="min:1,increment:1">
			</div>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="saveHomework()">Save</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
	</div>

	<script>
		var courseInfoId;
		function updateDataGrid(id) {
			courseInfoId = id;
			$('#dg').datagrid('load', {
				courseInfoId : id
			});
		}

		function newHomework() {
			$('#dlg').dialog('open').dialog('setTitle', 'New Homework');
			$('#fm').form('clear');
			url = 'addCourseHomework?courseInfoId=' + courseInfoId;
		}

		function editHomework() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$('#dlg').dialog('open').dialog('setTitle', 'Edit Homework');
				$('#id').val(row.id);
				$('#homeworkId').numberspinner('setValue', row.homeworkId);
				$('#submitDeadline').datetimebox('setValue',
						row.submitDeadlineToString);
				$('#correctDeadline').datetimebox('setValue',
						row.correctDeadlineToString);
				$('#format').combobox('setValue', row.format);
				$('#total').numberspinner('setValue', row.total);
				$('#difficulty').numberspinner('setValue', row.difficulty);
				url = 'editCourseHomework';
			}
		}

		function saveHomework() {
			$('#fm').form('submit', {
				url : url,
				success : function() {
					$('#dlg').dialog('close'); // close the dialog
					$('#dg').datagrid('reload'); // reload the user data
				}
			});
		}

		function destroyHomework() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('Confirm',
						'Are you sure you want to destroy this homework?',
						function(r) {
							if (r) {
								$.post('deleteCourseHomework', {
									homeworkId : row.id,
									courseInfoId : courseInfoId
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