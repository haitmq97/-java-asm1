<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="me.haitmq.spring.mvc.crud.utils.JSPDataFormat" %>
<!doctype html>
<html lang="en">
<head>
    <title>Donation website &mdash; Website Donation</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
<link rel="stylesheet"
	href="<c:url value='/static/common/assets/css/header-style.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/static/common/assets/css/donation-table-home-style.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/static/common/assets/css/footer-style.css' />">
	


 <!-- customer style -->
 <link rel="stylesheet"
	href="<c:url value='/static/common/assets/css/style.css'/>" />
	
<link rel="stylesheet"
	href="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css'/>"
	integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />




<!-- customer js -->	

<script src="<c:url value='https://code.jquery.com/jquery-3.6.4.min.js'/>"> </script>

<script src="<c:url value='/static/common/assets/js/script.js' />"></script>
<script src="<c:url value='/static/common/assets/js/layout-script.js' />"></script>
</head>
<body id="top">
<jsp:include page="../common/header-layout-test.jsp">
		<jsp:param name="includePart" value="headerSection" />
	</jsp:include>


<section class="site-section">
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
        
        
            
          </section>

	<jsp:include page="../common/footer-layout2.jsp">
		<jsp:param name="includePart" value="footerSection" />
	</jsp:include>
	
	
	<div class="overlay-container">
		<div class="row">
			<div id="overlay" onclick="closeAllPopup()"></div>
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