
function generatePaginationButtons(currentPage, totalPages) {
	const paginationContainer = document.getElementById('pagination-container');
	paginationContainer.innerHTML = '';

	const paginationList = document.createElement('ul');
	paginationList.className = 'pagination';

	// Button for the first page
	if (totalPages != 1) {
		addButton(1, currentPage, paginationList);
	}

	if (currentPage - 3 > 1) {
		// Add a gap if there are pages between the first page and the current page - 3
		addGap(paginationList);
	}

	// Buttons for the pages in the range [currentPage - 2, currentPage + 2]
	for (let i = Math.max(2, currentPage - 2); i <= Math.min(currentPage + 2, totalPages - 1); i++) {
		addButton(i, currentPage, paginationList);
	}

	if (currentPage + 3 < totalPages) {
		// Add a gap if there are pages between the current page + 3 and the last page
		addGap(paginationList);
	}

	// Button for the last page
	addButton(totalPages, currentPage, paginationList);

	paginationContainer.appendChild(paginationList);
}

function addButton(pageNumber, currentPage, parentElement) {
	const listItem = document.createElement('li');
	const button = document.createElement('button');

	button.textContent = pageNumber;
	button.addEventListener('click', () => onPageButtonClick(pageNumber));

	if (pageNumber === currentPage) {
		button.disabled = true;
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

function onPageButtonClick(pageNumber) {
	// TODO: Handle the logic to navigate to the selected page
	console.log('Navigating to page', pageNumber);
}

// Example usage:
var currentPage = parseInt(document.getElementById("currentPage").value, 10);

var cp = document.getElementById("currentPage").value;
 console.log("type" + typeof cp);
var totalPages = parseInt(document.getElementById("totalPages").value, 10);

generatePaginationButtons(currentPage, totalPages);
