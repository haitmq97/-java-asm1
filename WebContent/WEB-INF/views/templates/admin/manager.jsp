<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
<title>Donation website &mdash; Website Donation</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="author" content="Free-Template.co" />
<%-- 
<link rel="stylesheet"
	href="<c:url value='/static/common/assets/css/reset.css'/>" />
 --%>

<link rel="stylesheet"
	href="<c:url value='https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css'/>" />

<link rel="stylesheet"
	href="<c:url value='/static/admin1/assets/css/styles.css'/>" />
	
	<script src="<c:url value='https://code.jquery.com/jquery-3.6.4.min.js'/>"></script>

<script
	src="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js'/>"
	crossorigin="anonymous"></script>

<script
	src="<c:url value='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js'/>"
	crossorigin="anonymous"></script>
<%-- 
<script src="<c:url value='/static/admin1/assets/js/scripts.js'/>"
	crossorigin="anonymous"></script>

 --%>
<script
	src="<c:url value='https://cdn.jsdelivr.net/npm/simple-datatables@latest'/>"
	crossorigin="anonymous"></script>
<%-- 
<script
	src="<c:url value='static/admin1/assets/js/datatables-simple-demo.js'/>"></script>
 --%>
<script
	src="<c:url value='https://cdn.ckeditor.com/ckeditor5/29.0.0/classic/ckeditor.js'/>"></script>

<!-- customer style -->

<link rel="stylesheet"
	href="<c:url value='/static/common/assets/css/style.css'/>" />

<link rel="stylesheet"
	href="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css'/>"
	integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />



<!-- customer js -->

<script
	src="<c:url value='https://code.jquery.com/jquery-3.6.4.min.js'/>"> </script>
<script src="<c:url value='/static/common/assets/js/form.js' />"></script>
<script src="<c:url value='/static/common/assets/js/script.js' />"></script>
<script src="<c:url value='/static/common/assets/js/data-list.js' />"></script>
</head>
<body class="sb-nav-fixed">
	<div>
		<h3>header</h3>
	</div>
	<div id="test-content">
		<div class='navbar'>
			<div>
			
			
				 <button class="data-table-btn" onclick="window.location.href='${pageContext.request.contextPath}/admin/donations">Donations</button>
				<button class="data-table-btn" onclick="window.location.href='${pageContext.request.contextPath}/admin/users">Users</button>
				<button class="data-table-btn" onclick="window.location.href='${pageContext.request.contextPath}/admin/donates">Donates</button>
							<%--	 
				<input class="data-table-btn" type="button" name="tableBtn" value="donationTable"/>
				<input class="data-table-btn" type="button" name="tableBtn" value="userTable"/>
				<input class="data-table-btn" type="button" name="tableBtn" value="donateTable"/>
				
				--%>				
			</div>
		</div>
		<div class="content">
			<%-- <c:import url="${tableC}"/> --%>
		
		</div>
		
	<!-- 	
		<script>
		function changeTable(tableBtn, tableContent) {

			$.ajax({
				type: "GET",
				url: window.location.href,
				data: {
					tableBtn: tableBtn
				},
				
				success: function(data) {
					$(tableContent).html(
						$(data).find(tableContent).html());
				}
			});
		}

		
		
							
							$(document).ready(function() {
								
								var tableBtns = document.getElementsByClassName("data-table-btn");
						        
						        // Chuyển HTMLCollection thành mảng
						        var btnArray = Array.from(tableBtns);
						        
						        // Lặp qua mỗi nút và thêm sự kiện click cho từng nút
						        btnArray.forEach(function(btn) {
						            btn.addEventListener('click', function() {
						            	changeTable(btn.val(), "#test-content");
						            });
						        });

								
								
					
								
								
								

							});
							
							
							
							
							</script>
		 -->
	
	</div>
	<div>footer</div>
	
<!-- 	<script>
    $(document).ready(function() {
        $("#donation-btn").click(function() {
            var url = $(this).data("url");
            loadContent(url);
        });

        $("#user-btn").click(function() {
            var url = $(this).data("url");
            loadContent(url);
        });

        function loadContent(url) {
            $(".content").load(url);
        }
    });
</script> -->
<!-- <script>
    $(document).ready(function() {
        $("#donation-btn").click(function() {
            var url = "/PRJ321x_Project1_haitmqfx22585/admin/donations"; // Sử dụng đường dẫn tuyệt đối
            loadContent(url);
        });

        $("#user-btn").click(function() {
            var url = "/PRJ321x_Project1_haitmqfx22585/admin/users"; // Sử dụng đường dẫn tuyệt đối
            loadContent(url);
        });

        function loadContent(url) {
            $.get(url, function(data) {
                $(".content").html(data);
            });
        }
    });
</script> -->



<script>
    $(document).ready(function() {
        
        
		var tableBtns = document.getElementsByClassName("data-table-btn");
        
        // Chuyển HTMLCollection thành mảng
        var btnArray = Array.from(tableBtns);
        
        // Lặp qua mỗi nút và thêm sự kiện click cho từng nút
        btnArray.forEach(function(btn) {
            btn.addEventListener('click', function() {
                loadContent(this.dataset.url); // Sử dụng dataset để lấy giá trị của thuộc tính data-url
            });
        });

        function loadContent(url) {
            $.get(url, function(data) {
                $(".content").html(data);
            });
        }
        
        
        
        
    });
</script>


	
</body>
</html>
