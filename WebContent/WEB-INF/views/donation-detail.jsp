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
				<td>${donation.id}</td>
			</tr>
			<tr>
				<td>Code:</td>
				<td>${donation.code}</td>
			</tr>
			<tr>
				<td>name:</td>
				<td>${donation.name}</td>
			</tr>
			<tr>
				<td>start date:</td>
				<td>${donation.startDate}</td>
			</tr>
			<tr>
				<td>end date:</td>
				<td>${donation.endDate}</td>
			</tr>
			
			<tr>
				<td>total money:</td>
				<td>${donation.money}</td>
			</tr>
			
			<tr>
				<td>status:</td>
				<td>${donation.status}</td>
			</tr>
			<tr>
				<td>organization:</td>
				<td>${donation.organization}</td>
			</tr>
			<tr>
				<td>phone number:</td>
				<td>${donation.phoneNumber}</td>
			</tr>
			
			<tr>
				<td>description:</td>
				<td>${donation.description}</td>
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