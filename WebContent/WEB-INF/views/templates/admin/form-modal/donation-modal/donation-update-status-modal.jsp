<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="me.haitmq.spring.mvc.crud.content_path.ViewConstants"%>
<%@ page import="me.haitmq.spring.mvc.crud.utils.JSPDataFormat"%>
<%@ page import="me.haitmq.spring.mvc.crud.utils.Time"%>
<%@ page import="me.haitmq.spring.mvc.crud.entity.status.DonationStatus"%>

<div class="popup col-12 col-sm-8 col-md-4">
	<div class="form-container donate-form" id="update-status">

		<c:if test="${donation.status == DonationStatus.NEW}">

			<c:set var="output1" value="quyên góp" />
			<c:set var="output2"
				value="Ngày bắt đầu sẽ chuyển thành hôm nay ${Time.getCurrentDateTimeRaw()}" />

			<c:url var="updateStatusLink" value="/admin/updateDonationStatus">
				<c:param name="id" value="${donation.id}" />
				<c:param name="status" value="${DonationStatus.DONATING}" />
			</c:url>
		</c:if>

		<c:if test="${donation.status == DonationStatus.DONATING}">

			<c:set var="output1" value="kết thúc" />
			<c:set var="output2"
				value="Ngày kêt thúc sẽ chuyển thành hôm nay ${Time.getCurrentDateTimeRaw()}" />

			<c:url var="updateStatusLink" value="/admin/updateDonationStatus">
				<c:param name="id" value="${donation.id}" />
				<c:param name="status" value="${DonationStatus.END}" />
			</c:url>


		</c:if>


		<div class="container form-head">

			<h3>
				Bạn có chắc chắn muốn chuyển sang trạng thái
				<c:out value="${output1}" />
			</h3>
		</div>
		<div class="container form-main">
			<p>
				Đợt quyên góp: <span>${donation.name}</span>
			</p>
			<p>
				Mã quyên góp: <span>${donation.code}</span>
			</p>

			<p>
				<c:out value="${output2}" />
			</p>

			<div class="submit-p">
				<button type="button" class="cancel-btn " onclick="closeAllModal()">Hủy</button>
				<button type="submit" class="submit-btn" id="confirm-delete-btn"
					onclick="window.location.href='${updateStatusLink}'">Xác
					nhận</button>
			</div>

		</div>
	</div>

</div>



