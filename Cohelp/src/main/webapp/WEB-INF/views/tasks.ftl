<!DOCTYPE html>

<#assign  security=JspTaglibs["/WEB-INF/security.tld"] />

<html lang="en">
<head>

    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/bootstrap-grid.css">
    <link rel="stylesheet" href="resources/css/bootstrap-reboot.css">
    <script src="https://code.jquery.com/jquery-1.10.2.js"
            type="text/javascript"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>

</head>
<body>
<#include "header.ftl">
<button onclick="receiveTasksForDistrict()">
    Получить для своего района
</button>
<button onclick="receiveTasksForDistance(2)">
    Получить в радиусе двух километров от Вас
</button>
<div id="tasks">

<#list tasks as task>
    <div class="row">
        <div class="card" style="width: 50%; margin: 0 auto">
            <img class="card-img-top" src="" alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title">${task.district} ${task.street}</h5>
                <p class="card-text">${task.description}</p>
                <a href="/task?id=${task.id}" class="btn btn-primary">Подробнее</a>
            </div>
        </div>
    </div>
</#list>
</div>
<script>
    function receiveTasksForDistrict() {
        $.ajax({
            url: "/tasks/district",
            success: function (response) {
                var list = document.getElementById('tasks');
                list.innerHTML = "";
                for (let i=0; i<response.length; i++){
                    var div = document.createElement('div');
                    div.innerHTML = " <div class=\"row\">\n" +
                        "        <div class=\"card\" style=\"width: 50%; margin: 0 auto\">\n" +
                        "            <img class=\"card-img-top\" src=\"\" alt=\"Card image cap\">\n" +
                        "            <div class=\"card-body\">\n" +
                        "                <h5 class=\"card-title\">" +response[i].district+ " "+ response[i].street+ "</h5>\n" +
                        "                <p class=\"card-text\">" +response[i].description+"</p>\n" +
                        "                <a href=\"//task?id="+response[i].id+"\" class=\"btn btn-primary\">Подробнее</a>\n" +
                        "            </div>\n" +
                        "        </div>\n" +
                        "    </div>";
                    $('#tasks').append(div);
                }
            }
        });
    }
    function receiveTasksForDistance(km) {
        $.ajax({
            url: "/tasks/distance?km="+km,
            success: function (response) {
                var list = document.getElementById('tasks');
                list.innerHTML = "";
                for (let i=0; i<response.length; i++){
                    var div = document.createElement('div');
                    div.innerHTML = " <div class=\"row\">\n" +
                        "        <div class=\"card\" style=\"width: 50%; margin: 0 auto\">\n" +
                        "            <img class=\"card-img-top\" src=\"\" alt=\"Card image cap\">\n" +
                        "            <div class=\"card-body\">\n" +
                        "                <h5 class=\"card-title\">" +response[i].district+ " "+ response[i].street+ "</h5>\n" +
                        "                <p class=\"card-text\">" +response[i].description+"</p>\n" +
                        "                <a href=\"//task?id="+response[i].id+"\" class=\"btn btn-primary\">Подробнее</a>\n" +
                        "            </div>\n" +
                        "        </div>\n" +
                        "    </div>";
                    $('#tasks').append(div);
                }
            }
        });
    }
</script>
</body>
</html>