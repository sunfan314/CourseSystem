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
					style="width: 230px;" panelHeight="auto"
					data-options="
			panelWidth: 500,
			idField: 'id',
			textField: 'courseName',
			url: 'getAssistantCourses?id=${id}',
			method: 'get',
			columns: [[
				{field:'id',title:'Id',width:20},
				{field:'courseId',title:'Course Id',width:40},
				{field:'courseName',title:'Course Name',width:80},
				{field:'year',title:'Year',width:30},
				{field:'term',title:'Term',width:20},
				{field:'teachers',title:'Course Teacher',width:80},
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
			title="Correct Student Homework" style="width: 100%; height: 100%;"
			rownumbers="true" fitColumns="true"
			data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				method: 'get',
				onClickRow: onClickRow
			">
			<thead>
				<th data-options="field:'id',hidden:true">Id</th>
				<th data-options="field:'studentId',width:30">Student Id</th>
				<th data-options="field:'studentName',width:30">Student Name</th>
				<th
					data-options="field:'downloadFile',width:30,formatter:downloadStudentHomework">Student
					Homework</th>
				<th data-options="field:'score',width:20,editor:'numberbox'">Score</th>
				<th data-options="field:'remark',width:100,editor:'textbox'">Comment</th>
			</thead>
		</table>
	</div>

	<div id="tb" style="height: auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" id="saveBtn"
			data-options="iconCls:'icon-save',plain:true,disabled:true"
			onclick="saveHomeworkData()">Save</a> <span id="homeworkStatus"
			style="color: blue;"></span>
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
		var editIndex = undefined;
		function endEditing() {
			if (editIndex == undefined) {
				return true
			}
			if ($('#dg').datagrid('validateRow', editIndex)) {
				$('#dg').datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		}
		function onClickRow(index) {
			if (editIndex != index) {
				if (endEditing()) {
					$('#dg').datagrid('selectRow', index).datagrid('beginEdit',
							index);
					editIndex = index;
				} else {
					$('#dg').datagrid('selectRow', editIndex);
				}
			}
		}
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
			$('#dg').datagrid({
				url : 'getSubmittedHomework?homeworkId=' + homeworkId
			});
			$.post('checkHomeworkStatus', {
				homeworkId:homeworkId
			}, function(result) {
				if(result.deadlineOver){//检验是否过批改时间
					$('#homeworkStatus').html("Correct Deadline is Over.");
				}
				else{
					if(result.scorePublished){
						$('#homeworkStatus').html("Homework Scores have already been published.");
					}
					else{
						$('#saveBtn').linkbutton('enable');
						if(result.teacherChecked){
							$('#homeworkStatus').html("Homeworks need to be recorrected.");
						}
					}
				}
				
			}, 'json');
			
		}
		
		
		function downloadStudentHomework(val,row){
			
			return '<a href="downloadStudentHomework?submitHomeworkId='+row.id+'">download file</a>';
		}
			

		function saveHomeworkData() {
			if (endEditing()) {
				$('#dg').datagrid('acceptChanges');
			}
			var dataList = new Array();
			var rows = $('#dg').datagrid('getRows');
			for (var i = 0; i < rows.length; i++) {
				var row = rows[i];
				dataList.push(row);
			}
			$.post('updateSubmitHomeworkInfo', {
				data : JSON.stringify(dataList)
			}, function(result) {
				if(result.success){
					$('#dialogInfo')
					.text(
							"Success!You have corrected homework successfully!");
			$('#dlg').dialog('open')
					.dialog('setTitle',
							'SUCCESS');
				}
			}, 'json');
		}
	</script>
</body>
</html>