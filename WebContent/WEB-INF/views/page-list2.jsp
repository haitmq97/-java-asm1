<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <!-- Các thẻ head -->
    <title>Your Entity Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
</head>
<body>
<h1>Your Entity Page</h1>

<!-- Form rows per page -->
<form action="" method="get">
    <label for="pageSize">Rows per page:</label>
    <select id="pageSize" name="size" onchange="this.form.submit()">
        <option value="5" ${paginatedData.size == 5 ? 'selected' : ''}>5</option>
        <option value="10" ${paginatedData.size == 10 ? 'selected' : ''}>10</option>
        <option value="15" ${paginatedData.size == 15 ? 'selected' : ''}>15</option>
        <option value="20" ${paginatedData.size == 20 ? 'selected' : ''}>20</option>
        <!-- Add more options as needed -->
    </select>
    <noscript>
        <input type="submit" value="Submit">
    </noscript>
</form>

<!-- Form searching -->
<form action="" method="get">
    <input type="text" id="searchingValue" name="searchingValue" value="${searchingValue}"
           placeholder="Search by Email or Phone number"
           oninput="this.form.submit()">
    <noscript>
        <input type="submit" value="Submit">
    </noscript>
</form>

<!-- Table -->
<table class="table table-bordered table-striped border-black table-success">
    <thead>
    <tr class="table-dark">
        <th>Id</th>
        <th>full name</th>
        <th>email</th>
        <th>phone number</th>
        <th>address</th>
        <th>role</th>
        <th>status</th>
    </tr>
    </thead>

    <!-- Div to hold the user list fragment -->
    <div id="userListFragment">
        <%@include file="user-list-fragment.jsp" %>
    </div>
</table>

<!-- Pagination -->
<div>
    <ul>
        <c:forEach var="i" begin="${paginatedData.number}" end="${paginatedData.totalPages}">
            <li><a href="?page=${i}&size=${paginatedData.size}">${i}</a></li>
        </c:forEach>
    </ul>

    <!-- ... -->

    <script src="https://code.jquery.com/jquery-3.6.4.min.js"
            integrity="sha256-oP6HI/tZ1oPBlJUj8xxUCsaD/TtU5W9t6VUc1i0pE6k="
            crossorigin="anonymous"></script>

    <script>
        $(document).ready(function () {
            $("#searchingValue").on("input", function () {
                var searchingValue = $(this).val();
                var page = ${currentPage};
                var size = ${currentSize};

                $.ajax({
                    url: "/user/listByPageAjax",
                    type: "GET",
                    data: {
                        page: page,
                        size: size,
                        searchingValue: searchingValue
                    },
                    success: function (data) {
                        $("#userListFragment").html(data);
                    }
                });
            });
        });
    </script>

</div>

</body>
</html>
