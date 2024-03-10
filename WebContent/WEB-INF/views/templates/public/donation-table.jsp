<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>

</head>
<body>
	<section class="site-section content">
		<div class="container">
			<div class="row mb-5 justify-content-center">
				<div class="col-md-7 text-center">
					<h2 class="section-title mb-2">Các đợt quyên góp</h2>
				</div>
			</div>


			<div class="container">
				<div class="content">
					<div class="h-content">
						<div class="sp-tool d-flex flex-row justify-content-between mt-3">
							<div class="page-selector">

								<input id="currentPage" type="hidden" name="currentPage"
									value="${currentPage}" /> <label for="pageSize">Rows
									per page:</label> <select id="pageSize" name="size"
									onchange="entriesChange()"
									class="entries-select rounded form-control">
									<option value="3" ${donations.size == 3 ? 'selected' : ''}>3</option>
									<option value="4" ${donations.size == 4 ? 'selected' : ''}>4</option>
									<option value="5" ${donations.size == 5 ? 'selected' : ''}>5</option>
									<option value="10" ${donations.size == 10 ? 'selected' : ''}>10</option>
									<option value="15" ${donations.size == 15 ? 'selected' : ''}>15</option>
									<option value="20" ${donations.size == 20 ? 'selected' : ''}>20</option>
									<!-- Add more options as needed -->
								</select>


							</div>
							<div class="search-box">
								<label for="searching-input">Search:</label> <input type="text"
									name="searching-input" id="searching-input"
									class="searching-input rounded p-2 form-control"
									placeholder="by Code or by status ..."
									value="${searchingValue}" oninput="search(this.value)" />
							</div>
						</div>
					</div>
					<div class="m-content list" id="donation-list">
						<table class="table table-striped table-content">
							<thead class="tb-head-title">
								<tr>
									<th scope="col" class="th-custom"><p>id</p></th>
									<th scope="col" class="th-custom"><p>Name</p></th>
									<th scope="col" class="th-custom"><p>Code</p></th>
									<th scope="col" class="th-custom"><p>Start date</p></th>
									<th scope="col" class="th-custom"><p>End date</p></th>
									<th scope="col" class="th-custom"><p>Phone number</p></th>
									<th scope="col" class="th-custom"><p>Action</p></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="tempDonation" items="${donations.content}">

									<c:url var="donateLink" value="/v1/donateForm">
										<c:param name="id" value="${tempDonation.id}" />
									</c:url>

									<c:url var="detailLink" value="/donation/donation-details">
										<c:param name="id" value="${tempDonation.id}" />
									</c:url>



									<tr>
										<td><p>${tempDonation.id}</p></td>
										<th scope="row">
											<p class="d-name">${tempDonation.name}</p>
											<p class="d-status">${tempDonation.status}</p>
										</th>
										<td><p>${tempDonation.code}</p></td>
										<td><p>${tempDonation.startDate}</p></td>
										<td><p>${tempDonation.endDate}</p></td>
										<td><p>${tempDonation.phoneNumber}</p></td>
										<td class="action-c">
											<button class="btn btn-success donation-btn" title="Chi tiết"
												onclick="redirectToDetailLink('${detailLink}')">
												<span class="content-btn-text">Chi tiết</span><span
													class="content-btn-icon"><i class="fa-solid fa-info"></i></span>
											</button>
											<button class="btn btn-success donation-btn" title="Quyên góp"
												onclick="donateForm('${donateLink}')">
												<span class="content-btn-text">Quyên góp</span><span
													class="content-btn-icon"><i
													class="fa-solid fa-circle-dollar-to-slot"></i></span>
											</button> 
											
											
										</td>
									</tr>
								</c:forEach>


							</tbody>
						</table>
						<div>
							<div>
								<input id="currentPage" type="hidden" value="${currentPage}" />
								<input id="totalPage" type="hidden" value="${totalPage}" /> <input
									id="size" type="hidden" value="${size}" />
							</div>
							<div id="pagination-container"></div>

							<script
								src="<c:url value='/static/common/assets/js/pagination.js' />"></script>


						</div>

					</div>
				</div>

			</div>
		</div>
	</section>
	
	
	<div id="#donate-popup">
		<c:import url="/v1/donateForm?id=1" />
	
	</div>
	
	<script src="<c:url value='/static/common/assets/js/form.js' />"></script>

	<script>
		function donateForm(donateLink) {
			console.log(donateLink);
			
			$.ajax({
				type : "GET",
				url : donateLink,
				
				success : function(data) {
					$("#donate").html(
							$(data).find("#donate").html());
				}
			});
			openPopup('donate');
		}
		
		function redirectToDetailLink(link) {
			window.location.href = link;
		}
	</script>





	<script>
		function entriesChange() {

			var size = $("#pageSize").val();
			console.log(" page size: |" + size + "|");
			var page = 1;
			console.log(" current page: |" + page + "|");

			var searchingValue = document.getElementById("searching-input").value;
			console.log(" searching value: |" + searchingValue + "|");

			$.ajax({
				type : "GET",
				url : "<c:url value='/donation/list'> </c:url>",
				data : {
					size : size,
					page : page,
					searchingValue : searchingValue
				},
				success : function(data) {
					$("#donation-list").html(
							$(data).find("#donation-list").html());
				}
			});
		}
		function search(searchingValue) {

			console.log(" searching value: |" + searchingValue + "|");
			var size = $("#pageSize").val();
			$.ajax({
				type : "GET",
				url : "<c:url value='/donation/list'> </c:url>",
				data : {
					searchingValue : searchingValue,
					size : size
				},
				success : function(data) {
					$("#donation-list").html(
							$(data).find("#donation-list").html());
				}
			});
		}
	</script>

	<script src="<c:url value='/static/common/assets/js/form.js' />"></script>
	
</body>
</html>