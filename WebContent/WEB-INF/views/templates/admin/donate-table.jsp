<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>

	<script src="<c:url value='https://code.jquery.com/jquery-3.6.4.min.js' />"></script>
<script src="<c:url value='/static/common/assets/js/data-list.js' />"></script>
	
</head>
<body>
	<section class="site-section content">
		<div class="container" id="content-table">
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
									<option value="3" ${donates.size == 3 ? 'selected' : ''}>3</option>
									<option value="4" ${donates.size == 4 ? 'selected' : ''}>4</option>
									<option value="5" ${donates.size == 5 ? 'selected' : ''}>5</option>
									<option value="10" ${donates.size == 10 ? 'selected' : ''}>10</option>
									<option value="15" ${donates.size == 15 ? 'selected' : ''}>15</option>
									<option value="20" ${donates.size == 20 ? 'selected' : ''}>20</option>
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
					<div class="m-content list" id="donate-list">
						<table class="table table-striped table-content">
							<thead class="tb-head-title">
								<tr>
									<th scope="col" class="th-custom"><p>id</p></th>
									<th scope="col" class="th-custom"><p>Created Date</p></th>
									<th scope="col" class="th-custom"><p>Name</p></th>
									
									<th scope="col" class="th-custom"><p>Money</p></th>
									<th scope="col" class="th-custom"><p>Note</p></th>
									<th scope="col" class="th-custom"><p>UserName</p></th>
									<th scope="col" class="th-custom"><p>Donation Code</p></th>
									<th scope="col" class="th-custom"><p>Status</p></th>
									<th scope="col" class="th-custom"><p>Action</p></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="tempDonate" items="${donates.content}">

									<c:url var="donateLink" value="/v1/donateForm">
										<c:param name="id" value="${tempDonate.id}" />
									</c:url>

									<c:url var="detailLink" value="/v1/donate-detail">
										<c:param name="id" value="${tempDonate.id}" />
									</c:url>



									<tr>
										<td><p>${tempDonate.id}</p></td>
										<td><p>${tempDonate.createdDate}</p></td>
										<td><p>${tempDonate.name}</p></td>
										<td><p>${tempDonate.money}</p></td>
										<td><p>${tempDonate.note}</p></td>
										<td><p>${tempDonate.user.userName}</p></td>
										<td><p>${tempDonate.donation.code}</p></td>
										<td><p>${tempDonate.status}</p></td>
										<td class="action-c">
											<button class="btn btn-success donate-btn" title="Chi tiết">
												<span class="content-btn-text">Xác nhận</span><span
													class="content-btn-icon"></span>
											</button>
											<button class="btn btn-success donate-btn" title="Chi tiết">
												<span class="content-btn-text">Xóa</span><span
													class="content-btn-icon"></span>
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
								
								<c:set var="testValue1" value="<c:url value='/v1/donates'/>" />

 
 								
							</div>
							<div id="pagination-container"></div>
						
							<script>
							
							$(document).ready(function() {
								$('#pageSize').change(function() {
									updateShowingTable($('#pageSize').val(), $('#searchingValue').val(), "#donate-list");
								});

								$('#searchingValue').on('input', function() {
									updateShowingTable($('#pageSize').val(), $('#searchingValue').val(), "#donate-list")
								});
								
								
								var currentPage = parseInt(document.getElementById("currentPage1").value, 10);
								var totalPages = parseInt(document.getElementById("totalPages1").value, 10);



								generatePaginationButtons(currentPage, totalPages, $('#pageSize').val(), $('#searchingValue').val(), "#donate-list");
								
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