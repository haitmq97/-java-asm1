<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="header-sec">
	<c:choose>
		<c:when test="${param.includePart eq 'headerSection'}">
			<!-- Include only the header section -->
			<header class="header header-custom " id="header">
				<div>
					<div class="header-background"></div>
					<div class="navbar" id="navbar">
						<nav class="nav nav-custom">
							<ul class="nav-list">
								<li class="nav-item register-btn">
									<button
										onclick="window.location.href='${pageContext.request.contextPath}/v1/register'">
										<span class="txt-nav-i">Register</span><span
											class="icon-nav-i"><i class="fa-solid fa-user-plus"></i></span>
									</button>
								</li>
								<li class="nav-item">
									<button>
										<span class="txt-nav-i" onclick="openPopup('login')">Login</span><span
											class="icon-nav-i"><i
											class="fa-solid fa-right-to-bracket"></i></span>
									</button>
								</li>
								<li class="nav-item">
									<button>
										<span class="txt-nav-i">Logout</span><span class="icon-nav-i"><i
											class="fa-solid fa-right-from-bracket"></i></span>
									</button>
								</li>
								<li class="nav-item">
									<button>
										<span class="txt-nav-i">Manager</span><span class="icon-nav-i"><i
											class="fa-solid fa-list-check"></i></span>
									</button>
								</li>
								<li class="nav-item">
									<button onclick="redirectToUserProfile()">
										<span class="txt-nav-i">User</span><span class="icon-nav-i"><i
											class="fa-solid fa-user"></i></span>
									</button>
								</li>
							</ul>
						</nav>
						<div class="custom-bottom-nav"></div>
					</div>
				</div>
			</header>
		</c:when>
		<c:otherwise>
            
        </c:otherwise>
	</c:choose>

	
	<script>
function redirectToUserProfile() {
	window.location.href = '${pageContext.request.contextPath}/v2/detail';
}


function redirectToUserProfile() {
	window.location.href = '${pageContext.request.contextPath}/v2/detail';
}
</script>
</div>