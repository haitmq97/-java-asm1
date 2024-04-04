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

<script
	src="<c:url value='https://code.jquery.com/jquery-3.6.4.min.js'/>"></script>

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
	<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
		<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark"></nav>
		<!-- Navbar Brand-->
		<a class="navbar-brand ps-3" href="/admin/">QUẢN TRỊ</a>
		<!-- Sidebar Toggle-->
		<button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0"
			id="sidebarToggle" href="#!">
			<i class="fas fa-bars"></i>
		</button>
		<!-- Navbar Search-->
		<form
			class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0"></form>
		<!-- Navbar-->
		<ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
			<li class="nav-item dropdown"><a th:if="${session.admin}"
				class="nav-link dropdown-toggle" href="/admin/logout" role="button"><i
					class="fas fa-user fa-fw"></i><span> Logout</span></a>
				<ul class="dropdown-menu dropdown-menu-end"
					aria-labelledby="navbarDropdown1">
					<li><a class="dropdown-item" href="#">Settings</a></li>
					<li><a class="dropdown-item" href="#">Activity Log</a></li>
					<li>
						<hr class="dropdown-divider" />
					</li>
					<li><a class="dropdown-item" href="/logout">Logout</a></li>
				</ul></li>
		</ul>
	</nav>

	<div id="layoutSidenav">
		<div id="layoutSidenav_nav">
			<nav class="sb-sidenav accordion sb-sidenav-dark"
				id="sidenavAccordion">
				<div class="sb-sidenav-menu">
					<div class="nav">
						<a class="nav-link" href="account.html">
							<div class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> Quản lý người dùng
						</a> <a class="nav-link" href="donation.html">
							<div class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> Quản lý đợt quyên góp
						</a>

						<div>
							 <button class="data-table-btn" onclick="window.location.href='${pageContext.request.contextPath}/admin/donations">Donations</button>
				<button class="data-table-btn" onclick="window.location.href='${pageContext.request.contextPath}/admin/users">Users</button>
				<button class="data-table-btn" onclick="window.location.href='${pageContext.request.contextPath}/admin/donates">Donates</button>
							<!-- 
						<script>
						 function changeTable(urlImport) {
						        var url = "${pageContext.request.contextPath}" + urlImport;
						        $.ajax({
						            type: "GET",
						            url: url,
						            success: function(data) {
						                $("#t-content").html($(data).find("#t-content").html());
						            },
						            error: function(xhr, status, error) {
						                console.error("Error occurred:", status, error);
						                console.error("can not get the link", url);
						            }
						        });
						    }
              
              
              $(document).ready(function() {
					$('#donation-m').click(function() {
						changeTable("/admin/donations");
					});
					
					$('#user-m').click(function() {
						changeTable("/admin/users");
					});

					
					
					

				});
				
              </script> -->

						</div>

					</div>
				</div>
			</nav>
		</div>

		<div id="layoutSidenav_content">
			<main>

				<div class="table-content" id="t-content">
					<c:import url="/admin/donations" />
				</div>
			</main>
			<footer class="py-4 bg-light mt-auto">
				<div class="container-fluid px-4"></div>
			</footer>
		</div>
	</div>
	<script>
      $("#exampleModalView1").on("hidden.bs.modal", function (e) {
        $("#exampleModalView1 iframe").attr(
          "src",
          $("#exampleModalView1 iframe").attr("src")
        );
      });
    </script>
	<script>
      $(document).ready(function () {
        $("#autoWidth,#autoWidth2").lightSlider({
          autoWidth: true,
          loop: true,
          onSliderLoad: function () {
            $("#autoWidth,#autoWidth2").removeClass("cS-hidden");
          },
        });
      });
    </script>
	<script>
      ClassicEditor.create(document.querySelector("#editor"))
        .then((eidt) => {
          console.log("da" + eidt);
        })
        .catch((error) => {
          console.error(error);
        });
    </script>
	<script>
      ClassicEditor.create(document.querySelector("#editor1"))
        .then((eidt) => {
          console.log("da" + eidt);
        })
        .catch((error) => {
          console.error(error);
        });
    </script>


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

	<script src="js/JQuery3.3.1.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="js/scripts.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
		crossorigin="anonymous"></script>
	<script src="js/datatables-simple-demo.js"></script>
</body>
</html>
