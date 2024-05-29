
function redirectToLink(link) {

	var donateL = document.getElementById("donatePopup");
	donateL.url = link;
	openModal('donate');

}




function redirectToUserProfile() {
	window.location.href = '${pageContext.request.contextPath}/v2/detail';
}




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
	if (authorities !== "none") {
		return true;
	}
	return false;
}

/*	
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
			
			openModal('donate');
		} else {
			openModal('loginWarning')
		}
		
	}
	*/


function toDonateForm(donationId, isLogined) {
	
	console.log("donation id is: " + donationId);
	
	if (isLogined) {
		$.ajax({
			type: "GET",
			url: window.location.href,
			data: {

				donationId: donationId
			},
			success: function(data) {
				$("#donate").html(
					$(data).find("#donate").html());
			}
		});
		console.log("donation id is(test): " + donationId);
		
	} else {
		console.log("eeeeeeeeeeee");
	}
	openModal('#donate');

}



function toDeletePopup(donationId, authorities) {
	console.log("donation id:" + donationId);
	console.log("authorities:" + authorities);
	if (isAbleToDonate(authorities)) {
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

		openModal('delete');
	} else {
		openModal('loginWarning')
	}

}






function donationStatusFormat(status) {
	result = "";
	switch (status.toLowerCase()) {
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


// nút chuyển trang


function generatePaginationButtons(currentPage, totalPages, size, searchingValue, tableContent) {
	console.log("hello");

	
	const paginationContainer = document.getElementById('pagination-container');
	paginationContainer.innerHTML = '';

	// tạo list chứa các button trang
	const paginationList = document.createElement('ul');
	paginationList.className = 'pagination-list';
	
	
	// fist button
	addButton(1, currentPage, size, searchingValue, paginationList, tableContent,'<<');
	// thêm button cho trang đầu tiên
	if (totalPages != 1) {
		addButton(1, currentPage, size, searchingValue, paginationList, tableContent,'1');
	}
	// thêm gap
	if (currentPage - 3 > 1) {
		addGap(paginationList);
	}

	// thêm button các trang trước và sau trang hiện tại
	for (let i = Math.max(2, currentPage - 2); i <= Math.min(currentPage + 2, totalPages - 1); i++) {
		addButton(i, currentPage, size, searchingValue, paginationList, tableContent ,i);
	}
	// thêm gap
	if (currentPage + 3 < totalPages) {
		addGap(paginationList);
	}

	// thêm button cho trang cuối
	addButton(totalPages, currentPage, size, searchingValue, paginationList, tableContent, totalPages);
	
	
	// last button
	addButton(totalPages, currentPage, size, searchingValue, paginationList, tableContent, ">>");

	// thêm list vào div
	paginationContainer.appendChild(paginationList);
}

function addButton(pageNumber, currentPage, size, searchingValue, parentElement, tableContent, buttonContent) {
	const listItem = document.createElement('li');
	const button = document.createElement('button');
	button.className = 'page-btn';
	console.log(pageNumber);
	button.textContent = buttonContent;
	button.addEventListener('click', () => onPageButtonClick(pageNumber, size, searchingValue, tableContent));

	if (pageNumber === currentPage) {
		button.disabled = true;
	} else {
		/*button.style.backgroundColor = 'green';*/
	}

	listItem.appendChild(button);
	parentElement.appendChild(listItem);
}

function addGap(parentElement) {
	const gapItem = document.createElement('li');
	const gapSpan = document.createElement('span');
	gapSpan.textContent = '...';
	gapSpan.className = 'gap';
	gapItem.appendChild(gapSpan);
	parentElement.appendChild(gapItem);
}

/*
function onPageButtonClick(pageNumber, size, searchingValue, tableContent) {

	// cần thay đổi link và div container

	console.log('Navigating to page ', pageNumber);
	console.log('Navigating to page (type of) ', typeof pageNumber);
	console.log('Navigating to link ', window.location.href);

	$.ajax({
		type: "GET",
		url: window.location.href,
		data: {
			size: size,
			page: pageNumber,
			searchingValue: searchingValue
		},
		success: function(data) {
			$(tableContent).html(
				$(data).find(tableContent).html());
		}
	});

}
*/
/*

function onPageButtonClick(pageNumber, size, searchingValue, tableContent) {
    console.log('Navigating to page ', pageNumber);
    console.log('Navigating to page (type of) ', typeof pageNumber);
    console.log('Navigating to link ', window.location.href);

    $.ajax({
        type: "GET",
        url: window.location.href,
        data: {
            size: size,
            page: pageNumber,
            searchingValue: searchingValue
        },
        success: function(data) {
            $(tableContent).html($(data).find(tableContent).html());
            
            // Cập nhật lại các nút chuyển trang sau khi thay đổi nội dung bảng
            var currentPage = parseInt(document.getElementById("currentPage1").value, 10);
            var totalPages = parseInt(document.getElementById("totalPages1").value, 10);
            generatePaginationButtons(currentPage, totalPages, $('#pageSize').val(), $('#searchingValue').val(), tableContent);
            
            // Đảm bảo rằng div "pagination-container" không bị mất đi sau khi thay đổi nội dung
            var paginationContainer = document.getElementById('pagination-container');
            if (!paginationContainer) {
                paginationContainer = document.createElement('div');
                paginationContainer.id = 'pagination-container';
                document.body.appendChild(paginationContainer);
            }
        }
    });
}
*/



function onPageButtonClick(pageNumber, size, searchingValue, tableContent) {
    console.log('Navigating to page ', pageNumber);
    console.log('Navigating to page (type of) ', typeof pageNumber);
    console.log('Navigating to link ', window.location.href);
	var newUrl = window.location.pathname + "?size=" + size + "&page="+ pageNumber + "&searchingValue=" + encodeURIComponent(searchingValue);
    $.ajax({
        type: "GET",
        url: newUrl,
        
        success: function(data) {
            $(tableContent).html($(data).find(tableContent).html());
            
            // Cập nhật lại các nút chuyển trang sau khi thay đổi nội dung bảng
            var currentPage = parseInt(document.getElementById("currentPage1").value, 10);
            var totalPages = parseInt(document.getElementById("totalPages1").value, 10);
            generatePaginationButtons(currentPage, totalPages, $('#pageSize').val(), $('#searchingValue').val(), tableContent);
            
            // Đảm bảo rằng div "pagination-container" không bị mất đi sau khi thay đổi nội dung
            var paginationContainer = document.getElementById('pagination-container');
            if (!paginationContainer) {
                paginationContainer = document.createElement('div');
                paginationContainer.id = 'pagination-container';
                document.body.appendChild(paginationContainer);
            }
        }
    });
}



// update table cho searching value và số entries mỗi trang

if (typeof previousSearchValue === "undefined") {
    var previousSearchValue = ""; // Khai báo biến nếu chưa tồn tại
    var previousSize = $("#currentPage").val();
}


/*
function updateShowingTable(size, searchingValue, tableContent) {
    // Kiểm tra xem searchingValue có thay đổi so với lần trước không
    if ((searchingValue !== previousSearchValue)|| (size !== previousSize)) {
		
		
        $.ajax({
            type: "GET",
            url: window.location.href,
            data: {
                size: size,
                page: 1,
                searchingValue: searchingValue
            },
            success: function(data) {
                $(tableContent).html($(data).find(tableContent).html());
                
                var currentPage = parseInt(document.getElementById("currentPage1").value, 10);
            var totalPages = parseInt(document.getElementById("totalPages1").value, 10);
            generatePaginationButtons(currentPage, totalPages, $('#pageSize').val(), $('#searchingValue').val(), tableContent);
            
            // Đảm bảo rằng div "pagination-container" không bị mất đi sau khi thay đổi nội dung
            var paginationContainer = document.getElementById('pagination-container');
            if (!paginationContainer) {
                paginationContainer = document.createElement('div');
                paginationContainer.id = 'pagination-container';
                document.body.appendChild(paginationContainer);
            }
            
            }
        });
        // Cập nhật giá trị của previousSearchValue
        previousSearchValue = searchingValue;
        previousSize = size;
    }
}
*/

function updateShowingTable(size, searchingValue, tableContent) {
    // Kiểm tra xem searchingValue có thay đổi so với lần trước không
    if ((searchingValue !== previousSearchValue)|| (size !== previousSize)) {
		 var newUrl = window.location.pathname + "?size=" + size + "&page=1" + "&searchingValue=" + encodeURIComponent(searchingValue);
		
        $.ajax({
            type: "GET",
            url: newUrl,
            
            success: function(data) {
                $(tableContent).html($(data).find(tableContent).html());
                
                var currentPage = parseInt(document.getElementById("currentPage1").value, 10);
            var totalPages = parseInt(document.getElementById("totalPages1").value, 10);
            generatePaginationButtons(currentPage, totalPages, $('#pageSize').val(), $('#searchingValue').val(), tableContent);
            
            // Đảm bảo rằng div "pagination-container" không bị mất đi sau khi thay đổi nội dung
            var paginationContainer = document.getElementById('pagination-container');
            if (!paginationContainer) {
                paginationContainer = document.createElement('div');
                paginationContainer.id = 'pagination-container';
                document.body.appendChild(paginationContainer);
            }
            
            }
        });
        // Cập nhật giá trị của previousSearchValue
        previousSearchValue = searchingValue;
        previousSize = size;
    }
}


/*
// cập nhật lại bảng khi user thay đổi size(entries) và searchingValue
function updateShowingTable(size, searchingValue, tableContent) {

	$.ajax({
		type: "GET",
		url: window.location.href,
		data: {
			size: size,
			page: 1,
			searchingValue: searchingValue
		},
		success: function(data) {
			$(tableContent).html(
				$(data).find(tableContent).html());
		}
	});
}

*/

$(document).ready(function() {
	let timer;

	$('#pageSize').change(function() {
		updateShowingTable($('#pageSize').val(), $('#searchingValue').val(), "#data-list");
	});

	$('#searchingValue').on('input', function() {
		clearTimeout(timer);
		timer = setTimeout(function() {
			updateShowingTable($('#pageSize').val(), $('#searchingValue').val(), "#data-list")
		}, 500)


	});

/*
	var currentPage = parseInt(document.getElementById("currentPage1").value, 10);
	var totalPages = parseInt(document.getElementById("totalPages1").value, 10);



	generatePaginationButtons(currentPage, totalPages, $('#pageSize').val(), $('#searchingValue').val(), "#data-list");

	let btnList = document.getElementsByClassName("page-btn");
	Array.from(btnList).forEach(btn => {
		if (parseInt(btn.textContent) === currentPage) {

			btn.disabled = true;
		}
	});


*/	
	 var currentPageElement = document.getElementById("currentPage1");
    var totalPagesElement = document.getElementById("totalPages1");

    if (currentPageElement && totalPagesElement) {
        var currentPage = parseInt(currentPageElement.value, 10);
        var totalPages = parseInt(totalPagesElement.value, 10);

        generatePaginationButtons(currentPage, totalPages, $('#pageSize').val(), $('#searchingValue').val(), "#data-list");

        let btnList = document.getElementsByClassName("page-btn");
        Array.from(btnList).forEach(btn => {
            if (parseInt(btn.textContent) === currentPage) {
                btn.disabled = true;
            }
        });
    }




});



// donation delete popup

function toDelete(id, divId) {
		$.ajax({
			type: "GET",
			url: window.location.href,
			data: {

				id: id
			},
			success: function(data) {
				$(divId).html(
					$(data).find(divId).html());
			}
		});
		
		openModal(divId);

	}
	
// donation add or update modal

	function toAddOrUpdate(id, divId) {
		$.ajax({
			type: "GET",
			url: window.location.href,
			data: {

				id: id
			},
			success: function(data) {
				$(divId).html(
					$(data).find(divId).html());
			}
		});
		
		openModal(divId);

	}


