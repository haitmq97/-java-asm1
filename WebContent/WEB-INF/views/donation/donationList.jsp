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
	<div class="donation">
		<h2>Donation list</h2>

		<div class="page">
			<form id="page-selection" action="" method="get">
				<label for="pageSize">Rows per page:</label>
				<%-- 
				 <select id="pageSize"
					name="size" onchange="this.form.submit()">
					--%>
				<select id="pageSize"
					name="size" onchange="refreshPage(this.value)">
					<option value="3" ${donations.size == 3 ? 'selected' : ''}>3</option>
					<option value="4" ${donations.size == 4 ? 'selected' : ''}>4</option>
					<option value="5" ${donations.size == 5 ? 'selected' : ''}>5</option>
					<option value="10" ${donations.size == 10 ? 'selected' : ''}>10</option>
					<option value="15" ${donations.size == 15 ? 'selected' : ''}>15</option>
					<option value="20" ${donations.size == 20 ? 'selected' : ''}>20</option>
					<!-- Add more options as needed -->
				</select>
				<noscript>
					<input type="submit" value="Submit">
				</noscript>
			</form>
		</div>

		<div id="donation-list" class="list">
			<table
				class="table table-bordered table-striped border-black table-success">

				<tbody class="table-group-divider">
					<!-- loop over and print customer in list -->


					<c:forEach var="tempDonation" items="${donations.content}">

						<c:url var="donateLink" value="/donation/donate">
							<c:param name="id" value="${tempDonation.id}" />
						</c:url>

						<c:url var="detailLink" value="/donation/donation-details">
							<c:param name="id" value="${tempDonation.id}" />
						</c:url>


						<tr>
							<td>${tempDonation.id}</td>
							<td>${tempDonation.name}</td>
							<td>${tempDonation.startDate}</td>
							<td>${tempDonation.startDate}</td>
							<td>${tempDonation.phoneNumber}</td>
							<td>${tempDonation.status}</td>
							<td><a href="${donateLink}">Donate</a> <a
								href="${detailLink}">Details</a></td>
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

	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<script type="text/javascript">
		// Hàm để gửi yêu cầu Ajax khi xóa một khách hàng
		function refreshPage(size) {
			$.ajax({
				type : "GET",
				url : "<c:url value='/donation/list?page=0&size=${size}'/>", // Điều chỉnh đường dẫn xóa khách hàng theo dự án của bạn
				data : {
					size : size
				},
				success : function(result) {
					// Cập nhật danh sách khách hàng
					$("#donation-list").html(result);
				}
			});
		}
	</script>
</body>
</html>