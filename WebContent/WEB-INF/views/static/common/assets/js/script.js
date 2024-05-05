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




function redirectToLink(link) {

	var donateL = document.getElementById("donatePopup");
	donateL.url = link;
	openPopup('donate');

}




function redirectToUserProfile() {
	window.location.href = '${pageContext.request.contextPath}/v2/detail';
}


$(document).ready(function() {
	let timer;

	$('#pageSize').change(function() {
		updateShowingTable($('#pageSize').val(), $('#searchingValue').val(), "#donation-list");
	});

	$('#searchingValue').on('input', function() {
		clearTimeout(timer);
		timer = setTimeout(function() {
			updateShowingTable($('#pageSize').val(), $('#searchingValue').val(), "#donation-list")
		}, 500)


	});


	var currentPage = parseInt(document.getElementById("currentPage1").value, 10);
	var totalPages = parseInt(document.getElementById("totalPages1").value, 10);



	generatePaginationButtons(currentPage, totalPages, $('#pageSize').val(), $('#searchingValue').val(), "#donation-list");

	let btnList = document.getElementsByClassName("page-btn");
	Array.from(btnList).forEach(btn => {
		if (parseInt(btn.textContent) === currentPage) {

			btn.disabled = true;
		}
	});



	



});



$(document).ready(function() {
		var authorities = $("#authorities").val();
		
		console.log(authorities);
		/* 
		switch(authorities) {
		  case "admin":
			  $("#manager-btn").css("display", "block");
		  	  $("#user-btn").css("display", "block");
		  	$("#rs-b").css("display", "none");
		    break;
		  case "user":
			  $("#user-btn").css("display", "block");
			  $("#rs-b").css("display", "none");
		    break;
		  default:
			  $("#manager-btn").css("display", "none");
		  	  $("#user-btn").css("display", "none");
		}
		 */
		

/*		
		if(authorities !== "none") {
			$("#rs-b").css("display", "none");
			$("#user-btn").css("display", "inline-block");
			if(authorities === "admin") {
				
				
				$("#manager-btn").css("display", "inline-block");
				
				
			} else {
				$("#manager-btn").css("display", "none");
			}
		} else {
			$("#manager-btn").css("display", "none");
		  	  $("#user-btn").css("display", "none");
		  	$("#rs-b").css("display", "inline-block");  
		}
		
			
	*/
		
		
		
	});


	
	function isAbleToDonate(authorities) {
		if(authorities !== "none") {
			return true;
		}
		return false;
	}
	
	
	function toDonateForm(donationId, authorities) {
		console.log("donation id:" + donationId);
		console.log("authorities:" + authorities);
		if(isAbleToDonate(authorities)) {
			$.ajax({
				type: "GET",
				url: window.location.href,
				data: {

					donationId: donationId
				},
				success: function(data) {
					$('#donate').html(
						$(data).find('#donate').html());
				}
			});
			
			openPopup('donate');
		} else {
			openPopup('loginWarning')
		}
		
	}
	
	
	
	function toDeletePopup(donationId, authorities) {
		console.log("donation id:" + donationId);
		console.log("authorities:" + authorities);
		if(isAbleToDonate(authorities)) {
			$.ajax({
				type: "GET",
				url: window.location.href,
				data: {

					donationId: donationId
				},
				success: function(data) {
					$('#delete').html(
						$(data).find('#delete').html());
				}
			});
			
			openPopup('delete');
		} else {
			openPopup('loginWarning')
		}
		
	}
	

	
	
	
	
	function donationStatusFormat(status) {
		result = "";
		switch(status.toLowerCase()) {
			case 'new':
				result = "Mới tạo";
				break;
			case 'donating':
				result = "Đang quyên góp";
				break;
			case 'end':
				result = "Đã kết thúc";
				break;
			default:
				result = "Đã đóng";
		}
		return result;
	}
	
	



