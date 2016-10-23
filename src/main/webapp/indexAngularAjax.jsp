<%--
  Created by IntelliJ IDEA.
  User: s_okhoda
  Date: 28.02.2016
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JavaScript</title>

    <script type="text/javascript" src="/script/script.js"></script>
</head>
<body onload="/*fun()*/">
<h2 id="elh" onclick="ajaxic('Sestra')">Header</h2>
<form action="/hello.html" onsubmit="return check()">
    <input id="text" name="login" type="text" placeholder="user name"/>
    <input type="submit" value="send"/>
</form>
<p>${name}</p>


<p><a href="/helloAngular">Angular Hello</a></p>
<p><a href="/angularController">Angular AJAX</a></p>
</body>
</html>
