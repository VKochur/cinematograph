<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello cinematograph</title>
</head>
<body>

<div class="page_description">
    Page for search movies
    <br>
    You have to specify QueryParam in order to get movies.
    <br>
    Do it like that:
    <br><a href="/cinematograph/movie?movie_id=1">/cinematograph/movie?movie_id=1</a>, for get movie by id
    <br><a href="/cinematograph/movie?movie_name_contains=Ерминато">/cinematograph/movie?movie_name=Ерминато</a>, for get movie by name's part
    <br><a href="/cinematograph/movie?tags=Комедия,Боевик">/cinematograph/movie?tags=Комедия,Боевик</a>, for get movies that are suitable at least one tag
</div>

<div class="movies">

    <h4>${infoAboutMoviesList}</h4>

    <c:forEach items="${movies}" var="movie">

        <div class="movie">
            <div class="movie_info">
                <h4>Id: ${movie.data.id}</h4>
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
