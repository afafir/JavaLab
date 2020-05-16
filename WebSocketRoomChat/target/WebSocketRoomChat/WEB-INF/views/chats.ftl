<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<#list chats as chat>
    <form action="/chat">
        <input type="hidden" name="id" value="${chat.id}"/>
        <button type="submit">${chat.name}</button>
        <br>
    </form>
</#list>

<form action="/chats" method="post">
    <div class="form-group row">
        <label for="email">Название комнаты</label>
        <div class=>
            <input type="text" id="name" class="form-control" name="name" required autofocus>
        </div>
    </div>
    <div class="col-md-6 offset-md-4">
        <button type="submit" class="btn btn-warning">
            Создать комнату
        </button>
    </div>
</form>

</body>
</html>