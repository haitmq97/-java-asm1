<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="me.haitmq.spring.mvc.crud.content_path.ViewConstants" %>
<%@ page import="me.haitmq.spring.mvc.crud.utils.JSPDataFormat" %>
<%@ page import="me.haitmq.spring.mvc.crud.utils.Time" %>
<%@ page import="me.haitmq.spring.mvc.crud.entity.status.DonationStatus" %>

<!DOCTYPE html>
<html>
<head>

<!-- <link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
	integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />

 -->
</head>
<body>
	<div id="footer-sec">
		<c:choose>
			<c:when test="${param.includePart eq 'footerSection'}">

				<footer>
					<div class="footer">
						<div class="container">
							<div class="content-footer col-md-12 m-auto">
								<div class="footer-item footer-content-col">
									<h4 class="fi-title">Giới thiệu</h4>
									<ul>
										<li>Hỗ trợ</li>
										<li>Bảo mật</li>
										<li>Điều khoản</li>
									</ul>
								</div>
								<div class="footer-item footer-content-col">
									<h4 class="fi-title">Thông tin</h4>
									<ul>
										<li>Đợt quyên góp</li>
										<li>Nhà quyên góp</li>
									</ul>
								</div>
								<div class="footer-item footer-content-col">
									<h4 class="fi-title">Liên hệ</h4>
									<ul>
										<li><i class="fa-solid fa-mobile"></i> 0329420606</li>
										<li><i class="fa-regular fa-envelope"></i>
											haitran97@gmail.com</li>
									</ul>
								</div>
								<div class="footer-item footer-content-col">
									<h4 class="fi-title">Kết nối</h4>
									<ul class="conecction-icon-list">
										<li><a href=""><i class="fa-brands fa-facebook"></i></a></li>
										<li><a href=""><i class="fa-brands fa-twitter"></i></a></li>
										<li><a href=""><i class="fa-brands fa-linkedin"></i></a></li>
										<li><a href=""><i class="fa-brands fa-github"></i></a></li>
									</ul>
								</div>
								<div class="footer-item">
									<p>
										Copyright <span> &copy;</span>2024 All rights reserved
									</p>
								</div>
							</div>

						</div>

					</div>
				</footer>
			</c:when>
			<c:otherwise>

			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>