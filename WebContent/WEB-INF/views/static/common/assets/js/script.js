
// cập nhật lại bảng khi user thay đổi size(entries) và searchingValue
function updateShowingTable(size, searchingValue) {

	$.ajax({
		type: "GET",
		url: window.location.href,
		data: {
			size: size,
			page: 1,
			searchingValue: searchingValue
		},
		success: function(data) {
			$("#donation-list").html(
				$(data).find("#donation-list").html());
		}
	});
}


function generatePaginationButtons(currentPage, totalPages, size, searchingValue) {

	const paginationContainer = document.getElementById('pagination-container');
	paginationContainer.innerHTML = '';

	// tạo list chứa các button trang
	const paginationList = document.createElement('ul');
	paginationList.className = 'pagination-list';

	// thêm button cho trang đầu tiên
	if (totalPages != 1) {
		addButton(1, currentPage, size, searchingValue, paginationList);
	}
	// thêm gap
	if (currentPage - 3 > 1) {
		addGap(paginationList);
	}

	// thêm button các trang trước và sau trang hiện tại
	for (let i = Math.max(2, currentPage - 2); i <= Math.min(currentPage + 2, totalPages - 1); i++) {
		addButton(i, currentPage, size, searchingValue, paginationList);
	}
	// thêm gap
	if (currentPage + 3 < totalPages) {
		addGap(paginationList);
	}

	// thêm button cho trang cuối
	addButton(totalPages, currentPage, size, searchingValue, paginationList);

	// thêm list vào div
	paginationContainer.appendChild(paginationList);
}

function addButton(pageNumber, currentPage, size, searchingValue, parentElement) {
	const listItem = document.createElement('li');
	const button = document.createElement('button');
	button.className = 'page-btn';

	button.textContent = pageNumber;
	button.addEventListener('click', () => onPageButtonClick(pageNumber, size, searchingValue));

	/*
	if (pageNumber === currentPage) {
		button.disabled = true;
	}*/

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

function onPageButtonClick(pageNumber, size, searchingValue) {

	// cần thay đổi link và div container

	console.log('Navigating to page ', pageNumber);
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
			$("#donation-list").html(
				$(data).find("#donation-list").html());
		}
	});

}



/*

$(document).ready(function() {
	$('#pageSize').change(function() {
		updateShowingTable($('#pageSize').val(), $('#searchingValue').val());
	});

	$('#searchingValue').on('input', function() {
		updateShowingTable($('#pageSize').val(), $('#searchingValue').val())
	});



});
var currentPage = parseInt(document.getElementById("currentPage1").value, 10);
							var totalPages = parseInt(document.getElementById("totalPages1").value, 10);



							generatePaginationButtons(currentPage, totalPages, $('#pageSize').val(), $('#searchingValue').val());
*/
/*

$(document).ready(function() {
    $('#pageSize').change(function() {
        updateShowingTable($('#pageSize').val(), $('#searchingValue').val());
    });

    $('#searchingValue').on('input', function() {
        updateShowingTable($('#pageSize').val(), $('#searchingValue').val())
    });

    // Gọi AJAX để lấy dữ liệu trang đầu tiên và sau đó tạo nút phân trang
    $.ajax({
        type: "GET",
        url: window.location.href,
        data: {
            size: $('#pageSize').val(),
            page: 1,
            searchingValue: $('#searchingValue').val()
        },
        success: function(data) {
            $("#donation-list").html($(data).find("#donation-list").html());

            // Lấy số trang hiện tại và tổng số trang từ các phần tử ẩn
            var currentPage = parseInt($("#currentPage1").val(), 10);
            var totalPages = parseInt($("#totalPages1").val(), 10);

            // Tạo nút phân trang sau khi dữ liệu đã được tải và xử lý
            generatePaginationButtons(currentPage, totalPages, $('#pageSize').val(), $('#searchingValue').val());
        }
    });
});

*/

/*
document.addEventListener("DOMContentLoaded", function() {
    $('#pageSize').change(function() {
        updateShowingTable($('#pageSize').val(), $('#searchingValue').val());
    });

    $('#searchingValue').on('input', function() {
        updateShowingTable($('#pageSize').val(), $('#searchingValue').val())
    });

    var currentPage = parseInt(document.getElementById("currentPage1").value, 10);
    var totalPages = parseInt(document.getElementById("totalPages1").value, 10);

    generatePaginationButtons(currentPage, totalPages, $('#pageSize').val(), $('#searchingValue').val());
});


*/
function redirectToDonateLink(donateLink) {

	var donateL = document.selectElementById("donatePopup");
	donateL.url = donateLink;
	openPopup('donate');

}



