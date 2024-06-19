
function redirectToLink(link) {

	var donateL = document.getElementById("donatePopup");
	donateL.url = link;
	openModal('donate');

}




function redirectToUserProfile() {
	window.location.href = '${pageContext.request.contextPath}/v2/detail';
}







function isAbleToDonate(authorities) {
	if (authorities !== "none") {
		return true;
	}
	return false;
}


function toDonateForm(donationId, isLogined) {
	

	var currentUrl = new URL(window.location.href);
    
    // Cập nhật hoặc thêm các tham số query mới
    currentUrl.searchParams.set('size', $('#pageSize').val());
    currentUrl.searchParams.set('page', $('#currentPage').val());
    currentUrl.searchParams.set('searchingValue', $('#searchingValue').val());
    currentUrl.searchParams.set('donationId', donationId);

	if (isLogined) {
		$.ajax({
			type: "GET",
			url: currentUrl,
			
			success: function(data) {
				$("#donate").html(
					$(data).find("#donate").html());
			}
		});

		
	} else {

	}
	openModal('#donate');

}



function toDeletePopup(donationId, authorities) {

	var currentUrl = new URL(window.location.href);
    
    // Cập nhật hoặc thêm các tham số query mới
    currentUrl.searchParams.set('size', $('#pageSize').val());
    currentUrl.searchParams.set('page', $('#currentPage').val());
    currentUrl.searchParams.set('searchingValue', $('#searchingValue').val());
    currentUrl.searchParams.set('donationId', donationId);

	
	if (isAbleToDonate(authorities)) {
		$.ajax({
			type: "GET",
			url: currentUrl,
			
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


function onPageButtonClick(pageNumber, size, searchingValue, tableContent) {

	 var currentUrl = new URL(window.location.href);
    
    // Cập nhật hoặc thêm các tham số query mới
    currentUrl.searchParams.set('size', size);
    currentUrl.searchParams.set('page', pageNumber);
    currentUrl.searchParams.set('searchingValue', searchingValue);

    // Chuyển URL mới với các tham số đã cập nhật
    var newUrl = currentUrl.toString();
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

/*function updateShowingTable(size, searchingValue, tableContent) {
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
}*/
function updateShowingTable(size, searchingValue, tableContent) {
	// Kiểm tra xem searchingValue có thay đổi so với lần trước không
	if ((searchingValue !== previousSearchValue) || (size !== previousSize)) {
		var currentUrl = new URL(window.location.href);

		// Cập nhật hoặc thêm các tham số query mới
		currentUrl.searchParams.set('size', size);
		currentUrl.searchParams.set('page', 1);
		currentUrl.searchParams.set('searchingValue', searchingValue);

		// Chuyển URL mới với các tham số đã cập nhật
		var newUrl = currentUrl.toString();
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
	 var currentPageElement = document.getElementById("currentPage");
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
	var currentUrl = new URL(window.location.href);
	
	var size = document.getElementById("pageSize").value;
	var page = document.getElementById("currentPage").value;
	var searchingValue = document.getElementById("searchingValue").value;

	// Cập nhật hoặc thêm các tham số query mới
	currentUrl.searchParams.set('size', size);
	currentUrl.searchParams.set('page', page);
	currentUrl.searchParams.set('searchingValue', searchingValue);
	
	if(currentUrl.pathname.includes("/admin/donation-detail")) {

		currentUrl.searchParams.set('userDonationId', id);
	} else if(currentUrl.pathname.includes("/admin/user-detail")) {

		currentUrl.searchParams.set('userDonationId', id);
	} else {
		currentUrl.searchParams.set('id', id);
	}
		

		$.ajax({
			type: "GET",
			url: currentUrl,
			
			success: function(data) {
				$(divId).html(
					$(data).find(divId).html());
			}
		});
		
		openModal(divId);

	}
	
// donation add or update modal

function toAddOrUpdate(id, divId) {
	var currentUrl = new URL(window.location.href);
	
	var size = document.getElementById("pageSize").value;
	var page = document.getElementById("currentPage").value;
	var searchingValue = document.getElementById("searchingValue").value;

	// Cập nhật hoặc thêm các tham số query mới
	currentUrl.searchParams.set('size', size);
	currentUrl.searchParams.set('page', page);
	currentUrl.searchParams.set('searchingValue', searchingValue);
	
	$.ajax({
		type: "GET",
		url: currentUrl,
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


