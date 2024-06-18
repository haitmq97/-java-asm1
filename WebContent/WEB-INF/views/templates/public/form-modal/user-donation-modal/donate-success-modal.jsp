<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="me.haitmq.spring.mvc.crud.utils.DonationStatusMapper"%>
<%@ page import="me.haitmq.spring.mvc.crud.utils.FormatTest"%>
<%@ page import="me.haitmq.spring.mvc.crud.utils.JSPDataFormat"%>
<%@ page import="me.haitmq.spring.mvc.crud.content_path.ViewConstants"%>

<div class="popup col-12 col-sm-8 col-md-6 col-lg-4">
	<input type="hidden" id="successDonate" value="${successDonate}" />
	<div class="form-container donate-form " id="success-donate">
		<div class="container form-head">
			<div class="form-title">
				<h3 class="fonf-weight-bold text-align-center">Quyên góp thành công</h3>
			</div>
		</div>
		<div class="container form-main">
			<p class="text-align-center font-weight-bold">
				<i class="fa-solid fa-heart" style="font-size: 24px; color: red;"></i>Cám
				ơn bạn đã ủng hộ<i class="fa-solid fa-heart"
					style="font-size: 24px; color: red;"></i>
			</p>
			<p class="text-align-center ">
				Vui lòng chờ <span class="font-weight-bold">Quản trị viên</span> xác
				nhận
			</p>
			<div class="container form-main d-flex justify-content-end">
				<button type="button" class="btn btn-secondary cancel-btn "
					onclick="closeAllModal()">Đóng</button>
			</div>
		</div>

	</div>
</div>
