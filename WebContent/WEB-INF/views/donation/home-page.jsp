<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<div>


			<form:form action="" modelAttribute="currentUser" method="post">
				<form:input name="currentUserId" path="id" />
				<form:input name="currentUserRole" path="role" />
				<form:input name="currentUserRole" path="status" />
			</form:form>
		</div>

		<div class="nav">
			<h3>This is for navbar</h3>
			<button onclick="">Login</button>
			<button>Register</button>
			<button>Manager</button>
			<button>Logout</button>
		</div>
	</div>
	<div class="main">
		<h2>Donation list</h2>

		<table
			class="table table-bordered table-striped border-black table-success">

			<tbody class="table-group-divider">
				<!-- loop over and print customer in list -->


				<c:forEach var="tempDonation" items="${paginatedData.content}">

					<%-- 
				<c:url var="updateLink" value="/user/showFormForUpdate">
					<c:param name="userId" value="${tempUser.id}" />
				</c:url>

				<c:url var="deleteLink" value="/user/deleteUser">
					<c:param name="userId" value="${tempUser.id}" />
				</c:url>
--%>
				<c:url var="donateLink" value="/donation/donate">
					<c:param name="id" value="${tempDonation.id}" />
				</c:url>
				
				<c:url var="detailLink" value="/donation/donation-details">
					<c:param name="id" value="${tempDonation.id}" />
				</c:url>
				
				
					<tr>
						<td hidden>${tempDonation.id}</td>
						<td>${tempDonation.name}</td>
						<td>${tempDonation.startDate}</td>
						<td>${tempDonation.startDate}</td>
						<td>${tempDonation.phoneNumber}</td>
						<td>${tempDonation.status}</td>
						<td><a href="${donateLink}">Donate</a> <a href="${detailLink}">Details</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>

			<br>
			<div>
				<p>
					<a href="?page=0&size=${currentSize}">first</a>
				</p>
				<p>
					<a href="?page=${prevPage}&size=${currentSize}">previous</a>
				</p>
				<p>
					<a href="?page=${nextPage}&size=${currentSize}">next</a>
				</p>
				<p>
					<a href="?page=${paginatedData.totalPages-1}&size=${currentSize}">last</a>
				</p>



			</div>

		</div>

		<div class="footer">
			<p>This is a footer</p>
		</div>

		<div class="form-in">
			<div class="login-form">
				<form:form class=" col-8 mx-auto" modelAttribute="user"
					action="login" method="POST">

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
							href="${pageContext.request.contextPath}/user/showFormForAdd">register
							now</a>
					</p>
					<input type="submit" value="Login"
						class="btn btn-info mt-4 col-2 d-block mx-auto" />
				</form:form>
			</div>
			<div class="register-form"></div>
		</div>
</body>
</html>