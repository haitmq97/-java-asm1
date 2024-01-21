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
			placeholder="Search by Email or Phone number"
			onchange="this.form.submit()"></input>

		<noscript>
			<input type="submit" value="Submit">
		</noscript>
	</form>


	<%-- 
	<form action="" method="get">
		<input type="text" name="searchInput"
			placeholder="Search by Email or Phone number"
			oninput="return changeVariable(this.value)"></input>
 <p>abc:  </p>
	<input type="text" id="a1"  name="searchingValue" value="${searchingValue}"></input>
		<noscript>
			<input type="submit" value="Submit">
		</noscript>
		
	</form>

--%>

	<%-- customer list --%>
	<table
		class="table table-bordered table-striped border-black table-success">
		<thead>
			<tr class="table-dark">
				<th>Id</th>
				<th>full name</th>
				<th>email</th>
				<th>phone number</th>
				<th>address</th>
				<th>role</th>
				<th>status</th>
				<th>Action</th>

			</tr>
		</thead>

		<tbody class="table-group-divider">
			<!-- loop over and print customer in list -->


			<c:forEach var="tempUser" items="${paginatedData.content}">


				<c:url var="updateLink" value="/user/showFormForUpdate">
					<c:param name="userId" value="${tempUser.id}" />
				</c:url>

				<c:url var="deleteLink" value="/userr/deleteUser">
					<c:param name="userId" value="${tempUser.id}" />
				</c:url>


				<tr>
					<td>${tempUser.id}</td>
					<td>${tempUser.fullName}</td>
					<td>${tempUser.email}</td>
					<td>${tempUser.phoneNumber}</td>
					<td>${tempUser.address}</td>
					<td>${tempUser.role.roleName}</td>
					<td>${tempUser.status}</td>
					<td>
						<%-- display the update link --%> <a href="${updateLink}">Update</a>
						| <a href="${deleteLink}"
						onclick="if(!confirm('Are you sure you want to delete this user?')) return false;">Delete</a>
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
