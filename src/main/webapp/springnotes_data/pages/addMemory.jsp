<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 21.11.15
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hw7.notes.domain.Vendor" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="hw7.notes.dao.VendorDao" %>
<%@ page import="hw7.NotebookServiceImpl" %>
<%@ page import="hw7.notes.view.Menu" %>
<%--<%@ page errorPage="/hw7.notes/pages/generalErrorPage.jsp" %>--%>
<script src="../JS/select.js" type="text/javascript">    </script>
<script src="../JS/notebooks.js" type="text/javascript">    </script>

<html>
<head>
    <title>Add Memory</title>
    <style>
        <%@include file='../css/addNotebook.css' %>
    </style>
    <center><h1>Add New Memory Type</h1></center>
    <br>

</head>
<body>

<%!
    VendorDao vendorDao;
    List<Vendor> vendor = null;
%>
<%
    vendorDao = ((NotebookServiceImpl) Menu.service).getVendorDao();
    vendor = (List<Vendor>)vendorDao.findAll();
    request.setAttribute("vendor", vendor);
    request.setAttribute("size", hw7.notes.view.Servlet.sizze);
%>

<form action="/AddMem" method="get">
    <img src="../img/memory.jpg" align="left" style="margin-right:20px;
     width: 200px; height: auto">

    <div id="divVenSel">
        <label for="venSel">VENDOR:</label>
        <select size="1" name="venSel" id="venSel">
            <option disabled>select item</option>
            <c:forEach var="v" items="${vendor}" varStatus="cnt">
                <c:choose>
                    <c:when test="${SelInx != null}">
                        <c:if test="${cnt.index == SelInx}">
                            <option value="${v.id}" selected>${v.toString()}</option>
                        </c:if>
                        <c:if test="${cnt.index != SelInx}">
                            <option value="${v.id}">${v.toString()}</option>
                        </c:if>
                    </c:when>
                    <c:when test="${SelVal != null}">
                        <c:if test="${v.id == SelVal}">
                            <option value="${v.id}" selected>${v.toString()}</option>
                        </c:if>
                        <c:if test="${v.id != SelVal}">
                            <option value="${v.id}">${v.toString()}</option>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <option value="${v.id}">${v.toString()}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
        <br>
    </div>

    <div id="divSizeSel">
    <label for="sizeSel">SIZE:</label>
        <select size="1" name="sizeSel" id="sizeSel">
            <option disabled>select item</option>
            <c:forEach var="s" items="${size}" varStatus="cnt">
                <c:choose>
                    <c:when test="${SelInxS != null}">
                        <c:if test="${cnt.index == SelInxS}">
                            <option value="${s}" selected>${s}</option>
                        </c:if>
                        <c:if test="${cnt.index != SelInxS}">
                            <option value="${s}">${s}</option>
                        </c:if>
                    </c:when>
                    <c:when test="${SelValS != null}">
                        <c:if test="${s == SelValS}">
                            <option value="${s}" selected>${s}</option>
                        </c:if>
                        <c:if test="${s != SelValS}">
                            <option value="${s}">${s}</option>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <option value="${s}">${s}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select><br>
    </div>

    <br><br>
    <br><br>
    <p style="text-align: center;">
        <input type="submit" name="back2Menu" value="&longleftarrow; to Menu">
        <input type="submit" name="add" value="Add">

        <input type="button" value="Clear All"
               onclick="setSelectIndex('venSel', 1);
               setSelectIndex('sizeSel', 1);
               clearElemContent('message');">
    </p>
    <br>
    <br>
    <label id="message" style="width: 100%; margin-top:10%;
            color:${messageColor == null ? 'brown' : messageColor};
            text-align: center; font-size:x-large">${messageText}
    </label>
</form>

</body>
</html>