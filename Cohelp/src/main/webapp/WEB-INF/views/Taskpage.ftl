<#assign  security=JspTaglibs["/WEB-INF/security.tld"] />
<@security.authorize access=" isAuthenticated()">
    <@security.authentication var="user" property="principal.user" />
</@security.authorize>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/suggestions-jquery@20.2.3/dist/css/suggestions.min.css" rel="stylesheet"/>
    <script src="https://cdn.jsdelivr.net/npm/suggestions-jquery@20.2.3/dist/js/jquery.suggestions.min.js"></script>


</head>
<body>
<#include "header.ftl"/>
<div class="container">

    <!-- Portfolio Item Heading -->
    <h1 class="my-4">Задание
        <small>${task.type}</small>
        <small>${addr}</small>
    </h1>
    <!-- Portfolio Item Row -->
    <div class="row">

        <div class="col-md-8">
            <img class="img-fluid" src="http://placehold.it/750x500" alt="">
        </div>

        <div class="col-md-4">
            <h3 class="my-3">Описание задания</h3>
            <p>${task.description}</p>
            <h3 class="my-3">Детали Задания</h3>
            <ul>
                <li>Заказачик
                    - ${task.consumer.name} ${task.consumer.surname} <#if task.consumer.id==user.id> (это ваше задание)</#if></li>
                <@security.authorize access=" isAuthenticated()">
                    <#if user.id==task.consumer.id ||( (task.volunteer)??    && user.id == task.volunteer.id)>
                        <li>${task.consumer.phone}</li> </#if>
                </@security.authorize>
                <li>Адрес
                    - ${task.consumer.address.city} ${task.consumer.address.district} ${task.consumer.address.street}</li>
                <li>Состояние - ${task.state}</li>
            </ul>

            <@security.authorize access="hasAuthority('CONSUMER')">

                <#if task.state == "CONFIRMED" && task.consumer.id==user.id>
                    <form action="/task/done" method="post">
                        <input type="hidden" name="taskId" value="${task.id}">
                        <div class="col-md-6 offset-md-4">
                            <button type="submit" class="btn btn-warning">
                                Подтвердить выполнение
                            </button>
                        </div>
                    </form>
                </#if>

                <br>
                <#if (task.state == "IN_PROGRESS" || task.state =="CONFIRMED")&&task.consumer.id==user.id>
                    <form action="/task/reject" method="post">
                        <input type="hidden" name="taskId" value="${task.id}">
                        <div class="col-md-6 offset-md-4">
                            <button type="submit" class="btn btn-warning">
                                Отказать в выполнении
                            </button>
                        </div>
                    </form>
                    <br>
                    <br>
                    <form action="/task/chat" method="get">
                        <input type="hidden" name="id" value="${task.id}">
                        <div class="col-md-6 offset-md-4">
                            <button type="submit" class="btn btn-warning">
                                К обсуждению
                            </button>
                        </div>
                    </form>
                </#if>
            </@security.authorize>
            <@security.authorize access="hasAuthority('VOLUNTEER')">
            <#if task.state == "ACTIVE">
                <form action="/task/accept" method="post">
                    <input type="hidden" name="taskId" value="${task.id}">
                    <div class="col-md-6 offset-md-4">
                        <button type="submit" class="btn btn-warning">
                            Взять задание
                        </button>
                    </div>
                </form>
            </#if>
            <#if task.state == "IN_PROGRESS" && task.volunteer.id==user.id>
                <form action="/task/chat" method="get">
                    <input type="hidden" name="id" value="${task.id}">
                    <div class="col-md-6 offset-md-4">
                        <button type="submit" class="btn btn-warning">
                            К обсуждению
                        </button>
                    </div>
                </form>
                <br>
                <br>
                <form action="/task/confirm" method="post">
                    <input type="hidden" name="taskId" value="${task.id}">
                    <div class="col-md-6 offset-md-4">
                        <button type="submit" class="btn btn-warning">
                            Завершить
                        </button>
                    </div>
                </form>
            </#if>
            </@security.authorize>>
        </div>

    </div>
    <!-- /.row -->

    <!-- Related Projects Row -->

    <!-- /.row -->

</div>
</body>
</html>