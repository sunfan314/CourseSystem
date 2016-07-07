<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>作业进度查看</title>
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

	<table id="dg" class="easyui-datagrid" title="My Homework"
		style="width: 100%; height: auto" toolbar="#toolbar"
		data-options="singleSelect:true,collapsible:true,url:'getHomeworkStatus?studentId=${id}',method:'get'">
		<thead>
			<tr>
				<th data-options="field:'courseName',width:150">Course</th>
				<th data-options="field:'homeworkId',width:150">Homework No</th>
				<th data-options="field:'submitDeadlineToString',width:300">Submit
					Deadline</th>
				<th data-options="field:'homeworkStatus',width:200">Status</th>
				<th data-options="field:'scoreToString',width:300">Score</th>
			</tr>
		</thead>
	</table>

	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-search"
			plain="true" onclick="searchUnsubmittedHomework()">Unsubmitted
			Homework</a>
	</div>


</body>
<script>
	var id=${id};
	function searchUnsubmittedHomework(){
		alert("sss");
		$('#dg').datagrid('reload',"getUnsubmittedHomework?studentId="+id);
	}
</script>
</html>