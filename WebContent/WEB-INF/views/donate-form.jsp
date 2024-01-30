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
<div class="donate-fom">
	<h3>Donate</h3>
	
	
		
		<input type="hidden" name="donationId" value="${donationId}"/>
		<c:url var="process" value="/donate/donating">
					<c:param name="donationId" value="${donationId}" />
		</c:url>
		<form:form action="${process}" modelAttribute="donate" method="POST">
		<label for="name">Name:</label>
		<form:input type="text" id="name" path="name"/>
		<br>
		<label for="money">Money:</label>
		<form:input type="text" id="money" path="money"/>
		<br>
		<label for="note">Note:</label>
		<form:input type="text" id="note" path="note"/>
		<br>
		
		<input type="submit" value="Donate"/>
		</form:form>
	</div>
</body>
</html>