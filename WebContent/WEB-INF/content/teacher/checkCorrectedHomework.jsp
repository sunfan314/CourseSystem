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
<body class="easyui-layout">
	<div data-options="region:'north',split:false" style="height: 50px">
		<div style="margin: 10px;">
			<div style="margin-bottom: 10px;">
				<label style="font-size: 20px;">Homework Selection:</label><label
					style="margin-left: 30px;">course:</label> <select
					id="assistantCourse" class="easyui-combogrid" name="courseInfoId"
					style="width: 200px;" panelHeight="auto"
					data-options="
			panelWidth: 500,
			idField: 'id',
			textField: 'courseName',
			url: 'getTeacherCourses?id=${id}',
			method: 'get',
			columns: [[
				{field:'id',title:'Id',width:20},
				{field:'courseId',title:'Course Id',width:20},
				{field:'courseName',title:'Course Name',width:50},
				{field:'year',title:'Year',width:30},
				{field:'term',title:'Term',width:20},
			]],
			fitColumns: true,
			onChange:function(newValue, oldValue){ courseSelected(newValue)}
			
		">
				</select> <label style="margin-left: 20px; margin-right: 3px;">homework:</label>
				<select id="courseHomework" class="easyui-combogrid"
					name="homeworkId" style="width: 200px;" panelHeight="auto">
				</select>
			</div>
		</div>
	</div>

	<div data-options="region:'center'">
		<table id="dg" class="easyui-datagrid"
			title="Publish Student Homework" style="width: 100%; height: 100%;"
			rownumbers="true" fitColumns="true"
			data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar:'#tb',
				method: 'get'
			">
			<thead>
				<th data-options="field:'id',hidden:true">Id</th>
				<th data-options="field:'studentId',width:30">Student Id</th>
				<th data-options="field:'studentName',width:30">Student Name</th>
				<th
					data-options="field:'downloadFile',width:30,formatter:downloadStudentHomework">Student
					Homework</th>
				<th data-options="field:'score',width:20">Score</th>
				<th data-options="field:'remark',width:100">Comment</th>
			</thead>
		</table>
	</div>

	<div id="tb" style="height: auto">
		<a id="publishBtn" href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-ok',plain:true,disabled:true"
			onclick="publishScore()">Publish Score</a> <a id="recorrectBtn"
			href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-undo',plain:true,disabled:true"
			onclick="recorrectScore()">Recorrect Score</a>

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
		
		function downloadStudentHomework(val,row){
			
			return '<a href="downloadStudentHomework?submitHomeworkId='+row.id+'">download file</a>';
		}

		function homeworkSelected(value) {
			homeworkId = value;
			$('#dg').datagrid({
				url : 'getSubmittedHomework?homeworkId=' + homeworkId
			});
			$('#publishBtn').linkbutton('enable');
			$('#recorrectBtn').linkbutton('enable');
		}

		function publishScore() {
			$.post(
					'publishHomeworkScore',
					{
						homeworkId : homeworkId
					},
					function(result) {
						if (result.success) {
							$('#dialogInfo').text(
									"Success!You have published homework score successfully!");
							$('#dlg').dialog('open').dialog('setTitle','SUCCESS');
						}
					}, 'json');
		}
		
		function recorrectScore() {
			var published=false;
			$.post(
					'isScorePublished',
					{
						homeworkId : homeworkId
					},
					function(result) {
						if (result.scorePublished) {
							published=true;
							$('#dialogInfo').text(
							"Action Denied! You have already published homework score!");
							$('#dlg').dialog('open').dialog('setTitle','WARNING');
						}
					}, 'json');
			if(!published){
				$.post(
						'recorrectHomeworkScore',
						{
							homeworkId : homeworkId
						},
						function(result) {
							if (result.success) {
								$('#dialogInfo').text(
										"Success!We have inform the assistants to recorrect the homework!");
								$('#dlg').dialog('open').dialog('setTitle','SUCCESS');
							}
						}, 'json');
			}
		}
	</script>
</body>
</html>