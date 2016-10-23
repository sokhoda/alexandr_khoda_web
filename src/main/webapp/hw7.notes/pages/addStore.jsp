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
<%@ page import="NotebookDao" %>
<%@ page import="Notebook" %>
<%@ page errorPage="../pages/generalErrorPage.jsp" %>
<script src="../JS/select.js" type="text/javascript">    </script>
<script src="../JS/notebooks.js" type="text/javascript">    </script>

<html>
<head>
    <title>Add Store</title>
    <style>
        <%@include file='../css/addNotebook.css' %>
    </style>
    <center><h1>Add Store</h1></center>
</head>
<body>
<%!
    NotebookDao notebookDao;
    List<Notebook> notebook;
%>
<%
    notebookDao = ((NotebookServiceImpl) Menu.service).getNoteDao();
    notebook = (List<Notebook>)notebookDao.findAll();
    request.setAttribute("notebook", notebook);
%>

<form action="/AddSellStore" method="get">
    <%--<img src="/hw7.notes/img/addLaptop1.jpg" align="left"--%>
         <%--style="margin-right: 20px">--%>

    <div id="divNtbSel">
        <label for="notebookSel">NOTEBOOK TYPE:</label>
        <select size="1" name="notebookSel" id="notebookSel">
            <option disabled>select item</option>
            <c:forEach var="n" items="${notebook}" varStatus="cnt">
                <c:choose>
                    <c:when test="${SelInx != null}">
                        <c:if test="${cnt.index == SelInx}">
                            <option value="${n.id}" selected>${n.toString()}</option>
                        </c:if>
                        <c:if test="${cnt.index != SelInx}">
                            <option value="${n.id}">${n.toString()}</option>
                        </c:if>
                    </c:when>
                    <c:when test="${SelVal != null}">
                        <c:if test="${n.id == SelVal}">
                            <option value="${n.id}" selected>${n.toString()}</option>
                        </c:if>
                        <c:if test="${n.id != SelVal}">
                            <option value="${n.id}">${n.toString()}</option>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <option value="${n.id}">${n.toString()}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
        <br>
    </div>

    <label for="quantity">QUANTITY:</label>
    <input  type="text" value="${quantityA}" placeholder="1"
            name="quantity" id="quantity"><br>

    <label for="price">PRICE:</label>
    <input  type="text" value="${priceA}" placeholder="1000"
            name="price" id="price"><br>

    <br><br>
    <br><br>
    <p style="text-align: center;">
        <input type="submit" name="back2Menu" value="&longleftarrow; to Menu">
        <input type="submit" name="add" value="Add">

        <input type="button" value="Clear All"
               onclick="setSelectIndex('notebookSel', 1);
               clearElemContent('quantity');
               clearElemContent('price');
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