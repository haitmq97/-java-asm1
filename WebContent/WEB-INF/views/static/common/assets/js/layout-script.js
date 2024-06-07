

document.addEventListener("DOMContentLoaded", function() {
	window.onscroll = function() {
		stickyNav();
	};

	var navbar = document.getElementById("navbar");
	var header = document.getElementById("header-sec");
	var sticky = navbar.offsetTop;

	function stickyNav() {
		if (window.pageYOffset >= sticky) {
			navbar.classList.add("sticky");
			header.classList.add("sticky-header");
		} else {
			navbar.classList.remove("sticky");
			header.classList.remove("sticky-header");
		}
	}
});



function openModal(popupId) {
	document.getElementById("overlay").style.display = "block";
	document.querySelector(popupId).style.display = "block";
}

function closeModal(popupId) {
	document.getElementById("overlay").style.display = "none";
	document.querySelector(popupId).style.display = "none";
	var formContainers = document.getElementsByClassName("form-container");
	for (var i = 0; i < formContainers.length; i++) {
		formContainers[i].style.display = "none";
	}
	
	$.ajax({
			type: "GET",
			url: window.location.href,
			data: {

			},
			success: function(data) {
				$('#donate').html(
					$(data).find('#donate').html());
				$('#login').html(
					$(data).find('#login').html());
			}
		});
}


function closeAllModal() {
	document.getElementById("overlay").style.display = "none";
	var formContainers = document.getElementsByClassName("form-container");
	for (var i = 0; i < formContainers.length; i++) {
		formContainers[i].style.display = "none";
	}
	/*
	$.ajax({
		url: "/PRJ321x_Project1_haitmqfx22585/v1/clearErrors",  // URL của endpoint để xóa lỗi
		type: 'POST',
		success: function(response) {
			// Xử lý phản hồi nếu cần
			console.log(response);
			// Tùy chọn, bạn có thể tải lại trang hoặc cập nhật giao diện
			location.reload();
		}
	});
	*/


}


function togglePassword() {
	var passwordInput = document.getElementById("password");
	var eyeIcon = document.getElementById("eyeIcon");

	if (passwordInput.type === "password") {
		passwordInput.type = "text";
		eyeIcon.classList.add("fa-eye-slash");
		eyeIcon.classList.remove("fa-eye");
		document.querySelector(".toggle-pass-btn").title = "Ẩn mật khẩu";
	} else {
		passwordInput.type = "password";
		eyeIcon.classList.add("fa-eye");
		eyeIcon.classList.remove("fa-eye-slash");
		document.querySelector(".toggle-pass-btn").title = "Hiện mật khẩu";
	}
}



function loginErrorShowing() {
	if ($("#errorLogin").val() === 'true') {

		if (($("#userNameOrEmail").val().trim() !== '')
			&& ($("#password").val().trim() !== '')) {
			$("#global-error").removeClass("d-none")
				.addClass("d-block");

		} else {

			$("#global-error").removeClass("d-block")
				.addClass("d-none");

		}

		openModal('#login');

	}
}

function openSuccessDonateMgs() {
	var successDonate = document.getElementById("successDonate");
	
	if(successDonate != null) {
		openModal("#success-donate");
	}
	
}


function ErrorFormShowing() {
	if ($("#errorProcess").val() === 'true') {

		openModal('#donation-addOrUpdate');
		
		openModal('#user-addOrUpdate');

	}
}

function changeColorText() {
	document.querySelectorAll(".color-text").forEach(function(p) {
		switch (p.textContent.trim().toLowerCase()) {
			case "mới tạo":
				p.classList.add("text-primary");
				break;
			case 'đang quyên góp':
				p.classList.add("text-success");
				break;
			case 'đã kết thúc':
				p.classList.add("text-danger");
				break;
			case 'đã đóng':
				p.classList.add("text-danger");
				break;
		}
	});
}





$(document).ready(function() {

	loginErrorShowing();
	
	openSuccessDonateMgs();
	
	ErrorFormShowing();
	
	changeColorText();

});




