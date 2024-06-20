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
	
	<div class="form-container donate-form " id="success-register">
		<div class="container form-head">
			<div class="form-title">
				<h3 class="fonf-weight-bold text-align-center">Tạo tài khoản thành công</h3>
			</div>
		</div>
		<div class="container form-main">
			
			<p class="text-align-center pb-2">
				Tài khoản của bạn đã được tạo
			</p>

			<div class="container form-main d-flex justify-content-end">
				<button type="button" class="btn btn-secondary cancel-btn "
					onclick="closeAllModal()">Đóng</button>
			</div>
		</div>

	</div>
</div>
