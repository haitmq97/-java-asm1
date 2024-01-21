<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer List</title>
</head>
<body>

<h1>Customer List</h1>

<!-- Search input -->
<form action="/customers" method="post">
    <input type="text" name="searchTerm" placeholder="Search by name or email">
    <input type="submit" value="Search">
</form>

<!-- Display customers -->
<div id="customerList">
    <!-- Populate with customers from the server -->
    <c:forEach var="customer" items="${allCustomers}">
        <p>${customer.name} - ${customer.email}</p>
    </c:forEach>
</div>

</body>
</html>