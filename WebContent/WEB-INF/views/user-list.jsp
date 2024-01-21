<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>

	<div id="wrapper">
		<div id="header"
			class="py-5 d-flex justify-content-center bg-success text-white">
			<h2>URM - User Relationship Manager</h2>
		</div>
	</div>

	<div class="container">
		<h3 class="mt-5 .red">Crud Demo Application</h3>

		<%-- add customer button --%>
		<input type="button" value="Add Userr"
			onclick="window.location.href='showFormForAdd'; return false"
			class="mt-2 mb-2 btn btn-sm btn-primary" />

		
		<%-- customer list --%>
		<table
			class="table table-bordered table-striped border-black table-success">
			<thead>
				<tr class="table-dark">
				<th>Id</th>
					<th>full name</th>
					<th>email</th>
					<th>phone number</th>
					<th>role Id</th>
					<th>role</th>
					<th>status</th>
					
					<th>Action</th>
					
				</tr>
			</thead>

			<tbody class="table-group-divider">
				<!-- loop over and print customer in list -->
				
				
				<c:forEach var="tempUser" items="${users}">
					
					<c:url var="updateLink" value="/user/showFormForUpdate">
						<c:param name="userId" value="${tempUser.id}" />
					</c:url>
					
					<c:url var="deleteLink" value="/user/deleteUser">
						<c:param name="userId" value="${tempUser.id}" />
					</c:url>
					
					<c:url var="setStatus" value="/user/lockOrUnlock">
						<c:param name="userId" value="${tempUser.id}" />
					</c:url>
					
					<c:url var="details" value="/user/userDetails">
						<c:param name="userId" value="${tempUser.id}" />
					</c:url>
					 
					<tr>
						<td>${tempUser.id}</td>
						<td>${tempUser.fullName}</td>
						<td>${tempUser.email}</td>
						<td>${tempUser.phoneNumber}</td>
						<td>${tempUser.role.id}</td>
						<td>${tempUser.role.roleName}</td>
						<td>${tempUser.status}</td>
						
						
						
						
						<td>
						
						<a href="${updateLink}">Update</a> | 
						<a href="${deleteLink}" onclick="if(!confirm('Are you sure you want to delete this user?')) return false;">Delete</a>
						<a href="${setStatus}" onclick="if(!confirm('Are you sure you want to lock this user?')) return false;"><c:out value="${tempUser.status == 1 ? 'lock' : 'unlock'}"/></a>
						<a href="${details}">Details</a>
						
						</td>
					
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
</body>
</html>