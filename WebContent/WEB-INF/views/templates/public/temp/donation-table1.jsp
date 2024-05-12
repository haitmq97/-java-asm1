<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
<title>Donation website &mdash; Website Donation</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="author" content="Free-Template.co" />
<%-- 
<link rel="stylesheet"
	href="<c:url value='/static/common/assets/css/reset.css'/>" /> --%>

<link rel="shortcut icon" href="ftco-32x32.png">

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
<%-- 
<link rel="stylesheet"
	href="<c:url value='/static/user/assets/css/style.css' />">
 --%>
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



<!-- customer js -->

<script
	src="<c:url value='https://code.jquery.com/jquery-3.6.4.min.js'/>"> </script>
<script src="<c:url value='/static/common/assets/js/form.js' />"></script>
<script src="<c:url value='/static/common/assets/js/script.js' />"></script>
<script src="<c:url value='/static/common/assets/js/data-list.js' />"></script>
</head>
<body id="top">

	<!-- Header layout -->
	<jsp:include page="../common/header-layout2.jsp">
		<jsp:param name="includePart" value="headerSection" />
	</jsp:include>

	<section class="site-section content" id="m-content">
		<div class="container-lg">
			<div class="row mb-5 justify-content-center">
				<div class="col-md-7 text-center">
					<h2 class="section-title mb-2">Các đợt quyên góp</h2>
				</div>
			</div>


			<div class="container-lg">
				<div class="main-content">
					<div class="h-content">
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


					<%-- 					
					<c:choose>
						<c:when test="${totalElements > 0}">
						
						
						</c:when>
						<c:otherwise>  
       						<p>Không có kết quả tìm kiếm phù hợp</p>  
   						</c:otherwise>  
					
					</c:choose>
 --%>


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

									<c:url var="detailLink" value="/v1/donation-detail">
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
												onclick="window.location.href='${detailLink}'">
												<span class="content-btn-text">Chi tiết</span><span
													class="content-btn-icon"><i class="fa-solid fa-info"></i></span>
											</button> 
											<button class="btn btn-success donation-btn"
														title="Quyên góp"
														onclick="toDonateForm(${tempDonation.id},$('#authorities').val())">
														<span class="content-btn-text">Quyên góp</span><span
															class="content-btn-icon"><i
															class="fa-solid fa-circle-dollar-to-slot"></i></span>
													</button>
						
												
												<%-- 			
												
											<c:choose>
												
									

												<c:when test="${tempDonation.status == 1}">
													<button class="btn btn-success donation-btn"
														title="Quyên góp"
														onclick="toDonateForm(${tempDonation.id},$('#authorities').val())">
														<span class="content-btn-text">Quyên góp</span><span
															class="content-btn-icon"><i
															class="fa-solid fa-circle-dollar-to-slot"></i></span>
													</button>

												</c:when>

												<c:otherwise>
													<button class="btn btn-success donation-btn"
														title="Quyên góp" disabled>
														<span class="content-btn-text">Quyên góp</span><span
															class="content-btn-icon"><i
															class="fa-solid fa-circle-dollar-to-slot"></i></span>
													</button>

											</c:otherwise>
											</c:choose>

 --%>
											<p class="active-donate-btn">${tempDonation.status}</p>


										</td>
									</tr>
								</c:forEach>


							</tbody>
						</table>
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



							</div>
							<div id="pagination-container"></div>

							<script>
							
							
							
							
							</script>

						</div>

					</div>

				</div>

			</div>
		</div>
	</section>
	<jsp:include page="../common/footer-layout2.jsp">
		<jsp:param name="includePart" value="footerSection" />
	</jsp:include>
	<%-- 
	<div id="donate-popup">
		<c:import url="/v1/donateForm?id=2" />

	</div>
	 --%>
	<c:import url="/v1/login" />
	<script src="<c:url value='/static/common/assets/js/script.js' />"></script>


	<div>
		<input id="authorities" type="hidden" value="${authorities}" readonly>
	</div>

	////////////////////////////////////////

	<div class="overlay-container">
		<div id="overlay" onclick="closeAllPopup()"></div>
		<div class="popup">
			<div class="form-container donate-form" id="donate">
				<div class="form-head">
					<div class="form-title">
						<h3>Quyên góp</h3>
						<p>donation id: ${donationId}</p>
					</div>
				</div>
				<div class="form-main">
					<form:form modelAttribute="userDonation" action="${process}"
						method="POST">
						<input type="hidden" name="donationId" value="${donationId}" />
						<div class="f-field">
							<div class="label-d">
								<label class="field-label"> <span class="label-text">Họ
										tên:</span>
								</label>
							</div>
							<div class="input-d">
								<div class="field-input">
									<form:input type="text" class="input-p form-control"
										path="name" />
								</div>
								<div class=""></div>
							</div>
						</div>

						<div class="f-field">
							<div class="label-d">
								<label class="field-label"> <span class="label-text">Số
										tiền quyên góp:</span>
								</label>
							</div>
							<div class="input-d">
								<div class="field-input">
									<form:input type="number" class="input-p form-control"
										path="money" />
								</div>

							</div>

						</div>
						<div class="f-field">
							<div class="label-d">
								<label class="field-label"> <span class="label-text">Lời
										nhắn:</span>
								</label>
							</div>
							<div class="input-d">
								<div class="field-input">
									<form:input type="text" class="input-p form-control"
										path="note" />
								</div>

							</div>

						</div>


						<div class="submit-p">
							<button type="button" class="cancel-btn "
								onclick="closePopup('login')">Hủy</button>
							<button type="submit" class="submit-btn">Quyên góp</button>
						</div>
					</form:form>
				</div>

			</div>

			<div class="form-container loginWarning-form" id="loginWarning">
				<p>Bạn phải đăng nhập trước</p>
			</div>

		</div>
	</div>

	<div></div>



	<script src="https://code.jquery.com/jquery-3.6.4.min.js"
		crossorigin="anonymous"></script>



	<script src="<c:url value='/static/common/assets/js/form.js' />"></script>

	<script type="text/javascript">
	/* 	
	isAbleToDonate(authorities)? openPopup('donate'): openPopup('loginWarning')
	 */
		
	
	
	</script>




	<script type="text/javascript">
	
	
	</script>
</body>
</html>
