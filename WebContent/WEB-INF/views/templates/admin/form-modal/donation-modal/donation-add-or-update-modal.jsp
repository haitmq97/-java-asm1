<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="me.haitmq.spring.mvc.crud.content_path.ViewConstants"%>
<%@ page import="me.haitmq.spring.mvc.crud.utils.JSPDataFormat"%>
<%@ page import="me.haitmq.spring.mvc.crud.utils.Time"%>
<%@ page import="me.haitmq.spring.mvc.crud.entity.status.DonationStatus"%>

<div class="popup col-12 col-sm-8">
	<input type="hidden" id="errorProcess" value="${errorProcess}" />

	<div class="form-container donate-form " id="donation-addOrUpdate">
		<div class="container form-head">
			<div class="form-title">
				<div class="d-flex justify-content-between">


					<c:choose>
						<c:when test="${donation.id != 0}">
							<c:set var="output1" value="Cập nhật" />
						</c:when>
						<c:otherwise>
							<c:set var="output1" value="Thêm mới" />
						</c:otherwise>
					</c:choose>
					<h4 class="d-inline-block mx-auto">${output1}</h4>
				</div>

			</div>
		</div>
		<div class="container form-main">
			<form:form modelAttribute="donation" action="${process}"
				method="POST">

				<form:input type="hidden" id="donationId" path="id" />
				
				<div class="row">
					<div class="form-group form-group-custom col-12 col-md-6">
						<label class="field-label" for="code-add">Mã đợt quyên góp</label>
						<form:input type="text" class="form-control" id="code-add"
							path="code" readonly="${donation.id != 0 ? 'true' : 'false'}" />

						<c:if test="${not empty errors}">
							<p class="error-msg">${errors.getFieldError('code').defaultMessage}</p>


						</c:if>
					</div>

					<div class="form-group form-group-custom col-12 col-md-6">
						<label class="field-label" for="name-add">Tên đợt quyên
							góp:</label>
						<form:input type="text" class="form-control" id="name-add"
							path="name" />
						<c:if test="${not empty errors}">
							<p class="error-msg">${errors.getFieldError('name').defaultMessage}</p>
						</c:if>
					</div>

				</div>
				<div class="row">
					<div class="form-group form-group-custom col-12 col-md-6">
						<label class="field-label" for="startDate-add">Ngày bắt
							đầu:</label>
						<form:input type="date" class="form-control" id="startDate-add"
							path="startDate"
							readonly="${(donation.status == DonationStatus.DONATING) 
							|| (donation.status == DonationStatus.END)
							|| (donation.status == DonationStatus.CLOSED) ? 'true' : 'false'}" />
						<c:if
							test="${(not empty errors) && ((donation.id==0) || (donation.status == DonationStatus.NEW))}">
							<p class="error-msg">${errors.getFieldError('startDate').defaultMessage}</p>
						</c:if>
					</div>
					<div class="form-group form-group-custom col-12 col-md-6">
						<label class="field-label" for="endDate-add">Ngày kết
							thúc:</label>
						<form:input type="date" class="form-control" id="endDate-add"
							path="endDate" />
						<c:if test="${not empty errors}">
							<p class="error-msg">${errors.getFieldError('endDate').defaultMessage}</p>
						</c:if>
					</div>

				</div>

				<div class="row">
					<div class="form-group form-group-custom col-12 col-md-6">
						<label class="field-label" for="organization-add">Tổ chức:</label>
						<form:input type="text" class="form-control" id="organization-add"
							path="organization" />
						<c:if test="${not empty errors}">
							<p class="error-msg">${errors.getFieldError('organization').defaultMessage}</p>
						</c:if>
					</div>

					<div class="form-group form-group-custom col-12 col-md-6">
						<label class="field-label" for="phoneNumber-add">Số điện
							thoại:</label>
						<form:input type="number" class="form-control"
							id="phoneNumber-add" path="phoneNumber" />
						<c:if test="${not empty errors}">
							<p class="error-msg">${errors.getFieldError('phoneNumber').defaultMessage}</p>
						</c:if>
					</div>


				</div>

				<div class="row">
					<div class="form-group form-group-custom col-12 mb-0">
						<label class="field-label" for="description-add">Nội dung:</label>

						<form:textarea class="form-control" id="description-add"
							path="description" rows="3"></form:textarea>
					</div>

				</div>
				<form:input type="hidden" class="form-control" path="status" />


				<div class="submit-p mt-5">
					<button type="button" class="cancel-btn " onclick="closeAllModal()">Hủy</button>
					<button type="submit" class="submit-btn">${output1}</button>
				</div>
			</form:form>
		</div>

	</div>


</div>