<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<input type="hidden" id="successDonate" value="${successDonate}">
<div class="form-container donate-form " id="success-donate">
	<div class="container form-head">
		<div class="form-title">
			<p>quyên góp thành công. Vui lòng chờ Quản trị viên xác nhận</p>
		</div>
	</div>
	<div class="container form-main">
		<div class="container form-main d-flex justify-content-end">
			<button type="button" class="btn btn-secondary cancel-btn "
				onclick="closeAllModal()">Đóng</button>
		</div>
	</div>

</div>