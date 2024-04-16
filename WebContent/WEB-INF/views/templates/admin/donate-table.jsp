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
			
			
				 <button class="data-table-btn" onclick="window.location.href='${pageContext.request.contextPath}/admin/donations2'">Donations</button>
				<button class="data-table-btn" onclick="window.location.href='${pageContext.request.contextPath}/admin/users'"
								>Users</button>
				<button class="data-table-btn" onclick="window.location.href='${pageContext.request.contextPath}/admin/donates'"
								>Donates</button>

</div>


	<section class="site-section content">
		<div class="container" id="content-table">
			<div class="row mb-5 justify-content-center">
				<div class="col-md-7 text-center">
					<h2 class="section-title mb-2">Các luot quyên góp</h2>
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
									value="${currentPage}" /> <label for="size">Rows
									per page:</label> 
								<select id="pageSize" name="size"
									
									class="entries-select rounded form-control">
									<option value="3" ${donates.size == 3 ? 'selected' : ''}>3</option>
									<option value="4" ${donates.size == 4 ? 'selected' : ''}>4</option>
									<option value="5" ${donates.size == 5 ? 'selected' : ''}>5</option>
									<option value="10" ${donates.size == 10 ? 'selected' : ''}>10</option>
									<option value="15" ${donates.size == 15 ? 'selected' : ''}>15</option>
									<option value="20" ${donates.size == 20 ? 'selected' : ''}>20</option>
									<!-- Add more options as needed -->
								</select>
							
			

							</div>
							<div class="search-box">
								<label for="searching-input">Search:</label> <input type="text"
									name="searching-input" id="searchingValue"
									class="searching-input rounded p-2 form-control"
									placeholder="by Code or by status ..."
									value="${searchingValue}"  />
							</div>
						
						</div>
					</div>
					<div class="m-content list" id="donate-list">
						<table class="table table-striped table-content">
							<thead class="tb-head-title">
								<tr>
									<th scope="col" class="th-custom"><p>id</p></th>
									<th scope="col" class="th-custom"><p>Created Date</p></th>
									<th scope="col" class="th-custom"><p>Name</p></th>
									
									<th scope="col" class="th-custom"><p>Money</p></th>
									<th scope="col" class="th-custom"><p>Note</p></th>
									<th scope="col" class="th-custom"><p>UserName</p></th>
									<th scope="col" class="th-custom"><p>Donation Code</p></th>
									<th scope="col" class="th-custom"><p>Status</p></th>
									<th scope="col" class="th-custom"><p>Action</p></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="tempDonate" items="${donates.content}">

									<c:url var="donateLink" value="/v1/donateForm">
										<c:param name="id" value="${tempDonate.id}" />
									</c:url>

									<c:url var="detailLink" value="/v1/donate-detail">
										<c:param name="id" value="${tempDonate.id}" />
									</c:url>
									
									<c:url var="confirmLink" value="/admin/donateStatusComfirm">
										<c:param name="id" value="${tempDonate.id}" />
									</c:url>
									
									
									<c:url var="deleteLink" value="/admin/donateDelete">
										<c:param name="id" value="${tempDonate.id}" />
									</c:url>


									<tr>
										<td><p>${tempDonate.id}</p></td>
										<td><p>${tempDonate.createdDate}</p></td>
										<td><p>${tempDonate.name}</p></td>
										<td><p>${tempDonate.money}</p></td>
										<td><p>${tempDonate.note}</p></td>
										<td><p>${tempDonate.user.userName}</p></td>
										<td><p>${tempDonate.donation.code}</p></td>
										<td><p>${tempDonate.status}</p></td>
										<td class="action-c">
											<button class="btn btn-success donate-btn" title="Chi tiết" onclick="window.location.href='${confirmLink}'">
												<span class="content-btn-text">Xác nhận</span><span
													class="content-btn-icon"></span>
											</button>
											<button class="btn btn-success donate-btn" title="Chi tiết" onclick="deleteBtn('${tempDonate.id}', '${tempDonate.user.userName}','${tempDonate.donation.code}')">
												<span class="content-btn-text">Xóa</span><span
													class="content-btn-icon"></span>
											</button>
											
											
										</td>
									</tr>
								</c:forEach>


							</tbody>
						</table>
						<div>
							<div>
								<input id="currentPage1" type="hidden" value="${currentPage}" />
								<c:out value="current Pg:  ${currentPage}  " />
								<br> 
								<input id="totalPages1" type="hidden" value="${totalPage}" />
								<c:out value="total Pg:  ${totalPage}  " />
								
	
								<br> <input id="size1" type="hidden" value="${currentSize}" />
								<c:out value="size:  ${currentSize}  " />
								<br> <input id="searchingValue1" type="hidden"
									value="${searchingValue}" />
								<c:out value="searchingValue:  ${searchingValue}  " />
								<br> <input id="importUrl1" type="hidden"
									value="${searchingValue}" />
								<c:out value="searchingValue:  ${searchingValue}  " />
								
								<c:set var="testValue1" value="<c:url value='/v1/donates'/>" />

 
 								
							</div>
							<div id="pagination-container"></div>
						
							<script>
							
							$(document).ready(function() {
								$('#pageSize').change(function() {
									updateShowingTable($('#pageSize').val(), $('#searchingValue').val(), "#donate-list");
								});

								$('#searchingValue').on('input', function() {
									updateShowingTable($('#pageSize').val(), $('#searchingValue').val(), "#donate-list")
								});
								
								
								var currentPage = parseInt(document.getElementById("currentPage1").value, 10);
								var totalPages = parseInt(document.getElementById("totalPages1").value, 10);



								generatePaginationButtons(currentPage, totalPages, $('#pageSize').val(), $('#searchingValue').val(), "#donate-list");
								
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
		
			
			
			<div>
				<div class="form-container" id="login-warning">
				<p>Ban can dang nhap truoc khi thuc hien quyen gop</p>
				</div>
			</div>
			<div class="form-container " id="delete">
				<div id="deletePop">
					<h3>Bạn chắc chắn muốn xóa</h3>
					<p>username: <span id="donationName"></span></p>
					<p>Mã : <span id="donationCode"></span></p>
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
			window.location.href='${pageContext.request.contextPath}/admin/deleteDonate?id=' +id;
		});
		openPopup('delete');
	}

</script>

</body>
</html>