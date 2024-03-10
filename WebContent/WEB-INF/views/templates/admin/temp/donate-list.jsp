<%@ page import="javax.servlet.http.HttpServletRequest"%>
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
	<div class="donation" id="donations">
		<h2>Donate list</h2>
	
		<div class="page">
			<input id="currentPage" type="hidden" name="currentPage"
				value="${currentPage}" /> <label for="pageSize">Rows per
				page:</label> <select id="pageSize" name="size" onchange="refresh()">
				<option value="3" ${datas.size == 3 ? 'selected' : ''}>3</option>
				<option value="4" ${datas.size == 4 ? 'selected' : ''}>4</option>
				<option value="5" ${datas.size == 5 ? 'selected' : ''}>5</option>
				<option value="10" ${datas.size == 10 ? 'selected' : ''}>10</option>
				<option value="15" ${datas.size == 15 ? 'selected' : ''}>15</option>
				<option value="20" ${datas.size == 20 ? 'selected' : ''}>20</option>

			</select>

		</div>


		<div id="donate-list" class="list">
			<table
				class="table table-bordered table-striped border-black table-success">

				<tbody class="table-group-divider">
					<!-- loop over and print customer in list -->


					<c:forEach var="tempData" items="${datas.content}">

	<%-- 
						<c:url var="detailLink" value="/user/details">
							<c:param name="userId" value="${tempUser.id}" />
						</c:url>
						
						<c:url var="deleteLink" value="/user/delete">
							<c:param name="userId" value="${tempUser.id}" />
						</c:url>
						
						<c:url var="updateLink" value="/user/updateUser">
							<c:param name="userId" value="${tempUser.id}" />
						</c:url>
						
						<c:url var="changeStatusLink" value="/user/changeStatus">
							<c:param name="userId" value="${tempUser.id}" />
						</c:url>

--%>

						<c:url var="comfirmLink" value="/donate/statusComfirm">
							<c:param name="id" value="${tempData.id}"></c:param>
						
						</c:url>
						<c:url var="cancelLink" value="/donate/statusComfirm">
							<c:param name="id" value="tempData.id"></c:param>
						
						</c:url>
						
						
						<tr>
							<td>${tempData.id}</td>
							<td>${tempData.name}</td>
							<td>${tempData.money}</td>
							<td>${tempData.createdDate}</td>
							<td>${tempData.status}</td>
							<td>${tempData.user.id}</td>
							<td>${tempData.donation.id}</td>
							<td>${tempData.note}</td>
							<td> <a href="${comfirmLink}">comfirm</a> | <a href="${cancelLink}">cancel</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>



		<div class="pagebtn">

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
					<a href="?page=${donations.totalPages-1}&size=${currentSize}">last</a>
				</p>
			</div>

		</div>


	</div>

	<script src="https://code.jquery.com/jquery-3.6.4.min.js"
		crossorigin="anonymous"></script>
	<script>
		function refresh() {

			var size = $("#pageSize").val();
			console.log(" page size: |" + size + "|");
			var page = 0;
			console.log(" current page: |" + page + "|");

			$.ajax({
				type : "GET",
				url : "<c:url value='/donate/list'> </c:url>",
				data : {
					size : size,
					page : page
				},
				success : function(data) {
					$("#donate-list").html($(data).find("#donate-list").html());
				}
			});
		}
		
	</script>
</body>
</html>