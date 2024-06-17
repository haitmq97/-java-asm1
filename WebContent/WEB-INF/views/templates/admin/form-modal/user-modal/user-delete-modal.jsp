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

<div class="popup col-12 col-sm-8 col-md-6 col-lg-4">

	<div class="form-container donate-form" id="user-delete">
		<div class="container form-head">
			<h3 class="text-align-center">Xóa người dùng</h3>
		</div>
		<div class="container form-main">
			<h5>Bạn có chắc chắn xóa?</h5>
			<p class="mb-2">
				Email: <span class="font-weight-bold">${user.email}</span>
			</p>
			<p class="mb-2">
				username: <span class="font-weight-bold">${user.userName}</span>
			</p>
			<div class="submit-p">
				<button type="button" class="cancel-btn " onclick="closeAllPopup()">Hủy</button>
				<button type="submit" class="submit-btn" id="confirm-delete-btn"
					onclick="window.location.href='${pageContext.request.contextPath}/admin/deleteUser?id=${user.id}'">Xóa</button>
			</div>

		</div>
	</div>


</div>
