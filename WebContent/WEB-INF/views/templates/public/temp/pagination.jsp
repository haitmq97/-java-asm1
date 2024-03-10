<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
						<div class="f-content">
						<div class="page-number">
							<ul class="page-btn-list">
								<li><button class="page-btn btn btn-outline-secondary">First</button></li>
								<li class="page-btn-item "><button
										class="page-btn btn btn-outline-secondary">Prev</button></li>
							
								<c:choose>
									
									<c:when test="${totalPage<=4}">
										
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
						<!-- <div class="page-number pn-min">
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
						</div> -->
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

</body>
</html>