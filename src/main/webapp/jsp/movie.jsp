<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello cinematograph</title>
</head>
<body>
    <h4>${movie.name}</h4>
    <h3>${movie.description}</h3>
    <a href="${movie.url}">${movie.url}</a>
</body>
</html>
