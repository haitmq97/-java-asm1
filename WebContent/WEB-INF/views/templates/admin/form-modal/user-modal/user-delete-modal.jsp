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

<div class="popup col-12 col-sm-8 col-md-6 col-lg-6">

	<div class="form-container donate-form" id="delete">
		<div class="container form-head">
			<h3>Bạn có chắc chắn xóa?</h3>
		</div>
		<div class="container form-main">
			<p>
				Email: <span>${user.email}</span>
			</p>
			<p>
				username: <span>${user.userName}</span>
			</p>
			<div class="submit-p">
				<button type="button" class="cancel-btn " onclick="closeAllPopup()">Hủy</button>
				<button type="submit" class="submit-btn" id="confirm-delete-btn"
					onclick="window.location.href='${pageContext.request.contextPath}/admin/deleteUser?id=${user.id}'">Xóa</button>
			</div>

		</div>
	</div>


</div>
