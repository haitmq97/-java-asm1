<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="me.haitmq.spring.mvc.crud.content_path.ViewConstants" %>
<%@ page import="me.haitmq.spring.mvc.crud.utils.JSPDataFormat" %>
<%@ page import="me.haitmq.spring.mvc.crud.utils.Time" %>
<%@ page import="me.haitmq.spring.mvc.crud.entity.status.DonationStatus" %>
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

<link rel="icon" type="image/x-icon"
	href="<c:url value='/static/common/assets/img/icon/heart.ico' />">



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



<!-- customer js -->

<script src="<c:url value='/static/common/assets/js/script.js' />"></script>
<script
	src="<c:url value='/static/common/assets/js/layout-script.js' />"></script>

</head>
<body>
	<!-- Header layout -->
	<jsp:include page="../common/header-layout.jsp">
		<jsp:param name="includePart" value="headerSection" />
	</jsp:include>
	
	




	<section class="site-section content">
	<input type="hidden" id="isLogined" value="${isLogined}" />
		<div class="container" id="content-table">
			<div class="row mb-5 justify-content-center">
				<div class="col-md-7 text-center">
					<h2 class="section-title mb-2">Các đợt quyên góp</h2>
					
					

				</div>
			</div>




			<div class="">
				<div class="main-content">
					<div class="h-content">
					<div class="add-div">
						<button class="btn btn-success donation-btn" onclick="toAddOrUpdate(0, '#donation-addOrUpdate')">Thêm mới</button>
	
						
					</div>
		
						<div class="sp-tool d-flex flex-column flex-sm-row justify-content-between mt-3">
							<div class="page-selector">

							
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
						<div class="table-div table-responsive">
							<table class="table table-striped table-content">
							<thead class="tb-head-title">
								<tr>
				
									<th scope="col" class="th-custom col-1 custom-display-none"><p>Mã</p></th>
									<th scope="col" class="th-custom col-2 col-lg-3">
										<p>Tên</p>
										
									</th>
									
									<th scope="col" class="th-custom col-2"><p>Ngày</p></th>
									
									<th scope="col" class="th-custom col-2"><p>Tổ chức</p></th>
									<th scope="col" class="th-custom"><p>Số điện thoại</p></th>
									<th scope="col" class="th-custom"><p>Tổng tiền</p></th>
									<th scope="col" class="th-custom col-1 col-lg-2"><p>Trạng thái</p></th>
									<th scope="col" class="th-custom"><p>Hành động</p></th>
								</tr>
							</thead>
							
						
							
							<tbody id="scrollableRows">
								<c:forEach var="tempDonation" items="${donations.content}">

									<c:url var="donateLink" value="/v1/donateForm">
										<c:param name="id" value="${tempDonation.id}" />
									</c:url>

									<c:url var="detailLink" value="${ViewConstants.E_ADMIN_DONATION_DETAIL}">
										<c:param name="id" value="${tempDonation.id}" />
									</c:url>
									
									<c:url var="deleteLink" value="/admin/deleteDonation">
										<c:param name="id" value="${tempDonation.id}" />
										<c:param name="currentUrl" value="/admin/donations" />
									</c:url>
									
									<c:url var="updateLink" value="/admin/updateDonation">
										<c:param name="id" value="${tempDonation.id}" />
									</c:url>
									
									<c:url var="donatingStatusLink" value="/admin/updateDonationStatus">
										<c:param name="id" value="${tempDonation.id}" />
										<c:param name="status" value="${DonationStatus.DONATING}" />
									</c:url>
									
									
									
									<c:url var="endStatusLink" value="/admin/updateDonationStatus">
										<c:param name="id" value="${tempDonation.id}" />
										<c:param name="status" value="${DonationStatus.END}" />
									</c:url>
									
									<c:url var="closedStatusLink" value="/admin/updateDonationStatus">
										<c:param name="id" value="${tempDonation.id}" />
										<c:param name="status" value="${DonationStatus.CLOSED}" />
									</c:url>
									
									<c:url var="testLink" value="/admin/donations">
										
									</c:url>



									<tr ondblclick="window.location.href='${detailLink}'">

										<td class="custom-display-none"><p class="font-weight-bold">${tempDonation.code}</p></td>
										<td>
											<p class="text-align-left font-weight-bold">${tempDonation.name}</p>
											<p class="font-weight-bold custom-display-block">${tempDonation.code}</p>
										</td>
										<td>
											<p class="mb-1 font-italic">Bắt đầu:</p>
											<p class="mb-2">${JSPDataFormat.dateFormat(tempDonation.startDate)}</p>
											<p class="mb-1 font-italic">Kết thúc:</p>
											<p class="mb-2">${JSPDataFormat.dateFormat(tempDonation.endDate)}</p>
										</td>
										
										<td><p>${tempDonation.organization}</p></td>
										<td><p>${tempDonation.phoneNumber}</p></td>
										<td><p>${JSPDataFormat.moneyFormat(tempDonation.money)}</p></td>
										<td>
										
											<p class="d-status font-weight-bold color-text ">${JSPDataFormat.donationStatusFormat(tempDonation.status)}</p>
											<script>
											changeColorText();
											
											</script>
										
										</td>
										<td class="action-c">
											
											<c:if test="${tempDonation.status != DonationStatus.CLOSED}">
												<button class="btn btn-success donation-btn donation-update-btn" 
														title="Chi tiết" 
														onclick="toAddOrUpdate('${tempDonation.id}','#donation-addOrUpdate')">
													<span>Cập nhật</span>
												</button>
											</c:if>
											
										
										
											
											<button class="btn btn-success donation-btn" 
													title="Chi tiết"
													onclick="window.location.href='${detailLink}'">
												<span>Chi tiết</span>
											</button> 
											
											
											<c:choose>
												
												<c:when test="${tempDonation.status == DonationStatus.NEW}">
												
													<button class="btn btn-success donation-btn"
															title="Quyên góp"
															onclick="openModalId('${tempDonation.id}', '#update-status')">
														<span>Quyên góp</span>
													</button>
													
													<button class="btn btn-danger donation-btn d-delete-btn"
														title="Xóa"
														onclick="openModalId('${tempDonation.id}', '#closedOrDelete')"
														data-url="${deleteLink}">
														<span>Xóa</span>
													</button>
													
													
												</c:when>
												
												<c:when test="${tempDonation.status == DonationStatus.DONATING}">
            										
            										<button class="btn btn-danger donation-btn"
															title="Quyên góp"
															onclick="openModalId('${tempDonation.id}', '#update-status')">
														<span>Kết thúc</span><span
															class="content-btn-icon"></span>
													</button>
            										
         										</c:when>

												<c:when test="${tempDonation.status == DonationStatus.END}">
            										<button class="btn btn-danger donation-btn" 
															title="Chi tiết"
															onclick="openModalId('${tempDonation.id}', '#closedOrDelete')">
													<span >Đóng</span>
													</button>
         										</c:when>

												<c:otherwise>
            
         										</c:otherwise>
											</c:choose> 
												
											

										</td>
									</tr>
									
									
								
									
								</c:forEach>


							</tbody>
						</table>
						</div>
						<div>
							<c:if test="${donations.totalElements != 0}">
								<p class="font-weight-light font-italic text-muted">Showing
									${donations.number*donations.size +1} to
									${donations.number*donations.size +donations.numberOfElements}
									of ${donations.totalElements} entries</p>

								<div>
									<div>

										<input id="totalPages" type="hidden"
											value="${donations.totalPages}" /> 
										<input id="currentPage"
											type="hidden" name="currentPage"
											value="${donations.pageable.pageNumber+1}" />

									</div>
									
									<div id="pagination-container"></div>
									


								</div>
							</c:if>

							<c:if test="${donations.totalElements == 0}">
								<p class="font-weight-light font-italic text-muted">There
									are no entries to show</p>
							</c:if>


						</div>
						
						
						

						</div>

					</div>
					
					
				</div>

			</div>

	</section>
	


	
	<jsp:include page="../common/footer-layout.jsp">
		<jsp:param name="includePart" value="footerSection" />
	</jsp:include>


	<div class="overlay-container">
		<div class="row">
			<div id="overlay" onclick="closeAllModal()"></div>

			<!-- for update or add donation modal -->
			<jsp:include
				page="../admin/form-modal/donation-modal/donation-add-or-update-modal.jsp" />

			<!-- for close or delete donation modal -->
			<jsp:include
				page="../admin/form-modal/donation-modal/donation-closed-or-delete-modal.jsp" />

			<!-- for donating or end donation modal -->
			<jsp:include
				page="../admin/form-modal/donation-modal/donation-update-status-modal.jsp" />
			
				<!-- Hien thị thông báo tạo thành công -->

			<jsp:include
				page="../admin/form-modal/donation-modal/donation-success-add-modal.jsp" />
		
			<jsp:include
				page="../admin/form-modal/donation-modal/donation-success-update-modal.jsp" />

			<jsp:include
				page="../admin/form-modal/donation-modal/donation-success-delete-modal.jsp" />
			
			<jsp:include
				page="../admin/form-modal/donation-modal/donation-success-change-status-modal.jsp" />

		</div>
	
	</div>

	<script src="<c:url value='https://code.jquery.com/jquery-3.6.4.min.js' />"
		crossorigin="anonymous"></script>

	<script src="<c:url value='/static/common/assets/js/script.js' />"></script>

	<script
		src="<c:url value='/static/common/assets/js/layout-script.js' />"></script>

</body>
</html>