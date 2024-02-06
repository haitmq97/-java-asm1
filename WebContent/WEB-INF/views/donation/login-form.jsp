<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="login-form">

				
				<form:form class=" col-8 mx-auto" modelAttribute="user"
					action="${pageContext.request.contextPath}/user/processLogin" method="POST">

					<label class="fw-bold" for="userName">Username: </label>
					<br>
					<form:input type="text" name="userName" id="userName"
						path="userName" />
					<br>
					<label class="fw-bold" for="password">Password: </label>
					<br>
					<form:input type="password" name="password" id="password"
						path="password" />
					<br>
					<p>
						don't have a account yet, <a
							href="${pageContext.request.contextPath}/user/registerForm">register
							now</a>
					</p>
					<input type="submit" value="Login"
						class="btn btn-info mt-4 col-2 d-block mx-auto" />
				</form:form>
			</div>
</body>
</html>