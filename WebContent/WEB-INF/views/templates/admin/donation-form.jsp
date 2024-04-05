<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Add Donation</h3>

<div>
	<form:form class=" col-8 mx-auto" action="${process}"
			modelAttribute="donation" method="POST">
			
			<form:hidden path="id" />
			<form:input id="id" name="id" type="text" path="id" />
			
			 
			<table class="col-8 mx-auto">


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
				onclick="window.location.href='listForAdmin'; return false" value="Đóng"/>
			<input type="submit" value="cap nhat"
				class="btn btn-info mt-4 col-2 d-block mx-auto" />

		</form:form>
</div>

</body>
</html>