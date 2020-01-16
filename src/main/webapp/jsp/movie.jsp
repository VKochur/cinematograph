<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello cinematograph</title>
</head>
<body>

<div class="movies">

    <c:forEach items="${movies}" var="movie">

        <div class="movie">
            <div class="movie_info">
                <h3>Name: ${movie.data.name}</h3>
                <h4>Description: ${movie.data.description}</h4>
                <a href="${movie.data.url}">${movie.data.url}</a>
            </div>

            <div class="movie_actors">
                <h4>Actors:</h4>
                <c:forEach items="${movie.actors}" var="actor">
                    <span>${actor.name} - <a href="${actor.infoUrl}">${actor.infoUrl}</a></span>
                    <br/>
                </c:forEach>
            </div>

            <div class="movie_comments">
                <h4>Comments:</h4>
                    ${movie.htmlCodeComments}
            </div>
        </div>

    </c:forEach>

</div>
</body>
</html>
