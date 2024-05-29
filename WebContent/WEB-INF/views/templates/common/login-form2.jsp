<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>


<%-- 	<link rel="stylesheet"
	href="<c:url value='/static/common/assets/css/login-style.css' />"> --%>
</head>
<body>
	<input id="errorLogin" type="hidden" value="${errorLogin}"/>
	
	
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
		
					<form:form cssClass="row needs-validation"  modelAttribute="loginUser"
				action="${process}"
				method="POST">

							<div class="col-12">
								<label for="userNameOrEmail" class="form-label"><span class="label-text">Tài
										khoản:</span></label>
								<form:input id="userNameOrEmail" type="text" cssClass="form-control" path="userNameOrEmail"/>
								<div >
									<form:errors path="userNameOrEmail" cssClass="error"/>
								
								</div>
							</div>
							
							
							<div class="col-12">
								<label for="password" class="form-label"><span class="label-text">Mật
										khẩu:</span></label>
								<form:input id="password" type="password" cssClass="form-control" path="password"/>
								<div >
									<form:errors path="password" cssClass="error"/>
								
								</div>
							</div>

					
						
						<div id="global-error">
								 <form:errors path="*" cssClass="error" />
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
	
	<script type="text/javascript">
	
	</script>

<script src="<c:url value='/static/common/assets/js/script.js' />"></script>
<script src="<c:url value='/static/common/assets/js/layout-script.js' />"></script>

</body>
</html>