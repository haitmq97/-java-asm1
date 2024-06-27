<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="me.haitmq.spring.mvc.crud.content_path.ViewConstants" %>
<%@ page import="me.haitmq.spring.mvc.crud.utils.JSPDataFormat"%>
<%@ page import="me.haitmq.spring.mvc.crud.utils.Time"%>
<%@ page import="me.haitmq.spring.mvc.crud.entity.status.UserStatus" %>
<%@ page import="me.haitmq.spring.mvc.crud.entity.role.UserRole" %>

<!DOCTYPE html>
<html>
<head>
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
					<h2 class="section-title mb-2">Danh sách người dùng</h2>
				</div>
			</div>

			<div class="">
				<div class="main-content">
					<div class="h-content">
						<div class="add-div">
							<button class="btn btn-success donation-btn"
								onclick="toAddOrUpdate(0, '#user-addOrUpdate')">Thêm
								mới</button>

						</div>
						<div class="sp-tool d-flex flex-row justify-content-between mt-3">
							<div class="page-selector">

								
								<label for="size">Rows per page:</label> 
								<select id="pageSize" name="size"
									class="entries-select rounded form-control">
									<option value="3" ${users.size == 3 ? 'selected' : ''}>3</option>
									<option value="4" ${users.size == 4 ? 'selected' : ''}>4</option>
									<option value="5" ${users.size == 5 ? 'selected' : ''}>5</option>
									<option value="10" ${users.size == 10 ? 'selected' : ''}>10</option>
									<option value="15" ${users.size == 15 ? 'selected' : ''}>15</option>
									<option value="20" ${users.size == 20 ? 'selected' : ''}>20</option>
									
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
					<div class="m-content list" id="data-list">
						<div class=" table-responsive">
							<table class="table table-striped table-content">
								<thead class="tb-head-title">
									<tr>

										<th scope="col" class="th-custom col-md-2"><p>Name</p></th>
										<th scope="col" class="th-custom col-md-2"><p>
												Email <span class="custom-display-block">/Phone
													number</span>
											</p></th>
										<th scope="col" class="th-custom custom-display-none"><p>Phone
												number</p></th>
										<th scope="col" class="th-custom"><p>UserName</p></th>
										<th scope="col" class="th-custom"><p>Role</p></th>
										<th scope="col" class="th-custom"><p>status</p></th>
										<th scope="col" class="th-custom col-1 col-md-2 col-lg-3"><p>Action</p></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="tempUser" items="${users.content}">


										<c:url var="detailLink"
											value="${ViewConstants.E_ADMIN_USER_DETAIL}">
											<c:param name="id" value="${tempUser.id}" />
										</c:url>

										<c:url var="deleteLink" value="/admin/deleteUser">
											<c:param name="id" value="${tempUser.id}" />
											<c:param name="currentUrl" value="/admin/users" />
										</c:url>

										<c:url var="updateLink" value="/user/updateUser">
											<c:param name="userId" value="${tempUser.id}" />
										</c:url>

										<c:url var="changeStatusLink" value="/user/changeStatus">
											<c:param name="userId" value="${tempUser.id}" />
										</c:url>

										<c:url var="activeStatusLink" value="/admin/updateUserStatus">
											<c:param name="id" value="${tempUser.id}" />
											<c:param name="status" value="${UserStatus.ACTIVE}" />
										</c:url>

										<c:url var="closedStatusLink" value="/admin/updateUserStatus">
											<c:param name="id" value="${tempUser.id}" />
											<c:param name="status" value="${UserStatus.LOCKED}" />
										</c:url>

										<tr>

											<td class=""><p class="text-align-left">${tempUser.fullName}</p></td>
											<td>
												<p>${tempUser.email}</p>
												<p class="custom-display-block">${tempUser.phoneNumber}</p>
											</td>
											<td class="custom-display-none"><p>${tempUser.phoneNumber}</p></td>
											<td class=""><p>${tempUser.userName}</p></td>
											<td><p class="color-text">${tempUser.role.roleName}</p></td>
											<td><p class="color-text">${JSPDataFormat.userStatusFormat(tempUser.status)}</p></td>
											<td class="action-c">

												<div class="row col-12 row-custom">
													<button class="btn btn-success user-btn" title="Cập nhật"
														onclick="toAddOrUpdate('${tempUser.id}', '#user-addOrUpdate')">
														Cập nhật</button>
													<button class="btn btn-success user-btn" title="Quyên góp"
														onclick="window.location.href='${detailLink}'">
														Chi tiết</button>


													<c:if test="${tempUser.role.roleName == UserRole.USER}">

														<button class="btn btn-danger donation-btn d-delete-btn"
															title="Xóa"
															onclick="openModalId('${tempUser.id}', '#user-delete')"
															data-url="${deleteLink}">Xóa</button>

														<c:choose>

															<c:when test="${tempUser.status == UserStatus.ACTIVE}">

																<button
																	class="btn btn-warning donation-btn d-delete-btn"
																	title="Khóa"
																	onclick="openModalId('${tempUser.id}', '#user-lockOrUnlock')">
																	Khóa</button>

															</c:when>

															<c:when test="${tempUser.status == UserStatus.LOCKED}">

																<button class="btn btn-primary donation-btn" title="Mở"
																	onclick="openModalId('${tempUser.id}', '#user-lockOrUnlock')">
																	Mở</button>

															</c:when>

															<c:otherwise>

															</c:otherwise>
														</c:choose>


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
							<c:if test="${users.totalElements != 0}">
								<p class="font-weight-light font-italic text-muted">Showing
									${users.number*users.size +1} to ${users.number*users.size +users.numberOfElements}
									of ${users.totalElements} entries</p>

								<div>
									<div>

										<input id="totalPages" type="hidden"
											value="${users.totalPages}" /> <input id="currentPage"
											type="hidden" name="currentPage"
											value="${users.pageable.pageNumber+1}" />

									</div>

									<div id="pagination-container"></div>



								</div>
							</c:if>

							<c:if test="${users.totalElements == 0}">
								<p class="font-weight-light font-italic text-muted">There
									are no entries to show</p>
							</c:if>


						</div>

					</div>


				</div>

			</div>
		</div>
	</section>

	<div class="overlay-container">
		<div class="row">
			<div id="overlay" onclick="closeAllModal()"></div>
			
			<!-- for add or update modal -->
			<jsp:include
				page="../admin/form-modal/user-modal/user-add-or-update-modal.jsp" />

			<!-- for delete modal -->
			<jsp:include
				page="../admin/form-modal/user-modal/user-delete-modal.jsp" />
			
			<!-- for change user status modal -->
			<jsp:include
				page="../admin/form-modal/user-modal/user-lock-or-unlock-status-modal.jsp" />
			
			<!-- for success action modal -->
			
			<jsp:include
				page="../admin/form-modal/user-modal/user-success-add-modal.jsp" />

			<jsp:include
				page="../admin/form-modal/user-modal/user-success-update-modal.jsp" />
			
			<jsp:include
				page="../admin/form-modal/user-modal/user-success-delete-modal.jsp" />
				
			<jsp:include
				page="../admin/form-modal/user-modal/user-success-change-status-modal.jsp" />
			
		</div>

	</div>
	
	
	<jsp:include page="../common/footer-layout.jsp">
		<jsp:param name="includePart" value="footerSection" />
	</jsp:include>
    
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"
		crossorigin="anonymous"></script>



	<script src="<c:url value='/static/common/assets/js/form.js' />"></script>

	<script src="<c:url value='/static/common/assets/js/script.js' />"></script>

	<script
		src="<c:url value='/static/common/assets/js/layout-script.js' />"></script>


</body>
</html>