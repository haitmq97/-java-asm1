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

<script
	src="<c:url value='https://cdn.jsdelivr.net/npm/simple-datatables@latest'/>"
	crossorigin="anonymous"></script>

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
			
			
				 <button class="data-table-btn" id="donation-btn" data-v="donations"
								data-url="${pageContext.request.contextPath}/admin/donations">Donations</button>
				<button class="data-table-btn" id="user-btn" data-v="users"
								data-url="${pageContext.request.contextPath}/admin/users">Users</button>
				<button class="data-table-btn" id="donate-btn" data-v="donates"
								data-url="${pageContext.request.contextPath}/admin/donates">Donates</button>

</div>



	
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
