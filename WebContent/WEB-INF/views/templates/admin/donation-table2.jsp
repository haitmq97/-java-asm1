<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Donation website &mdash; Website Donation</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="author" content="Free-Template.co" />



<link rel="stylesheet"
	href="<c:url value='/static/common/assets/css/content-style.css' />">

<link rel="stylesheet"
	href="<c:url value='/static/user/assets/css/custom-bs.css' />">
<link rel="stylesheet"
	href="<c:url value='/static/user/assets/css/jquery.fancybox.min.css' />">
<link rel="stylesheet"
	href="<c:url value='/static/user/assets/css/bootstrap-select.min.css' />">
<link rel="stylesheet"
	href="<c:url value='/static/user/assets/fonts/icomoon/style.css' />">
<link rel="stylesheet"
	href="<c:url value='/static/user/assets/fonts/line-icons/style.css' />">
<link rel="stylesheet"
	href="<c:url value='/static/user/assets/css/owl.carousel.min.css' />">
<link rel="stylesheet"
	href="<c:url value='/static/user/assets/css/animate.min.css' />">

<!-- MAIN CSS -->
<link rel="stylesheet"
	href="<c:url value='/static/user/assets/css/style.css' />">

<script src="<c:url value='/static/user/assets/js/jquery.min.js' />"></script>
<script
	src="<c:url value='/static/user/assets/js/bootstrap.bundle.min.js' />"></script>
<script
	src="<c:url value='/static/user/assets/js/isotope.pkgd.min.js' />"></script>
<script src="<c:url value='/static/user/assets/js/stickyfill.min.js' />"></script>
<script
	src="<c:url value='/static/user/assets/js/jquery.fancybox.min.js' />"></script>
<script
	src="<c:url value='/static/user/assets/js/jquery.easing.1.3.js' />"></script>

<script
	src="<c:url value='/static/user/assets/js/jquery.waypoints.min.js' />"></script>
<script
	src="<c:url value='/static/user/assets/js/jquery.animateNumber.min.js' />"></script>
<script
	src="<c:url value='/static/user/assets/js/owl.carousel.min.js' />"></script>
<script
	src="<c:url value='/static/user/assets/js/bootstrap-select.min.js' />"></script>
<script src="<c:url value='/static/user/assets/js/custom.js' />"></script>

<script
	src="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js' />"
	crossorigin="anonymous"></script>
<script
	src="<c:url value='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' />"
	crossorigin="anonymous"></script>
<script
	src="<c:url value='https://unpkg.com/sweetalert/dist/sweetalert.min.js' />"></script>

<link rel="stylesheet"
	href="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css'/>"
	integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />


<!-- customer style -->

<link rel="stylesheet"
	href="<c:url value='/static/common/assets/css/style.css'/>" />

<link rel="stylesheet"
	href="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css'/>"
	integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />



<!-- customer js -->

<script
	src="<c:url value='https://code.jquery.com/jquery-3.6.4.min.js'/>"> </script>
<script src="<c:url value='/static/common/assets/js/form.js' />"></script>
<script src="<c:url value='/static/common/assets/js/script.js' />"></script>
<script src="<c:url value='/static/common/assets/js/data-list.js' />"></script>




</head>
<body>
	<div>


		<button class="data-table-btn"
			onclick="window.location.href='${pageContext.request.contextPath}/admin/donations2'">Donations</button>
		<button class="data-table-btn"
			onclick="window.location.href='${pageContext.request.contextPath}/admin/users'">Users</button>
		<button class="data-table-btn"
			onclick="window.location.href='${pageContext.request.contextPath}/admin/donates'">Donates</button>

	</div>

	<section class="site-section content">
		<div class="container" id="content-table">
			<div class="row mb-5 justify-content-center">
				<div class="col-md-7 text-center">
					<h2 class="section-title mb-2">Các đợt quyên góp</h2>
				</div>
			</div>


			<div class="container">
				<div class="content">
					<div class="h-content">
						<div>
							<a href="addDonation">them moi</a>
						</div>
						<div class="sp-tool d-flex flex-row justify-content-between mt-3">
							<div class="page-selector">

								<input id="currentPage" type="hidden" name="currentPage"
									value="${currentPage}" /> <label for="size">Rows per
									page:</label> <select id="pageSize" name="size"
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
									name="searching-input" id="searchingValue"
									class="searching-input rounded p-2 form-control"
									placeholder="by Code or by status ..."
									value="${searchingValue}" />
							</div>

						</div>
					</div>
					<div class="m-content list" id="donation-list">
						<table class="table table-striped table-content">
							<thead class="tb-head-title">
								<tr>
									<th scope="col" class="th-custom"><p>id</p></th>
									<th scope="col" class="th-custom"><p>Code</p></th>
									<th scope="col" class="th-custom"><p>Name</p></th>

									<th scope="col" class="th-custom"><p>Start date</p></th>
									<th scope="col" class="th-custom"><p>End date</p></th>
									<th scope="col" class="th-custom"><p>Organization</p></th>
									<th scope="col" class="th-custom"><p>Phone number</p></th>
									<th scope="col" class="th-custom"><p>Total money</p></th>
									<th scope="col" class="th-custom"><p>status</p></th>
									<th scope="col" class="th-custom"><p>Action</p></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="tempDonation" items="${donations.content}">

									<c:url var="donateLink" value="/v1/donateForm">
										<c:param name="id" value="${tempDonation.id}" />
									</c:url>

									<c:url var="detailLink" value="/v1/donation-detail">
										<c:param name="id" value="${tempDonation.id}" />
									</c:url>

									<c:url var="updateLink" value="/admin/updateDonation">
										<c:param name="id" value="${tempDonation.id}" />
									</c:url>



									<tr>
										<td><p>${tempDonation.id}</p></td>
										<td><p>${tempDonation.code}</p></td>
										<td><p>${tempDonation.name}</p></td>
										<td><p>${tempDonation.startDate}</p></td>
										<td><p>${tempDonation.endDate}</p></td>
										<td><p>${tempDonation.organization}</p></td>
										<td><p>${tempDonation.phoneNumber}</p></td>
										<td><p>${tempDonation.money}</p></td>
										<td><p class="d-status">${tempDonation.status}</p></td>
										<td class="action-c">
											<button class="btn btn-success donation-btn" title="Chi tiết"
												onclick="updateBtn('${tempDonation.id}')">
												<span class="content-btn-text">Cập nhật</span><span
													class="content-btn-icon"></span>
											</button>
											<button class="btn btn-success donation-btn" onclick="window.location.href='${detailLink}'"
												title="Quyên góp">
												<span class="content-btn-text">Chi tiết</span><span
													class="content-btn-icon"></span>
											</button>
											<button class="btn btn-success donation-btn" title="Chi tiết" onclick="deleteBtn('${tempDonation.id}', '${tempDonation.name}','${tempDonation.code}')">
												<span class="content-btn-text">Xóa</span><span
													class="content-btn-icon"></span>
											</button>
											<button class="btn btn-success donation-btn"
												title="Quyên góp">
												<span class="content-btn-text">Quyên góp</span><span
													class="content-btn-icon"></span>
											</button>
											<button class="btn btn-success donation-btn" title="Chi tiết">
												<span class="content-btn-text">Đóng</span><span
													class="content-btn-icon"></span>
											</button>
											<button class="btn btn-success donation-btn"
												title="Quyên góp">
												<span class="content-btn-text">Kết thúc</span><span
													class="content-btn-icon"></span>
											</button> <a href="${updateLink}">cap nhat</a>
										</td>
									</tr>
								</c:forEach>


							</tbody>
						</table>
						<div>
							<div>
								<input id="currentPage1" type="hidden" value="${currentPage}" />
								<c:out value="current Pg:  ${currentPage}  " />
								<br> <input id="totalPages1" type="hidden"
									value="${totalPage}" />
								<c:out value="total Pg:  ${totalPage}  " />


								<br> <input id="size1" type="hidden" value="${currentSize}" />
								<c:out value="size:  ${currentSize}  " />
								<br> <input id="searchingValue1" type="hidden"
									value="${searchingValue}" />
								<c:out value="searchingValue:  ${searchingValue}  " />
								<br> <input id="importUrl1" type="hidden"
									value="${searchingValue}" />
								<c:out value="searchingValue:  ${searchingValue}  " />

								<c:set var="testValue1" value="<c:url value='/v1/donations'/>" />



							</div>
							<div id="pagination-container"></div>

							<script>
							
							$(document).ready(function() {
								$('#pageSize').change(function() {
									updateShowingTable($('#pageSize').val(), $('#searchingValue').val(), "#donation-list");
								});

								$('#searchingValue').on('input', function() {
									updateShowingTable($('#pageSize').val(), $('#searchingValue').val(), "#donation-list")
								});
								
								
								var currentPage = parseInt(document.getElementById("currentPage1").value, 10);
								var totalPages = parseInt(document.getElementById("totalPages1").value, 10);



								generatePaginationButtons(currentPage, totalPages, $('#pageSize').val(), $('#searchingValue').val(), "#donation-list");
								
								let btnList = document.getElementsByClassName("page-btn");
								Array.from(btnList).forEach(btn => {
								    if (parseInt(btn.textContent) === currentPage) {
								    	
								        btn.disabled = true;
								    }
								});
								
								

							});
							
							
							
							
							</script>


						</div>

					</div>


				</div>

			</div>
		</div>
	</section>

	<%-- 
	<div id="">
		<c:import url="/admin/updateDonation?id=1" />

	</div>
	
	 --%>



	<script src="<c:url value='/static/common/assets/js/script.js' />"></script>
	<%-- 	
<script src="<c:url value='/static/common/assets/js/script.js' />"></script>

 --%>
	<%-- 
<script src="<c:url value='/static/common/assets/js/header.js' />"></script> 

<script src="<c:url value='/static/common/assets/js/form.js' />"></script>
 --%>
	<%-- <script src="<c:url value='/static/common/assets/js/pagination.js' />"></script> --%>

<div class="overlay-container">
		<div id="overlay" onclick="closeAllPopup()"></div>
		<div class="popup">
		
			
			<div class="form-container update-form" id="update">
				<div class="form-head">
					<div class="form-title">
						<h3>Cập nhật</h3>
					</div>
				</div>
				<div class="form-main" id="updateForm">
					
					<form:form class=" col-8 mx-auto" action="${process}"
						modelAttribute="donation" method="POST">

						<form:hidden path="id" />
						<form:input id="id" name="id" type="text" path="id" />


						<table class="col-8 mx-auto">


							<tbody>
								<tr>
									<td><label for="code">Mã đợt quyên góp:</label></td>
									<td><form:input id="code" name="code" type="text"
											path="code" /></td>
								</tr>
								<tr>
									<td><label for="name">Tên quyên góp:</label></td>
									<td><form:input id="name" name="name" type="text"
											path="name" /></td>
								</tr>

								<tr>
									<td><label for="startDate">Ngày bắt đầu</label></td>
									<td><form:input id="startDate" name="startDate"
											type="date" path="startDate" /></td>
								</tr>

								<tr>
									<td><label for="endDate">Ngày kết thúc</label></td>
									<td><form:input id="endDate" name="endDate" type="date"
											path="endDate" /></td>
								</tr>

								<tr>
									<td><label for="organization">Tổ chức:</label></td>
									<td><form:input id="organization" name="organization"
											type="text" path="organization" /></td>
								</tr>
								<tr>
									<td><label for="phoneNumber">Số điện thoại:</label></td>
									<td><form:input id="phoneNumber" name="phoneNumber"
											type="phoneNumber" path="phoneNumber" /></td>
								</tr>
								<tr>
									<td><label for="description">Nội dung:</label></td>
									<td><form:input id="description" name="description"
											type="text" path="description" /></td>
								</tr>

							</tbody>

						</table>
						<input type="button"
							class="btn btn-info mt-4 col-2 d-block mx-auto"
							onclick="closeAllPopup()"
							value="Đóng" />
						<input type="submit" value="capnhat"
							class="btn btn-info mt-4 col-2 d-block mx-auto" />

					</form:form>

				</div>

			</div>
			<div>
				<div class="form-container" id="login-warning">
				<p>Ban can dang nhap truoc khi thuc hien quyen gop</p>
				</div>
			</div>
			<div class="form-container " id="delete">
				<div id="deletePop">
					<h3>Bạn chắc chắn muốn xóa</h3>
					<p>Tên donation: <span id="donationName"></span></p>
					<p>Mã donation: <span id="donationCode"></span></p>
				</div>
				
				<div>
					<button id="confirmBtn" >Xác nhận</button>
					
					<button onclick="closeAllPopup()">Đóng</button>
				</div>
			
			</div>
			
			
		</div>
	</div>



	<script>
	function updateBtn(id) {
		$.ajax({
			type: "GET",
			url: window.location.href,
			data: {
				id: id
			},
			success: function(data) {
				$("#updateForm").html(
					$(data).find("#updateForm").html());
			}
		});
		
		openPopup('update');
	}
	
	function deleteBtn(id, name, code) {
		var donationName = document.getElementById("donationName");
		var donationCode = document.getElementById("donationCode");
		var confirmBtn = document.getElementById("confirmBtn");
		
		donationName.textContent=name;
		donationCode.textContent=code;
		confirmBtn.addEventListener("click", function() {
			window.location.href='${pageContext.request.contextPath}/admin/deleteDonation?id=' +id;
		});
		openPopup('delete');
	}

</script>



</body>
</html>