<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生信息界面</title>
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
<body class="easyui-layout">
	<div data-options="region:'north',split:false" style="height: 85px">
		<div style="margin: 10px;">
			<div style="margin-bottom: 10px;">
				<label style="font-size: 20px;">Homework Selection</label>
			</div>
			<div style="width: 900px;">
				<label>course:</label> <select id="teacherCourse"
					class="easyui-combogrid" name="courseInfoId" style="width: 200px;"
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
			onChange:function(newValue, oldValue){ courseSelected(newValue)}
			
		">
				</select><label style="margin-left: 20px; margin-right: 3px;">homework:</label><select
					id="courseHomework" class="easyui-combogrid" name="homeworkId"
					style="width: 200px;" panelHeight="auto">
				</select>

			</div>

		</div>

	</div>
	<div data-options="region:'west',title:'Homework Example',split:false"
		style="width: 300px">
		<div style="margin-left: 20px; margin-top: 60px; margin-right: 20px;">
			<div style="margin-bottom: 20px;">
				<label style="font-size: 15px;">Upload Homework Example</label>
			</div>
			<form id="uploadFileForm" method="post" enctype="multipart/form-data">
				<div>
					<input name="example" class="easyui-filebox"
						data-options="prompt:'Please select a file.'" style="width: 100%;" />
				</div>
				<div style="margin-top: 30px;">
					<input id="uploadFileBtn" type="submit" value="Upload"
						class="easyui-linkbutton" style="width: 100%"
						data-options="disabled:true" />
				</div>
			</form>

		</div>
	</div>
	<div data-options="region:'center',split:false">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'north',title:'Homework Requirement'"
				style="height: 185px">
				<div
					style="margin-left: 40px; margin-top: 15px; margin-bottom: 10px;">
					<label style="font-size: 15px;">Please Input or Modify the
						Homework Requirement here:</label><a id="saveRequirementBtn" href="#"
						style="width: 65px; height: 20px; margin-left: 77px;"
						class="easyui-linkbutton" onclick="saveRequirement()"
						data-options="iconCls:'icon-save',disabled:true">Save</a>
				</div>
				<div style="margin-left: 40px;">
					<input id="homeworkRequirement" class="easyui-textbox"
						data-options="multiline:true" style="width: 550px; height: 100px">
				</div>

			</div>
			<div data-options="region:'center',title:'Homework Summary'">
				<div
					style="margin-left: 40px; margin-top: 15px; margin-bottom: 10px;">
					<label style="font-size: 15px;">Please Input or Modify the
						Homework Summary here:</label><a id="saveSummaryBtn" href="#"
						style="width: 65px; height: 20px; margin-left: 102px;"
						class="easyui-linkbutton" onclick="saveSummary()"
						data-options="iconCls:'icon-save',disabled:true">Save</a>
				</div>
				<div style="margin-left: 40px;">
					<input id="homeworkSummary" class="easyui-textbox"
						data-options="multiline:true" style="width: 550px; height: 100px">
				</div>
			</div>
		</div>
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

	<script type="text/javascript">
		var courseInfoId;
		var homeworkId;
		
		function courseSelected(value) {
			courseInfoId = value;
			$('#courseHomework').combogrid({
				panelWidth : 500,
				idField : 'id',
				textField : 'homeworkId',
				url : 'getCourseHomework?courseInfoId=' + courseInfoId,
				columns : [ [ {
					field : 'id',
					hidden : true
				}, {
					field : 'homeworkId',
					title : 'Id',
					width : 40
				}, {
					field : 'submitDeadlineToString',
					title : 'Submit Deadline',
					width : 120
				}, {
					field : 'correctDeadlineToString',
					title : 'Correct Deadline',
					width : 120
				}, {
					field : 'format',
					title : 'Format',
					width : 80
				}, {
					field : 'total',
					title : 'Total',
					width : 40
				}, {
					field : 'difficulty',
					title : 'Difficulty',
					width : 80
				} ] ],
				fitColumns : true,
				onChange : function(newValue, oldValue) {
					homeworkSelected(newValue)
				}
			});
		}

		function homeworkSelected(value) {
			homeworkId = value;
			$('#saveRequirementBtn').linkbutton('enable');
			$('#saveSummaryBtn').linkbutton('enable');
			$('#uploadFileBtn').linkbutton('enable');
			$.post('getHomeworkInfo', {
				homeworkId : homeworkId,
				courseInfoId : courseInfoId
			}, function(result) {
				$('#homeworkRequirement').textbox('setValue',
						result.requirement);
				$('#homeworkSummary').textbox('setValue', result.summary);
				$('#uploadFileForm').form('clear');
			}, 'json');
			$('#uploadFileForm')
					.form(
							{
								url : 'uploadFile?courseInfoId=' + courseInfoId
										+ '&homeworkId=' + homeworkId + '',
								success : function(result) {
									result = JSON.parse(result);
									if (result.success) {
										$('#dialogInfo')
												.text(
														"Success!You have uploaded homework example successfully!");
										$('#dlg').dialog('open').dialog(
												'setTitle', 'SUCCESS');
									} else {
										$('#dialogInfo')
												.text(
														"Uploading file fail! Check if you have choose the file or your file oversize. ");
										$('#dlg').dialog('open').dialog(
												'setTitle', 'FAIL');
									}

								}
							});
		}

		function saveRequirement() {
			var data = $('#homeworkRequirement').val();
			$
					.post(
							'addHomeworkRequirement',
							{
								homeworkId : homeworkId,
								requirement : data
							},
							function(result) {
								if (result.success) {
									$('#dialogInfo')
											.text(
													"Success!You have modified requirement successfully!");
									$('#dlg').dialog('open').dialog('setTitle',
											'SUCCESS');
								}
							}, 'json');
		}

		function saveSummary() {
			var data = $('#homeworkSummary').val();
			$.post('addHomeworkSummary', {
				homeworkId : homeworkId,
				summary : data
			}, function(result) {
				if (result.success) {
					$('#dialogInfo').text(
							"Success!You have modified summary successfully!");
					$('#dlg').dialog('open').dialog('setTitle', 'SUCCESS');
				}
			}, 'json');
		}

		function uploadFile() {

		}
	</script>
</body>
</html>