<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="me.haitmq.spring.mvc.crud.utils.DonationStatusMapper"%>
<%@ page import="me.haitmq.spring.mvc.crud.utils.FormatTest"%>
<%@ page import="me.haitmq.spring.mvc.crud.utils.JSPDataFormat"%>
<%@ page import="me.haitmq.spring.mvc.crud.content_path.ViewConstants"%>

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
<title>Donation website &mdash; Website Donation</title>

<!-- meta tag -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="author" content="Free-Template.co" />

<!-- icon web -->
<link rel="icon" type="image/x-icon"
	href="<c:url value='/static/common/assets/img/icon/heart.ico' />">

<!-- style -->
<!-- from template -->
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

<!-- sytle custom -->
<!-- fontawesome -->
<link rel="stylesheet"
	href="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css'/>"
	integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />

<!-- bootstrap -->

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

<%-- <script
	src="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js' />"
	crossorigin="anonymous"></script> --%>

<!-- bootstrap cdn -->
<script
	src="<c:url value='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' />"
	crossorigin="anonymous"></script>
<script
	src="<c:url value='https://unpkg.com/sweetalert/dist/sweetalert.min.js' />"></script>

<!-- customer style -->

<link rel="stylesheet"
	href="<c:url value='/static/common/assets/css/style.css'/>" />


</head>
<body>

	<!-- Header layout -->
	<jsp:include page="../common/header-layout-test.jsp">
		<jsp:param name="includePart" value="headerSection" />
	</jsp:include>

	


	<section class="site-section content" id="m-content">
	<input type="hidden" id="isLogined" value="${isLogined}" />
		<div class="container">
			<div class="row mb-5 justify-content-center">
				<div class="col-md-7 text-center">
					<h2 class="section-title mb-2">Các đợt quyên góp</h2>

				</div>
			</div>


			<div class="">
				<div class="main-content">
					<div class="h-content">
						<div
							class="sp-tool d-flex flex-column flex-sm-row justify-content-between mt-3">
							<div class="page-selector">

								 <label for="pageSize">Số mục
									trên mỗi trang:</label> <select id="pageSize" name="size"
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
								<label for="searchingValue">Tìm kiếm:</label> <input type="text"
									name="searching-input" id="searchingValue"
									class="searching-input rounded p-2 form-control"
									placeholder="by Code or by status ..."
									title="Tìm theo tên, mã hoặc trạng thái"
									value="${searchingValue}" />
							</div>

						</div>
					</div>




					<!-- table-responsive -->
					<div class="m-content list " id="data-list">
						<div class=" table-responsive">
							<table class="table table-striped table-content">
								<thead class="tb-head-title">
									<tr class="">

										<th scope="col" class="th-custom col-md-3 scrollable-col"><p>Tên
												đợt quyên góp</p></th>

										<th scope="col" class="th-custom col-1"><p>Mã</p></th>
										<th scope="col" class="th-custom"><p>Ngày bắt đầu</p></th>
										<th scope="col" class="th-custom"><p>Ngày kết thúc</p></th>
										<th scope="col" class="th-custom col-md-2"><p>Số
												điện thoại</p></th>

										<th scope="col" class="th-custom col-3"><p>Hành động</p></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="tempDonation" items="${donations.content}">

										<c:url var="donateLink" value="/v1/donateForm">
											<c:param name="id" value="${tempDonation.id}" />
										</c:url>

										<c:url var="detailLink"
											value="${ViewConstants.E_DONATION_DETAIL}">
											<c:param name="id" value="${tempDonation.id}" />
										</c:url>



										<tr class="" ondblclick="window.location.href='${detailLink}'">

											<th scope="row" class="d-ns-cell col-12 scrollable-col">
												<p class="d-name text-align-left font-weight-bold">${tempDonation.name}</p>
												<p class="d-status color-text font-weight-bold">
													${JSPDataFormat.donationStatusFormat(tempDonation.status)}
												</p>

											</th>
											<td><p class="font-weight-bold">${tempDonation.code}</p></td>
											<td>
												<!-- <p class="mb-1">Bắt đầu:</p> -->
												<p class="mb-2">${JSPDataFormat.dateFormat(tempDonation.startDate)}</p>
												<!-- <p class="mb-1">Kết thúc:</p> -->
												
											</td>
											<td>
											<p class="mb-2">${JSPDataFormat.dateFormat(tempDonation.endDate)}</p>
											
											
											</td>

											<%-- <td><p>${JSPDataFormat.dateFormat(tempDonation.endDate)}</p></td> --%>
											<td><p>${tempDonation.phoneNumber}</p></td>



											<td class="action-c d-flex">


												<div class="row col-12 row-custom">
													<button class="btn btn-success donation-btn"
														title="Chi tiết"
														onclick="window.location.href='${detailLink}'">
														<span class="content-btn-text">Chi tiết</span><span
															class="content-btn-icon"><i
															class="fa-solid fa-info"></i></span>
													</button>

													<c:if test="${tempDonation.status == 'DONATING'}">
														<button class="btn btn-success donation-btn"
															title="Quyên góp"
															onclick="toDonateForm(${tempDonation.id},$('#isLogined').val())">
															<span class="content-btn-text">Quyên góp</span><span
																class="content-btn-icon"><i
																class="fa-solid fa-circle-dollar-to-slot"></i></span>
														</button>
													</c:if>


												</div>

											</td>
										</tr>
									</c:forEach>

									
								</tbody>
							</table>
							<script>
									changeColorText();
							</script>
						</div>
						<div>
							<div>
								<c:if test="${donations.totalElements != 0}">
									<p class="font-weight-light font-italic">Showing ${donations.number*donations.size +1} to
										${donations.number*donations.size +donations.numberOfElements}
										of ${donations.totalElements} entries</p>
								</c:if>

								<c:if test="${donations.totalElements == 0}">
									<p>There are no entries to show</p>
								</c:if>


							</div>
							<div>
								<div>
									<input id="currentPage1" type="hidden" value="${currentPage}" />

									<br> <input id="totalPages1" type="hidden"
										value="${totalPage}" /> <br> <input id="size1"
										type="hidden" value="${currentSize}" /> <br> <input
										id="searchingValue1" type="hidden" value="${searchingValue}" />

									<br> <input id="importUrl1" type="hidden"
										value="${searchingValue}" />


									<c:set var="testValue1" value="<c:url value='/v1/donations'/>" />

<input id="currentPage" type="hidden" name="currentPage"
										value="${donations.pageable.pageNumber+1}" /> 

								</div>
								<c:if test="${donations.totalElements != 0}">
									<div id="pagination-container"></div>
								</c:if>


							</div>


						</div>

					</div>

				</div>

			</div>
		</div>
	</section>
	


	<script src="<c:url value='/static/common/assets/js/script.js' />"></script>


	<div class="overlay-container">
		<div class="row">
			<div id="overlay" onclick="closeAllModal()"></div>

				<% Boolean isLogined = ((Boolean) request.getAttribute("isLogined")) != null 
					? (Boolean) request.getAttribute("isLogined") : false; %>
				<% Boolean isActive = ((Boolean) request.getAttribute("isActive")) != null 
					? (Boolean) request.getAttribute("isActive") : false; %>

				<% if (isLogined) { %>
					<% if (isActive) { %>
					
						<jsp:include page="../public/form-modal/user-donation-modal/donate-form.jsp" />

					<% } else { %>
				
						<jsp:include page="../common/form-modal/user-modal/user-no-permission-modal.jsp" />
					<% } %>
				
				<% } else { %>
					<jsp:include page="../common/form-modal/user-modal/user-no-login-modal.jsp" />
				
				<% } %>



				<jsp:include page="../common/form-modal/donate-success-modal.jsp" />
					


			</div>


		</div>



	<jsp:include page="../common/footer-layout2.jsp">
		<jsp:param name="includePart" value="footerSection" />
	</jsp:include>


	<script src="<c:url value='ttps://code.jquery.com/jquery-3.6.4.min.js' />h"
		crossorigin="anonymous"></script>



	<script src="<c:url value='/static/common/assets/js/form.js' />"></script>

	<script src="<c:url value='/static/common/assets/js/script.js' />"></script>

	<script
		src="<c:url value='/static/common/assets/js/layout-script.js' />"></script>


</body>
</html>
