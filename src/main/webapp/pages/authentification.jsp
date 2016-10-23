<%--
  Created by IntelliJ IDEA.
  User: s_okhoda
  Date: 19.01.2016
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome to TAXI order management service</title>
    <style>
        <%@include file='/css/authentification.css' %>
    </style>
</head>

<body>
<form action="/form" method="post">
    <img <%--height="100px" width="100px" --%>
         src="../img/login1.jpg" align="left"
         style="margin-right: 20px">
    <label for="login1" >LOGIN: </label>
    <input  type="text" placeholder="login" name="login" id="login1"><br>

    <label for="pass2">PASSWORD: </label>
    <input  type="password" value="" name="pass" id="pass2"><br><br>

    <label for="submt" >   </label>
    <input  type="submit" value="Sign In" id="submt">

</form>
</body>
</html>
