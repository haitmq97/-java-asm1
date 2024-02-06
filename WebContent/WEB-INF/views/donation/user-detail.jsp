<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
</head>
<body>
	<table class="table">

		<tbody>
			<tr>
				<td>Id:</td>
				<td>${user.id}</td>
			</tr>
			<tr>
				<td>Full Name:</td>
				<td>${user.fullName}</td>
			</tr>
			<tr>
				<td>User name:</td>
				<td>${user.userName}</td>
			</tr>
			<tr>
				<td>email:</td>
				<td>${user.email}</td>
			</tr>
			<tr>
				<td>phone Number:</td>
				<td>${user.phoneNumber}</td>
			</tr>
			<tr>
				<td>address:</td>
				<td><c:out
						value="${not empty user.address ? user.address : 'N/A'}" /></td>
			</tr>
			<tr>
				<td>status:</td>
				<td><c:out
						value="${user.status == 1 ? 'active' : 'locked'}" /></td>
			</tr>
			<tr>
				<td>cread Date:</td>
				<td>${user.created}</td>
			</tr>
			<tr>
				<td>role:</td>
				<td>${user.role.roleName}</td>
			</tr>
		</tbody>
	</table>
	
	<hr>
	<br>
	<p class="mt-5">
			<a href="${pageContext.request.contextPath}/user/list">Back to
				List</a>
		</p>

</body>
</html>