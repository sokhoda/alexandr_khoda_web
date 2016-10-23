<%--
  Created by IntelliJ IDEA.
  User: s_okhoda
  Date: 16.01.2016
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyFirst Table</title>
</head>

<body>
${name}
<%
    String s1 = (String) request.getAttribute("surname");
    out.println(s1);
%>
<table border="1">
    <thead>
    <tr>
        <th>First</th>
        <th colspan="2">Last</th>
        <%--<th>Age</th>--%>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>Alexandr</td>
        <td>Khodakovskyi</td>
        <td>34</td>
    </tr>
    </tbody>
    <tfoot>
        <td colspan="5">
            Copyright, All rights reserved, Kyiv, 2016
        </td>
    </tfoot>
    </table>
</body>
</html>
