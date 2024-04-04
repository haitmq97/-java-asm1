<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>

	<script src="<c:url value='https://code.jquery.com/jquery-3.6.4.min.js' />"></script>

	
</head>
<body>
	<section class="site-section content" id="m-content">
		<div class="container">
			<div class="row mb-5 justify-content-center">
				<div class="col-md-7 text-center">
					<h2 class="section-title mb-2">Các đợt quyên góp</h2>
				</div>
			</div>


			<div class="container">
				<div class="content">
					<div class="h-content">
						<div class="sp-tool d-flex flex-row justify-content-between mt-3">
							<div class="page-selector">

								<input id="currentPage" type="hidden" name="currentPage"
									value="${currentPage}" /> <label for="size">Rows
									per page:</label> 
								<select id="pageSize" name="size"
									
									class="entries-select rounded form-control">
									<option value="3" ${donations.size == 3 ? 'selected' : ''}>3</option>
									<option value="4" ${donations.size == 4 ? 'selected' : ''}>4</option>
									<option value="5" ${donations.size == 5 ? 'selected' : ''}>5</option>
									<option value="10" ${donations.size == 10 ? 'selected' : ''}>10</option>
									<option value="15" ${donations.size == 15 ? 'selected' : ''}>15</option>
									<option value="20" ${donations.size == 20 ? 'selected' : ''}>20</option>
									<!-- Add more options as needed -->
								</select>
							
			

							</div>
							<div class="search-box">
								<label for="searching-input">Search:</label> <input type="text"
									name="searching-input" id="searchingValue"
									class="searching-input rounded p-2 form-control"
									placeholder="by Code or by status ..."
									value="${searchingValue}"  />
							</div>
						
						</div>
					</div>
					<div class="m-content list" id="donation-list">
						<table class="table table-striped table-content">
							<thead class="tb-head-title">
								<tr>
									<th scope="col" class="th-custom"><p>id</p></th>
									<th scope="col" class="th-custom"><p>Name</p></th>
									<th scope="col" class="th-custom"><p>Code</p></th>
									<th scope="col" class="th-custom"><p>Start date</p></th>
									<th scope="col" class="th-custom"><p>End date</p></th>
									<th scope="col" class="th-custom"><p>Phone number</p></th>
									<th scope="col" class="th-custom"><p>Action</p></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="tempDonation" items="${donations.content}">

									<c:url var="donateLink" value="/v1/donateForm">
										<c:param name="id" value="${tempDonation.id}" />
									</c:url>

									<c:url var="detailLink" value="/v1/donation-detail">
										<c:param name="id" value="${tempDonation.id}" />
									</c:url>



									<tr>
										<td><p>${tempDonation.id}</p></td>
										<th scope="row">
											<p class="d-name">${tempDonation.name}</p>
											<p class="d-status">${tempDonation.status}</p>
										</th>
										<td><p>${tempDonation.code}</p></td>
										<td><p>${tempDonation.startDate}</p></td>
										<td><p>${tempDonation.endDate}</p></td>
										<td><p>${tempDonation.phoneNumber}</p></td>
										<td class="action-c">
											<button class="btn btn-success donation-btn" title="Chi tiết"
												onclick="redirectToDetailLink('${detailLink}')">
												<span class="content-btn-text">Chi tiết</span><span
													class="content-btn-icon"><i class="fa-solid fa-info"></i></span>
											</button>
											<button class="btn btn-success donation-btn"
												title="Quyên góp" onclick="donateForm('${donateLink}')">
												<span class="content-btn-text">Quyên góp</span><span
													class="content-btn-icon"><i
													class="fa-solid fa-circle-dollar-to-slot"></i></span>
											</button>


										</td>
									</tr>
								</c:forEach>


							</tbody>
						</table>
						<div>
							<div>
								<input id="currentPage1" type="hidden" value="${currentPage}" />
								<c:out value="current Pg:  ${currentPage}  " />
								<br> 
								<input id="totalPages1" type="hidden" value="${totalPage}" />
								<c:out value="total Pg:  ${totalPage}  " />
								
	
								<br> <input id="size1" type="hidden" value="${currentSize}" />
								<c:out value="size:  ${currentSize}  " />
								<br> <input id="searchingValue1" type="hidden"
									value="${searchingValue}" />
								<c:out value="searchingValue:  ${searchingValue}  " />
								<br> <input id="importUrl1" type="hidden"
									value="${searchingValue}" />
								<c:out value="searchingValue:  ${searchingValue}  " />
								
								<c:set var="testValue1" value="<c:url value='/v1/donations'/>" />

 
 								
							</div>
							<div id="pagination-container"></div>
						
							<script>
							
							

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
										$('#donation-list').html(
											$(data).find('#donation-list').html());
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

								if (pageNumber === currentPage) {
									/*button.style.backgroundColor = 'red';*/
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

							function onPageButtonClick(pageNumber, size, searchingValue) {

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
										$('#donation-list').html(
											$(data).find('#donation-list').html());
									}
								});

							}


							
							$(document).ready(function() {
								$('#pageSize').change(function() {
									updateShowingTable($('#pageSize').val(), $('#searchingValue').val(), "#donation-list");
								});

								$('#searchingValue').on('input', function() {
									updateShowingTable($('#pageSize').val(), $('#searchingValue').val(), "#donation-list")
								});
								
								
								var currentPage = parseInt(document.getElementById("currentPage1").value, 10);
								var totalPages = parseInt(document.getElementById("totalPages1").value, 10);



								generatePaginationButtons(currentPage, totalPages, $('#pageSize').val(), $('#searchingValue').val());
								
								let btnList = document.getElementsByClassName("page-btn");
								Array.from(btnList).forEach(btn => {
								    if (parseInt(btn.textContent) === currentPage) {
								    	
								        btn.disabled = true;
								    }
								});
								
								

							});
							
							
							
							
							</script>

						</div>

					</div>
					
					
				</div>

			</div>
		</div>
	</section>


	<div id="#donate-popup">
		<c:import url="/v1/donateForm?id=1" />

	</div>
	
<%-- 	
<script src="<c:url value='/static/common/assets/js/script.js' />"></script>

 --%>
<%-- 
<script src="<c:url value='/static/common/assets/js/header.js' />"></script> 

<script src="<c:url value='/static/common/assets/js/form.js' />"></script>
 --%>
<%-- <script src="<c:url value='/static/common/assets/js/pagination.js' />"></script> --%>


</body>
</html>