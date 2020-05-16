<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<form action="/signUp" method="post">
    <div class="form-group row">
        <label for="email">E-Mail</label>
        <div class>
            <input type="text" id="nickname" class="form-control" name="nickname" required autofocus>
        </div>
    </div>


    <div class="form-group row">
        <label for="password" class="col-md-4 col-form-label text-md-right">Пароль</label>
        <div class="col-md-6">
            <input type="password" id="password" class="form-control" name="password" required>
        </div>
    </div>


    <div class="col-md-6 offset-md-4">
        <button type="submit" class="btn btn-warning">
            Войти
        </button>
    </div>
</form>

</html>