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

 <main class="main container">
        <div class="register-form form-container">
          <div class="form-head">
            <div class="form-title"><h3>Đăng ký</h3></div>
            <div class="form-head-decription"><p>Nhanh chóng, tiện lợi</p></div>
          </div>
          <div class="form-main">
            <form:form action="${process}" method="POST" modelAttribute="user">
              <div class="f-row">
                <div class="f-field">
                  <div class="label-d">
                    <label class="field-label">
                      <span class="label-text">Họ và tên:</span>
                    </label>
                  </div>
                  <div class="input-d">
                    <div class="field-input">
                      <form:input type="text" class="input-p form-control" path="fullName" />
                    </div>
                    <div class=""></div>
                  </div>
                </div>

                <div class="f-field">
                  <div class="label-d">
                    <label class="field-label">
                      <span class="label-text">Email:</span>
                    </label>
                  </div>
                  <div class="input-d">
                    <div class="field-input">
                      <form:input type="email" class="input-p form-control" path="email" />
                      
                      <form:errors path="email" />
                    </div>
                    <div class=""></div>
                  </div>
                </div>
              </div>

              <div class="f-row">
                <div class="f-field">
                  <div class="label-d">
                    <label class="field-label">
                      <span class="label-text">Số điện thoại (tùy chọn):</span>
                      
                    </label>
                  </div>
                  <div class="input-d">
                    <div class="field-input">
                      <form:input type="number" class="input-p form-control" path="phoneNumber"/>
                      <form:errors path="phoneNumber" />
                    </div>
                    <div class=""></div>
                  </div>
                </div>

                <div class="f-field">
                  <div class="label-d">
                    <label class="field-label">
                      <span class="label-text">Địa chỉ (tùy chọn):</span>
                    </label>
                  </div>
                  <div class="input-d">
                    <div class="field-input">
                      <form:input type="text" class="input-p form-control" path="address"/>
                    </div>
                    <div class=""></div>
                  </div>
                </div>
              </div>

              <div class="f-row">
                <div class="f-field">
                  <div class="label-d">
                    <label class="field-label">
                      <span class="label-text">Tài khoản:</span>
                    </label>
                  </div>
                  <div class="input-d">
                    <div class="field-input">
                      <form:input type="text" class="input-p form-control" path="userName" autocomplete="off"/>
                      
                      <form:errors path="userName" />
                    </div>
                    <div class=""></div>
                  </div>
                </div>

                <div class="f-field">
                  <div class="label-d">
                    <label class="field-label">
                      <span class="label-text">Mật khẩu:</span>
                    </label>
                  </div>
                  <div class="input-d">
                    <div class="field-input">
                      <form:input type="password" id="passwordInput" class="input-p form-control pw-field-custom-fix" path="password" autocomplete="new-password"/>
                      <button type="button" class="button no-b-btn toggle-pass-btn" onclick="togglePassword()" title="Hiện mật khẩu">
                        <i id="eyeIcon" class="fa-regular fa-eye"></i>
                      </button>
                      
                      <form:errors path="password" />
                    </div>

                 
                  </div>
                </div>
              </div>
              <div class="d-flex justify-content-center align-items-center">
							<p class="term-in" style="display: inline;">
								Bằng cách nhấp vào Đăng ký, bạn đồng ý với <a href="">Điều
									khoản, Chính sách quyền riêng tư và Chính sách cookie</a> của chúng
								tôi.
							</p>

						</div>

              <div class="submit-p">
                <button type="button" class="cancel-btn " onclick="window.location.href='${pageContext.request.contextPath}/v1/home'">Hủy</button>
                <button type="submit" class="submit-btn">Đăng ký</button>
              </div>
              <div class="login-p"></div>
              <div class="term-p"></div>
            </form:form>
          </div>
          <div class="form-foot"></div>
        </div>
        <div></div>
      </main>
<script src="<c:url value='/static/common/assets/js/form.js' />"></script>


</body>
</html>