<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	.error {
		color: red;
	}
</style>

<link rel="stylesheet"
	href="<c:url value='/static/common/assets/css/style.css'/>" />

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	
	<script src="<c:url value='/static/common/assets/js/script.js' />"></script>
<script src="<c:url value='/static/common/assets/js/layout-script.js' />"></script>
</head>
<body>
	<div class="container">
		<div>
			<h3>Content</h3>
			<input type="hidden" id="errorLogin" value="${errorLogin}"/>
			<p>loginUser:</p>
			<p>
				userName: <span>${currentLoginUser.userNameOrEmail}</span>
			</p>
			<p>
				password: <span>${currentLoginUser.password}</span>
			</p>

		</div>
		<div>
			<button onclick="openModal('#testForm')">Click me</button>
		</div>
		
		
		
		
		<div class="overlay-container">
		<div class="row">
			<div id="overlay" onclick="closeAllPopup()"></div>
			<div class="popup col-12 col-sm-8 col-md-4">

				<div class="form-container donate-form " id="testForm">
					
					<div>
			<form:form modelAttribute="loginUser" class="" action="${pageContext.request.contextPath}/v22/processtest"
				method="POST">
			<div class="form-group">
				<label for="userName">username or email: </label>
				
				<form:input type='text' id="userName" class="form-control" path="userNameOrEmail" />
				<br>
				<form:errors path="userNameOrEmail" cssClass="error"/>
			</div>
				
			<div class="form-group">
				<label for="password">password: </label>
				<form:input type='text' id="password" class="form-control" path="password" />
				
				<br>
				<form:errors path="password" cssClass="error"/>
			</div>
			
			<div>
			
				<c:if test="${!theBindingResult.hasFieldErrors('userNameOrEmail') && !theBindingResult.hasFieldErrors('password')}">
    				<form:errors path="*" cssClass="error"/>
				</c:if>

			
			</div>
			
			<button type="submit" class="btn btn-primary">Submit</button>
			</form:form>

		</div>

				</div>

			

		</div>
		
		
		</div>
	
		
		
	</div>




	</div>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
		integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
		integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
		crossorigin="anonymous"></script>
		
		
		
		
		<script type="text/javascript">
	
		$(document).ready(function() {
			 // Kiểm tra nếu phần tử #errorLoginOrRegister có giá trị
		    if ($("#errorLogin").val() === 'true') {
		     openModal("#testForm");
		    } else {
		       
		    }
		    
			 console.log("test: " + $("#errorLogin").val());
		   
		});
	
	</script>
		
</body>
</html>