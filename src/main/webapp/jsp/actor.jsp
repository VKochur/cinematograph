<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello cinematograph</title>

    <style>

        .actors {
            padding: 2%;
        }

        .actor {
            border-style: inset;
            padding: inherit;
            margin: 1%;
        }
    </style>

</head>

<body>

<div class="page_description">
    <h3>Page for search actors</h3>
    <br>
    Do it like that:
    <br><a href="/cinematograph/actor">/cinematograph/actor</a> find all actors
    <br><a href="/cinematograph/actor?name_contains=паПаНо">/cinematograph/actor?name_contains=паПаНо</a> for get actor
    by name's part
</div>

<div class=actors">

    <h3>${infoAboutActorList}</h3>

    <c:forEach items="${actors}" var="actor">
        <div class="actor">
            <div class="actor_info">
                <h4>Id: ${actor.id}</h4>
                <h3>Name: ${actor.name}</h3>
                <h3>url: <a href="${actor.infoUrl}">${actor.infoUrl}</a></h3>
                <a href="/cinematograph/movie?actor_name_contains=${actor.name}">find movies with ${actor.name}</a>
            </div>
        </div>
    </c:forEach>

</div>
</body>
</html>
