<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>


</head>
<body>
	<input id="errorLogin" type="hidden" value="${errorLogin}" />


	<div class="overlay-container">
		<div id="overlay" onclick="closeAllModal()"></div>
		<div class="popup">

			<div class="form-container login-form" id="login">
				<div class="form-head">
					<div class="form-title">
						<h3>Đăng nhập</h3>
					</div>
				</div>
				<div class="form-main">
					<form:form modelAttribute="loginUser" action="${process}"
						method="POST">

						<div class="f-field">
						
							<div>
								<label class="field-label"> <span class="label-text font-weight-bold">Tài
										khoản:</span>
								</label>

								<form:input id="userNameOrEmail" type="text"
									class="input-p form-control" path="userNameOrEmail" />


							</div>

							<div class="error-msg">
								<form:errors path="userNameOrEmail" cssClass="error" />
							</div>
						</div>

						<div class="f-field">

							<div>
								<label class="field-label"> <span class="label-text font-weight-bold">Mật
										khẩu:</span>
								</label>
								<div class="password-input position-relative">

									<form:input type="password" id="password"
										class="input-p form-control pw-field-custom-fix"
										path="password" />

									<i id="eyeIcon" class="fa-regular fa-eye toggle-pass-icon"
										onclick="togglePassword()"></i>

								</div>


							</div>



							<div class="error-msg">
								<form:errors path="password" cssClass="error" />
							</div>

							<div id="global-error" class="error-msg">
								<form:errors path="*" cssClass="error" />
							</div>
							<div></div>
						</div>
						<div class="mt-4">
							<p>
								Don't have an account yet, <a
									href="${pageContext.request.contextPath}/user/registerForm">register
									now</a>
							</p>
						</div>

						<div class="submit-p">
							<button type="button" class="cancel-btn "
								onclick="closeModal('#login')">Hủy</button>
							<button type="submit" class="submit-btn">Đăng nhập</button>
						</div>
					</form:form>
				</div>

			</div>

		</div>
	</div>

	<script type="text/javascript">
		
	</script>

	<script src="<c:url value='/static/common/assets/js/script.js' />"></script>
	<script
		src="<c:url value='/static/common/assets/js/layout-script.js' />"></script>

</body>
</html>