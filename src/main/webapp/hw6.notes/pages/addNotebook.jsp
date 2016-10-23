<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 21.11.15
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.GregorianCalendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="static hw6.notes.view.Menu.*" %>
<%@ page errorPage="/hw6.notes/pages/errorPage.jsp" %>
<script src="/hw6.notes/JS/notebooks.js" type="text/javascript">    </script>

<html>
<head>
    <title>User management</title>
    <style>
        <%@include file='/hw6.notes/css/addNotebook.css' %>
    </style>
</head>
<body>
<%--<%!--%>
    <%--private String checkDate(GregorianCalendar gc) {--%>
        <%--SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");--%>
        <%--if (gc == null) {--%>
            <%--return "null";--%>
        <%--}--%>
        <%--else {--%>
            <%--return format1.format(gc.getTime());--%>
        <%--}--%>
    <%--}--%>
    <%--private String getAttribValue(HttpServletRequest req, String name){--%>
        <%--if (name == null){--%>
            <%--return "";--%>
        <%--}--%>
        <%--if (req.getAttribute(name) == null){--%>
            <%--return "";--%>
        <%--}--%>
        <%--else {--%>
            <%--return (String)req.getAttribute(name);--%>
        <%--}--%>
    <%--}--%>
<%--%>--%>
<%
    String serial = getAttribValue(request, "serialA");
    String vendor = getAttribValue(request, "vendorA");
    String model = getAttribValue(request, "modelA");
    String manDate = getAttribValue(request, "manDateA");
    String price = getAttribValue(request, "priceA");


    String[] message = getAttribArray(request);
%>



<form>
    <img src="/hw6.notes/img/addLaptop1.jpg" align="left" style="margin-right: 20px">
    <label for="1">SERIAL:</label>
    <input type="text" value="<%= serial %>" placeholder="SN-0000-0000-0000"
           name="serial" id="1"><br>

    <label for="2">VENDOR:</label>
    <input type="text" value="<%= vendor %>" placeholder="TOSHIBA" name="vendor" id="2"><br>

    <label for="3">MODEL:</label>
    <input  type="text" value="<%= model %>" placeholder="Satellite S-2535LX"
            name="model" id="3"><br>

    <label for="4">DATE OF MANUFACTURE:</label>
    <input  type="text" value="<%= manDate %>" placeholder="<%=checkDate(new
    GregorianCalendar())%>" name="manDate" id="4"><br>

    <label for="5">PRICE:</label>
    <input  type="text" value="<%= price %>" name="price" id="5">

    <br><br>
    <br><br>
    <p style="text-align: center;">
        <input type="submit" name="back" value="&longleftarrow; back"  action="/MenuNote" method="get">
        <input type="submit" name="addNoteB" value="Add Notebook"  action="/MenuNote" method="get">
        <button name="clearAddNoteB" onclick="clearAllAddNotebook();">Clear
            All</button>
    </p>
    <br>
    <br>
    <label id="message" style="width: 100%; margin-top:10%; color:<%=message[0]%>;
            text-align: center; font-size:x-large"><%=message[1]%>
    </label>
</form>

</body>
</html>