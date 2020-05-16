<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="/signIn" method="post">
    <div class="form-group row">
        <label for="email">E-Mail</label>
        <div class=>
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

</body>
</html>