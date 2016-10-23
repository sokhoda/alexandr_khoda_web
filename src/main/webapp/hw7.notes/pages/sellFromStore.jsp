<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 21.11.15
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="NotebookServiceImpl" %>
<%@ page import="hw7.notes.view.Menu" %>
<%@ page import="hw7.notes.dao.*" %>
<%@ page import="Store" %>
<%@ page errorPage="../pages/generalErrorPage.jsp" %>
<script src="../JS/select.js" type="text/javascript">    </script>
<script src="../JS/notebooks.js" type="text/javascript">    </script>

<html>
<head>
    <title>Sell from Store</title>
    <style>
        <%@include file='../css/addNotebook.css' %>
    </style>
    <center><h1>Sell from Store</h1></center>
</head>
<body>
<%!
    StoreDao storeDao;
    List<Store> store;
%>
<%
    storeDao = ((NotebookServiceImpl) Menu.service).getStoreDao();
    store = (List<Store>)storeDao.findAll();
    request.setAttribute("store", store);
%>

<form action="/AddSellStore" method="get">
    <%--<img src="/hw7.notes/img/addLaptop1.jpg" align="left"--%>
         <%--style="margin-right: 20px">--%>

    <div id="divStoreSel">
        <label for="storeSel">STORE:</label>
        <select size="1" name="storeSel" id="storeSel">
            <option disabled>select item</option>
            <c:forEach var="s" items="${store}" varStatus="cnt">
                <c:choose>
                    <c:when test="${SelInx != null}">
                        <c:if test="${cnt.index == SelInx}">
                            <option value="${s.id}" selected>${s.toString()}</option>
                        </c:if>
                        <c:if test="${cnt.index != SelInx}">
                            <option value="${s.id}">${s.toString()}</option>
                        </c:if>
                    </c:when>
                    <c:when test="${SelVal != null}">
                        <c:if test="${s.id == SelVal}">
                            <option value="${s.id}" selected>${s.toString()}</option>
                        </c:if>
                        <c:if test="${s.id != SelVal}">
                            <option value="${s.id}">${s.toString()}</option>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <option value="${s.id}">${s.toString()}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
        <br>
    </div>

    <label for="quantity">QUANTITY:</label>
    <input  type="text" value="${quantityA}" placeholder="1"
            name="quantity" id="quantity"><br>
    <br><br>
    <br><br>
    <p style="text-align: center;">
        <input type="submit" name="back2Menu" value="&longleftarrow; to Menu">
        <input type="submit" name="sell" value="Sell">

        <input type="button" value="Clear All"
               onclick="setSelectIndex('storeSel', 1);
               clearElemContent('quantity');
               clearElemContent('message');">
    </p>
    <br>
    <br>
    <label id="message" style="width: 100%; margin-top:10%;
            color:${messageColor}; text-align: center; font-size:x-large">${messageText}
    </label>
</form>

</body>
</html>