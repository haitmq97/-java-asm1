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
			<div class="form-container donate-form" id="donate">
				<div class="form-head">
					<div class="form-title">
						<h3>Quyên góp</h3>
						<p>donation id: ${donationId}</p>
					</div>
				</div>
				<div class="form-main">
					<form:form  modelAttribute="donate"
				action="${process}"
				method="POST">
				<input type="hidden" name="donationId" value="${donationId}"/>
						<div class="f-field">
							<div class="label-d">
								<label class="field-label"> <span class="label-text">Họ tên:</span>
								</label>
							</div>
							<div class="input-d">
								<div class="field-input">
									<form:input type="text" class="input-p form-control" path="name"/>
								</div>
								<div class=""></div>
							</div>
						</div>

						<div class="f-field">
							<div class="label-d">
								<label class="field-label"> <span class="label-text">Số tiền quyên góp:</span>
								</label>
							</div>
							<div class="input-d">
								<div class="field-input">
									<form:input type="number" class="input-p form-control" path="money"/>
								</div>

							</div>

						</div>
						<div class="f-field">
							<div class="label-d">
								<label class="field-label"> <span class="label-text">Lời nhắn:</span>
								</label>
							</div>
							<div class="input-d">
								<div class="field-input">
									<form:input type="text" class="input-p form-control" path="note"/>
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
		</div>
	</div>



	<script src="https://code.jquery.com/jquery-3.6.4.min.js"
		crossorigin="anonymous"></script>

	
	
	<script src="<c:url value='/static/common/assets/js/form.js' />"></script>

</body>
</html>