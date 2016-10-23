<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 21.11.15
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page errorPage="/hw5.users/errorPage.jsp" %>

<html>
<head>
    <title>User management</title>
    <style>
        <%@include file='/css/authentification.css' %>
    </style>
</head>
<body>

<form action="/UserAddRender" method="post">
    <img src="../img/login1.jpg" align="left" style="margin-right: 20px">
    <label for="login1">LOGIN: </label>
    <input type="text" placeholder="login" name="login" id="login1"><br>

    <label for="pass2">PASSWORD: </label>
    <input type="password" value="" name="pass" id="pass2">
    <br><br>
    <br><br>
    <p style="text-align: center;">
        <input type="submit" name="addUser" value="Add User">
        <input type="submit" name="listAll" value="List All">
    </p>
    <%
        String messageColor = "red";
        String messageText = "";
        if (request.getAttribute("FailedCreation") != null) {
            messageColor = "red";
            messageText = (String) request.getAttribute("FailedCreation");
        }
        else if (request.getAttribute("UserAddedSuccess") != null) {
            messageColor = "green";
            messageText = (String) request.getAttribute("UserAddedSuccess");
        }
    %>
    <br>
    <label style="width: 100%; margin-top:10%; color:<%=messageColor%>;
            text-align: center; font-size:x-large"><%=messageText%>
    </label>
</form>

</body>
</html>