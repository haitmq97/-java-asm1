<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Save customer</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
</head>
<body>

	<div id="wrapper">
		<div id="header"
			class="py-5 d-flex justify-content-center bg-success text-white">
			<h2>Donation Relationship Manager</h2>
		</div>
	</div>

	<div id="container"
		class="container d-flex flex-column justify-content-center">
		<h3 class="my-4">Add Donation</h3>

		<%-- add customer form --%>
		<form:form class=" col-8 mx-auto" action="save-donation"
			modelAttribute="donation" method="POST">
			
			<form:hidden path="id" />
			
			 
			<table class="col-8 mx-auto">

<%-- 
				<tbody>
					<tr>
						<td><label for="code">Mã đợt quyên góp:</label></td>
						<td><form:input id="code" name="code" type="text" path="code" /></td>
					</tr>
					<tr>
						<td><label for="name">Tên quyên góp:</label></td>
						<td><form:input id="name" name="name" type="text" path="name" /></td>
					</tr>

					<tr>
						<td><label for="startDate">Ngày bắt đầu</label></td>
						<td><form:input id="startDate" name="startDate" type="date"
								path="startDate" /></td>
					</tr>

					<tr>
						<td><label for="endDate">Ngày kết thúc</label></td>
						<td><form:input id="endDate" name="endDate" type="date"
								path="endDate" /></td>
					</tr>

					<tr>
						<td><label for="organization">Tổ chức:</label></td>
						<td><form:input id="organization" name="organization"
								type="text" path="organization" /></td>
					</tr>
					<tr>
						<td><label for="phoneNumber">Số điện thoại:</label></td>
						<td><form:input id="phoneNumber" name="phoneNumber"
								type="phoneNumber" path="phoneNumber" /></td>
					</tr>
					<tr>
						<td><label for="description">Nội dung:</label></td>
						<td><form:input id="description" name="description"
								type="text" path="description" /></td>
					</tr>

				</tbody>
--%>

			<tbody>
					<tr>
						<td><label for="code">Mã đợt quyên góp:</label></td>
						<td><form:input id="code" name="code" type="text" path="code" /></td>
					</tr>
					<tr>
						<td><label for="name">Tên quyên góp:</label></td>
						<td><form:input id="name" name="name" type="text" path="name" /></td>
					</tr>

					<tr>
						<td><label for="startDate">Ngày bắt đầu</label></td>
						<td><form:input id="startDate" name="startDate" type="date"
								path="startDate" /></td>
					</tr>

					<tr>
						<td><label for="endDate">Ngày kết thúc</label></td>
						<td><form:input id="endDate" name="endDate" type="date"
								path="endDate" /></td>
					</tr>

					<tr>
						<td><label for="organization">Tổ chức:</label></td>
						<td><form:input id="organization" name="organization"
								type="text" path="organization" /></td>
					</tr>
					<tr>
						<td><label for="phoneNumber">Số điện thoại:</label></td>
						<td><form:input id="phoneNumber" name="phoneNumber"
								type="phoneNumber" path="phoneNumber" /></td>
					</tr>
					<tr>
						<td><label for="description">Nội dung:</label></td>
						<td><form:input id="description" name="description"
								type="text" path="description" /></td>
					</tr>

				</tbody>
			
			</table>
			<input type="button" class="btn btn-info mt-4 col-2 d-block mx-auto"
				onclick="window.location.href='list'; return false" value="Đóng"/>
			<input type="submit" value="Thêm"
				class="btn btn-info mt-4 col-2 d-block mx-auto" />

		</form:form>
		<p class="mt-5">
			<a href="${pageContext.request.contextPath}/donation/list">Back
				to List</a>
		</p>
		
		<%--
		<form:form action="" method="POST">
			<form:input type="date" value="05/07/2023"/>
		</form:form>
		
		 --%>
		 
		 <form action="" method="post">
		 	<input>
		 </form>
	</div>
</body>
</html>