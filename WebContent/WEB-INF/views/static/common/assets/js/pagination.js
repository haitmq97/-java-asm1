function generatePaginationButtons(currentPage, totalPages, size) {
    const paginationContainer = document.getElementById('pagination-container');
    paginationContainer.innerHTML = '';

    const paginationList = document.createElement('ul');
    paginationList.className = 'pagination';

    // Button for the first page
      if(totalPages!=1) {
        addButton(1, currentPage, paginationList);
    }

    if (currentPage - 3 > 1) {
      // Add a gap if there are pages between the first page and the current page - 3
      addGap(paginationList);
    }

    // Buttons for the pages in the range [currentPage - 2, currentPage + 2]
    for (let i = Math.max(2, currentPage - 2); i <= Math.min(currentPage + 2, totalPages - 1); i++) {
      addButton(i, currentPage, paginationList, size);
    }

    if (currentPage + 3 < totalPages) {
      // Add a gap if there are pages between the current page + 3 and the last page
      addGap(paginationList);
    }

    // Button for the last page
    addButton(totalPages, currentPage, paginationList, size);

    paginationContainer.appendChild(paginationList);
  }

  function addButton(pageNumber, currentPage, parentElement, size) {
    const listItem = document.createElement('li');
    const button = document.createElement('button');

    button.textContent = pageNumber;
    button.addEventListener('click', () => onPageButtonClick(pageNumber,size));

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
  


  function onPageButtonClick(pageNumber, size) {
	  $.ajax({
	    				type : "GET",
	    				url : "<c:url value='/donation/list'> </c:url>",
	    				data : {
	    					size : size,
	    					page : page,
	    					searchingValue : searchingValue 
	    				},
	    				success : function(data) {
	    					$("#donation-list").html(
	    							$(data).find("#donation-list").html());
	    				}
	    			});
	  
  }

  var currentPage = document.getElementById('currentPage').value; // Replace with the current page number
  var totalPages = document.getElementById('totalPage').value; // Replace with the total number of pages
  var size = document.getElementById('size').value; // Replace with the total number of pages
	console.log("currentpage: " + currentPage );
	console.log("totalPage: " + totalPages );
	console.log("currentpage: " + size );
  generatePaginationButtons(currentPage, totalPages, size);