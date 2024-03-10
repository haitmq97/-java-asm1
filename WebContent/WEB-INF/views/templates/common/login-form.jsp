<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>


	<link rel="stylesheet"
	href="<c:url value='/static/common/assets/css/login-style.css' />">
</head>
<body>

	<div class="overlay-container">
		<div id="overlay" onclick="closeAllPopup()"></div>
		<div class="popup">
			<div class="form-container login-form" id="login">
				<div class="form-head">
					<div class="form-title">
						<h3>Đăng nhập</h3>
					</div>
				</div>
				<div class="form-main">
					<form:form  modelAttribute="user"
				action="${process}"
				method="POST">

						<div class="f-field">
							<div class="label-d">
								<label class="field-label"> <span class="label-text">Tài
										khoản:</span>
								</label>
							</div>
							<div class="input-d">
								<div class="field-input">
									<form:input type="text" class="input-p form-control" path="userName"/>
								</div>
								<div class=""></div>
							</div>
						</div>

						<div class="f-field">
							<div class="label-d">
								<label class="field-label"> <span class="label-text">Mật
										khẩu:</span>
								</label>
							</div>
							<div class="input-d">
								<div class="field-input">
									<form:input type="password" id="passwordInput"
										class="input-p form-control pw-field-custom-fix" path="password" />
									<button type="button" class="button no-b-btn toggle-pass-btn"
										onclick="togglePassword()" title="Hiện mật khẩu">
										<i id="eyeIcon" class="fa-regular fa-eye"></i>
									</button>
								</div>

							</div>

						</div>
						<div>
						<p>
					Don't have a account yet, <a
						href="${pageContext.request.contextPath}/user/registerForm">register
						now</a>
				</p>
						</div>
						
						<div class="submit-p">
							<button type="button" class="cancel-btn "
								onclick="closePopup('login')">Hủy</button>
							<button type="submit" class="submit-btn">Đăng nhập</button>
						</div>
					</form:form>
				</div>

			</div>
		</div>
	</div>

<script src="<c:url value='/static/common/assets/js/form.js' />"></script>
</body>
</html>