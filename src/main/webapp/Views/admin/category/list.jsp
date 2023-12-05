<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
    <h5><a href="<c:url value='/admin/category/insert'></c:url>">Insert</a></h5>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Action</th>
        </tr>
        <c:forEach var="category" items="${listcate}" >
            <tr>
                <td>${category.category_id}</td>
                <td>${category.category_name}</td>
                <td>
                    <a href="<c:url value='/admin/category/update?category_id=${category.category_id}'></c:url>">Update</a>
                    <label> || </label>
                    <a href="<c:url value='/admin/category/delete?category_id=${category.category_id}'></c:url>">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
