<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Your Entity Page</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">



</head>
<body>
	<h1>Your Entity Page</h1>

	<form action="" method="get">
		<label for="pageSize">Rows per page:</label> <select id="pageSize"
			name="size" onchange="this.form.submit()">
			<option value="5" ${paginatedData.size == 5 ? 'selected' : ''}>5</option>
			<option value="10" ${paginatedData.size == 10 ? 'selected' : ''}>10</option>
			<option value="15" ${paginatedData.size == 15 ? 'selected' : ''}>15</option>
			<option value="20" ${paginatedData.size == 20 ? 'selected' : ''}>20</option>
			<!-- Add more options as needed -->
		</select>
		<noscript>
			<input type="submit" value="Submit">
		</noscript>
	</form>

	<form action="" method="get">
		<input type="text" name="searchingValue" value="${searchingValue}"
			placeholder="Search by Phone number, Organization name or code"
			onchange="this.form.submit()"></input>

		<noscript>
			<input type="submit" value="Submit">
		</noscript>
	</form>




	<table
		class="table table-bordered table-striped border-black table-success">
		<thead>
				<tr class="table-dark">
					<th>id</th>
					<th>Mã</th>
					<th>Tên</th>
					<th>Ngày bắt đầu</th>
					<th>Ngày kết thúc</th>
					<th>Số điện thoại</th>
					<th>Tổng tiền</th>
					<th>Trạng thái</th>

					<th>Hành động</th>

				</tr>
			</thead>

		<tbody class="table-group-divider">
			<!-- loop over and print customer in list -->


			<c:forEach var="tempDonation" items="${donations}">

					<%--
					<c:url var="updateLink" value="/user/showFormForUpdate">
						<c:param name="userId" value="${tempUser.id}" />
					</c:url>

					

					<c:url var="setStatus" value="/user/lockOrUnlock">
						<c:param name="userId" value="${tempUser.id}" />
					</c:url>

					<c:url var="details" value="/user/userDetails">
						<c:param name="userId" value="${tempUser.id}" />
					</c:url>
 --%>
					<c:url var="deleteLink" value="/donation/delete">
						<c:param name="id" value="${tempDonation.id}" />
					</c:url>
					
					<c:url var ="updateLink" value="/donation/updateForm">
						<c:param name="id" value="${tempDonation.id}"/>
					</c:url>

					<tr>
						<td>${tempDonation.id}</td>
						<td>${tempDonation.code}</td>
						<td>${tempDonation.name}</td>
						<td>${tempDonation.startDate}</td>
						<td>${tempDonation.endDate}</td>
						<td>${tempDonation.phoneNumber}</td>
						<td>${tempDonation.money}</td>
						<td>${tempDonation.status}</td>




						<td>
						<a href="${updateLink}" >Update</a>
						<a
							href="${deleteLink}"
							onclick="if(!confirm('Are you sure you want to delete this donation?')) return false;">Delete</a>
							<%--
						<a href="${updateLink}">Update</a> | 
							<a href="${setStatus}"
							onclick="if(!confirm('Are you sure you want to lock this user?')) return false;"><c:out
									value="${tempUser.status == 1 ? 'lock' : 'unlock'}" /></a> <a
							href="${details}">Details</a>
							--%>
						</td>


					</tr>
				</c:forEach>
		</tbody>
	</table>


	<div>
		<ul>
			<c:forEach var="i" begin="${paginatedData.number}"
				end="${paginatedData.totalPages}">
				<li><a href="?page=${i}&size=${paginatedData.size}">${i}</a></li>
			</c:forEach>
		</ul>

		<br>
		<div>
			<p>
				<a
					href="?page=0&size=${currentSize}&searchingValue=${searchingValue}">first</a>
			</p>
			<p>
				<a
					href="?page=${prevPage}&size=${currentSize}&searchingValue=${searchingValue}">previous</a>
			</p>
			<p>
				<a
					href="?page=${nextPage}&size=${currentSize}&searchingValue=${searchingValue}">next</a>
			</p>
			<p>
				<a
					href="?page=${paginatedData.totalPages-1}&size=${currentSize}&searchingValue=${searchingValue}">last</a>
			</p>



			<form action="" method="get">
				<label for="jumpToPage">Jump to page:</label> <input type="number"
					id="jumpToPage" name="page" min="0"
					max="${paginatedData.totalPages - 1}" required> <input
					type="hidden" name="size" value="${currentSize}">
				<button type="submit">Go</button>
			</form>

		</div>

	</div>


	<script>
		function changeVariable(stringParam) {
			document.getElementById("a1").value = stringParam;
			return true;
		}
	</script>

</body>
</html>
