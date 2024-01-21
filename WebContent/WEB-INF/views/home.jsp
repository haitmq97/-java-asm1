<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi-VN">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2> Home page here </h2>


<hr>
<br><br>

<a href="${pageContext.request.contextPath}/user/list">user list</a>
<br><br>



<hr>

<a href="${pageContext.request.contextPath}/user/listByPage">list by page</a>

<br>
<a href="${pageContext.request.contextPath}/donation/list">Donation list</a>
<br>

<a href="${pageContext.request.contextPath}/donation/datetest">Donation list</a>

</body>
</html>