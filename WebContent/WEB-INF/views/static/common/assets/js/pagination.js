
function generatePaginationButtons(currentPage, totalPages, size, searchingValue, urlImport) {
	const paginationContainer = document.getElementById('pagination-container');
	paginationContainer.innerHTML = '';

	const paginationList = document.createElement('ul');
	paginationList.className = 'pagination';

	// Button for the first page
	if (totalPages != 1) {
		addButton(1, currentPage, size, searchingValue, urlImport, paginationList);
	}

	if (currentPage - 3 > 1) {
		// Add a gap if there are pages between the first page and the current page - 3
		addGap(paginationList);
	}

	// Buttons for the pages in the range [currentPage - 2, currentPage + 2]
	for (let i = Math.max(2, currentPage - 2); i <= Math.min(currentPage + 2, totalPages - 1); i++) {
		addButton(i, currentPage, size, searchingValue, urlImport, paginationList);
	}

	if (currentPage + 3 < totalPages) {
		// Add a gap if there are pages between the current page + 3 and the last page
		addGap(paginationList);
	}

	// Button for the last page
	addButton(totalPages, currentPage, size, searchingValue,  urlImport, paginationList);

	paginationContainer.appendChild(paginationList);
}

function addButton(pageNumber, currentPage,  size, searchingValue, urlImport ,parentElement) {
	const listItem = document.createElement('li');
	const button = document.createElement('button');

	button.textContent = pageNumber;
	button.addEventListener('click', () => onPageButtonClick(pageNumber, size, searchingValue, urlImport));

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

function onPageButtonClick(pageNumber, size, searchingValue, urlImport) {

// cần thay đổi link và div container

	console.log('Navigating to page ', pageNumber);
	console.log('Navigating to link ', urlImport);

	$.ajax({
		type: "GET",
		url: "/PRJ321x_Project1_haitmqfx22585/v1/donations",
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

// Example usage:

var currentPage = parseInt(document.getElementById("currentPage").value, 10);
var totalPages = parseInt(document.getElementById("totalPages").value, 10);
var size = parseInt(document.getElementById("size").value, 10);
var searchingValue = document.getElementById("searchingValue").value;


generatePaginationButtons(currentPage, totalPages, size, searchingValue, "/v1/donations");

