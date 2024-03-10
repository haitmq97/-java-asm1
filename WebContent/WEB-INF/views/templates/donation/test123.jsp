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
	<c:forEach var="i" begin="1" end="5">
		<button class="btn btn-success" title="Quyên góp"
			onclick="updateValue(${i})">
			<span class="content-btn-text">${i}</span> <span
				class="content-btn-icon"><i
				class="fa-solid fa-circle-dollar-to-slot"></i></span>
		</button>
	</c:forEach>
	
	<c:set var = "temp" scope = "session" value = "${donationId}"/>
	<p>Test here: <c:out value="${temp}"/></p>
	<c:choose>
		<c:when test="${donationId!=0}">
			<c:out value="${donationId}" />
		</c:when>
		<c:otherwise>
			<c:out value="0" />
		</c:otherwise>
	</c:choose>

<p> test value here: </p>

<div id="check-btn"></div>

<p> test value here: </p>

<div id="check"></div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<script>
    

	function updateValue(i) {
	    var checkBtn = document.getElementById("check-btn");
	    var check = document.getElementById("check");
	    var mainValue = "<c:out value='helo' />";
	    var mainValue2 = "&lt;c:forEach var='k' begin='2' end='6'&gt;";
	    console.log(mainValue2);
	    check.innerHTML="<c:out value='hello world'/>";
	    // Hiển thị chuỗi HTML trong console mà không chuyển đổi
	}
</script>
</body>
</html>