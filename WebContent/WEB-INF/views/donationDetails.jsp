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
	<table class="table">

		<tbody>
			<tr>
				<td>Id:</td>
				<td><p name="donationId" value="${donation.id}">${donation.id}</p></td>
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

		</tbody>
	</table>


	<p>Danh sach dot quyen gop</p>


	<div>
		<c:url var="donateLink" value="/donate/donate-form">
			<c:param name="donationId" value="${donation.id}" />
		</c:url>
		<p><a href="${donateLink}">Donate</a></p>
	</div>
	<hr>
	<br>
	<p class="mt-5">
		<a href="${pageContext.request.contextPath}/v1/home3">Back to List</a>
	</p>



</body>
</html>