<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 21.11.15
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>

<html>
    <head>
        <title>Welcome to TAXI order management service</title>
        <style>
            <%@include file='/hw8.taxi/css/loginTaxi.css' %>
        </style>
        <script>
            console.log('Hello JS');
        </script>
    </head>
    <body>

    <form action="/taxi" method="post">
        <img src="../../img/login1.jpg" align="left" style="margin-right: 20px">
        <label for="login1" >LOGIN: </label>
        <input  type="text" placeholder="login" name="login" id="login1"><br>

        <label for="pass2">PASSWORD: </label>
        <input  type="password" value="" name="pass" id="pass2">
        <br><br>
        <br><br>
        <p style="text-align: center;">
            <input  type="submit" name="authenticate" value="Sign In">
        </p>
        <%
//                request.getRequestDispatcher("pages/menu.jsp").forward(request,response);
            if (request.getAttribute("FailedAuth") != null){
        %>
            <br>
            <label style="width: 100%; margin-top:10%; color:red;
             text-align: center; font-size:x-large">${FailedAuth}</label>
        <%
            }
        %>
    </form>

    </body>
</html>