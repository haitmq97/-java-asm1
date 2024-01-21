<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<tbody class="table-group-divider">
    <!-- loop over and print customer in list -->
    <c:forEach var="tempUser" items="${paginatedData.content}">
        <tr>
            <td>${tempUser.id}</td>
            <td>${tempUser.fullName}</td>
            <td>${tempUser.email}</td>
            <td>${tempUser.phoneNumber}</td>
            <td>${tempUser.address}</td>
            <td>${tempUser.role.roleName}</td>
            <td>${tempUser.status}</td>
        </tr>
    </c:forEach>
</tbody>