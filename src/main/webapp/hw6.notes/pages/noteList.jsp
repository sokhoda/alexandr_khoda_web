<%@ page import="java.util.List" %>
<%@ page import="hw6.Notebook" %>
<%@ page import="static hw6.notes.view.Menu.*" %>
<%@ page errorPage="/hw6.notes/pages/generalErrorPage.jsp" %>
<%--
  Created by IntelliJ IDEA.
  User: s_okhoda
  Date: 04.02.2016
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>UserList</title>
    <style>
        <%@include file='../css/noteList.css' %>
    </style>
</head>

<body>
<%!
    List<Notebook> nlist;
%>
<%
    nlist = (List<Notebook>) request.getAttribute("nlist");
    String[] message = getAttribArray(request);
    if (nlist.size() == 0 ) {
        message[0] = "brown";
        message[1] = "Notebook list is empty.";
    }
%>
<%--onclick="window.location.href='/hw5.users/UserAddRender.jsp'"--%>
<div>
    <a href="/hw6.notes/pages/menu.jsp">
        <button name="back" class="but">&longleftarrow;</button>
    </a><label class="regMessage"style="color: <%=message[0]%>"><%=message[1]%></label>
</div>

<%
    if (nlist.size() != 0 ) {
%>
<table>
    <thead>
    <tr>
        <th colspan="100%"><h1>Notebook list</h1></th>
    </tr>
    <tr>
        <%--<th class="shrink"><h3>ID</h3></th>--%>
        <th><h3>ID</h3></th>
        <th><h3>Serial</h3></th>
        <th><h3>Vendor</h3></th>
        <th><h3>Model</h3></th>
        <th><h3>Date of Manufacture</h3></th>
        <th><h3>Price</h3></th>
    </tr>
    </thead>
    <tbody>

    <%
        for (int i = 0; i < nlist.size(); i++) {
    %>
    <tr>
        <td class="shrink"><%= nlist.get(i).getId()%></td>
        <td align="left"><%= nlist.get(i).getSerial()%></td>
        <td align="left"><%= nlist.get(i).getVendor()%></td>
        <td align="left"><%= nlist.get(i).getModel()%></td>
        <td><%=checkDate(nlist.get(i).getManDate())%></td>
        <td align="left"><%= nlist.get(i).getPrice()%></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<%
    }
%>
</body>
</html>
