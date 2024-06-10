<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="me.haitmq.spring.mvc.crud.content_path.ViewConstants" %>
<%@ page import="me.haitmq.spring.mvc.crud.utils.JSPDataFormat" %>
<%@ page import="me.haitmq.spring.mvc.crud.utils.Time" %>
<%@ page import="me.haitmq.spring.mvc.crud.entity.status.DonationStatus" %>


<div class="form-container donate-form" id="user-donation-cancel">

	<div class="container form-head">

		<h3>
			Bạn có chắc chắn muốn hủy?
		</h3>
	</div>
	<div class="container form-main">
		<p class="font-weight-bold">
			Đợt quyên góp: <span class="font-weight-light">${userDonation.name}</span>
		</p>
		<p class="font-weight-bold">
			Mã quyên góp: <span class="font-weight-light">${userDonation.donation.code}</span>
		</p>
		
		<p class="font-weight-bold">
			Username: <span class="font-weight-light">${userDonation.user.userName}</span>
		</p>
		
		<p class="font-weight-bold">
			Số tiền: <span class="font-weight-light">${JSPDataFormat.moneyFormat(userDonation.money)}</span>
		</p>
		<c:url var="deleteUserDonation" value="/admin/deleteUserDonation">
			<c:param name="userDonationId" value="${userDonation.id}" />
		</c:url>

		<div class="submit-p">
			<button type="button" class="cancel-btn "
				onclick="closeAllModal()">Hủy</button>
			<button type="submit" class="submit-btn" id="confirm-delete-btn"
				onclick="window.location.href='${deleteUserDonation}'">Xác nhận</button>
		</div>

	</div>
</div>