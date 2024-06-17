

document.addEventListener("DOMContentLoaded", function() {
	window.onscroll = function() {
		stickyNav();
	};

	var navbar = document.getElementById("navbar");
	var header = document.getElementById("header-sec");
	
	
	if(navbar!=null) {
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
	var currentUrl = new URL(window.location.href);
    
    // Cập nhật hoặc thêm các tham số query mới
    currentUrl.searchParams.set('size', $('#pageSize').val());
    currentUrl.searchParams.set('page', 1);
    currentUrl.searchParams.set('searchingValue', $('#searchingValue').val());
	
	$.ajax({
			type: "GET",
			url: currentUrl,
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
	
	if(passwordInput!=null) {
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

	console.log("...............passwordInput" + passwordInput);
	/*if (passwordInput.type === "password") {
		passwordInput.type = "text";
		eyeIcon.classList.add("fa-eye-slash");
		eyeIcon.classList.remove("fa-eye");
		document.querySelector(".toggle-pass-btn").title = "Ẩn mật khẩu";
	} else {
		passwordInput.type = "password";
		eyeIcon.classList.add("fa-eye");
		eyeIcon.classList.remove("fa-eye-slash");
		document.querySelector(".toggle-pass-btn").title = "Hiện mật khẩu";
	}*/
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

function openSuccessProccessMgs() {
	var successDonate = document.getElementById("successDonate");
	var successAdd = document.getElementById("successAdd");
	var successUpdate = document.getElementById("successUpdate");
	var successDelete = document.getElementById("successDelete");
	var successChangeStatus = document.getElementById("successChangeStatus");
	
	if(successDonate != null) {
		if (successDonate.value == "true") {
			openModal("#success-donate");
		}
		
	}
	
	if(successAdd != null) {
		console.log("successAdd value: " + successAdd.value);
		console.log("successAdd value equals true: " + (successAdd.value == "true"));
		if (successAdd.value == "true") {
			openModal("#success-add");
		}
	}

	if(successUpdate != null) {
		console.log("successUpdate value: " +  successUpdate.value);
		console.log("successUpdate value equals true: " + (successUpdate.value == "true"));
		
		if(successUpdate.value == "true"){
			openModal("#success-update");
		}
		
	}
	if(successDelete != null) {
		console.log("successUpdate value: " +  successDelete.value);
		console.log("successUpdate value equals true: " + (successDelete.value == "true"));
		
		if(successDelete.value == "true"){
			openModal("#success-delete");
		}
		
	}
	if(successChangeStatus != null) {
		console.log("successUpdate value: " +  succsuccessChangeStatusessUpdate.value);
		console.log("successUpdate value equals true: " + (successChangeStatus.value == "true"));
		
		if(successChangeStatus.value == "true"){
			openModal("#success-changeStatus");
		}
		
	}
	
}


function ErrorFormShowing() {
	var donationModal = document.getElementById("donation-addOrUpdate");
	var userModal = document.getElementById("user-addOrUpdate");
	var errorProcess = document.getElementById("errorProcess");
	
	if (errorProcess && errorProcess.value === 'true') {

		if(donationModal!=null) {
			openModal('#donation-addOrUpdate');
		}
		
		if(userModal!=null) {
			openModal('#user-addOrUpdate');
		}

	}
}

function changeColorText() {
	document.querySelectorAll(".color-text").forEach(function(p) {
		switch (p.textContent.trim().toLowerCase()) {
			case "mới tạo":
				p.classList.add("text-primary");
				break;
			case 'đang quyên góp':
			case 'đang hoạt động':
			case 'đã xác nhận':
				p.classList.add("text-success");
				break;
			case 'đã kết thúc':
			case 'đã đóng':
			case 'admin':
			case 'bị khóa':
			case 'chờ xác nhận':
				p.classList.add("text-danger");
				break;
			
		}
	});
}


function checkUrl() {
	var pathName = window.location.pathname;
	
	console.log("....................current url: " + window.location.href);
	console.log("....................current pathname: " + window.location.pathname);
	
	var homeBtn = document.getElementById("nav-home-btn");
	var managerBtn= document.getElementById("nav-manager-btn");
	var userBtn = document.getElementById("nav-user-btn");
	
	const buttons = document.querySelectorAll('.nav-item .btn');
	
	buttons.forEach(button => {
		button.classList.remove("active-b");
	});
	
	if(pathName.includes("admin")) {
			managerBtn.classList.add("active-b");
	} else if(pathName.includes("home")) {
		homeBtn.classList.add("active-b");
	} else if(pathName.includes("profile")) {
		userBtn.classList.add("active-b");
	}

	
	
}


$(document).ready(function() {
	checkUrl();

	loginErrorShowing();
	
	openSuccessProccessMgs();
	
	ErrorFormShowing();
	
	changeColorText();

});




