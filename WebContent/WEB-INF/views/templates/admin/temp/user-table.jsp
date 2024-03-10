<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Donation website &mdash; Website Donation</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="author" content="Free-Template.co" />

<link rel="stylesheet"
	href="<c:url value='/static/common/assets/css/reset.css'/>" />

<link rel="shortcut icon" href="ftco-32x32.png">

<link rel="stylesheet"
	href="<c:url value='/static/common/assets/css/content-style.css' />">

<link rel="stylesheet"
	href="<c:url value='/static/user/assets/css/custom-bs.css' />">
<link rel="stylesheet"
	href="<c:url value='/static/user/assets/css/jquery.fancybox.min.css' />">
<link rel="stylesheet"
	href="<c:url value='/static/user/assets/css/bootstrap-select.min.css' />">
<link rel="stylesheet"
	href="<c:url value='/static/user/assets/fonts/icomoon/style.css' />">
<link rel="stylesheet"
	href="<c:url value='/static/user/assets/fonts/line-icons/style.css' />">
<link rel="stylesheet"
	href="<c:url value='/static/user/assets/css/owl.carousel.min.css' />">
<link rel="stylesheet"
	href="<c:url value='/static/user/assets/css/animate.min.css' />">

<!-- MAIN CSS -->
<link rel="stylesheet"
	href="<c:url value='/static/user/assets/css/style.css' />">

<script src="<c:url value='/static/user/assets/js/jquery.min.js' />"></script>
<script
	src="<c:url value='/static/user/assets/js/bootstrap.bundle.min.js' />"></script>
<script
	src="<c:url value='/static/user/assets/js/isotope.pkgd.min.js' />"></script>
<script src="<c:url value='/static/user/assets/js/stickyfill.min.js' />"></script>
<script
	src="<c:url value='/static/user/assets/js/jquery.fancybox.min.js' />"></script>
<script
	src="<c:url value='/static/user/assets/js/jquery.easing.1.3.js' />"></script>

<script
	src="<c:url value='/static/user/assets/js/jquery.waypoints.min.js' />"></script>
<script
	src="<c:url value='/static/user/assets/js/jquery.animateNumber.min.js' />"></script>
<script
	src="<c:url value='/static/user/assets/js/owl.carousel.min.js' />"></script>
<script
	src="<c:url value='/static/user/assets/js/bootstrap-select.min.js' />"></script>
<script src="<c:url value='/static/user/assets/js/custom.js' />"></script>

<script
	src="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js' />"
	crossorigin="anonymous"></script>
<script
	src="<c:url value='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' />"
	crossorigin="anonymous"></script>
<script
	src="<c:url value='https://unpkg.com/sweetalert/dist/sweetalert.min.js' />"></script>

<link rel="stylesheet"
	href="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css'/>"
	integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet"
	href="<c:url value='/static/common/assets/css/header-style.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/static/common/assets/css/donation-table-home-style.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/static/common/assets/css/footer-style.css' />">

</head>
<body>
	<section class="site-section content">
		<div class="container">
			<div class="row mb-5 justify-content-center">
				<div class="col-md-7 text-center">
					<h2 class="section-title mb-2">Danh sách User</h2>
				</div>
			</div>


			<div class="container">
				<div class="content">
					<div class="h-content">
						<div class="sp-tool d-flex flex-row justify-content-between mt-3">
							<div class="page-selector">

								<input id="currentPage" type="hidden" name="currentPage"
									value="${currentPage}" /> <label for="pageSize">Rows
									per page:</label> 
								<select id="pageSize" name="size"
									onchange="entriesChange()"
									class="entries-select rounded form-control">
									<option value="3" ${users.size == 3 ? 'selected' : ''}>3</option>
									<option value="4" ${users.size == 4 ? 'selected' : ''}>4</option>
									<option value="5" ${users.size == 5 ? 'selected' : ''}>5</option>
									<option value="10" ${users.size == 10 ? 'selected' : ''}>10</option>
									<option value="15" ${users.size == 15 ? 'selected' : ''}>15</option>
									<option value="20" ${users.size == 20 ? 'selected' : ''}>20</option>
									<!-- Add more options as needed -->
								</select>


							</div>
							<div class="search-box">
								<label for="searching-input">Search:</label> 
								<input type="text" 
									name="searching-input" id="searching-input"
									class="searching-input rounded p-2 form-control"
									placeholder="by Code or by status ..."
									value="${searchingValue}" oninput="search(this.value)" />
							</div>
						</div>
						<div>
							<input type="button" value="Add User"
								onclick="window.location.href='addUser'; return false"
									class="mt-2 mb-2 btn btn-sm btn-primary" />
						</div>
					</div>
					<div class="m-content list" id="user-list">
						<table class="table table-striped table-content">
							<thead class="tb-head-title">
								<tr>
									<th scope="col" class="th-custom"><p>id</p></th>
									<th scope="col" class="th-custom"><p>Name</p></th>
									<th scope="col" class="th-custom"><p>Email</p></th>
									<th scope="col" class="th-custom"><p>Phone Number</p></th>
									<th scope="col" class="th-custom"><p>UserName</p></th>
									<th scope="col" class="th-custom"><p>Status</p></th>
									<th scope="col" class="th-custom"><p>Action</p></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="tempUser" items="${users.content}">


									<c:url var="detailLink" value="/user/details">
										<c:param name="userId" value="${tempUser.id}" />
									</c:url>
									
									<c:url var="deleteLink" value="/user/delete">
										<c:param name="userId" value="${tempUser.id}" />
									</c:url>
									
									<c:url var="updateLink" value="/user/updateUser">
										<c:param name="userId" value="${tempUser.id}" />
									</c:url>
									
									<c:url var="changeStatusLink" value="/user/changeStatus">
										<c:param name="userId" value="${tempUser.id}" />
									</c:url>


									<tr>
										<td><p>${tempUser.id}</p></td>
										<th scope="row">
											<p class="d-name">${tempUser.fullName}</p>
										</th>
										<td><p>${tempUser.email}</p></td>
										<td><p>${tempUser.phoneNumber}</p></td>
										<td><p>${tempUser.userName}</p></td>
										<td><p>${tempUser.status}</p></td>
										<td class="action-c">
											<div>
												<button class="btn btn-success" title="Chi tiết"
												onclick="">
												<span class="content-btn-text">Chi tiết</span><span
													class="content-btn-icon"><i class="fa-solid fa-info"></i></span>
											</button>
											<button class="btn btn-success" title="Sửa"
												onclick="">
												<span class="content-btn-text">Sửa</span><span
													class="content-btn-icon"><i
													class="fa-solid fa-circle-dollar-to-slot"></i></span>
											</button>
											
											</div>
											<div>
												<button class="btn btn-success" title="Xóa"
												onclick="">
												<span class="content-btn-text">Xóa</span><span
													class="content-btn-icon"><i class="fa-solid fa-info"></i></span>
											</button>
											<button class="btn btn-success" title="Khóa"
												onclick="">
												<span class="content-btn-text">Khóa</span><span
													class="content-btn-icon"><i
													class="fa-solid fa-circle-dollar-to-slot"></i></span>
											</button>
											<button class="btn btn-success" title="Mở"
												onclick="">
												<span class="content-btn-text">Mở</span><span
													class="content-btn-icon"><i
													class="fa-solid fa-circle-dollar-to-slot"></i></span>
											</button>
											</div>
											
											
											
											 <script>
												

												function redirectToDetailLink() {
													window.location.href = "${detailLink}";
												}
												function redirectToDeleteLink() {
													window.location.href = "${deleteLink}";
												}
												function redirectToUpdateLink() {
													window.location.href = "${updateLink}";
												}
												function redirectToChangeStatusLink() {
													window.location.href = "${changeStatusLink}";
												}
											</script>
										</td>
									</tr>
								</c:forEach>


							</tbody>
						</table>
						
						<div class="f-content">
						<div class="page-number">
							<ul class="page-btn-list">
								<li><button class="page-btn btn btn-outline-secondary">First</button></li>
								<li class="page-btn-item "><button
										class="page-btn btn btn-outline-secondary">Prev</button></li>
							
								<c:choose>
									
									<c:when test="${currentPage==1}">
            							<li class="page-btn-item"><button
										class="page-btn btn btn-outline-secondary" >${currentPage}</button></li>
										<li class="page-btn-item"><button
										class="page-btn btn btn-outline-secondary">${currentPage+1}</button></li>
										<li class="page-btn-item tdot-i"><i
									class="fa-solid fa-ellipsis"></i></li>
         							</c:when>

									<c:when test="${currentPage < totalPage}">
							            <li class="page-btn-item tdot-i"><i
									class="fa-solid fa-ellipsis"></i></li>
								<li class="page-btn-item"><button
										class="page-btn btn btn-outline-secondary">${currentPage-1}</button></li>
								<li class="page-btn-item"><button
										class="page-btn btn btn-outline-secondary">${currentPage}</button></li>
								<li class="page-btn-item"><button
										class="page-btn btn btn-outline-secondary">${currentPage+1}</button></li>
								<li class="page-btn-item tdot-i"><i
									class="fa-solid fa-ellipsis"></i></li>
							         </c:when>

									<c:otherwise>
										<li class="page-btn-item tdot-i"><i
											class="fa-solid fa-ellipsis"></i></li>
							            <li class="page-btn-item"><button
										class="page-btn btn btn-outline-secondary">${currentPage-1}</button></li>
										<li class="page-btn-item"><button
										class="page-btn btn btn-outline-secondary">${currentPage}</button></li>
										
							         </c:otherwise>
								</c:choose>
<!-- 
								
								<li class="page-btn-item tdot-i"><i
									class="fa-solid fa-ellipsis"></i></li>
								<li class="page-btn-item"><button
										class="page-btn btn btn-outline-secondary">3</button></li>
								<li class="page-btn-item"><button
										class="page-btn btn btn-outline-secondary">4</button></li>
								<li class="page-btn-item"><button
										class="page-btn btn btn-outline-secondary">5</button></li>
								<li class="page-btn-item tdot-i"><i
									class="fa-solid fa-ellipsis"></i></li>
								<li class="page-btn-item"><button
										class="page-btn btn btn-outline-secondary">Next</button></li>
								<li class="page-btn-item"><button
										class="page-btn btn btn-outline-secondary">Last</button></li> -->
										<li class="page-btn-item"><button
										class="page-btn btn btn-outline-secondary">Next</button></li>
								<li class="page-btn-item"><button
										class="page-btn btn btn-outline-secondary">Last</button></li>
							</ul>
						</div>
						<div class="page-number pn-min">
							<ul class="page-btn-list">
								<li><button class="page-btn btn btn-outline-secondary">
										<i class="fa-solid fa-angles-left"></i>
									</button></li>
								<li class="page-btn-item "><button
										class="page-btn btn btn-outline-secondary">
										<i class="fa-solid fa-angle-left"></i>
									</button></li>
								<li class="page-btn-item tdot-i"><i
									class="fa-solid fa-ellipsis"></i></li>

								<li class="page-btn-item"><button
										class="page-btn btn btn-outline-secondary">4</button></li>

								<li class="page-btn-item tdot-i"><i
									class="fa-solid fa-ellipsis"></i></li>
								<li class="page-btn-item"><button
										class="page-btn btn btn-outline-secondary">
										<i class="fa-solid fa-angle-right"></i>
									</button></li>
								<li class="page-btn-item"><button
										class="page-btn btn btn-outline-secondary">
										<i class="fa-solid fa-angles-right"></i>
									</button></li>
							</ul>
						</div>
					</div>
					</div>
					

					<%-- <div class="pagebtn">

						<div>
							<p>
								<a href="?page=1&size=${currentSize}">first</a>
							</p>
							<p>
								<a href="?page=${prevPage}&size=${currentSize}">previous</a>
							</p>
							<p>
								<a href="?page=${nextPage}&size=${currentSize}">next</a>
							</p>
							<p>
								<a href="?page=${donations.totalPages}&size=${currentSize}">last</a>
							</p>
						</div>

					</div> --%>

				</div>
			</div>

		</div>
	</section>
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"
		crossorigin="anonymous"></script>

	<script>
		function entriesChange() {

			var size = $("#pageSize").val();
			console.log(" page size: |" + size + "|");
			var page = 1;
			console.log(" current page: |" + page + "|");
			
			var searchingValue = document.getElementById("searching-input").value;
			console.log(" searching value: |" + searchingValue+ "|");

			$.ajax({
				type : "GET",
				url : "<c:url value='/test/userlist'> </c:url>",
				data : {
					size : size,
					page : page,
					searchingValue : searchingValue 
				},
				success : function(data) {
					$("#user-list").html(
							$(data).find("#user-list").html());
				}
			});
		}
		function search(searchingValue) {

			console.log(" searching value: |" + searchingValue + "|");
			var size = $("#pageSize").val();
			$.ajax({
				type : "GET",
				url : "<c:url value='/test/userlist'> </c:url>",
				data : {
					searchingValue : searchingValue,
					size: size
				},
				success : function(data) {
					$("#user-list").html(
							$(data).find("#user-list").html());
				}
			});
		}
		

		
		function changePage() {
		    /* console.log(" searching value: |" + searchingValue + "|"); */
		    var pageButtons = document.querySelectorAll(".page-btn");
		    console.log(" number button: |" + pageButtons + "|");
		    
		    var searchingValue = document.getElementById("searching-input").value;
		    
		    console.log("searching value: " + searchingValue);
		    /* pageButtons.forEach(function(button, index) {
		        console.log("Button " + (index + 1) + ":", button);
		    }); */
		    pageButtons.forEach(function(button) {
		        button.addEventListener("click", function(event) {
		            // Lấy thông tin về button được click và hiển thị trong console
		            console.log("Button clicked:", button);
		        });
		    });
		    
		    /* pageButtons.forEach(function(button) {
		        button.addEventListener('click', function() {
		          // Lấy nội dung của button được click và hiển thị nó
		          var content = button.innerHTML;
		          console.log(content);
		        });
		      }); */
		    
		   /*  // Tạo một mảng để lưu trữ các promise từ các cuộc gọi AJAX
		    var ajaxPromises = [];

		    pageButtons.forEach(function(button) {
		        var page = button.textContent || button.innerText;
		        console.log("value: " +page);

		        // Tạo promise từ cuộc gọi AJAX và đẩy vào mảng
		        var ajaxPromise = $.ajax({
		            type: "GET",
		            url: "<c:url value='/donation/list'></c:url>",
		            data: {
		           searchingValue: searchingValue, 
		                page: page,
		                searchingValue: searchingValue
		            },
		            success: function(data) {
		                $("#donation-list").html($(data).find("#donation-list").html());
		            },
		            error: function(xhr, status, error) {
		                console.error("AJAX error:", error);
		            }
		        });

		        ajaxPromises.push(ajaxPromise);
		    });

		    // Sử dụng Promise.all để đợi tất cả các cuộc gọi AJAX hoàn thành
		    Promise.all(ajaxPromises)
		        .then(function() {
		            console.log("All AJAX requests completed successfully.");
		            // Thực hiện các hành động khác nếu cần
		        })
		        .catch(function(error) {
		            console.error("One or more AJAX requests failed:", error);
		        }); */
		}
		
		document.addEventListener("DOMContentLoaded", function() {
	        var pageButtons = document.querySelectorAll(".page-btn");

	        pageButtons.forEach(function(button) {
	            button.addEventListener("click", function(event) {
	            	var size = $("#pageSize").val();
	    			console.log(" page size: |" + size + "|");
	    			var page = button.value;
	    			console.log(" change to page: |" + page + "|");
	    			
	    			var searchingValue = document.getElementById("searching-input").value;
	    			console.log(" searching value: |" + searchingValue+ "|");

	    			$.ajax({
	    				type : "GET",
	    				url : "<c:url value='/test/userlist'> </c:url>",
	    				data : {
	    					size : size,
	    					page : page,
	    					searchingValue : searchingValue 
	    				},
	    				success : function(data) {
	    					$("#user-list").html(
	    							$(data).find("#user-list").html());
	    				}
	    			});
	            });
	        });
	    });


	</script>
</body>
</html>