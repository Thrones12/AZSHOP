<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
    <form action="insert" method="POST">
        <div>
            <label for="category_id">Category ID:</label><br>
            <input type="text" id="category_id" name="category_id" /><br>
        </div>
        
        <div>
            <label for="category_name">Name:</label><br>
            <input type="text" id="category_name" name="category_name" /><br>
        </div>
        
        <div>
            <input type="submit" value="Category Insert" />
        </div>
    </form>
</body>
</html>
