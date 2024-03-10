<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">


</head>
<body>
	<div class="header">
		<h2>This is a header</h2>
		<c:url var="DetailLink"  value="/user/details">
			<c:param name="userId" value="${userId}"></c:param>
		</c:url>
		
		<div class="nav">
			<h3>This is for navbar</h3>
			<button onclick="">Login</button>
			<button>Register</button>
			<button onclick="window.location.href='${pageContext.request.contextPath}/admin/manager'">Manager</button>
			<button onclick="window.location.href='${pageContext.request.contextPath}/user/processLogout'">Logout</button>
			<button onclick="window.location.href='${DetailLink}'">User</button>
		</div>
	</div>
	<div class="main">
	
 
	<c:import url="/donation/list" />
	
	</div>
	
		

		<div class="footer">
			<p>This is a footer</p>
		</div>
		<div class="login">
			<c:import url="/user/login" />
		</div>

		<div class="form-in">
			
			<div class="register-form"></div>
		</div>
		
		
		
		
		
</body>
</html>