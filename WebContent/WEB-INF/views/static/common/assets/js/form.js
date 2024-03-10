function openPopup(popupId) {
	document.getElementById("overlay").style.display = "block";
	document.getElementById(popupId).style.display = "block";
}

function closePopup() {
	document.getElementById("overlay").style.display = "none";
	var formContainers = document.getElementsByClassName("form-container");
	for (var i = 0; i < formContainers.length; i++) {
		formContainers[i].style.display = "none";
	}
}


function closeAllPopup() {
	document.getElementById("overlay").style.display = "none";
	var formContainers = document.getElementsByClassName("form-container");
	for (var i = 0; i < formContainers.length; i++) {
		formContainers[i].style.display = "none";
	}
}


function togglePassword() {
	var passwordInput = document.getElementById("passwordInput");
	var eyeIcon = document.getElementById("eyeIcon");

	if (passwordInput.type === "password") {
		passwordInput.type = "text";
		eyeIcon.className = "fa-regular fa-eye-slash";
		document.querySelector(".toggle-pass-btn").title = "Ẩn mật khẩu";
	} else {
		passwordInput.type = "password";
		eyeIcon.className = "fa-regular fa-eye";
		document.querySelector(".toggle-pass-btn").title = "Hiện mật khẩu";
	}
}


function redirectToHome() {
	window.location.href = '/v1/home';
}




