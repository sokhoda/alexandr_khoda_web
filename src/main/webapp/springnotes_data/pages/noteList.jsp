<%@ page import="java.util.List" %>
<%@ page import="hw7.Notebook" %>
<%@ page import="static hw6.notes.view.Menu.*" %>
<%@ page errorPage="../pages/generalErrorPage.jsp" %>
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
    int cnt;
    int totPages;
%>
<%
    cnt = (Integer)request.getAttribute("cnt");
    totPages = (Integer)request.getAttribute("totPages");
    nlist = (List<Notebook>) request.getAttribute("nlist");
    String[] message = getAttribArray(request);
    if (nlist == null || nlist.size() == 0 ) {
        message[0] = "brown";
        message[1] = "Notebook list is empty.";
    }
%>
<%--onclick="window.location.href='/hw5.users/UserAddRender.jsp'"--%>
<form action="/List" method="post">
<div>

    <input type="submit" name="back" id="back" class="but"
           value="&longleftarrow;">
    <label class="cntMark"><%=cnt%> of <%=totPages%></label>
    <input type="submit" name="forward" id="forward" class="but"
           value="&longrightarrow;">

    <input type="hidden" name="cntMark" value="<%=cnt%> of <%=totPages%>">

    <label class="regMessage"style="color: <%=message[0]%>"><%=message[1]%></label>
</div>

<%
    if (cnt == totPages){
%>
        <script type="text/javascript">
            document.getElementById("forward").disabled = true;
        </script>
<%
    }
    if (cnt == 1){
%>
        <script type="text/javascript">
            document.getElementById("back").disabled = true;
        </script>
<%
    }

    if (nlist != null && nlist.size() != 0 ) {
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
        <%--<td class="shrink"><%= nlist.get(i).getId()%></td>--%>
        <%--<td align="left"><%= nlist.get(i).getSerial()%></td>--%>
        <%--<td align="left"><%= nlist.get(i).getVendor()%></td>--%>
        <%--<td align="left"><%= nlist.get(i).getModel()%></td>--%>
        <%--<td><%=checkDate(nlist.get(i).getManDate())%></td>--%>
        <%--<td align="left"><%= nlist.get(i).getPrice()%></td>--%>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<%
    }
%>

</form>
</body>
</html>
