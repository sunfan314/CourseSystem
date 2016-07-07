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
		style="width: 950px; height: 475px" url="getStudents"
		toolbar="#toolbar" rownumbers="true" fitColumns="true"
		singleSelect="true">
		<thead>
			<tr>
				<th field="id" width="20">Id</th>
				<th field="username" width="60">Username</th>
				<th field="password" width="60">Password</th>
				<th field="userType" width="50">Type</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
			onclick="newUser()">New User</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-edit" plain="true"
			onclick="editUser()">Edit User</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-remove" plain="true"
			onclick="destroyUser()">Remove User</a>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">Student Information</div>
		<form id="fm" method="post">
			<div class="fitem" style="padding: 5px; display: none;">
				<label>id:</label> <input id="id" name="user.id"
					class="easyui-validatebox">
			</div>
			<div class="fitem" style="padding: 5px">
				<label>username:</label> <input id="username" name="user.username"
					class="easyui-validatebox">
			</div>
			<div class="fitem" style="padding: 5px">
				<label>password:</label> <input id="password" name="user.password"
					class="easyui-validatebox">
			</div>
			<div style="padding: 5px">
				<label style="padding-right: 5px">user type:</label> <select
					class="easyui-combobox" id="userType" name="user.userType"
					style="width: 100px;" panelHeight="auto">
					<option value="student">Student</option>
					<option value="assistant">Assistant</option>
				</select>
			</div>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="saveUser()">Save</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
	</div>

	<script>
		function newUser() {
			$('#dlg').dialog('open').dialog('setTitle', 'New User');
			$('#fm').form('clear');
			url = 'addStudent';
		}

		function editUser() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$('#dlg').dialog('open').dialog('setTitle', 'Edit User');
				$("#id").val(row.id);
				$("#username").val(row.username);
				$("#password").val(row.password);
				$('#userType').combobox('setValue', row.userType);
				url = 'editStudent';
			}
		}

		function saveUser() {
			$('#fm').form('submit', {
				url : url,
				success : function() {
					$('#dlg').dialog('close'); // close the dialog
					$('#dg').datagrid('reload'); // reload the user data
				}
			});
		}

		function destroyUser() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('Confirm',
						'Are you sure you want to destroy this user?',
						function(r) {
							if (r) {
								$.post('deleteUser', {
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