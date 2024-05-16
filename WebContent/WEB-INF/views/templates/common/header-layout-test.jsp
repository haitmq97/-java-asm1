<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="me.haitmq.spring.mvc.crud.content_path.ViewConstants" %>
<div id="header-sec">
	<c:choose>
		<c:when test="${param.includePart eq 'headerSection'}">
			<!-- Include only the header section -->
			<header class="header header-custom" id="header">
				<div>
					<div class="header-background">
						<div class="header-content">
							<h2>
								<b>Website Quyên góp</b>
							</h2>
							<div class="sub-header-content d-grid col-8"></div>
						</div>
					</div>
					<div class="navbar" id="navbar">

						<ul class="nav-list">
							<li class="nav-item">
								<button class="btn active-b"
									onclick="window.location.href='${pageContext.request.contextPath}${ViewConstants.E_HOME}'">
									<span class="txt-nav-i">Home</span><span class="icon-nav-i"><i
										class="fa fa-home" aria-hidden="true"></i></span>
								</button>

								<% Boolean isLogined = ((Boolean)request.getAttribute("isLogined")) != null ? (Boolean)request.getAttribute("isLogined") : false; %>
								<% Boolean isAdmin = ((Boolean)request.getAttribute("isAdmin")) != null ? (Boolean)request.getAttribute("isAdmin") : false; %>
								<%-- 
								<% Boolean isAdmin = (Boolean)request.getAttribute("isAdmin"); %>
								 --%>
    							<% if (isAdmin) { %>
        							<div class="user-l-btn d-inline-block">
									<div class="btn-group" id="mng-btn">
										<button type="button"
											class="btn dropdown-toggle none-outline-btn-custom"
											data-bs-toggle="dropdown" data-bs-display="static"
											aria-expanded="false">
											<span class="txt-nav-i">Quản lý</span><span class="icon-nav-i"><i
										class="fa-solid fa-list-check"></i></span>
										</button>
										<ul
											class="dropdown-menu dropdown-menu-lg-end dropdown-menu-custom">
											<li>
												<button class="dropdown-item" type="button"
													onclick="window.location.href='${pageContext.request.contextPath}${ViewConstants.E_ADMIN_DONATIONS}'">
													Donations</button>
											</li>
											<li>
												<button class="dropdown-item" type="button"
													onclick="window.location.href='${pageContext.request.contextPath}${ViewConstants.E_ADMIN_USERS}'">
													Users</button>
											</li>
											<li>
												<button class="dropdown-item" type="button"
													onclick="window.location.href='${pageContext.request.contextPath}${ViewConstants.E_ADMIN_USERDONATIONS}'">
													Donates</button>
											</li>
										</ul>
									</div>
									
								</div>
    							<% } %>
								
							</li>

							<li class="nav-item">
								<div class="user-l-btn">
									<% if (isLogined) { %>
									<div class="btn-group" id="user-btn">
										<button type="button"
											class="btn dropdown-toggle none-outline-btn-custom"
											data-bs-toggle="dropdown" data-bs-display="static"
											aria-expanded="false">
											<span class="txt-nav-i"><i
												class="fa-solid fa-user"></i></span><span class="icon-nav-i"><i
												class="fa-solid fa-user"></i></span>
										</button>
										<ul
											class="dropdown-menu dropdown-menu-lg-end dropdown-menu-custom">
											<li>
												<button class="dropdown-item" type="button"
													onclick="window.location.href='${pageContext.request.contextPath}${ViewConstants.E_USER_PROFILE}'">
													Profile</button>
											</li>
											<li>
												<button class="dropdown-item" type="button"
													onclick="window.location.href='${pageContext.request.contextPath}${ViewConstants.E_LOGOUT}'">
													Logout</button>
											</li>
										</ul>
									</div>
									<% } else { %>
									<div class="rs-b" id="rs-b">
										<button class="btn custom-rs-btn"
											onclick="window.location.href='${pageContext.request.contextPath}${ViewConstants.E_REGISTER}'">
											<span class="txt-nav-i">Đăng ký</span><span
												class="icon-nav-i"><i
												class="fa-solid fa-user-plus"></i></span>
										</button>
										<button class="btn custom-rs-btn" onclick="openModal('#login')">
											<span class="txt-nav-i">Đăng nhập</span>
											<span class="icon-nav-i"><i class="fa-solid fa-right-to-bracket"></i></span>
										</button>
									</div>
									<% } %>
									
									
									
								</div>
							</li>
						</ul>
					</div>
				</div>
			</header>
		</c:when>
		<c:otherwise>

		</c:otherwise>
	</c:choose>


</div>