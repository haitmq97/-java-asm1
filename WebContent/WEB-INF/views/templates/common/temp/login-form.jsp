<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Login</title>


<script
	src="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js' />"
	crossorigin="anonymous"></script>

<link rel="stylesheet"
	href="<c:url value='/static/common/assets/css/login-style.css' />">

<script src="<c:url value='/static/common/assets/js/form.js' />"></script>
</head>
<body>

	<div class="popup">
		<div id="login" class="login-form form">
			<h2>Log in</h2>
			<button class="btn close-btn" onclick="closePopup('login')">X</button>

			<form:form class=" col-8 mx-auto" modelAttribute="user"
				action="${pageContext.request.contextPath}/user/processLogin"
				method="POST">

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
					Don't have a account yet, <a
						href="${pageContext.request.contextPath}/user/registerForm">register
						now</a>
				</p>
				<input type="submit" value="Login"
					class="btn btn-info mt-4 col-2 d-block mx-auto" />
			</form:form>

		</div>

	</div>


	<div id="overlay" onclick="closeAllPopup()"></div>
</body>
</html>