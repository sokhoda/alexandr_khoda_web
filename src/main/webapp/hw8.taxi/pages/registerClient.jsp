<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.GregorianCalendar" %><%--
  Created by IntelliJ IDEA.
  User: s_okhoda
  Date: 20.01.2016
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Registration Form</title>
        <style>
            <%@include file='/hw8.taxi/css/registerClient.css' %>
        </style>
    </head>

    <body>
    <form action="/taxi" method="post">
        <img <%--height="100px" width="100px" --%>
                src="../../img/newUser.jpg" align="left"
                style="margin-right: 20px">
        <label for="name">Имя: </label>
        <input  type="text" placeholder="Надія" name="name" id="name"><br>

        <label for="surname">Фамилия: </label>
        <input  type="text" placeholder="Міцненька" name="surname" id="surname"><br>

        <label for="phone">Телефон: </label>
        <input  type="text" placeholder="+38 097 111 22 33" name="phone" id="phone"><br>

        <label for="address">Адрес: </label>
        <input  type="text" placeholder="25, I. Franka Str, app. 7, 03051 Kyiv"
                name="address" id="address">
        <br>
        <br>
        <input  type="submit" name="back" value="Назад в главное меню"
                style="margin-left: 5%;">
        <input  type="submit" name="register" value="Зарегистрировать"
                style="margin: 40px" >
        <br>
        <%
            if (request.getAttribute("FailedRegistration") != null){
        %>
                <label class="regMessage" style="color:red;">${FailedRegistration}</label>
        <%
            }
            if (request.getAttribute("SuccessfulRegistration") != null){
        %>
                <label class="regMessage" style="color:blue;">${SuccessfulRegistration}</label>
        <%
            }
        %>

    </form>
    </body>
</html>
