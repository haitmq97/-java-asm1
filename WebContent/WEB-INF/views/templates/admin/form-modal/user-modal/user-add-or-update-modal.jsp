<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="me.haitmq.spring.mvc.crud.content_path.ViewConstants"%>
<%@ page import="me.haitmq.spring.mvc.crud.utils.JSPDataFormat"%>
<%@ page import="me.haitmq.spring.mvc.crud.utils.Time"%>
<%@ page import="me.haitmq.spring.mvc.crud.entity.status.UserStatus"%>
<%@ page import="me.haitmq.spring.mvc.crud.entity.role.UserRole"%>


<div class="popup col-12 col-sm-8">
	<input type="hidden" id="errorProcess" value="${errorProcess}" />

	<div class="form-container donate-form " id="user-addOrUpdate">
		<div class="container form-head">
			<div class="form-title">
				<div class="d-flex justify-content-between">


					<c:choose>
						<c:when test="${user.id != 0}">
							<c:set var="output1" value="Cập nhật" />
						</c:when>
						<c:otherwise>
							<c:set var="output1" value="Thêm mới" />
						</c:otherwise>
					</c:choose>
					<h4 class="d-inline-block mx-auto">${output1}</h4>

				</div>

			</div>
		</div>
		<div class="container form-main">
			<form:form modelAttribute="user" action="${process}" method="POST">

				<form:input type="hidden" id="userId" path="id" />

				<div class="row">
					<div class="form-group form-group-custom col-12 col-md-6">
						<label class="field-label" for="fullName">Họ và tên:</label>
						<form:input type="text" class="form-control" id="fullName"
							path="fullName" />

						<c:if test="${not empty errors}">
							<p class="error-msg">${errors.getFieldError('fullName').defaultMessage}</p>

						</c:if>
					</div>

					<div class="form-group form-group-custom col-12 col-md-6">
						<label class="field-label" for="email">Email:</label>
						<form:input type="text" class="form-control" id="email"
							path="email" readonly="${user.id != 0 ? 'true' : 'false'}" />
						<c:if test="${not empty errors}">
							<p class="error-msg">${errors.getFieldError('email').defaultMessage}</p>
						</c:if>
					</div>

				</div>

				<div class="row">
					<div class="form-group form-group-custom col-12 col-md-6">
						<label class="field-label" for="phoneNumber">Số điện
							thoại:</label>
						<form:input type="number" class="form-control" id="phoneNumber"
							path="phoneNumber" />

						<c:if test="${not empty errors}">
							<p class="error-msg">${errors.getFieldError('phoneNumber').defaultMessage}</p>

						</c:if>
					</div>

					<div class="form-group form-group-custom col-12 col-md-6">
						<label class="field-label" for="address">Địa chỉ:</label>
						<form:input type="text" class="form-control" id="address"
							path="address" />
						<c:if test="${not empty errors}">
							<p class="error-msg">${errors.getFieldError('address').defaultMessage}</p>
						</c:if>
					</div>

				</div>

				<div class="row">
					<div class="form-group form-group-custom col-12 col-md-6">
						<label class="field-label" for="userName">Tài khoản:</label>
						<form:input type="text" class="form-control" id="userName"
							path="userName" readonly="${user.id != 0 ? 'true' : 'false'}" />

						<c:if test="${not empty errors}">
							<p class="error-msg">${errors.getFieldError('userName').defaultMessage}</p>

						</c:if>
					</div>


					<div class="form-group form-group-custom col-12 col-md-6">

						<c:choose>

							<c:when test="${user.id == 0}">

								<label class="field-label" for="password">Mật khẩu:</label>
								<form:input type="text" class="form-control" id="password"
									path="password" />
								<c:if test="${not empty errors}">
									<p class="error-msg">${errors.getFieldError('password').defaultMessage}</p>
								</c:if>

							</c:when>

							<c:otherwise>
								<form:input type="hidden" class="form-control" id="password"
									path="password" />
							</c:otherwise>
						</c:choose>

					</div>



				</div>

				<c:if test="${user.role.roleName != UserRole.ADMIN}">
					<div class="row">
						<div class="form-group form-group-custom col-12 col-md-6">
							<label class="field-label" for="role">Vai trò:</label>
							<form:select id="role" class="form-control" path="role.roleName">
								<form:option value="${UserRole.USER}">USER</form:option>
								<form:option value="${UserRole.ADMIN}">ADMIN</form:option>
							</form:select>
							<c:if test="${not empty errors}">
								<p class="error-msg">${errors.getFieldError('role').defaultMessage}</p>

							</c:if>
						</div>
					</div>
				</c:if>






				<div class="submit-p">
					<button type="button" class="cancel-btn " onclick="closeAllPopup()">Hủy</button>
					<button type="submit" class="submit-btn">${output1}</button>
				</div>
			</form:form>
		</div>

	</div>

</div>