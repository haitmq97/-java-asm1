<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="me.haitmq.spring.mvc.crud.content_path.ViewConstants"%>
<%@ page import="me.haitmq.spring.mvc.crud.utils.JSPDataFormat"%>
<%@ page import="me.haitmq.spring.mvc.crud.utils.Time"%>
<%@ page import="me.haitmq.spring.mvc.crud.entity.status.DonationStatus"%>
<%@ page import="me.haitmq.spring.mvc.crud.entity.status.UserStatus"%>
<%@ page import="me.haitmq.spring.mvc.crud.entity.status.UserDonationStatus"%>
<!doctype html>
<html lang="en">
<head>
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
	<jsp:include page="../common/header-layout-test.jsp">
		<jsp:param name="includePart" value="headerSection" />
	</jsp:include>


	<%-- <section class="site-section">
            <div class="container">
              <div class="row">
                <div class="col-lg-6">
                    <div class="bg-light p-3 border rounded mb-4">
                      <h3 class="text-primary  mt-3 h5 pl-3 mb-3 ">Thông tin</h3>
                      <ul class="list-unstyled pl-3 mb-0">
                        <li class="mb-2"><strong class="text-black">Tài khoản:</strong> <span>${user.userName}</span></li>
                        <li class="mb-2"><strong class="text-black">Vai trò:</strong> <span>${user.role.roleName}</span></li>
                        <li class="mb-2"><strong class="text-black">Họ và tên:</strong> <span>${user.fullName}</span> </li>
                        <li class="mb-2"><strong class="text-black">Email:</strong> <span>${user.email}</span></li>
                        <li class="mb-2"><strong class="text-black">Số điện thoại:</strong> <span>${user.phoneNumber}</span></li>
                        <li class="mb-2"><strong class="text-black">Địa chỉ:</strong> <span>${user.address}</span></li>
                        <li class="mb-2"><strong class="text-black">Ngày lập:</strong> <span>${user.createdDate}</span></li>
                        <li class="mb-2"><strong class="text-black">Số lần quyên góp thành công: </strong> <span></span></li>
                        <li class="mb-2"><strong class="text-black">Trạng thái:</strong> <span>${JSPDataFormat.userStatusFormat(user.status)}</span>
                          <!-- <span >Mới tạo</span>
                          <span >Đang quyên góp</span>
                          <span>Kết thúc quyên góp</span>
                          <span>Đóng quyên góp</span> -->
                        </li>
          
                      </ul>
                    </div>
          			<div class="bg-light p-3 border rounded">
							<button type="button" 
							style="color: white" 
							data-toggle="modal" data-target="#exampleModal" 
							class="btn btn-block btn-primary btn-md"
							 onclick="openModal('#userUpdate')">Cập nhật</button>
					</div>
                    
          
                  </div>
                <div class="col-lg-6">
                  
                  <div class="pt-5">
                    <h4 class="mb-5"> Danh sách các lần quyên góp</h4>
        <!--            <h3 class="mb-5" th:text="+  donation"> </h3>-->
        			<ul>
        				<c:forEach var="tempUserDonation" items="${userDonations}">
                		<li>
                			<div>
                				<p>${tempUserDonation.donation.code}</p>
                				<p>${tempUserDonation.createdDate}</p>
                				<p>${tempUserDonation.money}</p>
                				<p>${tempUserDonation.status}</p>
                			
                			</div>
                		</li>
						                	
                	
                	
                		</c:forEach>
        			
        			
        			
        			</ul>
                	
                  </div>
                </div>
                
              </div>
            </div>
            <!-- Button trigger modal -->
        
        
            
          </section> --%>
	
	<section class="site-section content">
		<div class="container">
			<div class="row mb-5 justify-content-center">
				<div class="col-md-7 text-center">
					<h2 class="section-title mb-2">Chi tiết cá nhân</h2>
					
					

				</div>
				

			</div>


			<div class="donation-detail-containter">
				<div class="row">
					<!-- Row 1 -->
					<div class="col-md-6">
						<div class="p-3">
							<div class="">
							
								<p class="font-weight-bold mb-1 text-align-left">Họ và tên:</p>
								
							</div>
							<div class="border rounded bg-light">
							
								<p class="p-1 m-1">${currentUser.fullName}</p>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="p-3">
							<div class="">
								<p class="font-weight-bold mb-1 text-align-left">Username:</p>
							</div>
							<div class="border rounded bg-light">
								<p class="p-1 m-1">${currentUser.userName}</p>
							</div>
						
						</div>
					</div>
				</div>
				
				<div class="row">
					<!-- Row 2 -->
					<div class="col-md-6">
						<div class="p-3">
							<div class="">
								<p class="font-weight-bold mb-1 text-align-left">Email:</p>
							</div>
							<div class="border rounded bg-light">
								<p class="p-1 m-1">${currentUser.email}</p>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="p-3">
							<div class="">
								<p class="font-weight-bold mb-1 text-align-left">Số điện thoại:</p>
							</div>
							<div class="border rounded bg-light">
								<p class="p-1 m-1">${currentUser.phoneNumber}</p>
							</div>
						
						</div>
					</div>
				</div>
				<div class="row">
					<!-- Row 2 -->
					<div class="col-md-6">
						<div class="p-3">
							<div class="">
								<p class="font-weight-bold mb-1 text-align-left">Địa chỉ:</p>
							</div>
							<div class="border rounded bg-light">
								<p class="p-1 m-1">${currentUser.address}</p>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="p-3">
							<div class="">
								<p class="font-weight-bold mb-1 text-align-left">Ngày tạo:</p>
							</div>
							<div class="border rounded bg-light">
								<p class="p-1 m-1">${currentUser.createdDate}</p>
							</div>
						
						</div>
					</div>
				</div>
				
				<div class="row">
					<!-- Row 2 -->
					<div class="col-md-6">
						<div class="p-3">
							<div class="">
								<p class="font-weight-bold mb-1 text-align-left">Vai trò:</p>
							</div>
							<div class="border rounded bg-light">
								<p class="p-1 m-1">${currentUser.role.roleName}</p>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="p-3">
							<div class="">
								<p class="font-weight-bold mb-1 text-align-left">Trạng thái:</p>
							</div>
							<div class="border rounded bg-light">
								<p class="p-1 m-1">${JSPDataFormat.userStatusFormat(currentUser.status)}</p>
							</div>
						
						</div>
					</div>
				</div>
				<div class="row">
					<!-- Row 5 with full width -->
					<div class="col-md-12">
						<div class="p-3 border bg-light">
							<div class="">
								<p class="font-weight-bold mb-1 text-align-left">Số lần quyên góp thành công:</p>
							</div>
							<div class="border rounded bg-light">
								<p class="p-1 m-1"></p>
							</div>
						
						</div>
					</div>
				</div>
			</div>
			
			<div class= "row mt-5">
				<div class="col-12">
					
							<div>
								<h3 class="text-align-center">Danh sách các lượt quyên góp của người dùng</h3>
							</div>

					<c:choose>

						<c:when test="${userDonations.totalElements == 0}">
							<p class="mt-4 text-align-center font-italic">Hiện bạn này chưa quyên góp</p>
						</c:when>



						<c:otherwise>
							<div class="d-flex justify-content-between flex-wrap mb-3">
								<div class="page-selector">
								
										
									<label for="size">Rows per
										page:</label> 
									<select id="pageSize" name="size"
										class="entries-select rounded form-control">
										<option value="3" ${userDonations.size == 3 ? 'selected' : ''}>3</option>
										<option value="4" ${userDonations.size == 4 ? 'selected' : ''}>4</option>
										<option value="5" ${userDonations.size == 5 ? 'selected' : ''}>5</option>
										<option value="10" ${userDonations.size == 10 ? 'selected' : ''}>10</option>
										<option value="15" ${userDonations.size == 15 ? 'selected' : ''}>15</option>
										<option value="20" ${userDonations.size == 20 ? 'selected' : ''}>20</option>

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
							<div>
							<div class="" id="data-list">
						<div class=" table-responsive">
							<table class="table table-striped table-content">
								<thead class="tb-head-title bg-secondary">
									<tr>


										<th scope="col" class="th-custom col-2 col-lg-3"><p>Tên đợt</p></th>
										<th scope="col" class="th-custom"><p>Mã đợt</p></th>
										<th scope="col" class="th-custom"><p>Trạng thái đợt</p></th>
										<th scope="col" class="th-custom col-2"><p>Số tiền</p></th>
										<th scope="col" class="th-custom"><p>Ngày quyên góp</p></th>
										<th scope="col" class="th-custom col-1 col-lg-2"><p>Trạng thái</p></th>
									
									</tr>
								</thead>



								<tbody id="scrollableRows">
									<c:forEach var="tempUserDonation" items="${userDonations.content}">

										
										<c:url var="confirmLink"
											value="/admin/update_user_donations">
											<c:param name="id" value="${tempUserDonation.id}" />
											<c:param name="status" value="${UserDonationStatus.CONFIRMED}" />
										</c:url>
										
										<c:url var="cancelLink"
											value="/admin/update_user_donations">
											<c:param name="id" value="${tempUserDonation.id}" />
											<c:param name="status" value="${UserDonationStatus.CANCELED}" />
										</c:url>



													<tr>
													<td><p class="font-weight-bold">${tempUserDonation.id}</p></td>
													<td><p class="font-weight-bold">${tempUserDonation.name}</p></td>
														<td><p class="font-weight-bold">${tempUserDonation.donation.name}</p></td>
														<td><p class="font-weight-bold">${tempUserDonation.donation.code}</p></td>
														<td><p class="font-weight-bold color-text">${JSPDataFormat.donationStatusFormat(tempUserDonation.donation.status)}</p></td>
														<td><p>${JSPDataFormat.moneyFormat(tempUserDonation.money)}</p></td>
														<td><p>${tempUserDonation.createdDate}</p></td>

														<td><p class="font-weight-bold color-text">${JSPDataFormat.userDonationStatusFormat(tempUserDonation.status)}</p></td>

												
													</tr>




												</c:forEach>


								</tbody>
							</table>
						</div>
						<div>
							<c:if test="${userDonations.totalElements != 0}">
									<p class="font-weight-light font-italic">Showing ${userDonations.number*userDonations.size +1} to
										${userDonations.number*userDonations.size +userDonations.numberOfElements}
										of ${userDonations.totalElements} entries</p>
								</c:if>

								<c:if test="${donations.totalElements == 0}">
									<p>There are no entries to show</p>
								</c:if>
						</div>

						<div>
							<div>
								<input id="totalPages" type="hidden"
										value="${userDonations.totalPages}" />
										<input id="currentPage" type="hidden" name="currentPage"
										value="${userDonations.pageable.pageNumber+1}" />

							</div>
							<div id="pagination-container"></div>


						</div>

					</div>
						
						
						
						</div>
							
						</c:otherwise>
					</c:choose>

				
				</div>

						
					</div>


				</div>


	</section>


	<jsp:include page="../common/footer-layout2.jsp">
		<jsp:param name="includePart" value="footerSection" />
	</jsp:include>
	
	
	<div class="overlay-container">
		<div class="row">
			<div id="overlay" onclick="closeAllModal()"></div>
			<div class="popup col-12 col-sm-8 col-md-4">
		
		<div class="form-container donate-form " id="userUpdate">
			        <div class="container form-head">
						<div class="form-title">
							<div class="d-flex justify-content-between">

								<h4 class="d-inline-block mx-auto">Cập nhật</h4>

							</div>

						</div>
					</div>
			        <div class="container form-main">
			            <form:form modelAttribute="user" action="${process}"
			                method="POST">
			  		
			 				<form:input type="hidden"  id="donationId" path="id" />
			 				
			                <div class="form-group form-group-custom">
			                  <label class="field-label" for="fullName">Họ và tên</label>
			                  <form:input type="text" class="form-control" id="fullName" path="fullName" />
			                  
			                </div>
			                <div class="form-group form-group-custom">
			                  <label class="field-label" for="email">Email:</label>
			                  <form:input type="text" class="form-control" id="email" path="email" readonly="${user.id != 0 ? 'true' : 'false'}"/>
			                </div>
			                
			                <div class="form-group form-group-custom">
			                  <label class="field-label" for="phoneNumber">Số điện thoại:</label>
			                  <form:input type="text" class="form-control" id="phoneNumber" path="phoneNumber" />
			                </div>
			                <div class="form-group form-group-custom">
			                  <label class="field-label" for="address">Địa chỉ:</label>
			                  <form:input type="text" class="form-control" id="address" path="address" />
			                </div>
			                <div class="form-group form-group-custom">
			                  <label class="field-label" for="userName">Tài khoản:</label>
			                  <form:input type="text" class="form-control" id="userName" path="userName" readonly="${user.id != 0 ? 'true' : 'false'}"/>
			                </div>
			                
			                <c:if test="${user.id == 0}">
			                	<div class="form-group form-group-custom">
			                  		<label class="field-label" for="password">Mật khẩu:</label>
			                  		<form:input type="text" class="form-control" id="password" path="password" />
			                	</div>
			                </c:if>
							
							
			                <div class="submit-p">
			                    <button type="button" class="cancel-btn "
			                        onclick="closeAllPopup()">Hủy</button>
			                    <button type="submit" class="submit-btn">
			                    	<c:choose>
										<c:when test="${user.id != 0}">
											Cập nhật
										</c:when>
										<c:otherwise>
											Thêm mới
										</c:otherwise>
			                    	</c:choose>
			                    
			                    </button>
			                </div>
			            </form:form>
			        </div>

    		</div>
		
			
		        <%-- <div class="form-container donate-form " id="userUpdate">
        <div class="container form-head">
            <div class="form-title">
                <div class="d-flex justify-content-between">
                    <p>Cập nhật</p>
                    
                </div>

            </div>
        </div>
        <div class="container form-main">
        

			
            <form:form modelAttribute="user" action="${process}"
                method="POST">
							

				<div><form:input type="hidden" path="id" value="${user.id}"/></div>
     
                <div class="form-group form-group-custom">
                  <label class="field-label" for="fullName">Họ và Tên:</label>
                  <form:input type="text" class="form-control" id="fullName" path="fullName" />
                  
                </div>
                <div class="form-group form-group-custom">
                  <label class="field-label" for="email">Email:</label>
                  <form:input type="text" class="form-control" id="email" path="email" />
                </div>
             	<div class="form-group form-group-custom">
                  <label class="field-label" for="phoneNumber">Số điện thoại:</label>
                  <form:input type="text" class="form-control" id="phoneNumber" path="phoneNumber" />
                </div>
                <div class="form-group form-group-custom">
                  <label class="field-label" for="address">Địa chỉ:</label>
                  <form:input type="text" class="form-control" id="address" path="address" />
                </div>
				<div class="form-group form-group-custom">
                  <label class="field-label" for="userName">Username:</label>
                  <form:input type="text" class="form-control" id="userName" path="userName" />
                </div>

                <div class="submit-p">
                    <button type="button" class="cancel-btn "
                        onclick="closeAllPopup()">Hủy</button>
                    <button type="submit" class="submit-btn">Cập nhật</button>
                </div>
            </form:form>

              
            
        </div>

    </div> --%>
		        
		
			

			

		</div>
		
		
		</div>
	
		
		
	</div>
	
	

</body>
</html>