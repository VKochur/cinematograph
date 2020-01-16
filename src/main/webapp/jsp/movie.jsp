<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello cinematograph</title>
</head>

<style>
        .movies {
            padding: 2%;
        }

        .movie {
            border-style: inset;
            padding: inherit;
            margin: 1%;
        }

        .movie_comments {
            border-style: inherit;
            padding: inherit;
        }

</style>

<body>

<div class="page_description">
    <h3>Page for search movies</h3>
    You have to specify QueryParam in order to get movies.
    <br>
    Do it like that:
    <br><a href="/cinematograph/movie?movie_id=1">/cinematograph/movie?movie_id=1</a>, for get movie by id
    <br><a href="/cinematograph/movie?movie_name_contains=Ерминато">/cinematograph/movie?movie_name_contains=Ерминато</a>, for get movie by name's part
    <br><a href="/cinematograph/movie?actor_name_contains=ронов">/cinematograph/movie?actor_name_contains=ронов</a>, for get movie by actor's name's part
    <br><a href="/cinematograph/movie?tags=Комедия,Боевик">/cinematograph/movie?tags=Комедия,Боевик</a>, for get movies that are suitable at least one tag
    <br><br>Multi specify parameters not supported: if specific several parameters, only one is used.
    <br>Parameters by priority: movie_id, movie_name_contain, actor_name_contain, tags
    <br>So  <a href="/cinematograph/movie?movie_name_contains=Ерминато&movie_id=1">/cinematograph/movie?movie_name_contains=Ерминато&movie_id=1</a> is search by id, not by name
</div>

<div class="movies">

    <h3>${infoAboutMoviesList}</h3>

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
