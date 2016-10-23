<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 28.11.15
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JavaScript</title>

    <script type="text/javascript" src="JS/script.js"></script>
</head>
<body onload="/*fun()*/">
  <h2 id="elh" onclick="ajaxic('Sestra')">Header</h2>
  <form action="/hello.html" onsubmit="return check()">
        <input id="text" name="login" type="text" value="user name"/>
      <input type="submit" value="send"/>
      <table border="2">
          <thead>
          <th>id</th>
          <th>firstName</th>
          <th>lastName</th>
          <th>email</th>
          <th>phoneNumber</th>
          <th>hireDate</th>
          <th>jobId</th>
          <th>salary</th>
          <th>comissionPct</th>
          <th>manager</th>
          </thead>
          <tbody>
          <%--<c:forEach var="e" items="${employee}">--%>
          <c:set var="e" value="${employee}"/>
            <tr>
                <td>${e.id}</td>
                <td>${e.firstName}</td>
                <td>${e.lastName}</td>
                <td>${e.email}</td>
                <td>${e.phoneNumber}</td>
                <td>${e.hireDate}</td>
                <td>${e.jobId}</td>
                <td>${e.salary}</td>
                <td>${e.comissionPct}</td>
                <td>${e.manager.id}</td>

            </tr>
          <%--</c:forEach>--%>

          </tbody>
      </table>

  </form>
${name}
</body>
</html>
