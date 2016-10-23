<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 21.11.15
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static hw7.notes.view.Servlet.*" %>
<%@ page import="hw7.notes.domain.CPU" %>
<%@ page import="java.util.List" %>
<%@ page import="hw7.notes.dao.CPUDao" %>
<%@ page import="hw7.notes.view.Menu" %>
<%@ page import="hw7.NotebookServiceImpl" %>
<%@ page import="hw7.notes.domain.Vendor" %>
<%@ page import="hw7.notes.dao.VendorDao" %>
<%--<%@ page errorPage="/hw7.notes/pages/generalErrorPage.jsp" %>--%>
<script src="../JS/select.js" type="text/javascript">    </script>
<script src="../JS/notebooks.js" type="text/javascript">    </script>

<html>
<head>
    <title>Update Memory Type</title>
    <style>
        <%@include file='../css/updateCPU.css' %>
    </style>
    <center><h1>Update Memory Type</h1></center><br>
</head>
<body>

    <%--String vendorInputText = getAttribValue(request, "nameA");--%>

<script type="text/javascript">
    function onUpdate(id){
       var hid = document.getElementById('idVal');
        hid.value = (id == null ? 0 : id);
        this.form.submit();
    }
</script>

<form action="/AddMem" method="get">
    <div style="display: inline-block">
        <input type="submit" name="back2Menu" value="&longleftarrow; to Menu">

        <input type="submit" name="back" id="back" style="margin-left: 8em"
               value="&longleftarrow;">
        <label class="cntMark">${cnt} of ${totPages}</label>
        <input type="submit" name="forward" id="forward" class="but"
               value="&longrightarrow;">
        <label id="message" class="cntMark"
               style="color:${messageColor == null ? 'brown' :messageColor};
                       text-align: center; width: auto" >${messageText}
        </label>

        <input type="hidden" name="cntMark" value="${cnt} of ${totPages}">
        <input type="hidden" name="sPortion" value="${sPortion}">
        <input type="hidden" name="idVal" id="idVal" value="">
    </div>


    <table>
        <thead>
        <tr>
            <%--<th class="shrink"><h3>ID</h3></th>--%>
            <th><h3>ID</h3></th>
            <th><h3>Vendor</h3></th>
            <th><h3>Size</h3></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="m" items="${memoryPortion}" varStatus="count">
            <tr>
                <td class="shrink">${m.id}</td>
                <td align="left">${m.vendor}</td>
                <td align="left">${m.sizze}</td>
                <td><button id="butUpdate" name="updMemory2"
                            onclick="onUpdate(${m.id})">Update</button>
                </td>
                <td><button id="butDelete" name="delMemory"
                            onclick="onUpdate(${m.id})">Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

<c:if test="${cnt == totPages}">
    <script type="text/javascript">
        document.getElementById("forward").disabled = true;
    </script>
</c:if>
<c:if test="${cnt == 1}">
    <script type="text/javascript">
        document.getElementById("back").disabled = true;
    </script>
</c:if>
</form>

</body>
</html>
