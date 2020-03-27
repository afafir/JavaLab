<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div>
    <form action="signUp" method="post">
        <input name="username" placeholder="username">
        <input name="email" placeholder="email">
        <input type="password" name="password" placeholder="password">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <input type="submit" value="SignUp">
    </form>
</div>
</body>
</html>