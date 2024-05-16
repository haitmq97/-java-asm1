<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="me.haitmq.spring.mvc.crud.utils.DonationStatusMapper" %>
<%@ page import="me.haitmq.spring.mvc.crud.utils.FormatTest" %>
<%@ page import="me.haitmq.spring.mvc.crud.utils.JSPDataFormat" %>
<%@ page import="me.haitmq.spring.mvc.crud.content_path.ViewConstants" %>

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
<%-- 
<script
	src="<c:url value='https://code.jquery.com/jquery-3.6.4.min.js'/>"> </script>
<script src="<c:url value='/static/common/assets/js/form.js' />"></script>

<script src="<c:url value='/static/common/assets/js/data-list.js' />"></script>

 --%>
<script src="<c:url value='/static/common/assets/js/script.js' />"></script>
<script src="<c:url value='/static/common/assets/js/layout-script.js' />"></script>
</head>
<body id="top">

	<!-- Header layout -->
	<jsp:include page="../common/header-layout-test.jsp">
		<jsp:param name="includePart" value="headerSection" />
	</jsp:include>
	
	<input type="hidden" id="isLogined" value="${isLogined}" />
	
	<section class="site-section content" id="m-content">
		<div class="container">
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

								<input id="currentPage" type="hidden" name="currentPage" value="${currentPage}" />
								 
								<label for="size">Số mục trên mỗi trang:</label> 
								<select id="pageSize" name="size" class="entries-select rounded form-control">
									<option value="3" ${donations.size == 3 ? 'selected' : ''}>3</option>
									<option value="4" ${donations.size == 4 ? 'selected' : ''}>4</option>
									<option value="5" ${donations.size == 5 ? 'selected' : ''}>5</option>
									<option value="10" ${donations.size == 10 ? 'selected' : ''}>10</option>
									<option value="15" ${donations.size == 15 ? 'selected' : ''}>15</option>
									<option value="20" ${donations.size == 20 ? 'selected' : ''}>20</option>
								</select>



							</div>
							<div class="search-box">
								<label for="searching-input">Tìm kiếm:</label> 
								<input type="text"
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


					<div class="m-content list" id="data-list">
						<table class="table table-striped table-content">
							<thead class="tb-head-title">
								<tr>

									<th scope="col" class="th-custom"><p>Tên đợt quyên góp</p></th>

									<th scope="col" class="th-custom"><p>Mã</p></th>
									<th scope="col" class="th-custom"><p>Ngày bắt đầu</p></th>
									<th scope="col" class="th-custom"><p>Ngày kết thúc</p></th>
									<th scope="col" class="th-custom"><p>Số điện thoại</p></th>
									
									<th scope="col" class="th-custom"><p>Hành động</p></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="tempDonation" items="${donations.content}">

									<c:url var="donateLink" value="/v1/donateForm">
										<c:param name="id" value="${tempDonation.id}" />
									</c:url>

									<c:url var="detailLink" value="${ViewConstants.E_DONATION_DETAIL}">
										<c:param name="id" value="${tempDonation.id}" />
									</c:url>



									<tr>
	
										<th scope="row" class="d-ns-cell">
											<p class="d-name">${FormatTest.formatTest(tempDonation.name)}</p>
											<p class="d-status">
															${JSPDataFormat.donationStatusFormat(tempDonation.status)}
											<%-- 
												<c:choose>
												    <c:when test="${tempDonation.status == 'NEW'}">Mới tạo</c:when>
												    <c:when test="${tempDonation.status == 'DONATING'}">Đang quyên góp</c:when>
												    <c:when test="${tempDonation.status == 'END'}">Đã kết thúc</c:when>
												    <c:when test="${tempDonation.status == 'CLOSED'}">Đã đóng</c:when>
												    <c:otherwise>
											            Không xác định
											         </c:otherwise>
												</c:choose>
											
											 --%>
											
											
											
											
											</p>
									
										</th>
										<td><p>${tempDonation.code}</p></td>
										<td><p>${tempDonation.startDate}</p></td>
										<td><p>${tempDonation.endDate}</p></td>
										<td><p>${tempDonation.phoneNumber}</p></td>
										
		
										
										<td class="action-c d-flex">
											
									 
											<div class="row col-12 row-custom">
												<button class="btn btn-success donation-btn" title="Chi tiết"
												onclick="window.location.href='${detailLink}'">
												<span class="content-btn-text">Chi tiết</span><span
													class="content-btn-icon"><i class="fa-solid fa-info"></i></span>
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
										
										
																		<%-- 
										<td class="d-flex">
											
									 
											<div class="row col-12 d-flex">
												<button class="" title="Chi tiết"
												onclick="window.location.href='${detailLink}'">
												<span class="content-btn-text">Chi tiết</span><span
													class="content-btn-icon"><i class="fa-solid fa-info"></i></span>
											</button> 
										
											 <c:if test="${tempDonation.status == 'DONATING'}">
        											<button class=""
														title="Quyên góp"
														onclick="toDonateForm(${tempDonation.id},$('#authorities').val())">
														<span class="content-btn-text">Quyên góp</span><span
															class="content-btn-icon"><i
															class="fa-solid fa-circle-dollar-to-slot"></i></span>
													</button>
    										</c:if>
											
											</div>

										</td>
										
										 --%>
										
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
	</section>
	<jsp:include page="../common/footer-layout2.jsp">
		<jsp:param name="includePart" value="footerSection" />
	</jsp:include>


	<script src="<c:url value='/static/common/assets/js/script.js' />"></script>

<%-- 
	<div>
		<input id="authorities" type="hidden" value="${authorities}" readonly>
	</div>

 --%>

	<div class="overlay-container">
		<div class="row">
			<div id="overlay" onclick="closeAllPopup()"></div>
			<div class="popup col-12 col-sm-8 col-md-4">
		
		
			<% Boolean isLogined = ((Boolean)request.getAttribute("isLogined")) != null ? (Boolean)request.getAttribute("isLogined") : false; %>
		<%-- 
			<% boolean isLogined = (Boolean)request.getAttribute("isLogined"); %>
			 --%>
			
		    <% if (isLogined) { %>
				<div class="form-container donate-form " id="donate">
					<div class="container form-head">
						<div class="form-title">
							<div class="d-flex justify-content-between">
								<p>Quyên góp:</p>
								<%-- 
                    <p>${donationCode}</p>
                     --%>

								<p>${donation.code}</p>
							</div>
							<%-- 
                <h4 class="d-inline-block mx-auto">${donationName}</h4>
 --%>
							<h4 class="d-inline-block mx-auto">${donation.name}</h4>
						</div>
					</div>
					<div class="container form-main">
						<form:form modelAttribute="userDonation" action="${process}"
							method="POST">
							<input type="hidden" name="donationId" value="${donation.id}" />
							<%-- 
                <input type="hidden" name="donationId" value="${donationId}" />
 --%>
							<div class="form-group form-group-custom">
								<label class="field-label" for="fullName">Họ và Tên:</label>
								<form:input type="text" class="form-control" id="fullName"
									path="name" />

							</div>
							<div class="form-group form-group-custom">
								<label class="field-label" for="donationAmount">Số Tiền
									Quyên Góp:</label>
								<form:input type="number" class="form-control"
									id="donationAmount" path="money" />
							</div>
							<div class="form-group form-group-custom">
								<label class="field-label" for="note">Ghi Chú:</label>
								<form:textarea class="form-control" id="note" path="note"
									rows="3"></form:textarea>
							</div>

							<!-- 
                <div class="f-field">

                    <div class="label-d">
                        <label class="field-label"> <span class="label-text">Họ
                                tên:</span>
                        </label>
                    </div>
                    <div class="input-d">
                        <div class="field-input">
                            <input type="text" class="input-p form-control"
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
                            <input type="number" class="input-p form-control"
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
                            <input type="text" class="input-p form-control"
                                path="note" /> 

                                <textarea class="input-p form-control"
                                path="note" rows="3"></textarea>
                        </div>

                    </div>

                </div> 
              -->


							<div class="submit-p">
								<button type="button" class="cancel-btn "
									onclick="closeAllPopup()">Hủy</button>
								<button type="submit" class="submit-btn">Quyên góp</button>
							</div>
						</form:form>
					</div>

				</div>


				<!-- 
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
		        
		         -->
		        
		    <% } else { %>
		        <div class="form-container loginWarning-form" id="donate">
		        	<div class="container form-head">
		        		<p>Bạn phải đăng nhập trước</p>
		        	</div>
					
					
					<div class="container form-main d-flex justify-content-end">
						<button type="button" class="btn btn-secondary cancel-btn "
                        onclick="closeAllPopup()">Hủy</button>
					
					</div>
					
					
				</div>
		    <% } %>
		
		
		

			

		</div>
		
		
		</div>
	
		
		
	</div>

	
	<input id="errorLoginOrRegister" type="hidden" value="${errorLoginOrRegister}"/>
	

	
	<c:import url="${ViewConstants.E_LOGIN}" />


	<script src="https://code.jquery.com/jquery-3.6.4.min.js"
		crossorigin="anonymous"></script>



	<script src="<c:url value='/static/common/assets/js/form.js' />"></script>
	
	<script src="<c:url value='/static/common/assets/js/script.js' />"></script>
	
	<script src="<c:url value='/static/common/assets/js/layout-script.js' />"></script>


	<script type="text/javascript">
	$(document).ready(function() {
	    // Kiểm tra nếu phần tử #errorLoginOrRegister có giá trị
	    if ($("#errorLoginOrRegister").val() === 'true') {
	        // Thay đổi class của phần tử #test123
	        $("#test123").removeClass("d-none").addClass("d-block");
	        
	        // Mở modal (đảm bảo bạn có định nghĩa openModal hoặc sử dụng một phương thức hợp lệ)
	        openModal('#login');
	        
	        console.log("test test : true");
	    } else {
	        // Thay đổi class của phần tử #test123
	        $("#test123").removeClass("d-block").addClass("d-none");
	        
	        console.log("test test : false");
	    }
	    
	    console.log("test test ssss: " + $("#errorLoginOrRegister").val());
	});
		
	
	
	</script>



</body>
</html>
