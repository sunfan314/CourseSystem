<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程学生管理界面</title>
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
		style="width: 950px; height: 475px" url="getCourseStudents"
		toolbar="#toolbar" rownumbers="true" fitColumns="true"
		singleSelect="false"></table>

	<div id="toolbar">
		<div style="margin: 5px;">
			<label>course:</label> <select id="teacherCourse"
				class="easyui-combogrid" name="courseInfoId" style="width: 200px"
				panelHeight="auto"
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
			</select> <label style="margin-left: 10px;">action:</label> <select
				id="actionType" class="easyui-combobox" style="width: 200px;"
				panelHeight="auto"
				data-options="
				onChange:function(newValue, oldValue){changeActionType(newValue)}
				">
				<option value="delete">Remove Students</option>
				<option value="add">Add Students</option>
			</select>
		</div>
		<div style="margin-bottom: 5px;">
			<a id="deleteBtn" href="#" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="deleteStudents()">Remove
				Selected Students</a> <a id="addBtn" href="#" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" disabled="true"
				onclick="addStudents()">Add Selected Students</a>
		</div>

	</div>

	<script type="text/javascript">
		var courseInfoId;
		$(function() {
			$('#dg').datagrid({
				url : 'getCourseStudents',
				columns : [ [ {
					field : 'id',
					title : 'Id',
					width : 50
				}, {
					field : 'username',
					title : 'Student Name',
					width : 80
				}, {
					field : 'userType',
					title : 'Student Type',
					width : 80,
				} ] ]
			});
		});
		function updateDataGrid(id) {
			courseInfoId = id;
			$('#dg').datagrid('load', {
				courseInfoId : id
			});
		}

		function changeActionType(value) {
			if (value == "add") {
				$('#addBtn').linkbutton('enable');
				$('#deleteBtn').linkbutton('disable');
				$('#dg').datagrid({
					url : 'getOtherStudents',
					columns : [ [ {
						field : 'id',
						title : 'Id',
						width : 50
					}, {
						field : 'username',
						title : 'Student Name',
						width : 80
					}, {
						field : 'userType',
						title : 'Student Type',
						width : 80,
					} ] ]
				});
				$('#dg').datagrid('load', {
					courseInfoId : courseInfoId
				});
			}
			if (value == "delete") {
				$('#addBtn').linkbutton('disable');
				$('#deleteBtn').linkbutton('enable');
				$('#dg').datagrid({
					url : 'getCourseStudents',
					columns : [ [ {
						field : 'id',
						title : 'Id',
						width : 50
					}, {
						field : 'username',
						title : 'Student Name',
						width : 80
					}, {
						field : 'userType',
						title : 'Student Type',
						width : 80,
					} ] ]
				});
				$('#dg').datagrid('load', {
					courseInfoId : courseInfoId
				});
			}

		}

		function addStudents() {
			alert("add");
		}

		function deleteStudents() {
			alert("remove");
		}

		function getSelections() {
			var ss = [];
			var rows = $('#dg').datagrid('getSelections');
			for (var i = 0; i < rows.length; i++) {
				var row = rows[i];
				ss.push('<span>' + row.itemid + ":" + row.productid + ":"
						+ row.attr1 + '</span>');
			}
			$.messager.alert('Info', ss.join('<br/>'));
		}
	</script>
</body>
</html>