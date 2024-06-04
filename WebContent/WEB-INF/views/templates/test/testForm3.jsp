<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <div>
        <h1>Donation Form test</h1>
        <form:form modelAttribute="donation" action="${process}" method="POST">
            id: <form:input type="hidden" id="donationId" path="id" />
            <br><br>
            code: <form:input type="text" class="form-control" id="code-add" path="code" />
            <form:errors path="code" cssClass="error"/> 
            <br><br>
            name: <form:input type="text" class="form-control" id="name-add" path="name" />
            <form:errors path="name" cssClass="error"/> 
            <br><br>
            start date: <form:input type="date" class="form-control" id="startDate-add" path="startDate" />
            <form:errors path="startDate" cssClass="error"/> 
            <br><br>
            end date: <form:input type="date" class="form-control" id="endDate-add" path="endDate" />
            <form:errors path="endDate" cssClass="error"/> 
            <br><br>
            organization:  <form:input type="text" class="form-control" id="organization-add" path="organization" />
            <form:errors path="organization" cssClass="error"/> 
            <br><br>
            phone number: <form:input type="number" class="form-control" id="phoneNumber-add" path="phoneNumber" />
            <form:errors path="phoneNumber" cssClass="error"/> 
            <br><br>
            note: <form:textarea class="form-control" id="description-add" path="description" rows="3"></form:textarea>
            <br><br>
            <form:errors path="" cssClass="error" />
            <br>
            <input type="submit" value="Submit" />
        </form:form>
    </div>
</body>
</html>
