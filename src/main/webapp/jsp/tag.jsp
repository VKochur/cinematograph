<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello cinematograph</title>
</head>

<body>

<div class="page_description">
    <h3>Page for search tags</h3>
</div>

<div class=tags">

    <h3>${infoAboutTagList}</h3>

    <c:forEach items="${tags}" var="tag">
        <div class="tag">
            <div class="tag_info">
                <a href="/cinematograph/movie?tags=${tag.name}">Id: ${tag.id} Name: ${tag.name}</a>
            </div>
        </div>
    </c:forEach>

</div>
</body>
</html>
