<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>My Page</title>
</head>
<body>
    <% boolean showDiv = (Boolean)request.getAttribute("showDiv"); %>
    <% if (showDiv) { %>
        <div>This div is shown</div>
    <% } else { %>
        <div>This div is hidden</div>
    <% } %>
</body>
</html>