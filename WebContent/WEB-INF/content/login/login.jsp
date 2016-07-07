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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
<script
	src="${pageContext.request.contextPath}/jquery/jquery-2.1.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<style type="text/css">
.myBackground {
	background:
		url("${pageContext.request.contextPath}/picture/background.jpg")
		no-repeat;
	background-position: 50px 50px;
}

.myInstitute {
	background:
		url("${pageContext.request.contextPath}/picture/institute.jpg")
		no-repeat;
}

.myBackgroundColor {
	background-color: #F5F5F5;
}
</style>

</head>
<body class="easyui-layout">
	<div data-options="region:'north',split:false" style="height: 100px"
		class="myInstitute myBackgroundColor"></div>
	<div data-options="region:'center',split:false" style="width: 800px"
		class="myBackground">
		<div style="margin-top: 30px; margin-left: 250px;">
			<h2 style="font-size: 40px; color: grey;">课程作业管理系统</h2>
			<h4
				style="font-size: 15px; color: grey; margin-left: 100px; margin-top: 30px;">——南京大学软件学院
				孙凡 121250133</h4>
		</div>
		<div style="margin-top: -10px; margin-left: 800px;">
			<form action="loginPro" method="post"
				style="border-left: #CCC 2px solid">
				<div style="margin: 20px; margin-left: 50px;">
					<input type="text" name="user.username" class="form-control"
						id="inputUsername" placeholder="用户名" style="width: 250px;">
				</div>
				<div style="margin: 20px; margin-left: 50px;">
					<input type="password" name="user.password" class="form-control"
						id="inputPassword" placeholder="密码" style="width: 250px;">
				</div>
				<div style="margin: 20px; margin-left: 50px;">
					<button type="submit" class="btn btn-default" style="width: 250px;">登录</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>