<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 21.11.15
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="/session14/pages/generalErrorPage.jsp" %>
<%@ page import="session14.view.LoginServlet" %>
<html>
<head>
    <title>User management</title>
    <style>
        <%@include file='/css/authentification.css' %>
    </style>
</head>
<body>

<form action="/LoginPage" method="post">
    <img src="../../img/login1.jpg" align="left" style="margin-right: 20px">
    <label for="login1">LOGIN: </label>
    <input type="text" placeholder="login" name="login" id="login1"><br>

    <label for="pass2">PASSWORD: </label>
    <input type="password" value="" name="pass" id="pass2">

    <label for="compID">CompanyId: </label>
    <input type="text" value="" name="CompID" id="CompID">

    <br><br>
    <br><br>
    <p style="text-align: center;">
        <input type="submit" name="loginBut" value="Login">
        <input type="submit" name="getEmployees" value="getEmployees">

    </p>
    <%
        String messageColor = "red";
        String messageText = "";
        if (request.getAttribute("FailedAuth") != null) {
            messageColor = "red";
            messageText = (String) request.getAttribute("FailedAuth");
        }
    %>
    <br>
    <label style="width: 100%; margin-top:10%; color:<%=messageColor%>;
            text-align: center; font-size:x-large"><%=messageText%>
    </label>
</form>

</body>
</html>