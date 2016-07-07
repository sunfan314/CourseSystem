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
<style>
table {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 2px;
	border-color: #95B8E7;
	border-collapse: collapse;
	margin-bottom: 15px;
	margin-left: 60px;
	margin-top: 10px;
}

table th {
	text-align: left;
	padding: 4px;
	border-width: 2px;
	border-style: solid;
	border-color: #95B8E7;
	width: 200px;
}

table td {
	padding-left: 10px;
	border-width: 2px;
	border-style: solid;
	border-color: #95B8E7;
	background-color: #ffffff;
	width: 600px;
}

p {
	margin-top: 0px;
	margin-bottom: -4px;
	line-height: 20px;
}
</style>
</head>
<body>
	<div style="margin-left: 60px;">
		<label
			style="font-family: verdana, arial, sans-serif; font-size: 20px;">Homework
			Published of ${courseName}:</label>
	</div>

	<s:iterator value="courseHomeworkList" var="c">
		<table>
			<tr>
				<th>Homework No:</th>
				<td>${c.homeworkId}</td>
			</tr>
			<tr>
				<th>Submit Deadline:</th>
				<td>${c.submitDeadlineToString}</td>
			</tr>
			<tr>
				<th>Homework Requirement:</th>
				<td><s:if test="#c.requirement==null">
						<span style="color: blue;">Not Published Yet.</span>
					</s:if> <s:else>
						<p>${c.requirement}</p>
					</s:else></td>
			</tr>
			<tr>
				<th>Homework Example:</th>
				<td><s:if test="#c.exampleSubmitted">
						<a href="downloadFile?homeworkId=${c.id}">download file</a>
					</s:if> <s:else>
						<span style="color: blue;">Not Uploaded Yet. </span>
					</s:else></td>
			</tr>
			<tr>
				<th>Homework Summary:</th>
				<td><s:if test="#c.summary==null">
						<span style="color: blue;">Not Published Yet.</span>
					</s:if> <s:else>
						<p>${c.summary}</p>
					</s:else></td>
			</tr>
			<tr>
				<th>Homework Format:</th>
				<td>${c.format}</td>
			</tr>
			<tr>
				<th>Homework Status:</th>
				<td><s:if test="#c.homeworkSubmitted">
						<span>Homework Submitted.</span>
					</s:if> <s:else>
						<span style="color: blue;" class="homeworkStatus">Not
							Submitted Yet.</span>
					</s:else></td>
			</tr>
			<tr>
				<th>Homework Submit:</th>
				<td><s:if test="#c.deadlineOver">
						<span style="color: blue;">Deadline is over.</span>
					</s:if> <s:else>
						<div>
							<form
								action="uploadHomework?homeworkId=${c.id}&courseInfoId=${courseInfoId}&studentId=${studentId}"
								method="post" enctype="multipart/form-data">
								<input name="homework" class="easyui-filebox"
									data-options="prompt:'Please select a file.'"
									style="width: 200px; height: 20px;" /> <input type="submit"
									value="Upload" class="easyui-linkbutton homeworkUpload"
									style="width: 70px; height: 20px;" />

							</form>
						</div>
					</s:else></td>
			</tr>
			<tr>
				<th>Homework Score:</th>
				<td><s:if test="#c.scorePublished==0">
						<span style="color: blue">Not Published Yet.</span>
					</s:if> <s:else>
						<s:if test="#c.homeworkSubmitted">
							${studentScore}
						</s:if>
						<s:else>
							<span style="color: blue;"> You have not submitted
								homework. </span>
						</s:else>
					</s:else></td>
			</tr>
			<tr>
				<th>Homework Comment:</th>
				<td><s:if test="#c.scorePublished==0">
						<span style="color: blue">Not Published Yet.</span>
					</s:if> <s:else>
						<s:if test="#c.homeworkSubmitted">
							${homeworkComment}
						</s:if>
						<s:else>
							<span style="color: blue;"> You have not submitted
								homework. </span>
						</s:else>
					</s:else></td>
				</td>
			</tr>
		</table>
	</s:iterator>

	<div id="dlg" class="easyui-dialog"
		style="width: 300px; height: 140px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<label id="dialogInfo" style="font-size: 15px;"></label>
	</div>

	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="javascript:$('#dlg').dialog('close')">OK</a>
	</div>

	<script>
		$(".homeworkUpload")
				.click(
						function() {
							var homeworkStatus = $(this).parent("form")
									.parent().parent().parent().parent().find(
											"span.homeworkStatus");
							var form = $(this).parent("form");
							var url = form.attr("action");
							form
									.form({
										url : url,
										success : function(result) {
											result = JSON.parse(result);
											if (result.success) {
												homeworkStatus
														.html("Homework Submitted.");
												$('#dialogInfo')
														.text(
																"Success!You have uploaded homework successfully!");
												$('#dlg').dialog('open')
														.dialog('setTitle',
																'SUCCESS');
											} else {
												$('#dialogInfo')
														.text(
																"Uploading file fail! Check if you have choose the file or your file oversize. ");
												$('#dlg').dialog('open')
														.dialog('setTitle',
																'FAIL');
											}

										}
									});
						});
	</script>
</body>
</html>