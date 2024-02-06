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
		<h2>User list</h2>
	
		<div class="page">
			<input id="currentPage" type="hidden" name="currentPage"
				value="${currentPage}" /> <label for="pageSize">Rows per
				page:</label> <select id="pageSize" name="size" onchange="refresh()">
				<option value="3" ${donations.size == 3 ? 'selected' : ''}>3</option>
				<option value="4" ${donations.size == 4 ? 'selected' : ''}>4</option>
				<option value="5" ${donations.size == 5 ? 'selected' : ''}>5</option>
				<option value="10" ${donations.size == 10 ? 'selected' : ''}>10</option>
				<option value="15" ${donations.size == 15 ? 'selected' : ''}>15</option>
				<option value="20" ${donations.size == 20 ? 'selected' : ''}>20</option>
				<!-- Add more options as needed -->
			</select>

		</div>
		<div class="searching-field" id="searching-field">
			<label for="searching-input">Search:</label> <input type="text"
				name="searching-input" id="searching-input" class="searching-input"
				placeholder="by Email or Phone number" value="${searchingValue}"
				oninput="search(this.value)" />

		</div>
		
		<div>
			<input type="button" value="Add User"
			onclick="window.location.href='addUser'; return false"
			class="mt-2 mb-2 btn btn-sm btn-primary" />
		</div>

		<div id="user-list" class="list">
			<table
				class="table table-bordered table-striped border-black table-success">

				<tbody class="table-group-divider">
					<!-- loop over and print customer in list -->


					<c:forEach var="tempUser" items="${users.content}">


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


						<tr>
							<td>${tempUser.id}</td>
							<td>${tempUser.fullName}</td>
							<td>${tempUser.email}</td>
							<td>${tempUser.phoneNumber}</td>
							<td>${tempUser.userName}</td>
							<td>${tempUser.status}</td>
							<td> 
							<a href="${detailLink}">Details</a> | 
							<a href="${deleteLink}" onclick="if(!confirm('Are you sure you want to delete this user?')) return false;">Delete</a> | 
							<a href="${updateLink}" >Update</a> | 
							<a href="${changeStatusLink}" >${tempUser.status == 1 ? 'Lock': 'Unlock' }</a> | 
							</td>
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
				url : "<c:url value='/user/list'> </c:url>",
				data : {
					size : size,
					page : page
				},
				success : function(data) {
					$("#user-list").html($(data).find("#user-list").html());
				}
			});
		}
		function search(searchingValue) {

	
			console.log(" searching value: |" + searchingValue+ "|");
		

			$.ajax({
				type : "GET",
				url : "<c:url value='/user/list'> </c:url>",
				data : {
					searchingValue : searchingValue
				},
				success : function(data) {
					$("#user-list").html($(data).find("#user-list").html());
				}
			});
		}
	</script>
</body>
</html>