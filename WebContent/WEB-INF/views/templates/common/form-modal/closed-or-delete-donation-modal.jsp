<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="me.haitmq.spring.mvc.crud.content_path.ViewConstants"%>
<%@ page import="me.haitmq.spring.mvc.crud.utils.JSPDataFormat"%>
<%@ page import="me.haitmq.spring.mvc.crud.utils.Time"%>
<%@ page import="me.haitmq.spring.mvc.crud.entity.status.DonationStatus"%>
<div class="form-container donate-form" id="closedOrDelete">

	<c:choose>

		<c:when test="${donation.status == DonationStatus.NEW}">
			<c:set var="output1" value="xóa" />
			<c:url var="closedOrDeleteLink" value="/admin/updateDonationStatus">
				<c:param name="id" value="${donation.id}" />
				<c:param name="status" value="${DonationStatus.CLOSED}" />
			</c:url>
		</c:when>



		<c:otherwise>

			<c:set var="output1" value="đóng" />
			<c:url var="closedOrDeleteLink" value="/admin/deleteDonation">
				<c:param name="id" value="${donation.id}" />
			</c:url>


		</c:otherwise>
	</c:choose>


	<div class="container form-head">
		<h3>
			Bạn có chắc chắn <c:out value="${output1}" />?
		</h3>
	</div>
	<div class="container form-main">
		<p>
			Đợt quyên góp: <span>${donation.name}</span>
		</p>
		<p>
			Mã quyên góp: <span>${donation.code}</span>
		</p>
		<div class="submit-p">
			<button type="button" class="cancel-btn " onclick="closeAllModal()">Hủy</button>
			<button type="submit" class="submit-btn" id="confirm-delete-btn"
				onclick="window.location.href='${closedOrDeleteLink}'">Xác
				nhận</button>
		</div>

	</div>
</div>