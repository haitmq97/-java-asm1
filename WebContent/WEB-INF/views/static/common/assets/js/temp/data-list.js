
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


function generatePaginationButtons(currentPage, totalPages, size, searchingValue, tableContent) {

	const paginationContainer = document.getElementById('pagination-container');
	paginationContainer.innerHTML = '';

	// tạo list chứa các button trang
	const paginationList = document.createElement('ul');
	paginationList.className = 'pagination-list';
	
	
	// fist button
	addButton(1, currentPage, size, searchingValue, paginationList, tableContent,'Fist');
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
	
	// thêm button cho trang cuối
	addButton(totalPages, currentPage, size, searchingValue, paginationList, tableContent, totalPages);
	
	// last button
	addButton(totalPages, currentPage, size, searchingValue, paginationList, tableContent, "Last");

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
		button.style.backgroundColor = 'red';
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



function redirectToDonateLink(donateLink) {

	var donateL = document.selectElementById("donatePopup");
	donateL.url = donateLink;
	openPopup('donate');

}






