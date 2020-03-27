<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<#if message??>
    ${message}
</#if>
<form action="signIn" method="post">
    <input name="username" placeholder="username">
    <input type="password" name="password" placeholder="password">
    <input type="submit" value="SignIn">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
</form>
</body>
</html>