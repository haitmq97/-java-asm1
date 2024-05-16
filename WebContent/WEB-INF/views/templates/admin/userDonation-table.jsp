<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="me.haitmq.spring.mvc.crud.content_path.ViewConstants" %>
<%@ page import="me.haitmq.spring.mvc.crud.utils.JSPDataFormat" %>
<%@ page import="me.haitmq.spring.mvc.crud.entity.status.DonationStatus" %>
<%@ page import="me.haitmq.spring.mvc.crud.entity.status.UserDonationStatus" %>
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

<script src="<c:url value='https://code.jquery.com/jquery-3.6.4.min.js'/>"> </script>
<script src="<c:url value='/static/common/assets/js/form.js' />"></script>
<script src="<c:url value='/static/common/assets/js/script.js' />"></script> 
<script src="<c:url value='/static/common/assets/js/data-list.js' />"></script> 
<script src="<c:url value='/static/common/assets/js/layout-script.js' />"></script>


</head>
<body>
	<!-- Header layout -->
	<jsp:include page="../common/header-layout-test.jsp">
		<jsp:param name="includePart" value="headerSection" />
	</jsp:include>
	
	<input type="hidden" id="isLogined" value="${isLogined}" />




	
	<section class="site-section content">
		<div class="container" id="content-table">
			<div class="row mb-5 justify-content-center">
				<div class="col-md-7 text-center">
					<h2 class="section-title mb-2">Các đợt quyên góp</h2>
					
					

				</div>
			</div>




			<div class="container">
				<div class="main-content">
					<div class="h-content">
					
						<div class="sp-tool d-flex flex-column flex-sm-row justify-content-between mt-3">
							<div class="page-selector">

								<input id="currentPage" type="hidden" name="currentPage"
									value="${currentPage}" /> 
								<label for="size">Rows per page:</label> 
								<select id="pageSize" name="size"
									
									class="entries-select rounded form-control">
									<option value="3" ${donations.size == 3 ? 'selected' : ''}>3</option>
									<option value="4" ${donations.size == 4 ? 'selected' : ''}>4</option>
									<option value="5" ${donations.size == 5 ? 'selected' : ''}>5</option>
									<option value="10" ${donations.size == 10 ? 'selected' : ''}>10</option>
									<option value="15" ${donations.size == 15 ? 'selected' : ''}>15</option>
									<option value="20" ${donations.size == 20 ? 'selected' : ''}>20</option>
									
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
					
					
					
					
					<div class="m-content list" id="data-list">
						<div class="table-div">
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
								<c:forEach var="tempUserDonation" items="${userDonations.content}">

									<c:url var="donateLink" value="/v1/donateForm">
										<c:param name="id" value="${tempUserDonation.id}" />
									</c:url>

									<c:url var="detailLink" value="/v1/donate-detail">
										<c:param name="id" value="${tempUserDonation.id}" />
									</c:url>
									
									<c:url var="confirmLink" value="/admin/donateStatusComfirm">
										<c:param name="id" value="${tempUserDonation.id}" />
									</c:url>
									
									
									<c:url var="deleteLink" value="/admin/donateDelete">
										<c:param name="id" value="${tempUserDonation.id}" />
									</c:url>
									
									
									<c:url var="confirmStatusLink" value="/admin/update_user_donations">
										<c:param name="id" value="${tempUserDonation.id}" />
										<c:param name="status" value="${UserDonationStatus.CONFIRMED}" />
									</c:url>
									
								

									<tr>
										<td><p>${tempUserDonation.id}</p></td>
										<td><p>${tempUserDonation.createdDate}</p></td>
										<td><p>${tempUserDonation.name}</p></td>
										<td><p>${tempUserDonation.money}</p></td>
										<td><p>${tempUserDonation.note}</p></td>
										<td><p>${tempUserDonation.user.userName}</p></td>
										<td><p>${tempUserDonation.donation.code}</p></td>
										<td><p>${tempUserDonation.status}</p></td>
										<td class="action-c">
											<c:if test="${tempUserDonation.status == UserDonationStatus.WAITING}">
												<button class="btn btn-success donate-btn" title="Chi tiết" onclick="window.location.href='${confirmStatusLink}'">
												<span class="content-btn-text">Xác nhận</span><span
													class="content-btn-icon"></span>
											</button>
											
											<button class="btn btn-success donate-btn" title="Chi tiết" onclick="toDelete('${tempUserDonation.id}', '#delete')">
												<span class="content-btn-text">Xóa</span><span
													class="content-btn-icon"></span>
											</button>
											
											</c:if>
											
											
											
											
											
										</td>
									</tr>
								</c:forEach>


							</tbody>
						</table>
						<div>
							<div>
								<input id="currentPage1" type="hidden" value="${currentPage}" />

								<br> 
								<input id="totalPages1" type="hidden"
									value="${totalPage}" /> 
								<br> 
								<input id="size1"
									type="hidden" value="${currentSize}" /> 
								<br> 
								<input
									id="searchingValue1" type="hidden" value="${searchingValue}" />

								<br> <input id="importUrl1" type="hidden"
									value="${searchingValue}" />


								<c:set var="testValue1" value="<c:url value='/v1/donations'/>" />



							</div>
							<div id="pagination-container"></div>
						
							

						</div>

					</div>
					
					
				</div>

			</div>
		</div>
		</div>
	</section>



	<script src="<c:url value='/static/common/assets/js/script.js' />"></script> 


	
	<jsp:include page="../common/footer-layout2.jsp">
		<jsp:param name="includePart" value="footerSection" />
	</jsp:include>
	
	
	
	<div class="overlay-container">
		<div class="row">
			<div id="overlay" onclick="closeAllPopup()"></div>
			<div class="popup col-12 col-sm-8 col-md-4">
			
			<% Boolean isLogined = ((Boolean)request.getAttribute("isLogined")) != null ? (Boolean)request.getAttribute("isLogined") : false; %>
			<% Boolean isAdmin = ((Boolean)request.getAttribute("isAdmin")) != null ? (Boolean)request.getAttribute("isAdmin") : false; %>
			
		<%-- 
			<% boolean isLogined = (Boolean)request.getAttribute("isLogined"); %>
			<% boolean isAdmin = (Boolean)request.getAttribute("isAdmin"); %>
    		 --%>
    		<% if (isAdmin) { %>
    			<div class="form-container donate-form" id="delete">
					<div class="container form-head">
						<h3>Bạn có chắc chắn xóa?</h3>
					</div>
					<div class="container form-main">
						<p>
							Đợt quyên góp: <span>${userDonation.donation.name}</span>
						</p>
						<p>
							Mã quyên góp: <span>${userDonation.donation.code}</span>
						</p>
						
						<p>
							Người quyên góp: <span>${userDonation.user.userName}</span>
						</p>
						
						<p>
							Số tiền quyên góp: <span>${userDonation.money}</span>
						</p>
						
						<div class="submit-p">
							<button type="button" class="cancel-btn "
								onclick="closeAllPopup()">Hủy</button>
							<button type="submit" class="submit-btn" id="confirm-delete-btn"
								onclick="window.location.href='${pageContext.request.contextPath}/admin/deleteUserDonation?id=${userDonation.id}'">Xóa</button>
						</div>

					</div>
				</div>
				
				
				
    		
    		 <% } else { %>
    		 
    		 <% } %>
    		
    		

		
    		
    		

			

		</div>
		
		
		</div>
	
	</div>
	
	
	
	
	
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<script>
$(document).ready(function() {
	  $(".d-delete-btn").click(function() {
	    var url = $(this).data("url");
	    console.log(url);
	    
	   	$("#confirm-delete-btn").click(function() {
	      window.location.href = url;
	    });
	     
	     openModal("delete");

	  });
	});

</script>

</body>
</html>