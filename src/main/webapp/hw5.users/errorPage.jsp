<%--
  Created by IntelliJ IDEA.
  User: s_okhoda
  Date: 04.02.2016
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8"
         language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p style="font-size: large; color: red;">
        Unfortunately user with such login can not be added since it exists already.
        Please register under other login.
    </p>
    <%=
    exception.toString()
    %>
</body>
</html>
