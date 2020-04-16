<!DOCTYPE html>
<#assign  security=JspTaglibs["/WEB-INF/security.tld"] />
<@security.authorize access=" isAuthenticated()"/>
<@security.authentication var="user" property="principal.user" />
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chat</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-grid.css">
    <link rel="stylesheet" href=/resources/css/bootstrap-reboot.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-1.10.2.js"
            type="text/javascript"></script>
    <script src="/resources/js/chat.js"></script>
</head>
<#include "header.ftl">
<body onload="getAllMessages(${task.id})">

<div>
    <input id="message" placeholder="Ваше сообщение">
    <button onclick="sendMessage('${user.id}','${user.name} ${user.surname}',
            $('#message').val(),'${task.id}')">Отправить</button>
</div>
<div>
    <ul id="messages">

    </ul>
</div>

</body>
</html>