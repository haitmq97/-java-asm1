<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="me.haitmq.spring.mvc.crud.content_path.ViewConstants"%>
<%@ page import="me.haitmq.spring.mvc.crud.utils.JSPDataFormat"%>
<%@ page import="me.haitmq.spring.mvc.crud.utils.Time"%>
<%@ page import="me.haitmq.spring.mvc.crud.entity.status.DonationStatus"%>


<div class="popup col-12 col-sm-8 col-md-6 col-lg-4">
	<input type="hidden" id="errorProcess" value="${errorProcess}" />
	<div class="form-container donate-form " id="donate">
		<div class="container form-head">
			<div class="form-title">
				<div class="d-flex justify-content-between">
					<p>Quyên góp:</p>

					<p class="font-weight-bold">${donation.code}</p>
				</div>
				<h4 class="d-inline-block mx-auto">${donation.name}</h4>
			</div>
		</div>
		<div class="container form-main">
			<form:form modelAttribute="userDonation" action="${processDonating}"
				method="POST">
		
				<div class="form-group form-group-custom">
					<label class="field-label" for="fullName">Họ và Tên:</label>
					<form:input type="text" class="form-control" id="fullName"
						path="name" />
					<form:errors cssClass="error-msg" path="name" />
				</div>
				<div class="form-group form-group-custom">
					<label class="field-label" for="donationAmount">Số Tiền
						Quyên Góp:</label>
					<form:input type="number" class="form-control" id="donationAmount"
						path="money" />
						<form:errors cssClass="error-msg" path="money" />
				</div>
				<div class="form-group form-group-custom">
					<label class="field-label" for="note">Ghi Chú:</label>
					<form:textarea class="form-control" id="note" path="note" rows="3"></form:textarea>
				</div>
				
				<form:input type="hidden" path="donationId" value="${donation.id}" />
				

				<div class="submit-p">
					<button type="button" class="cancel-btn " onclick="closeAllModal()">Hủy</button>
					<button type="submit" class="submit-btn">Quyên góp</button>
				</div>
			</form:form>
		</div>

	</div>

</div>
