<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="me.haitmq.spring.mvc.crud.content_path.ViewConstants"%>
<%@ page import="me.haitmq.spring.mvc.crud.utils.JSPDataFormat"%>
<%@ page import="me.haitmq.spring.mvc.crud.utils.Time"%>
<%@ page import="me.haitmq.spring.mvc.crud.entity.status.UserDonationStatus"%>

<div class="popup col-12 col-sm-8 col-md-6">
	

	<div class="form-container donate-form" id="userDonation-cancel">
	
		<c:url var="cancelStatusLink" value="/admin/update_user_donations">
		<c:param name="id" value="${userDonation.id}" />
		<c:param name="status" value="${UserDonationStatus.CANCELED}" />
	</c:url>
		<div class="container form-head">
			<h3 class="text-align-center">Hủy quyên góp</h3>
		</div>
		<div class="container form-main">
			<h5>Bạn có chắc chắn muốn hủy quyên góp này?</h5>
			<p class="mb-2">
				Tên người dùng: <span class="font-weight-bold">${userDonation.user.userName}</span>
			</p>
			<p class="mb-2">
				Mã đợt: <span class="font-weight-bold">${userDonation.donation.code}</span>
			</p>
			<p class="mb-2">
				Số tiền: <span class="font-weight-bold">${userDonation.money}</span>
			</p>
			<div class="submit-p">
				<button type="button" class="cancel-btn " onclick="closeAllModal()">Đóng</button>
				<button type="submit" class="submit-btn" id="confirm-delete-btn"
					onclick="window.location.href='${cancelStatusLink}'">Xác nhận</button>
			</div>

		</div>
	</div>


</div>
