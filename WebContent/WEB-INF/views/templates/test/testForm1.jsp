<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<h1>Donation Form test</h1>
		<form:form action="${process}" modelAttribute="donationTest">
	name: <form:input path="name" />
		<br>
		<br>
	code: <form:input path="code" />
		<form:errors path="code" />

		<br>
		<br>
	
		<br>
		<br>
		<input type="submit" value="Submit" />


	</form:form>
	</div>

</body>
</html>