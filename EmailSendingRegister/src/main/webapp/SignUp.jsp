<%--
  Created by IntelliJ IDEA.
  User: afafi
  Date: 03.03.2020
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${message ne null}">${message}</c:if>
<form method="post" action="signUp">
    Name:<input type="text" name="name" /><br/>
    Email:<input type="text" name="email" /><br/>
    Password:<input type="text" name="pass" /><br/>
    <input type="submit" value="register" />
</form>
</body>
</html>
