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
	<c:forEach var = "i" begin = "1" end = "5">

		<button class="btn btn-success" title="Quyên góp"
			onclick="updateImportUrl(${i})">
			<span class="content-btn-text">${i}</span>
			<span class="content-btn-icon"><i
				class="fa-solid fa-circle-dollar-to-slot"></i></span>
		</button>
	</c:forEach>


<c:choose>
		<c:when test="${not empty donationId}">
			<c:out value="/v1/donateForm?id=${donationId}" />
		</c:when>
		<c:otherwise>
			<!-- Default import URL -->
			<c:out value="/v1/donateForm?id=1" />
		</c:otherwise>
	</c:choose>


	<script>
  var donationId; // Global variable to store the current donation ID

  function updateImportUrl(id) {
    donationId = id;
  }
</script>
</body>
</html>