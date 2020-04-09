<!DOCTYPE html>
<#assign  security=JspTaglibs["/WEB-INF/security.tld"] />
<@security.authentication var="user" property="principal.user" />
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/bootstrap-grid.css">
    <link rel="stylesheet" href="resources/css/bootstrap-reboot.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
</head>
<body>
<#include "header.ftl">
<#list volunteers as volunteer>
    <div class="row">
        <div class="card" style="width: 18rem;">
            <img class="card-img-top" src="https://picsum.photos/200/150/?random" alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title">Волонтер</h5>
                <p class="card-text">${volunteer.name} ${volunteer.surname}</p>
                <a href="/profile/id${volunteer.id}" class="btn btn-primary">Подробнее</a>
            </div>
        </div>
    </div>
    </#list>
</body>
</html>