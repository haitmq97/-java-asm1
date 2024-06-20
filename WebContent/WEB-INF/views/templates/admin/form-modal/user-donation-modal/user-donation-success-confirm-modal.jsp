<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div class="popup col-12 col-sm-8 col-md-4">
	<input type="hidden" id="successUpdate" value="${successConfirm}">
	<div class="form-container donate-form" id="success-update">
		<div class="container form-head">
			<div class="form-title">
				<h4 class= "text-align-center">Xác nhận thành công</h4>
			</div>
		</div>
		<div class="container form-main">
			<p>Đã xác nhận quyên góp thành công</p>
			
			<div class="container form-main d-flex justify-content-end">
				<button type="button" class="btn btn-secondary cancel-btn "
					onclick="closeAllModal()">Đóng</button>
			</div>
		</div>

	</div>
</div>