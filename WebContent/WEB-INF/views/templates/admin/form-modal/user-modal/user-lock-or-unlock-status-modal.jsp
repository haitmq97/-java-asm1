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

	<div class="form-container donate-form" id="user-lockOrUnlock">
	<c:choose>

			<c:when test="${user.status == UserStatus.ACTIVE}">
				<c:set var="output1" value="khóa" />
				<c:url var="changeStatusLink" value="/admin/updateUserStatus">
					<c:param name="id" value="${user.id}" />
					<c:param name="status" value="${UserStatus.LOCKED}" />
				</c:url>
			</c:when>

			<c:when test="${user.status == UserStatus.LOCKED}">
				<c:set var="output1" value="hủy khóa" />
				<c:url var="changeStatusLink" value="/admin/updateUserStatus">
					<c:param name="id" value="${user.id}" />
					<c:param name="status" value="${UserStatus.ACTIVE}" />
				</c:url>
			</c:when>

		</c:choose>
	
		<div class="container form-head">
			<h3  class="text-align-center"><c:out value="${output1}" /> người dùng</h3>
		</div>
		<div class="container form-main">
			<h5>
				Bạn có chắc chắn
				<c:out value="${output1}" />?
			</h5>
			<p class="mb-2">
				Email: <span class="font-weight-bold">${user.email}</span>
			</p >
			<p class="mb-2">
				username: <span class="font-weight-bold">${user.userName}</span>
			</p>
			<div class="submit-p">
				<button type="button" class="cancel-btn " onclick="closeAllModal()">Hủy</button>
				<button type="submit" class="submit-btn btn-danger" id="confirm-delete-btn"
					onclick="window.location.href='${changeStatusLink}'">Xác
					nhận</button>
			</div>

		</div>
	</div>


</div>
