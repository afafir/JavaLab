<!DOCTYPE html>

<#assign  security=JspTaglibs["/WEB-INF/security.tld"] />

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
<#list tasks as task>
    <div class="row">
        <div class="card" style="width: 50%; margin: 0 auto">
            <img class="card-img-top" src="https://picsum.photos/200/150/?random" alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title">${task.consumer.address.district} ${task.consumer.address.street}</h5>
                <p class="card-text">${task.description}</p>
                <a href="/task?id=${task.id}" class="btn btn-primary">Подробнее</a>
            </div>
        </div>
    </div>
</#list>
</body>
</html>