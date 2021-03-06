<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="pl-PL">
<head>
    <meta charset="UTF-8">
    <title>Users</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <style>
        body {
            background: #f5f5f5
        }

        .border-bottom {
            border-bottom: 1px solid #e5e5e5;
        }

        .box-shadow {
            box-shadow: 0 .25rem .75rem rgba(0, 0, 0, .05);
        }
    </style>
</head>
<body>
<main role="main" class="container">

    <%@include file="header.jsp" %>

    <div class="my-3 p-3 bg-white rounded box-shadow">
        <h6 class="border-bottom border-gray pb-2 mb-0">Following</h6>
        <c:forEach items="${followedUsers}" var="followedUsers">
            <div class="media text-muted pt-3">
                <img src="https://bootdey.com/img/Content/avatar/avatar4.png" alt="" class="mr-2 rounded" width="32"
                     height="32">
                <p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
                    <strong class="d-block text-gray-dark">${followedUsers.login}</strong>
                    <strong class="d-block text-gray-dark">${followedUsers.name}</strong>
                    <strong class="d-block text-gray-dark">
                        On twitter since :
                        <fmt:formatDate value="${followedUsers.registeredSince}" pattern="yyyy-MM-dd"/> </strong>
                    <a href="unfollow?userLoginToUnfollow=${followedUsers.login}"><b>Unfollow</b></a>
                </p>
            </div>
        </c:forEach>
    </div>
    <div class="my-3 p-3 bg-white rounded box-shadow">
        <h6 class="border-bottom border-gray pb-2 mb-0">Followers</h6>
        <c:forEach items="${followers}" var="follower">
            <div class="media text-muted pt-3">
                <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="" class="mr-2 rounded" width="32"
                     height="32">
                <p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
                    <strong class="d-block text-gray-dark">${follower.login}</strong>
                    <strong class="d-block text-gray-dark">${follower.name}</strong>
                    <strong class="d-block text-gray-dark">
                        On twitter since :
                        <fmt:formatDate value="${follower.registeredSince}" pattern="yyyy-MM-dd"/> </strong>
                </p>
            </div>
        </c:forEach>
    </div>
    <div class="my-3 p-3 bg-white rounded box-shadow">
        <h6 class="border-bottom border-gray pb-2 mb-0">Others</h6>
        <c:forEach items="${notFollowedUsers}" var="notFollowedUser">
            <div class="media text-muted pt-3">
                <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="" class="mr-2 rounded" width="32"
                     height="32">
                <p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
                    <strong class="d-block text-gray-dark">${notFollowedUser.login}</strong>
                    <strong class="d-block text-gray-dark">${notFollowedUser.name}</strong>
                    <strong class="d-block text-gray-dark">${notFollowedUser.lastName}</strong>
                    <strong class="d-block text-gray-dark">
                        On Twitter since :
                        <fmt:formatDate value="${notFollowedUser.registeredSince}" pattern="yyyy-MM-dd"/> </strong>
                    </br>
                    <a href="follow?userLoginToFollow=${notFollowedUser.login}"><b>Follow</b></a>
                </p>
            </div>
        </c:forEach>
    </div>
</main>
</body>
</html>
