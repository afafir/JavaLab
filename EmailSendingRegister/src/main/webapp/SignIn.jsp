<%--
  Created by IntelliJ IDEA.
  User: afafi
  Date: 05.03.2020
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${message ne null}">${message}</c:if>
<form method="post" action="signIn">
    Name:<input type="text" name="name" /><br/>
    Password:<input type="text" name="pass" /><br/>
    <input type="submit" value="signIn" />
</form>
</body>
</html>
