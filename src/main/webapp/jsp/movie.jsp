<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello cinematograph</title>
</head>
<body>
<div class="movie">
    <div class="movie_info">
        <h3>Name: ${movie.name}</h3>
        <h4>Description: ${movie.description}</h4>
        <a href="${movie.url}">${movie.url}</a>
    </div>

    <div class="movie_actors">
        <h4>Actors:</h4>
        <c:forEach items="${actors}" var="actor">
            <span>${actor.name} - <a href="${actor.infoUrl}">${actor.infoUrl}</a></span>
            <br/>
        </c:forEach>
    </div>

    <div class="movie_comments">
        <h4>Comments:</h4>
        ${htmlCodeForComments}
    </div>
</div>
</body>
</html>
